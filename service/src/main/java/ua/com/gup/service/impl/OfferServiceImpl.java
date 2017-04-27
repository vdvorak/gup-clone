package ua.com.gup.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.repository.OfferRepository;
import ua.com.gup.repository.filter.OfferFilter;
import ua.com.gup.service.CurrencyConverterService;
import ua.com.gup.service.ImageService;
import ua.com.gup.service.OfferService;
import ua.com.gup.service.SequenceService;
import ua.com.gup.service.dto.*;
import ua.com.gup.service.mapper.OfferMapper;
import ua.com.gup.service.util.SEOFriendlyUrlUtil;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.util.SecurityUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service Implementation for managing Offer.
 */
@Service
@Qualifier(value = "OfferServiceNew")
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
     * @param offerUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfferDetailsDTO save(OfferUpdateDTO offerUpdateDTO) {
        log.debug("Request to save Offer : {}", offerUpdateDTO);
        Offer offer = offerRepository.findOne(offerUpdateDTO.getId());
        offerMapper.offerUpdateDTOToOffer(offerUpdateDTO, offer);
        offer.setLastModifiedBy(SecurityUtils.getCurrentUserId());
        offer.setLastModifiedDate(LocalDateTime.now());
        offer = offerRepository.save(offer);
        OfferDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    /**
     * Save a offer.
     *
     * @param offerCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfferDetailsDTO save(OfferCreateDTO offerCreateDTO) {
        log.debug("Request to save Offer : {}", offerCreateDTO);
        String seoURL = generateUniqueSeoUrl(offerCreateDTO.getTitle());
        for (OfferImageDTO offerImageDTO : offerCreateDTO.getImages()) {
            offerImageDTO.setImageId(imageService.saveOfferImage(offerImageDTO, seoURL));
        }
        Offer offer = offerMapper.offerCreateDTOToOffer(offerCreateDTO);
        offer.setStatus(OfferStatus.ON_MODERATION);
        offer.setSeoUrl(seoURL);
        String userID = SecurityUtils.getCurrentUserId();
        offer.setCreatedBy(userID);
        offer.setLastModifiedBy(userID);
        offer = offerRepository.save(offer);
        OfferDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
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
    public Page<OfferShortDTO> findAll(OfferFilter offerFilter, Pageable pageable) {
        log.debug("Request to get all Offers by filter");
        Page<Offer> result = new PageImpl<>(offerRepository.findByFilter(offerFilter, OfferStatus.ACTIVE, pageable));
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }

    /**
     * Get one offer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public OfferDetailsDTO findOne(String id) {
        log.debug("Request to get Offer : {}", id);
        Offer offer = offerRepository.findOne(id);
        OfferDetailsDTO offerDetailsDTO = offerMapper.offerToOfferDetailsDTO(offer);
        return offerDetailsDTO;
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
        Offer offer = offerRepository.findOne(id);
        if (offer != null && SecurityUtils.isAuthenticated()) {
            String currentUserID = "user_id"; // TODO userservice.getCurrentUserId()
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

    private String generateUniqueSeoUrl(String title) {
        return SEOFriendlyUrlUtil.generateSEOFriendlyUrl(title + "-" + sequenceService.getNextSequenceValue(OFFER_SEQUENCE_ID));
    }


}
