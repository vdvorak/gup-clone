package ua.com.gup.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.domain.filter.MoneyFilter;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.domain.offer.OfferCategory;
import ua.com.gup.repository.OfferRepository;
import ua.com.gup.repository.file.FileWrapper;
import ua.com.gup.service.CurrencyConverterService;
import ua.com.gup.service.ImageService;
import ua.com.gup.service.OfferService;
import ua.com.gup.service.SequenceService;
import ua.com.gup.service.dto.offer.OfferCreateDTO;
import ua.com.gup.service.dto.offer.OfferImageDTO;
import ua.com.gup.service.dto.offer.OfferModeratorDTO;
import ua.com.gup.service.dto.offer.OfferUpdateDTO;
import ua.com.gup.service.dto.offer.enumeration.OfferImageSizeType;
import ua.com.gup.service.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.service.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.service.mapper.OfferMapper;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.service.util.SEOFriendlyUrlUtil;
import ua.com.itproekt.gup.model.profiles.UserRole;

import java.math.BigDecimal;
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

    private final OfferRepository offerRepository;

    private final ImageService imageService;

    private final SequenceService sequenceService;

    private final CurrencyConverterService currencyConverterService;

    private final OfferMapper offerMapper;

    @Autowired
    public OfferServiceImpl(
            OfferRepository offerRepository,
            ImageService imageService,
            SequenceService sequenceService,
            CurrencyConverterService currencyConverterService,
            OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.imageService = imageService;
        this.sequenceService = sequenceService;
        this.currencyConverterService = currencyConverterService;
        this.offerMapper = offerMapper;
    }

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
        if (isNeededModeration(offerUpdateDTO) || offer.getLastModerationReport().isRefused()) {
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
     * @param offerModeratorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfferViewDetailsDTO save(OfferModeratorDTO offerModeratorDTO) {
        log.debug("Request to save Offer modified by moderator: {}", offerModeratorDTO);
        Offer offer = offerRepository.findOne(offerModeratorDTO.getId());
        offerMapper.offerModeratorDTOToOffer(offerModeratorDTO, offer);
        if (offer.getLastModerationReport().isRefused()) {
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
        Page<Offer> result = new PageImpl<>(offerRepository.findByFilter(offerFilter, OfferStatus.ACTIVE, pageable));
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
    public Page<OfferViewShortDTO> findAllByStatusAndAuthorId(OfferStatus status, String authorId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and authorId = {}", status, authorId);
        Page<Offer> result = offerRepository.findAllByStatusAndAuthorId(status, authorId, pageable);
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }

    /**
     * Get all the offers by status and author id.
     *
     * @param status   the offer status
     * @param pageable the offer filter
     * @return the list of entities
     */
    @Override
    public Page<OfferViewShortDTO> findAllByStatus(OfferStatus status, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and authorId = {}", status);
        Page<Offer> result = offerRepository.findAllByStatus(status, pageable);
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }


    /**
     * Get one offer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public OfferViewDetailsDTO findOne(String id) {
        log.debug("Request to get Offer : {}", id);
        offerRepository.incrementViews(id);
        Offer offer = offerRepository.findOne(id);
        OfferViewDetailsDTO offerViewDetailsDTO = offerMapper.offerToOfferDetailsDTO(offer);
        return offerViewDetailsDTO;
    }

    /**
     * Get one OfferDetailsDTO by seoUrl.
     *
     * @param seoUrl the seoUrl of the entity
     * @return the entity
     */
    @Override
    public Optional<OfferViewDetailsDTO> findOneBySeoUrl(String seoUrl) {
        log.debug("Request to get Offer : {}", seoUrl);
        offerRepository.incrementViewsBySeoUrl(seoUrl);
        Optional<Offer> offer = offerRepository.findOneBySeoUrl(seoUrl);
        if (offer.map(o -> o.getStatus()).get() != OfferStatus.ACTIVE) {
            offer = Optional.empty();
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
        return new PageImpl<OfferViewShortDTO>(new ArrayList<>());
    }

    /**
     * Increment statistic phone views by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public void incrementPhoneViews(String id) {
        offerRepository.incrementPhoneViews(id);
    }

    /**
     * Increment statistic favorites by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public void incrementFavorites(String id) {
        offerRepository.incrementFavorites(id);
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
     * @param id must not be {@literal null}.
     * @return true if an user has permission for update, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public boolean hasPermissionForUpdate(String id) {
        log.debug("Request has permission for update offer : {}", id);
        Offer offer = offerRepository.findOne(id);
        if (offer != null && SecurityUtils.isAuthenticated()) {
            String currentUserID = SecurityUtils.getCurrentUserId();
            return (offer.getAuthorId() == currentUserID && offer.getStatus() != OfferStatus.ARCHIVED) ||
                    (SecurityUtils.isCurrentUserInRole(UserRole.ROLE_MODERATOR.name()) && offer.getStatus() == OfferStatus.ON_MODERATION);
        } else {
            return false;
        }
    }

    /**
     * Update active offers base price by current exchange rate.
     *
     * @return void
     */
    @Override
    public void updateActiveOffersBasePrice() {
        for (Currency currency : Currency.values()) {
            final BigDecimal exchangeRate = currencyConverterService.convertToBaseCurrency(currency, new BigDecimal("1"));
            offerRepository.updateBasePriceByExchangeRate(OfferStatus.ACTIVE, currency, currencyConverterService.getBaseCurrency(), exchangeRate.doubleValue());
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
            offer = null;
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

    private String generateUniqueSeoUrl(String title) {
        return SEOFriendlyUrlUtil.generateSEOFriendlyUrl(title + "-" + sequenceService.getNextSequenceValue(OFFER_SEQUENCE_ID));
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
    private void saveOfferImages(Set<String> offerImageIds, Set<OfferImageDTO> offerImageDTOS, String seoURL) {
        if (offerImageIds == null) {
            offerImageIds = new LinkedHashSet<>();
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
