package ua.com.gup.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.repository.OfferRepository;
import ua.com.gup.service.OfferService;
import ua.com.gup.service.dto.OfferCreateDTO;
import ua.com.gup.service.dto.OfferDetailsDTO;
import ua.com.gup.service.dto.OfferShortDTO;
import ua.com.gup.service.dto.OfferUpdateDTO;
import ua.com.gup.service.filter.*;
import ua.com.gup.service.mapper.OfferMapper;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.util.SecurityUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing Offer.
 */
@Service
@Qualifier(value = "OfferServiceNew")
public class OfferServiceImpl implements OfferService {

    private final Logger log = LoggerFactory.getLogger(OfferServiceImpl.class);

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
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
        offer.setLastModifiedDate(ZonedDateTime.now());
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
        Offer offer = offerMapper.offerCreateDTOToOffer(offerCreateDTO);
        offer.setStatus(OfferStatus.ON_MODERATION);
        offer.setSeoUrl("offer/seoUrl"); // TODO
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
     * @param pageable    the pagination information
     * @return the list of entities
     */
    @Override
    public Page<OfferShortDTO> findAll(OfferFilter offerFilter, Pageable pageable) {
        log.debug("Request to get all Offers by filter");
//        Page<Offer> result = new PageImpl<>(offerRepository.findAll(getAggregationOperations(offerFilter, pageable, OfferStatus.ACTIVE)));
        Page<Offer> result = offerRepository.findAll(pageable);

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

    private Aggregation getAggregationOperations(OfferFilter offerFilter, Pageable pageable, OfferStatus status) {
        List<OfferStatus> offerStatuses = new ArrayList<>();
        offerStatuses.add(status);
        return getAggregationOperations(offerFilter, pageable, offerStatuses);
    }

    private Aggregation getAggregationOperations(OfferFilter offerFilter, Pageable pageable, List<OfferStatus> statusList) {
        List<AggregationOperation> aggregationOperations = new ArrayList<>();
        if (!StringUtils.isEmpty(offerFilter.getQuery())) {
            TextCriteria textCriteria = TextCriteria.
                    forLanguage("ru").
                    matching(offerFilter.getQuery());
//            aggregationOperations.add(Aggregation.match(textCriteria));
        }
        Criteria criteria = new Criteria();
        if (statusList != null && statusList.size() > 0) {
            if (statusList.size() == 1) {
                criteria.andOperator(Criteria.where("status").is(statusList.get(0)));
            } else {
                criteria.andOperator(Criteria.where("status").in(statusList));
            }
        } else {
            criteria.andOperator(Criteria.where("status").is(OfferStatus.ACTIVE));
        }
        if (offerFilter.getCategories() != null) {
            String regex = "(?i:" + offerFilter.getCategories().replace(",", "/") + ".*)";
            criteria.andOperator(Criteria.where("categoriesRegExp").regex(regex));
        }
        if (offerFilter.getPrice() != null) {
            MoneyFilter price = offerFilter.getPrice();
            if (price.getCurrency() != null) {
                double exchangeRate = 1; // Todo
                if (price.getAmount() != null) {
                    if (price.getAmount().getFrom() != null) {
                        double from = price.getAmount().getFrom().doubleValue();
                        criteria.andOperator(Criteria.where("price.baseAmount").gte(from * exchangeRate));
                    }
                    if (price.getAmount().getTo() != null) {
                        double to = price.getAmount().getTo().doubleValue();
                        criteria.andOperator(Criteria.where("price.baseAmount").lte(to * exchangeRate));
                    }
                }
            }
        }
        if (offerFilter.getAttrs() != null) {
            for (AttributeFilter attrFilter : offerFilter.getAttrs()) {
                criteria.andOperator(Criteria.where("attrs." + attrFilter.getKey()).in(attrFilter.getVals().split(",")));
            }
        }
        if (offerFilter.getNumAttrs() != null) {
            for (NumericAttributeFilter filter : offerFilter.getNumAttrs()) {
                if (filter.getVal().getFrom() != null) {
                    criteria.andOperator(Criteria.where("numAttrs." + filter.getKey()).gte(filter.getVal().getFrom()));
                }
                if (filter.getVal().getTo() != null) {
                    criteria.andOperator(Criteria.where("numAttrs." + filter.getKey()).lte(filter.getVal().getTo()));
                }
            }
        }
        if (offerFilter.getBoolAttrs() != null) {
            for (BooleanAttributeFilter filter : offerFilter.getBoolAttrs()) {
                criteria.andOperator(Criteria.where("boolAttrs." + filter.getKey()).is(filter.getVal()));
            }
        }
        aggregationOperations.add(Aggregation.match(criteria));
        if (StringUtils.isEmpty(offerFilter.getQuery())) {
            aggregationOperations.add(Aggregation.sort(pageable.getSort()));
        }

        aggregationOperations.add(Aggregation.skip(pageable.getOffset()));
        aggregationOperations.add(Aggregation.limit(pageable.getPageSize()));

        return Aggregation.newAggregation(aggregationOperations);
    }
}
