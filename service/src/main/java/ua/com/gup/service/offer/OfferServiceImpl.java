package ua.com.gup.service.offer;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.enumeration.CommonCurrency;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.dto.offer.*;
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
import ua.com.gup.mongo.model.filter.MoneyFilter;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.offer.OfferCategoryCount;
import ua.com.gup.mongo.model.other.EntityPage;
import ua.com.gup.repository.offer.OfferRepository;
import ua.com.gup.repository.offer.OfferRepositoryCustom;
import ua.com.gup.repository.offer.OfferRepositoryOLD;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.service.category.CategoryService;
import ua.com.gup.service.currency.CurrencyConverterService;
import ua.com.gup.service.sequence.SequenceService;
import ua.com.gup.util.SEOFriendlyUrlUtil;
import ua.com.gup.util.security.SecurityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import ua.com.gup.common.model.FileInfo;
import ua.com.gup.common.model.FileType;
import ua.com.gup.common.model.ImageFileInfo;
import ua.com.gup.common.service.FileStorageService;
import ua.com.gup.common.util.ImageScaleUtil;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.image.ImageSizeType;
import static ua.com.gup.common.model.image.ImageSizeType.LARGE;
import ua.com.gup.common.service.ImageService;

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
    @Autowired(required = false)
    private FileStorageService fileStorageService;
    @Autowired
    private ImageService imageService;


    //-------------------- OLD -----------------------------//
    @Autowired
    private OfferRepositoryOLD offerRepositoryOLD;

    @Autowired
    private OfferRepositoryCustom offerRepositoryCustom;
    
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
        
        Offer offer = offerMapper.offerCreateDTOToOffer(offerCreateDTO);
        offer.setStatus(CommonStatus.ON_MODERATION);
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
        offerMapper.offerUpdateDTOToOffer(offerUpdateDTO, offer);
        offer.setLastModifiedBy(SecurityUtils.getCurrentUserId());
        offer.setLastModifiedDate(ZonedDateTime.now());
        // on moderation if fields was changed and moderation is needed or last moderation is refused - moderation any way
        if (isNeededModeration(offerUpdateDTO) || offer.getLastOfferModerationReport().isRefused()) {
            offer.setStatus(CommonStatus.ON_MODERATION);
        } else {
            offer.setStatus(CommonStatus.ACTIVE);
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
            offer.setStatus(CommonStatus.REJECTED);
        } else {
            offer.setStatus(CommonStatus.ACTIVE);
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
        //set authorId by exists publicId for OfferFilter
        if (offerFilter.getAuthorFilter() != null) {
            if (offerFilter.getAuthorFilter().getPublicId() != null && offerFilter.getAuthorFilter().getAuthorId() == null) {
                offerFilter.getAuthorFilter().setAuthorId(profileRepository.findByPublicId(offerFilter.getAuthorFilter().getPublicId().trim()).getId());
            }
        }
        log.debug("Request to get all Offers by filter  {} ", offerFilter);
        calculatePriceInBaseCurrency(offerFilter.getPrice());
        long count = offerRepositoryCustom.countByFilter(offerFilter, CommonStatus.ACTIVE);
        List<Offer> offers = Collections.EMPTY_LIST;
        if (count > 0) {
            offers = offerRepositoryCustom.findByFilter(offerFilter, CommonStatus.ACTIVE, pageable);
        }
        Page<Offer> result = new PageImpl<>(offers, pageable, count);
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }

    @Override
    public List<OfferViewCoordinatesDTO> findCoordinatesByFilter(OfferFilter offerFilter, Pageable pageable) {
        log.debug("Request to get offers coordinates by filter");

        List<Offer> offers = offerRepositoryCustom.findByFilter(offerFilter, CommonStatus.ACTIVE, pageable);

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
    public Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserId(CommonStatus status, String authorId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and authorId = {}", status, authorId);
        Profile profile = profileRepository.findById(authorId);
        if (profile == null) {
            return new PageImpl<OfferViewShortWithModerationReportDTO>(Collections.EMPTY_LIST);
        }
        Page<Offer> result = offerRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }


    @Override
    public Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserPublicId(CommonStatus status, String userPublicId, Pageable pageable) {
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
    public Page<OfferViewShortWithModerationReportDTO> findAllByStatus(CommonStatus status, Pageable pageable) {
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
            Set<CommonStatus> statuses = new HashSet<>();
            statuses.addAll(Arrays.asList(CommonStatus.ACTIVE, CommonStatus.DEACTIVATED, CommonStatus.REJECTED, CommonStatus.ON_MODERATION));
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
            Page<Offer> result = new PageImpl<>(offerRepositoryCustom.findByFilter(filter, CommonStatus.ACTIVE, offer.getId(), pageable));
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
        offer.setStatus(CommonStatus.ARCHIVED);
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
    @Deprecated
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
            Set<CommonStatus> statuses = new HashSet<>();
            statuses.addAll(Arrays.asList(CommonStatus.ACTIVE, CommonStatus.DEACTIVATED, CommonStatus.REJECTED, CommonStatus.ARCHIVED));
            //if current user owner offer
            if ((offer.get().getAuthorId() == currentUserID && statuses.contains(offer.get().getStatus()))) {
                return true;
            }
            //if current user it's moderator(admin role)
            if (SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_MODERATOR) && offer.get().getStatus() == CommonStatus.ON_MODERATION) {
                return true;
            }
        }   //access denied
        return false;
    }


    /**
     * Update offer's status.
     *
     * @param id     the id of the entity
     * @param status the status to be updated
     * @return the entity
     */
    @Override
    public Optional<OfferViewDetailsDTO> updateStatus(String id, CommonStatus status) {
        log.debug("Request to update update offer's status : {}", id);
        Offer offer = offerRepository.findOne(id);
        offer.setStatus(status);
        offer = offerRepository.save(offer);
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    /**
     * Can user changge offer's status.
     *
     * @param id     the id of the offer entity
     * @param status the status to be changed
     * @return the entity
     */
    @Override
    public Boolean isCanUpdateStatus(String id, CommonStatus status) {
        Offer offer = offerRepository.findOne(id);
        Boolean fromStatus = (offer.getStatus() == CommonStatus.ACTIVE || offer.getStatus() == CommonStatus.DEACTIVATED);
        Boolean toStatus = (status == CommonStatus.ACTIVE || status == CommonStatus.DEACTIVATED || status == CommonStatus.ARCHIVED);
        return fromStatus && toStatus;
    }


    /**
     * Get offer image by id and size type.
     *
     * @param id       the id of the entity
     * @param sizeType the size type of image
     * @return the entity
     */
//    @Override
//    public FileWrapper findImageByIdAndSizeType(String id, OfferImageSizeType sizeType) {
//        return imageService.findOne(id, sizeType);
//    }

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
        result |= offerUpdateDTO.getAddress() != null;

        // price can be change without moderation

        result |= offerUpdateDTO.getContactInfo() != null;
        result |= offerUpdateDTO.getAttrs() != null;
        result |= offerUpdateDTO.getMultiAttrs() != null;
        result |= offerUpdateDTO.getNumAttrs() != null;
        result |= offerUpdateDTO.getBoolAttrs() != null;

        return result;
    }   

    

    private void calculatePriceInBaseCurrency(MoneyFilter moneyFilter) {
        if (moneyFilter != null) {
            if (moneyFilter.getCurrency() != null) {
                final CommonCurrency currency = moneyFilter.getCurrency();
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
    public Offer findById(String offerId) {
        return offerRepositoryOLD.findById(offerId);
    }

    @Override
    public EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions) {
        return offerRepositoryOLD.findOffersWithOptions(offerFilterOptions);
    }   

    @Override
    public String getMainOfferImage(Offer offer) {
        List<ImageStorage> imageList = offer.getImages();
        if (imageList != null && !offer.getImages().isEmpty()) {
           return offer.getImages().get(0).getImages().get(LARGE).getS3id();
        }
        return null;
    }

    /**
     * @param offerId
     * @return Collection of  phone numbers from offer's contactinfo
     */
    @Override
    public Collection<String> getOfferContactInfoPhoneNumbersById(String offerId) {
        Offer offer = offerRepository.findOne(offerId);
        return offer.getContactInfo().getPhoneNumbers();
    }

    @Override
    public boolean existsByIdAndStatus(String id, CommonStatus status) {
        return offerRepository.existsByIdAndStatus(id, status);
    }
    
    @Override
    public List<ImageStorage> getImages(String offerId){
        return offerRepositoryCustom.findOfferImages(offerId);
    }
    
    @Override
    public ImageStorage getImage(String offerId, String imageId){
        return offerRepositoryCustom.findOfferImage(offerId, imageId);
    }   
    
    @Override
    public ImageStorage addImage(String offerId, MultipartFile file) throws IOException {
        ImageStorage image = imageService.saveImageStorage(file);
        Offer offer = offerRepository.findOne(offerId);
        offer.getImages().add(image);
        offerRepository.save(offer);
        return image;
    }    
    
    @Override
    public boolean isExistsImage(String offerId, String imageId){
        return offerRepositoryCustom.isExistsOfferImage(offerId, imageId);
    }
    
    @Override
    public void deleteImage(String offerId, String imageId) throws IOException{        
        ImageStorage image = offerRepositoryCustom.findOfferImage(offerId, imageId);        
        imageService.deleteImageStorage(image);
        offerRepositoryCustom.deleteOfferImage(image);
    }
}