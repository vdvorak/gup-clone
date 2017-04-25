package ua.com.gup.repository.custom.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.util.StringUtils;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.repository.custom.OfferRepositoryCustom;
import ua.com.gup.repository.filter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfferRepositoryImpl implements OfferRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Offer> findByFilter(OfferFilter offerFilter, OfferStatus offerStatus, Pageable pageable) {
        List<OfferStatus> offerStatuses = new ArrayList<>();
        offerStatuses.add(offerStatus);
        return findByFilter(offerFilter, offerStatuses, pageable);
    }

    @Override
    public List<Offer> findByFilter(OfferFilter offerFilter, List<OfferStatus> offerStatuses, Pageable pageable) {
        return createQueryAndFind(offerFilter, offerStatuses, pageable);
    }

    private List<Offer> createQueryAndFind(OfferFilter offerFilter, List<OfferStatus> statusList, Pageable pageable) {
        Query query = new Query();
        if (!StringUtils.isEmpty(offerFilter.getQuery())) {
            TextCriteria textCriteria = TextCriteria.
                    forLanguage("ru").
                    matching(offerFilter.getQuery());
            query.addCriteria(textCriteria);
        }
        if (offerFilter.getDate() != null) {
            final DateFilter dateFilter = offerFilter.getDate();
            if (dateFilter.getFrom() != null && dateFilter.getTo() != null) {
                query.addCriteria(Criteria.where("lastModifiedDate").gte(dateFilter.getFrom()).lte(dateFilter.getTo()));
            } else {
                if (dateFilter.getFrom() != null) {
                    query.addCriteria(Criteria.where("lastModifiedDate").gte(dateFilter.getFrom()));
                }
                if (dateFilter.getTo() != null) {
                    query.addCriteria(Criteria.where("lastModifiedDate").lte(dateFilter.getTo()));
                }
            }
        }
        if (statusList != null && statusList.size() > 0) {
            if (statusList.size() == 1) {
                query.addCriteria(Criteria.where("status").is(statusList.get(0).toString()));
            } else {
                query.addCriteria(Criteria.where("status").in(
                        statusList
                                .stream()
                                .map(OfferStatus::toString)
                                .collect(Collectors.toList())));
            }
        } else {
            query.addCriteria(Criteria.where("status").is(OfferStatus.ACTIVE));
        }
        if (offerFilter.getCategories() != null) {
            String regex = "(?i:" + offerFilter.getCategories().replace(",", "/") + ".*)";
            query.addCriteria(Criteria.where("categoriesRegExp").regex(regex));
        }
        if (offerFilter.getAddress() != null) {
            AddressFilter addressFilter = offerFilter.getAddress();
            if (addressFilter.getCountries() != null) {
                query.addCriteria(Criteria.where("address.country.code").in(addressFilter.getCountries().split(",")));
            }
            if (addressFilter.getDistricts() != null) {
                query.addCriteria(Criteria.where("address.district.code").in(addressFilter.getDistricts().split(",")));
            }
            if (addressFilter.getCities() != null) {
                query.addCriteria(Criteria.where("address.city.code").in(addressFilter.getCities().split(",")));
            }
        }
        if (offerFilter.getPrice() != null) {
            MoneyFilter price = offerFilter.getPrice();
            if (price.getCurrency() != null) {
                if (price.getFrom() != null && price.getTo() != null) {
                    query.addCriteria(Criteria.where("price.baseAmount").gte(price.getFrom()).lte(price.getTo()));
                } else {
                    if (price.getFrom() != null) {
                        query.addCriteria(Criteria.where("price.baseAmount").gte(price.getFrom()));
                    }
                    if (price.getTo() != null) {
                        query.addCriteria(Criteria.where("price.baseAmount").lte(price.getTo()));
                    }
                }
            }
        }
        if (offerFilter.getAttrs() != null) {
            for (AttributeFilter attrFilter : offerFilter.getAttrs()) {
                query.addCriteria(Criteria.where("attrs." + attrFilter.getKey()).in(attrFilter.getVals().split(",")));
            }
        }
        if (offerFilter.getNumAttrs() != null) {
            for (NumericAttributeFilter filter : offerFilter.getNumAttrs()) {
                if (filter.getFrom() != null && filter.getTo() != null) {
                    query.addCriteria(Criteria.where("numAttrs." + filter.getKey()).gte(filter.getFrom()).lte(filter.getTo()));
                } else {
                    if (filter.getFrom() != null) {
                        query.addCriteria(Criteria.where("numAttrs." + filter.getKey()).gte(filter.getFrom()));
                    }
                    if (filter.getTo() != null) {
                        query.addCriteria(Criteria.where("numAttrs." + filter.getKey()).lte(filter.getTo()));
                    }
                }
            }
        }
        if (offerFilter.getBoolAttrs() != null) {
            for (BooleanAttributeFilter filter : offerFilter.getBoolAttrs()) {
                query.addCriteria(Criteria.where("boolAttrs." + filter.getKey()).is(filter.getVal()));
            }
        }
        query.with(pageable);
        return mongoTemplate.find(query, Offer.class);
    }
}
