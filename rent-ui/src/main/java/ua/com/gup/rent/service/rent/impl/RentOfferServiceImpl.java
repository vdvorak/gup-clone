package ua.com.gup.rent.service.rent.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.filter.OfferModeratorFilter;
import ua.com.gup.common.model.mongo.offer.OfferContactInfo;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.impl.CommonOfferServiceImpl;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.mapper.RentOfferMapper;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.user.Profile;
import ua.com.gup.rent.model.rent.statistic.RentOfferStatistic;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.repository.rent.RentOfferRepository;
import ua.com.gup.rent.repository.rent.impl.RentOfferMongoRepository;
import ua.com.gup.rent.service.calendar.RentOfferCalendarService;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
import ua.com.gup.rent.service.dto.rent.RentOfferModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticByDateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewCoordinatesDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortWithModerationReportDTO;
import ua.com.gup.rent.service.rent.RentOfferElasticService;
import ua.com.gup.rent.service.rent.RentOfferService;
import ua.com.gup.rent.service.sequence.RentSequenceService;
import ua.com.gup.rent.util.RentOfferSEOFriendlyUrlUtil;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RentOfferServiceImpl extends CommonOfferServiceImpl implements RentOfferService {

    private static final String RENT_OFFER_SEQUENCE_ID = "rent_offer_sequence";
    private final Logger log = LoggerFactory.getLogger(RentOfferServiceImpl.class);

    @Autowired
    private RentOfferMapper offerMapper;

    @Autowired
    private RentOfferProfileRepository profileRepository;

    @Autowired
    private RentOfferMongoRepository offerMongoRepository;

    @Autowired
    private RentOfferRepository offerRepository;

    @Autowired
    private RentSequenceService sequenceService;

    @Autowired
    private RentOfferCategoryService categoryService;

    @Autowired
    private RentOfferCalendarService rentOfferCalendarService;

    @Autowired
    private RentOfferElasticService rentOfferElasticService;

    private RentOffer createWrapper(RentOffer rentOffer) {
        rentOffer = offerMongoRepository.save(rentOffer);
        rentOfferCalendarService.indexRentOffer(rentOffer);
        rentOfferCalendarService.createRentOfferCalendars(rentOffer);
        rentOfferCalendarService.indexRentOfferCalendars(rentOffer);
        rentOfferElasticService.createElasticRentOffer(rentOffer);
        return rentOffer;
    }

    private RentOffer updateWrapper(RentOffer rentOffer) {
        rentOffer = offerMongoRepository.save(rentOffer);
        rentOfferCalendarService.indexRentOffer(rentOffer);
//        rentOfferCalendarService.refreshRentOfferCalendars(rentOffer);
//        rentOfferCalendarService.indexRentOfferCalendars(rentOffer);
//
        rentOfferElasticService.updateElasticRentOffer(rentOffer);
        return rentOffer;
    }

    @Override
    public void refreshAndIndexOfferCalendars(String rentOfferId) {
        RentOffer rentOffer = offerMongoRepository.findOne(rentOfferId);
        rentOfferCalendarService.refreshRentOfferCalendars(rentOffer);
        rentOfferCalendarService.indexRentOfferCalendars(rentOffer);
    }

    @Override
    public void refreshAndIndexAllOffersCalendars() {
        for (RentOffer rentOffer : offerMongoRepository.findAll()) {
            rentOfferCalendarService.refreshRentOfferCalendars(rentOffer);
            rentOfferCalendarService.indexRentOfferCalendars(rentOffer);
        }
    }

    @Override
    public RentOfferViewDetailsDTO save(RentOfferCreateDTO rentOfferCreateDTO) {
        log.debug("Request to save Offer : {}", rentOfferCreateDTO);
        RentOffer offer = saveAndReturn(rentOfferCreateDTO);
        RentOfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    @Override
    public RentOffer saveAndReturn(RentOfferCreateDTO rentOfferCreateDTO) {
        String seoURL = generateUniqueSeoUrl(rentOfferCreateDTO.getTitle());
        RentOffer offer = offerMapper.offerCreateDTOToOffer(rentOfferCreateDTO);
        offer.setStatus(CommonStatus.ON_MODERATION);
        offer.setSeoUrl(seoURL);

        GupLoggedUser loggedUser = RentSecurityUtils.getLoggedUser();
        offer.setLastModifiedUser(loggedUser);
        offer.setAuthorId(loggedUser.getId());

        offer = createWrapper(offer);

        return offer;
    }


    @Override
    public RentOfferViewDetailsDTO update(String rentOfferId, RentOfferUpdateDTO offerUpdateDTO) {
        log.debug("Request to updateRentOfferCalendars  Rent Offer : {}", offerUpdateDTO);
        RentOffer offer = updateAndReturn(rentOfferId, offerUpdateDTO);
        RentOfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    @Override
    public RentOffer updateAndReturn(String offerId, RentOfferUpdateDTO offerUpdateDTO) {
        RentOffer offer = offerMongoRepository.findOne(offerId);
        offer.setLastModifiedUser(RentSecurityUtils.getLoggedUser());
        offerMapper.offerUpdateDTOToOffer(offerUpdateDTO, offer);
        // on moderation if fields was changed and moderation is needed or last moderation is refused - moderation any way
        if (isNeededModeration(offerUpdateDTO) || offer.getLastOfferModerationReport().isRefused()) {
            offer.setStatus(CommonStatus.ON_MODERATION);
        } else {
            offer.setStatus(CommonStatus.ACTIVE);
        }
        return updateWrapper(offer);
    }

    @Override
    public RentOfferViewDetailsDTO save(RentOfferModerationReportDTO offerModerationReportDTO) {
        log.debug("Request to save Rent Offer modified by moderator: {}", offerModerationReportDTO);
        RentOffer offer = offerMongoRepository.findOne(offerModerationReportDTO.getId());
        offerMapper.offerModeratorDTOToOffer(offerModerationReportDTO, offer);
        if (offer.getLastOfferModerationReport().isRefused()) {
            offer.setStatus(CommonStatus.REJECTED);
        } else {
            offer.setStatus(CommonStatus.ACTIVE);
        }
        offer = updateWrapper(offer);
        RentOfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOne(String id) {
        log.debug("Request to get Offer : {}", id);
        RentOffer offer = offerMongoRepository.findOne(id);
        if (offer == null) {
            return Optional.empty();
        }
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    @Override
    public Page<RentOfferViewShortDTO> findAll(RentOfferFilter offerFilter, Pageable pageable) {

        log.debug("Request to get all Rent Offers by filter  {} ", offerFilter);
        long count = getRepository().countByFilter(offerFilter, CommonStatus.ACTIVE);
        List<RentOffer> offers = Collections.EMPTY_LIST;
        if (count > 0) {
            offers = getRepository().findByFilter(offerFilter, CommonStatus.ACTIVE, pageable);
        }
        Page<RentOffer> result = new PageImpl<>(offers, pageable, count);
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }


    @Override
    public List<RentOfferViewShortDTO> findByIds(List<String> ids, Sort sort) {
        List<RentOffer> offers = Collections.EMPTY_LIST;
        if (ids.size() > 0) {
            offers = getRepository().findByIds(ids, sort);
        }
        return offers.stream().map(offer -> offerMapper.offerToOfferShortDTO(offer)).collect(Collectors.toList());
    }

    @Override
    public List<RentOfferViewShortDTO> findByIds(List<String> ids) {
        return findByIds(ids, null);

    }

    @Override
    public List<RentOfferViewCoordinatesDTO> findCoordinatesByFilter(RentOfferFilter offerFilter, Pageable pageable) {
        log.debug("Request to get offers coordinates by filter");
        List<RentOffer> offers = getRepository().findByFilter(offerFilter, CommonStatus.ACTIVE, pageable);
        List<RentOfferViewCoordinatesDTO> coordinatesList = new ArrayList<>(offers.size());
        offers.forEach(offer -> coordinatesList.add(offerMapper.offerToOfferCoordinatesDTO(offer)));
        return coordinatesList;
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatusAndUserId(CommonStatus status, String authorId, Pageable pageable) {
        log.debug("Request to get all Rent Offers by status = {} and authorId = {}", status, authorId);
        Profile profile = profileRepository.findById(authorId);
        if (profile == null) {
            return new PageImpl<RentOfferViewShortWithModerationReportDTO>(Collections.EMPTY_LIST);
        }
        Page<RentOffer> result = offerMongoRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatusAndUserPublicId(CommonStatus status, String userPublicId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and userPublicId = {}", status, userPublicId);
        Profile profile = profileRepository.findByPublicId(userPublicId);
        if (profile == null) {
            return new PageImpl<RentOfferViewShortWithModerationReportDTO>(Collections.EMPTY_LIST);
        }
        Page<RentOffer> result = offerMongoRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatus(CommonStatus status, Pageable pageable) {
        log.debug("Request to get all Offers by status = {}", status);
        Page<RentOffer> result = offerMongoRepository.findAllByStatus(status, pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOneBySeoUrlWithIncrementViewsCount(String seoUrl) {
        log.debug("Request to get Rent Offer : {}", seoUrl);
        Optional<RentOffer> offer = offerMongoRepository.findOneBySeoUrl(seoUrl);
        if (offer.isPresent()) {
            final RentOffer o = offer.get();
            switch (o.getStatus()) {
                case ACTIVE:
                    o.incrementView(true, false);
                    offerMongoRepository.save(o);
                    break;
                case ON_MODERATION:
                case DEACTIVATED:
                    GupLoggedUser loggedUser = RentSecurityUtils.getLoggedUser();
                    if (loggedUser != null && loggedUser.getId().equals(o.getAuthorId())) {
                        break;
                    }
                default:
                    offer = Optional.empty();
            }
        }
        return offer.map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOneBySeoUrlWithoutIncrementViewsCount(String seoUrl) {
        log.debug("Request to get Rent Offer : {}", seoUrl);
        Optional<RentOffer> offer = offerMongoRepository.findOneBySeoUrl(seoUrl);
        if (offer.isPresent()) {
            final RentOffer o = offer.get();
            if (CommonStatus.ON_MODERATION.equals(o.getStatus())) {
                offer = Optional.empty();
            }
        }
        return offer.map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    @Override
    public String getRentOfferIdBySeoUrl(String seoUrl) {
        return offerRepository.getOfferIdBySeoUrl(seoUrl);
    }

    @Override
    public RentOffer findById(String id) {
        return offerRepository.findOne(id);
    }

    @Override
    public Page<RentOfferViewShortDTO> findRelevantBySeoUrl(String seoUrl, Pageable pageable) {
        Optional<RentOffer> offerOptional = offerMongoRepository.findOneBySeoUrl(seoUrl);
        if (offerOptional.isPresent()) {
            final RentOffer offer = offerOptional.get();
            StringBuilder search = new StringBuilder();

            List<RentOfferCategory> categories = categoryService.findByCodeInOrderByCodeAsc(offer.getCategories());
            for (RentOfferCategory category : categories) {
                final Map<String, String> titles = category.getTitle();
                for (String lang : titles.keySet()) {
                    search.append(titles.get(lang) + " ");
                    break;
                }
            }

            search.append(offer.getTitle());
            RentOfferFilter filter = new RentOfferFilter();
            filter.setQuery(search.toString());
            Page<RentOffer> result = new PageImpl<>(getRepository().findByFilter(filter, CommonStatus.ACTIVE, offer.getId(), pageable));
            return result.map(o -> offerMapper.offerToOfferShortDTO(o));

        }
        return new PageImpl<>(new ArrayList<>());
    }

    @Override
    public void incrementPhoneViews(String id) {
        RentOffer offer = offerMongoRepository.findOne(id);
        offer.incrementView(false, true);
        offerMongoRepository.save(offer);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Offer : {}", id);
        RentOffer offer = offerMongoRepository.findOne(id);
        offer.setStatus(CommonStatus.ARCHIVED);
        offer = offerMongoRepository.save(offer);
        rentOfferElasticService.updateElasticRentOffer(offer);
    }


    @Override
    public boolean exists(String id) {
        return offerMongoRepository.exists(id);
    }


    @Override
    public boolean existsBySeoUrl(String seoUrl) {
        return offerMongoRepository.existsBySeoUrl(seoUrl);
    }

    /**
     * Returns whether an entity can be updated by current user.
     *
     * @param offerId must not be {@literal null}.
     * @return true if an user has permission for updateRentOfferCalendars, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    @Deprecated
    public boolean hasPermissionForUpdate(String offerId, String authorId) {
        Optional<RentOffer> offer = null;
        if (offerId != null && authorId != null) {
            offer = offerMongoRepository.findOfferByIdAndAuthorId(offerId, authorId);
        } else if (offer != null) {
            offer = Optional.ofNullable(offerMongoRepository.findOne(offerId));
        }
        if (log.isDebugEnabled()) {
            log.debug("Request has permission for updateRentOfferCalendars offer : {}", offer);
        }

        if (offer != null && offer.get() != null && RentSecurityUtils.isAuthenticated()) {
            String currentUserID = RentSecurityUtils.getCurrentUserId();
            Set<CommonStatus> statuses = new HashSet<>();
            statuses.addAll(Arrays.asList(CommonStatus.ACTIVE, CommonStatus.DEACTIVATED, CommonStatus.REJECTED, CommonStatus.ARCHIVED));
            //if current user owner offer
            if ((offer.get().getAuthorId() == currentUserID && statuses.contains(offer.get().getStatus()))) {
                return true;
            }
            //if current user it's moderator(admin role)
            if (RentSecurityUtils.isCurrentUserInRole(Role.ROLE_MODERATOR) && offer.get().getStatus() == CommonStatus.ON_MODERATION) {
                return true;
            }
        }   //access denied
        return false;
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> updateStatus(String id, CommonStatus status) {
        log.debug("Request to updateRentOfferCalendars updateRentOfferCalendars rent offer's status : {}", id);
        RentOffer offer = offerMongoRepository.findOne(id);
        offer.setStatus(status);
        offer = offerMongoRepository.save(offer);
        rentOfferElasticService.updateElasticRentOffer(offer);
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    @Override
    public Boolean isCanUpdateStatus(String id, CommonStatus status) {
        RentOffer offer = offerRepository.findOne(id);
        Boolean fromStatus = (offer.getStatus() == CommonStatus.ACTIVE || offer.getStatus() == CommonStatus.DEACTIVATED);
        Boolean toStatus = (status == CommonStatus.ACTIVE || status == CommonStatus.DEACTIVATED || status == CommonStatus.ARCHIVED);
        return fromStatus && toStatus;
    }

    @Override
    public Optional<List<RentOfferStatisticByDateDTO>> findOfferStatisticBySeoUrlAndDateRange(String seoUrl, LocalDate dateStart, LocalDate dateEnd) {
        Optional<RentOffer> offerOptional = offerMongoRepository.findOneBySeoUrl(seoUrl);
        if (offerOptional.isPresent()) {
            RentOffer offer = offerOptional.get();
            return Optional.of(offer.getStatistic())
                    .map(o -> offerMapper.offerStatisticToOfferStatisticDTO(o, LocalDate.from(offer.getCreatedDate()), dateStart, dateEnd));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOfferByIdAndAuthorId(String offerId, String authorId) {
        return null;
    }

    @Override
    public Collection<String> getOfferContactInfoPhoneNumbersById(String offerId) {
        RentOffer offer = offerMongoRepository.findOne(offerId);
        return offer.getContactInfo().getPhoneNumbers();
    }

    @Override
    public Page<RentOfferViewShortDTO> findByManagerAndPublicIdAndStatus(CommonStatus status, String userPublicId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and userPublicId = {}", status, userPublicId);
        Profile profile = profileRepository.findByPublicId(userPublicId);
        if (profile == null) {
            return new PageImpl<RentOfferViewShortDTO>(Collections.EMPTY_LIST);
        }
        Page<RentOffer> result = status != null ?
                offerMongoRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable) :
                offerMongoRepository.findAllByAuthorId(profile.getId(), pageable);

        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }


    private RentOfferRepository getRepository() {
        return offerRepository;
    }


    private String generateUniqueSeoUrl(String title) {
        // index number in 36 radix
        return RentOfferSEOFriendlyUrlUtil.generateSEOFriendlyUrl(title + "-" + Long.toString(sequenceService.getNextSequenceValue(RENT_OFFER_SEQUENCE_ID), 36));
    }

    private boolean isNeededModeration(RentOfferUpdateDTO offerUpdateDTO) {
        boolean result = false;

        result |= offerUpdateDTO.getCategory() != null;
        result |= offerUpdateDTO.getTitle() != null;
        result |= offerUpdateDTO.getDescription() != null;

        result |= offerUpdateDTO.getAddress() != null;

        // price can be change without moderation

        result |= offerUpdateDTO.getContactInfo() != null;
        result |= offerUpdateDTO.getAttrs() != null;
        result |= offerUpdateDTO.getMultiAttrs() != null;
        result |= offerUpdateDTO.getNumAttrs() != null;
        result |= offerUpdateDTO.getBoolAttrs() != null;

        return result;
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findByModeratorFilter(OfferModeratorFilter filter, Pageable pageable) {
        long count = getRepository().countByFilter(filter);
        List<RentOffer> offers = Collections.EMPTY_LIST;
        if (count > 0) {
            offers = getRepository().searchByFilter(filter, pageable);
        }
        Page<RentOffer> result = new PageImpl<>(offers, pageable, count);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }

    @Override
    public void cloneOffers(String fromUserPublicId, String toUserPublicId, boolean copyFromUser) {
        Profile from = profileRepository.findByPublicId(fromUserPublicId);
        Profile to = profileRepository.findByPublicId(toUserPublicId);

        List<RentOffer> fromOffers = offerMongoRepository.findAllByAuthorId(from.getId());
        for (RentOffer src : fromOffers) {
            RentOffer trgt = new RentOffer();

            String seoURL = generateUniqueSeoUrl(src.getTitle());
            trgt.setSeoUrl(seoURL);
            trgt.setStatus(src.getStatus());
            trgt.setTitle(src.getTitle());
            trgt.setDescription(src.getDescription());
            trgt.setCategories(src.getCategories());
            trgt.setPrice(src.getPrice());
            trgt.setSettings(src.getSettings());
            trgt.setDeposit(src.getDeposit());
            trgt.setLands(src.getLands());
            trgt.setYoutubeVideoId(src.getYoutubeVideoId());

            trgt.setAttrs(src.getAttrs());
            trgt.setBoolAttrs(src.getBoolAttrs());
            trgt.setNumAttrs(src.getNumAttrs());
            trgt.setMultiAttrs(src.getMultiAttrs());

            trgt.setLastOfferModerationReport(src.getLastOfferModerationReport());
            trgt.setLastModifiedUser(RentSecurityUtils.getLoggedUser());
            trgt.setAuthorId(to.getId());

            trgt.setContactInfo(new OfferContactInfo());
            trgt.getContactInfo().setPhoneNumbers(src.getContactInfo().getPhoneNumbers());
            trgt.getContactInfo().setContactName(src.getContactInfo().getContactName());
            trgt.setAddress(src.getAddress());
            trgt.setImages(src.getImages());

            if (copyFromUser) {
                trgt.getContactInfo().setContactName(to.getFirstname());
                //get all phones from user contacts that not hidden
                Set<String> phoneNumbers = to.getContact().getContactPhones().stream()
                        // .filter(phone -> !phone.getHidden())
                        // .map(p -> p.getPhoneNumber())
                        .collect(Collectors.toSet());
                trgt.getContactInfo().setPhoneNumbers(phoneNumbers);


                trgt.setAddress(to.getAddress());
            }
            trgt.setStatistic(new RentOfferStatistic());
            trgt.setRentOfferCalendar(src.getRentOfferCalendar());
            trgt.setRentObjectsCount(src.getRentObjectsCount());
            trgt = createWrapper(trgt);

        }
    }
}