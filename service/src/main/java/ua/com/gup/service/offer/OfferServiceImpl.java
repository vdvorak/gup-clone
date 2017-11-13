package ua.com.gup.service.offer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.dto.offer.*;
import ua.com.gup.dto.offer.enumeration.OfferImageSizeType;
import ua.com.gup.dto.offer.statistic.OfferStatisticByDateDTO;
import ua.com.gup.dto.offer.view.OfferViewCoordinatesDTO;
import ua.com.gup.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.dto.offer.view.OfferViewShortWithModerationReportDTO;
import ua.com.gup.mapper.OfferCategoryMapper;
import ua.com.gup.mapper.OfferMapper;
import ua.com.gup.mongo.composition.domain.category.Category;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.enumeration.Currency;
import ua.com.gup.mongo.model.enumeration.OfferStatus;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.mongo.model.file.FileUploadWrapper;
import ua.com.gup.mongo.model.file.FileWrapper;
import ua.com.gup.mongo.model.filter.MoneyFilter;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.offer.*;
import ua.com.gup.mongo.model.other.EntityPage;
import ua.com.gup.repository.offer.OfferRepository;
import ua.com.gup.repository.offer.OfferRepositoryCustom;
import ua.com.gup.repository.offer.OfferRepositoryOLD;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.service.category.CategoryService;
import ua.com.gup.service.currency.CurrencyConverterService;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.service.image.ImageService;
import ua.com.gup.service.sequence.SequenceService;
import ua.com.gup.util.SEOFriendlyUrlUtil;
import ua.com.gup.util.SecurityOperations;
import ua.com.gup.util.Translit;
import ua.com.gup.util.security.SecurityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Offer.
 */
@Service
public class OfferServiceImpl implements OfferService {
    private static final String OFFER_SEQUENCE_ID = "offer_sequence";
    private final Logger log = LoggerFactory.getLogger(OfferServiceImpl.class);

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private CurrencyConverterService currencyConverterService;
    @Autowired
    private OfferMapper offerMapper;
    @Autowired
    private OfferCategoryMapper offerCategoryMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProfileRepository profileRepository;


    //-------------------- OLD -----------------------------//
    @Autowired
    private OfferRepositoryOLD offerRepositoryOLD;

    @Autowired
    private OfferRepositoryCustom offerRepositoryCustom;

    @Autowired
    private StorageService storageService;
    //--------------------------------------------------------------//

    /**
     * Save a offer.
     *
     * @param offerCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfferViewDetailsDTO save(OfferCreateDTO offerCreateDTO) {
        log.debug("Request to save Offer : {}", offerCreateDTO);
        String seoURL = generateUniqueSeoUrl(offerCreateDTO.getTitle());
        saveOfferImages(null, offerCreateDTO.getImages(), seoURL);
        Offer offer = offerMapper.offerCreateDTOToOffer(offerCreateDTO);
        offer.setStatus(OfferStatus.ON_MODERATION);
        offer.setSeoUrl(seoURL);
        String userID = SecurityUtils.getCurrentUserId();
        offer.setLastModifiedBy(userID);
        offer.setAuthorId(userID);
        offer = offerRepository.save(offer);
        OfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    /**
     * Save a offer.
     *
     * @param offerUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfferViewDetailsDTO save(OfferUpdateDTO offerUpdateDTO) {
        log.debug("Request to save Offer : {}", offerUpdateDTO);
        Offer offer = offerRepository.findOne(offerUpdateDTO.getId());
        saveOfferImages(offer.getImageIds(), offerUpdateDTO.getImages(), offer.getSeoUrl());
        offerMapper.offerUpdateDTOToOffer(offerUpdateDTO, offer);
        offer.setLastModifiedBy(SecurityUtils.getCurrentUserId());
        offer.setLastModifiedDate(ZonedDateTime.now());
        // on moderation if fields was changed and moderation is needed or last moderation is refused - moderation any way
        if (isNeededModeration(offerUpdateDTO) || offer.getLastOfferModerationReport().isRefused()) {
            offer.setStatus(OfferStatus.ON_MODERATION);
        } else {
            offer.setStatus(OfferStatus.ACTIVE);
        }
        offer = offerRepository.save(offer);
        OfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    /**
     * Save a offer modified by moderator.
     *
     * @param offerModerationReportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfferViewDetailsDTO save(OfferModerationReportDTO offerModerationReportDTO) {
        log.debug("Request to save Offer modified by moderator: {}", offerModerationReportDTO);
        Offer offer = offerRepository.findOne(offerModerationReportDTO.getId());
        offerMapper.offerModeratorDTOToOffer(offerModerationReportDTO, offer);
        if (offer.getLastOfferModerationReport().isRefused()) {
            offer.setStatus(OfferStatus.REJECTED);
        } else {
            offer.setStatus(OfferStatus.ACTIVE);
        }
        offer = offerRepository.save(offer);
        OfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    /**
     * Get all the offers.
     *
     * @param offerFilter the offer filter
     * @param pageable    the pageable
     * @return the list of entities
     */
    @Override
    public Page<OfferViewShortDTO> findAll(OfferFilter offerFilter, Pageable pageable) {
        log.debug("Request to get all Offers by filter");
        calculatePriceInBaseCurrency(offerFilter.getPrice());
        long count = offerRepositoryCustom.countByFilter(offerFilter, OfferStatus.ACTIVE);
        List<Offer> offers = Collections.EMPTY_LIST;
        if (count > 0) {
            offers = offerRepositoryCustom.findByFilter(offerFilter, OfferStatus.ACTIVE, pageable);
        }
        Page<Offer> result = new PageImpl<>(offers, pageable, count);
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }

    @Override
    public List<OfferViewCoordinatesDTO> findCoordinatesByFilter(OfferFilter offerFilter, Pageable pageable) {
        log.debug("Request to get offers coordinates by filter");

        List<Offer> offers = offerRepositoryCustom.findByFilter(offerFilter, OfferStatus.ACTIVE, pageable);

        List<OfferViewCoordinatesDTO> coordinatesList = new ArrayList<>(offers.size());
        offers.forEach(offer -> coordinatesList.add(offerMapper.offerToOfferCoordinatesDTO(offer)));
        return coordinatesList;
    }


    /**
     * Get all the offers by status and author id.
     *
     * @param status   the offer status
     * @param authorId the offer authorId
     * @param pageable the offer filter
     * @return the list of entities
     */
    @Override
    public Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserId(OfferStatus status, String authorId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and authorId = {}", status, authorId);
        Profile profile = profileRepository.findById(authorId);
        if (profile == null) {
            return new PageImpl<OfferViewShortWithModerationReportDTO>(Collections.EMPTY_LIST);
        }
        Page<Offer> result = offerRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }


    @Override
    public Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserPublicId(OfferStatus status, String userPublicId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and userPublicId = {}", status, userPublicId);
        Profile profile = profileRepository.findByPublicId(userPublicId);
        if (profile == null) {
            return new PageImpl<OfferViewShortWithModerationReportDTO>(Collections.EMPTY_LIST);
        }
        Page<Offer> result = offerRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }

    /**
     * Get all the offers by status and author id.
     *
     * @param status   the offer status
     * @param pageable the offer filter
     * @return the list of entities
     */
    @Override
    public Page<OfferViewShortWithModerationReportDTO> findAllByStatus(OfferStatus status, Pageable pageable) {
        log.debug("Request to get all Offers by status = {}", status);
        Page<Offer> result = offerRepository.findAllByStatus(status, pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }


    /**
     * Get one offer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<OfferViewDetailsDTO> findOne(String id) {
        log.debug("Request to get Offer : {}", id);
        Offer offer = offerRepository.findOne(id);
        if (offer == null) {
            return Optional.empty();
        }
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }


    @Override
    public Optional<OfferViewDetailsDTO> findOneBySeoUrl(String seoUrl) {
        log.debug("Request to get Offer : {}", seoUrl);
        Optional<Offer> offer = offerRepository.findOneBySeoUrl(seoUrl);
        if (offer.isPresent()) {
            final Offer o = offer.get();
            o.incrementView(true, false);
            offerRepository.save(o);
            Set<OfferStatus> statuses = new HashSet<>();
            statuses.addAll(Arrays.asList(OfferStatus.ACTIVE, OfferStatus.DEACTIVATED, OfferStatus.REJECTED, OfferStatus.ON_MODERATION));
            if (!statuses.contains(o.getStatus())) {
                offer = Optional.empty();
            }
        }
        return offer.map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    /**
     * Get one OfferViewShortDTOs by seoUrl.
     *
     * @param seoUrl the seoUrl of the entity
     * @return the list of entities
     */
    @Override
    public Page<OfferViewShortDTO> findRelevantBySeoUrl(String seoUrl, Pageable pageable) {
        Optional<Offer> offerOptional = offerRepository.findOneBySeoUrl(seoUrl);
        if (offerOptional.isPresent()) {
            final Offer offer = offerOptional.get();
            StringBuilder search = new StringBuilder();

            List<Category> categories = categoryService.findByCodeInOrderByCodeAsc(offer.getCategories());
            for (Category category : categories) {
                final Map<String, String> titles = category.getTitle();
                for (String lang : titles.keySet()) {
                    search.append(titles.get(lang) + " ");
                    break;
                }
            }

            search.append(offer.getTitle());
            OfferFilter filter = new OfferFilter();
            filter.setQuery(search.toString());
            Page<Offer> result = new PageImpl<>(offerRepositoryCustom.findByFilter(filter, OfferStatus.ACTIVE, offer.getId(), pageable));
            return result.map(o -> offerMapper.offerToOfferShortDTO(o));

        }
        return new PageImpl<>(new ArrayList<>());
    }

    /**
     * Increment statistic phone views by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public void incrementPhoneViews(String id) {
        Offer offer = offerRepository.findOne(id);
        offer.incrementView(false, true);
        offerRepository.save(offer);
    }


    /**
     * Delete the  offer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Offer : {}", id);
        Offer offer = offerRepository.findOne(id);
        offer.setStatus(OfferStatus.ARCHIVED);
        offerRepository.save(offer);
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public boolean exists(String id) {
        return offerRepository.exists(id);
    }

    /**
     * Returns whether an entity can be updated by current user.
     *
     * @param offerId must not be {@literal null}.
     * @return true if an user has permission for update, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public boolean hasPermissionForUpdate(String offerId, String authorId) {
        Optional<Offer> offer = null;
        if (offerId != null && authorId != null) {
            offer = offerRepository.findOfferByIdAndAuthorId(offerId, authorId);
        } else if (offer != null) {
            offer = Optional.ofNullable(offerRepository.findOne(offerId));
        }
        if (log.isDebugEnabled()) {
            log.debug("Request has permission for update offer : {}", offer);
        }

        if (offer != null && offer.get() != null && SecurityUtils.isAuthenticated()) {
            String currentUserID = SecurityUtils.getCurrentUserId();
            Set<OfferStatus> statuses = new HashSet<>();
            statuses.addAll(Arrays.asList(OfferStatus.ACTIVE, OfferStatus.DEACTIVATED, OfferStatus.REJECTED, OfferStatus.ARCHIVED));
            //if current user owner offer
            if ((offer.get().getAuthorId() == currentUserID && statuses.contains(offer.get().getStatus()))) {
                return true;
            }
            //if current user it's moderator(admin role)
            if (SecurityUtils.isCurrentUserInRole(UserRole.ROLE_MODERATOR) && offer.get().getStatus() == OfferStatus.ON_MODERATION) {
                return true;
            }
        }   //access denied
        return false;
    }

    /**
     * Returns whether an entity can be updated by current user.
     *
     * @param id must not be {@literal null}.
     * @return true if an user has permission for update, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */

    /**
     * Update active offers base price by current exchange rate.
     *
     * @return void
     */
    @Override
    //todo vdvorak
    //@Scheduled(cron = "${offer.job.updateActiveOffersBasePrice.cron}", zone = "${offer.job.updateActiveOffersBasePrice.zone}")
    public void updateActiveOffersBasePrice() {
        for (Currency currency : Currency.values()) {
            final BigDecimal exchangeRate = currencyConverterService.convertToBaseCurrency(currency, new BigDecimal("1"));
            //todo vdvorak
            //offerRepository.updateBasePriceByExchangeRate(OfferStatus.ACTIVE, currency, currencyConverterService.getBaseCurrency(), exchangeRate.doubleValue());
        }
    }

    /**
     * Update offer's status.
     *
     * @param id     the id of the entity
     * @param status the status to be updated
     * @return the entity
     */
    @Override
    public Optional<OfferViewDetailsDTO> updateStatus(String id, OfferStatus status) {
        log.debug("Request to update update offer's status : {}", id);
        Offer offer = offerRepository.findOne(id);
        if (offer != null && offer.getAuthorId().equals(SecurityUtils.getCurrentUserId()) &&
                (offer.getStatus() == OfferStatus.ACTIVE || offer.getStatus() == OfferStatus.DEACTIVATED) &&
                (status == OfferStatus.ACTIVE || status == OfferStatus.DEACTIVATED || status == OfferStatus.ARCHIVED)) {
            offer.setStatus(status);
            offer = offerRepository.save(offer);
        } else {
            return Optional.empty();
        }
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }


    /**
     * Get offer image by id and size type.
     *
     * @param id       the id of the entity
     * @param sizeType the size type of image
     * @return the entity
     */
    @Override
    public FileWrapper findImageByIdAndSizeType(String id, OfferImageSizeType sizeType) {
        return imageService.findOne(id, sizeType);
    }

    /**
     * Get one offer categories by search word.
     *
     * @param string the string
     * @param page   the page
     * @param size   the size
     * @return the list of entities
     */
    @Override
    public List<OfferCategoryCountDTO> searchCategoriesByString(String string, int page, int size) {
        log.debug("Request to search category by string : {}", string);
        final List<OfferCategoryCount> offerCategoryCounts = offerRepositoryCustom.searchCategoriesByString(string, page, size);
        return offerCategoryCounts
                .stream()
                .map(c -> offerCategoryMapper.fromOfferCategoryCountToOfferCategoryCountDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List<OfferStatisticByDateDTO>> findOfferStatisticBySeoUrlAndDateRange(String seoUrl, LocalDate dateStart, LocalDate dateEnd) {
        Optional<Offer> offerOptional = offerRepository.findOneBySeoUrl(seoUrl);
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            return Optional.of(offer.getStatistic())
                    .map(o -> offerMapper.offerStatisticToOfferStatisticDTO(o, offer.getCreatedDate().toLocalDate(), dateStart, dateEnd));
        }
        return Optional.empty();
    }

    @Override
    public Optional<OfferViewDetailsDTO> findOfferByIdAndAuthorId(String offerId, String authorId) {
        Optional<Offer> offer = offerRepository.findOfferByIdAndAuthorId(offerId, authorId);
        if (offer == null) {
            return Optional.empty();
        }
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o.get()));
    }


    private String generateUniqueSeoUrl(String title) {
        // index number in 36 radix
        return SEOFriendlyUrlUtil.generateSEOFriendlyUrl(title + "-" + Long.toString(sequenceService.getNextSequenceValue(OFFER_SEQUENCE_ID), 36));
    }

    private boolean isNeededModeration(OfferUpdateDTO offerUpdateDTO) {
        boolean result = false;

        result |= offerUpdateDTO.getCategory() != null;
        result |= offerUpdateDTO.getTitle() != null;
        result |= offerUpdateDTO.getDescription() != null;
        if (offerUpdateDTO.getImages() != null) {
            for (OfferImageDTO imageDTO : offerUpdateDTO.getImages()) {
                result |= (imageDTO.getBase64Data() != null && imageDTO.getImageId() == null);
            }
        }
        result |= offerUpdateDTO.getAddress() != null;

        // price can be change without moderation

        result |= offerUpdateDTO.getContactInfo() != null;
        result |= offerUpdateDTO.getAttrs() != null;
        result |= offerUpdateDTO.getMultiAttrs() != null;
        result |= offerUpdateDTO.getNumAttrs() != null;
        result |= offerUpdateDTO.getBoolAttrs() != null;

        return result;
    }


    /**
     * Get offer image by id and size type.
     *
     * @param offerImageIds  the ids of persisted images
     * @param offerImageDTOS the image dtos to persist or update
     * @param seoURL         the seo URL for name creation
     * @return the entity
     */
    private void saveOfferImages(List<String> offerImageIds, List<OfferImageDTO> offerImageDTOS, String seoURL) {
        if (offerImageIds == null) {
            offerImageIds = new LinkedList<>();
        }
        if (offerImageDTOS == null) {
            return;
        }
        for (OfferImageDTO offerImageDTO : offerImageDTOS) {
            if (!StringUtils.isEmpty(offerImageDTO.getImageId()) && offerImageIds.contains(offerImageDTO.getImageId())) {
                imageService.deleteOfferImage(offerImageDTO.getImageId());
            }
            if (offerImageDTO.getBase64Data() != null) {
                final String id = imageService.saveOfferImage(offerImageDTO, seoURL);
                if (!StringUtils.isEmpty(id)) {
                    offerImageDTO.setImageId(id);
                }
            }
        }
        offerImageIds.removeAll(offerImageDTOS.stream().map(OfferImageDTO::getImageId).collect(Collectors.toSet()));
        offerImageIds.forEach(id -> imageService.deleteOfferImage(id));
    }

    private void calculatePriceInBaseCurrency(MoneyFilter moneyFilter) {
        if (moneyFilter != null) {
            if (moneyFilter.getCurrency() != null) {
                final Currency currency = moneyFilter.getCurrency();
                if (moneyFilter.getFrom() != null) {
                    final BigDecimal fromInBaseCurrency = currencyConverterService.convertToBaseCurrency(currency, new BigDecimal(moneyFilter.getFrom()));
                    moneyFilter.setFrom(fromInBaseCurrency.doubleValue());
                }
                if (moneyFilter.getTo() != null) {
                    final BigDecimal toInBaseCurrency = currencyConverterService.convertToBaseCurrency(currency, new BigDecimal(moneyFilter.getTo()));
                    moneyFilter.setTo(toInBaseCurrency.doubleValue());
                }
                moneyFilter.setCurrency(currencyConverterService.getBaseCurrency());
            }
        }
    }


    //------------------------------------------- OLD SERVICE FUNCTION ----------------------------------------------//


    @Override
    public void create(Offer offer) {
        OfferModerationReport offerModerationReport = new OfferModerationReport();
        //todo maybe add in future
        //offerModerationReport.setModerationStatus(ModerationStatus.NO);

        try {
            List<Image> images = null;
            if (offer.getImageIds().size() < 1) {
                images = new ArrayList<>();
                Image image = new Image();
                image.setUrl("null");
                image.setImageId("58edef514c8ea73b0dff0164"); //TODO hard)
                images.add(image);

            }
            //todo vdvorak
            //offer.setImageIds(images);
        } catch (NullPointerException e) {
            offer.setImageIds(null);
        }

        Offer newOffer = new Offer();
        newOffer.setAuthorId(offer.getAuthorId());
        //newOffer.setUserInfo(offer.getUserInfo());
        newOffer.setCreatedDate(ZonedDateTime.now());
        newOffer.setLastOfferModerationReport(offerModerationReport);
        //newOffer.setProperties(offer.getProperties())
        newOffer.setImageIds(offer.getImageIds());
        newOffer.setSeoUrl(offer.getSeoUrl());
        //newOffer.setSeoKey(offer.getSeoKey())
        newOffer.setCategories(offer.getCategories());
        newOffer.setYoutubeVideoId(offer.getYoutubeVideoId());
        newOffer.setTitle(offer.getTitle());
        newOffer.setDescription(offer.getDescription());
        newOffer.setPrice(offer.getPrice());
        //newOffer.setOldPrice(0l)
        //newOffer.setPriceCanBeNegotiated(offer.getPriceCanBeNegotiated())
        //newOffer.setUsed(offer.getUsed())
        //newOffer.setActive(Boolean.TRUE)
        //newOffer.setDeleted(false)
        newOffer.setAddress(offer.getAddress());
        newOffer.getPrice().setCurrency(offer.getPrice().getCurrency());
        //newOffer.setCanBeReserved(offer.getCanBeReserved())
        //newOffer.setMaximumReservedPeriod(offer.getMaximumReservedPeriod())
        //newOffer.setCanBeRented(offer.getCanBeRented())
        //newOffer.setAvailableShippingMethods(offer.getAvailableShippingMethods())
        //newOffer.setAvailablePaymentMethods(offer.getAvailablePaymentMethods())
        //newOffer.setShowOrdersCount(offer.isShowOrdersCount())
        //newOffer.setPaidServices(offer.getPaidServices())
        //newOffer.setMonthOfPrices(offer.getMonthOfPrices())
        //newOffer.setRents(offer.getRents())
        //newOffer.setMadeInUkraine(offer.isMadeInUkraine());

        offerRepositoryOLD.create(newOffer);
        offer.setId(newOffer.getId());
    }

    @Override
    public Offer findById(String offerId) {
        return offerRepositoryOLD.findById(offerId);
    }

    @Override
    public Offer findBySeoKey(String seoKey) {
        return offerRepositoryOLD.findBySeoKey(seoKey);
    }


    @Override
    public boolean offerExists(String id) {
        return offerRepositoryOLD.offerExists(id);
    }

    @Override
    public EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions) {
        return offerRepositoryOLD.findOffersWithOptions(offerFilterOptions);
    }

    @Override
    public Offer edit(Offer oldOffer) {
        Offer newOffer = new Offer();
        newOffer.setId(oldOffer.getId());
        newOffer.setLastOfferModerationReport(oldOffer.getLastOfferModerationReport());
        newOffer.setAuthorId(oldOffer.getAuthorId());
        //newOffer.setUserInfo(oldOffer.getUserInfo());
        newOffer.setCreatedDate(ZonedDateTime.now());
        newOffer.setCategories(oldOffer.getCategories());
        //newOffer.setProperties(oldOffer.getProperties());
        newOffer.setImageIds(oldOffer.getImageIds());
        newOffer.setSeoUrl(oldOffer.getSeoUrl());
        //newOffer.setSeoKey(oldOffer.getSeoKey());
        //newOffer.setSeoCategory(oldOffer.getSeoCategory());
        newOffer.setYoutubeVideoId(oldOffer.getYoutubeVideoId());
        newOffer.setTitle(oldOffer.getTitle());
        newOffer.setDescription(oldOffer.getDescription());
        newOffer.setPrice(oldOffer.getPrice());
        //newOffer.setOldPrice(offerRepository.findById(oldOffer.getId()).getPrice() != null ? offerRepository.findById(oldOffer.getId()).getPrice() : 0l)
        //newOffer.setPriceCanBeNegotiated(oldOffer.getPriceCanBeNegotiated());
        //newOffer.setUsed(oldOffer.getUsed());
        //newOffer.setActive(oldOffer.getActive());
        newOffer.setAddress(oldOffer.getAddress());
        newOffer.getPrice().setCurrency(oldOffer.getPrice().getCurrency());
        //newOffer.setCanBeReserved(oldOffer.getCanBeReserved());
        //newOffer.setMaximumReservedPeriod(oldOffer.getMaximumReservedPeriod());
        //newOffer.setCanBeRented(oldOffer.getCanBeRented());
        //newOffer.setAvailableShippingMethods(oldOffer.getAvailableShippingMethods());
        //newOffer.setAvailablePaymentMethods(oldOffer.getAvailablePaymentMethods());
        //newOffer.setShowOrdersCount(oldOffer.isShowOrdersCount()); //TODO
        //newOffer.setDeleted(oldOffer.isDeleted());
        //newOffer.setPaidServices(oldOffer.getPaidServices());
        //newOffer.setMonthOfPrices(oldOffer.getMonthOfPrices());
        //newOffer.setRents(oldOffer.getRents());
        //newOffer.setMadeInUkraine(oldOffer.isMadeInUkraine());

        return offerRepositoryOLD.findAndUpdate(newOffer);
    }

    @Override
    public ResponseEntity<String> editByUser(OfferRegistration offerRegistration, MultipartFile[] files) {

        Offer updatedOffer = offerRegistration.getOffer();

        // check is offer not null and exist
        if (updatedOffer.getId() == null) {
            return new ResponseEntity<>("You did not sent offer ID", HttpStatus.BAD_REQUEST);
        } else if (!offerExists(updatedOffer.getId())) {
            return new ResponseEntity<>("Offer with this ID: " + updatedOffer.getId() + " is not exist", HttpStatus.NOT_FOUND);
        }

        Offer oldOffer = findById(updatedOffer.getId());

        String userId = SecurityOperations.getLoggedUserId();


        // Check if current user is not an author
        if (!findById(updatedOffer.getId()).getAuthorId().equals(userId)) {
            return new ResponseEntity<>("You are not author of this offer", HttpStatus.FORBIDDEN);
        }


        // update SEO url title for offer
        String newTranslitTitle = Translit.makeTransliteration(updatedOffer.getTitle());
        String newSeoUrl = newTranslitTitle + "-" + oldOffer.getSeoUrl();
        updatedOffer.setSeoUrl(newSeoUrl);


        updatedOffer.setImageIds(prepareImageBeforeOfferUpdate(oldOffer, offerRegistration, files));


        // if critical information was changed in the offer - we must resubmit this offer for the moderation
        //todo maybe add in future
        /*if (isOfferWasCriticalChanged(oldOffer, updatedOffer, files)) {
            oldOffer.getLastOfferModerationReport().setModerationStatus(ModerationStatus.NO);
            updatedOffer.setOfferModerationReports(oldOffer.getOfferModerationReports());
        }*/

        Offer newOffer = edit(updatedOffer);

        return new ResponseEntity<>(newOffer.getSeoUrl(), HttpStatus.OK);
    }

    @Override
    public void reserveOffer(String offerId, Reservation reservation) {
        //todo vdvorak this method not use
/*        Reservation newReservation = new Reservation()
                .setProfileId(reservation.getProfileId())
                .setUserContactInfo(reservation.getUserContactInfo())
                .setCreatedDateEqualsToCurrentDate();
        Offer newOffer = new Offer();
        newOffer.setId(offerId);
        //newOffer.setReservation(newReservation);
        Offer updatedOffer = offerRepository.findAndUpdate(newOffer);*/
    }

    @Override
    public void deleteReservation(String offerId) {
        offerRepositoryOLD.deleteReservation(offerId);
    }

    @Override
    public void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo) {
        offerRepositoryOLD.rentOffer(offerId, rentedOfferPeriodInfo);
    }

    @Override
    public void deleteRent(String offerId, String rentId) {
        offerRepositoryOLD.deleteRent(offerId, rentId);
    }

    @Override
    public void setActive(String offerId, boolean isActive) {
        Offer offer = new Offer();
        offer.setId(offerId);
        offerRepositoryOLD.findAndUpdate(offer);
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        return offerRepositoryOLD.getMatchedNames(name);
    }

    @Override
    public String getMainOfferImage(Offer offer) {
        List<String> imageListId = offer.getImageIds();
        if (imageListId != null) {
            for (String imageId : imageListId) {
                if (!StringUtils.isEmpty(imageId)) {
                    return imageId;
                }
            }
        }
        return null;
    }

    /**
     * Create OfferFilterOption object for search offers relevant to current on it's city.
     *
     * @param offer the offer to which we must find relevant offers.
     * @return the OfferFilterOptions object.
     */
    private OfferFilterOptions offerFilterOptionsPreparatorForRelevantSearchWithCity(Offer offer) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setAddress(new Address());

        // add categories in filter
        offerFilterOptions.setCategories(offer.getCategories());

        // add address in filter
        if (offer.getAddress().getCity() != null) {
            offerFilterOptions
                    .getAddress()
                    .setCity(offer.getAddress().getCity());
        }

        return offerFilterOptions;
    }


    private List<String> prepareImageBeforeOfferUpdate(Offer oldOffer, OfferRegistration newOfferRegistration, MultipartFile[] files) {

        List<String> resultImages = new ArrayList<>();
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();

        List<Image> newImageList = newOfferRegistration.getImages();
        List<String> oldImageList = oldOffer.getImageIds();
        deleteImages(oldImageList, newImageList); // delete unnecessary images

        for (Image image : newImageList) {
            Integer currentImageIndex = image.getIndex();
            Image newImage = new Image();
            if (currentImageIndex != null) {
                // Сохраняем одну фотографию, которая лежит по указанному индексу
                try {
                    fileUploadWrapper
                            .setServiceName("offers")
                            .setInputStream(files[currentImageIndex].getInputStream())
                            .setContentType(files[currentImageIndex].getContentType())
                            .setFilename(files[currentImageIndex].getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String newImageId = storageService.saveCachedImageOffer(fileUploadWrapper);
                newImage.setImageId(newImageId);
                resultImages.add(newImageId);
            } else if (!StringUtils.isEmpty(image.getImageId())) {
                newImage.setImageId(image.getImageId());
                resultImages.add(image.getImageId());
            }
        }

        return resultImages;
    }


    /**
     * Find if in the new ImageList some images were deleted and delete them from the DB.
     *
     * @param oldImageList - the old Image list.
     * @param newImageList - the new Image list.
     */
    private void deleteImages(List<String> oldImageList, List<Image> newImageList) {
        Set<String> setOfTheImagesForDelete = new HashSet<>();
        boolean isDeleted;
        for (String oldImage : oldImageList) {
            isDeleted = true;
            for (Image newImage : newImageList) {
                if (oldImage.equals(newImage.getImageId())) {
                    isDeleted = false;
                }
            }
            if (isDeleted) {
                setOfTheImagesForDelete.add(oldImage);
            }
        }
        storageService.deleteListOfOfferImages(setOfTheImagesForDelete);
    }

}