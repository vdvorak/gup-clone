package ua.com.gup.service.offer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.domain.filter.MoneyFilter;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.domain.offer.Offer;
import ua.com.gup.dto.offer.*;
import ua.com.gup.dto.offer.enumeration.OfferImageSizeType;
import ua.com.gup.dto.offer.statistic.OfferStatisticByDateDTO;
import ua.com.gup.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.dto.offer.view.OfferViewShortWithModerationReportDTO;
import ua.com.gup.mapper.OfferCategoryCountMapper;
import ua.com.gup.mapper.OfferMapper;
import ua.com.gup.model.file.FileWrapper;
import ua.com.gup.model.offer.OfferCategory;
import ua.com.gup.model.offer.OfferCategoryCount;
import ua.com.gup.model.profiles.UserRole;
import ua.com.gup.repository.offer.OfferRepository;
import ua.com.gup.service.currency.CurrencyConverterService;
import ua.com.gup.service.image.ImageService;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.service.sequence.SequenceService;
import ua.com.gup.service.util.SEOFriendlyUrlUtil;

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
    private  OfferRepository offerRepository;
    @Autowired
    private  ImageService imageService;
    @Autowired
    private  SequenceService sequenceService;
    @Autowired
    private  CurrencyConverterService currencyConverterService;
    @Autowired
    private  OfferMapper offerMapper;
    @Autowired
    private  OfferCategoryCountMapper offerCategoryCountMapper;


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
        offer.setCreatedBy(userID);
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
        long count = offerRepository.countByFilter(offerFilter, OfferStatus.ACTIVE);
        List<Offer> offers = Collections.EMPTY_LIST;
        if (count > 0) {
            offers = offerRepository.findByFilter(offerFilter, OfferStatus.ACTIVE, pageable);
        }
        Page<Offer> result = new PageImpl<>(offers, pageable, count);
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
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
    public Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndAuthorId(OfferStatus status, String authorId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and authorId = {}", status, authorId);
        Page<Offer> result = offerRepository.findAllByStatusAndAuthorId(status, authorId, pageable);
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
            for (OfferCategory category : offer.getCategories()) {
                final Map<String, String> titles = category.getTitle();
                for (String lang : titles.keySet()) {
                    search.append(titles.get(lang) + " ");
                    break;
                }
            }
            search.append(offer.getTitle());
            OfferFilter filter = new OfferFilter();
            filter.setQuery(search.toString());
            Page<Offer> result = new PageImpl<>(offerRepository.findByFilter(filter, OfferStatus.ACTIVE, offer.getId(), pageable));
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
        Offer offer = null;
        if (offerId != null && authorId != null) {
            offer = offerRepository.findOfferByIdAndAuthorId(offerId, authorId);
        } else if (offer != null) {
            offer = offerRepository.findOne(offerId);
        }
        if (log.isDebugEnabled()) {
            log.debug("Request has permission for update offer : {}", offer);
        }

        if (offer != null && SecurityUtils.isAuthenticated()) {
            String currentUserID = SecurityUtils.getCurrentUserId();
            Set<OfferStatus> statuses = new HashSet<>();
            statuses.addAll(Arrays.asList(OfferStatus.ACTIVE, OfferStatus.DEACTIVATED, OfferStatus.REJECTED, OfferStatus.ARCHIVED));
            //if current user owner offer
            if ((offer.getAuthorId() == currentUserID && statuses.contains(offer.getStatus()))) {
                return true;
            }
            //if current user it's moderator(admin role)
            if (SecurityUtils.isCurrentUserInRole(UserRole.ROLE_MODERATOR) && offer.getStatus() == OfferStatus.ON_MODERATION) {
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
        final List<OfferCategoryCount> offerCategoryCounts = offerRepository.searchCategoriesByString(string, page, size);
        return offerCategoryCounts
                .stream()
                .map(c -> offerCategoryCountMapper.fromOfferCategoryCountToOfferCategoryCountDTO(c))
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
        Offer offer = offerRepository.findOfferByIdAndAuthorId(offerId, authorId);
        if (offer == null) {
            return Optional.empty();
        }
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
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

}
