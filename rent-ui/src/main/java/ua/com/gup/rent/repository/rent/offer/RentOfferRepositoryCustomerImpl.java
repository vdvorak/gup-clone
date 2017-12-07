package ua.com.gup.rent.repository.rent.offer;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ua.com.gup.rent.filter.*;
import ua.com.gup.rent.model.enumeration.RentOfferCurrency;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.rent.RentOfferCategoryCount;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class RentOfferRepositoryCustomerImpl implements RentOfferRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(RentOfferRepositoryCustomerImpl.class);
    private final Integer COORDINATES_MAX_DIFF_LAT = 6;
    private final Integer COORDINATES_MAX_DIFF_LON = 3;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void createIndex() {
        TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .named(RentOffer.COLLECTION_NAME + "_TextIndex")
                .onField("$**")
                .withDefaultLanguage("russian")
                .build();
        mongoTemplate.indexOps(RentOffer.class).ensureIndex(textIndex);
    }

    @Override
    public long countByFilter(RentOfferFilter offerFilter, RentOfferStatus offerStatus) {
        return countOffersByFilter(offerFilter, Arrays.asList(offerStatus), null, null);
    }

    @Override
    public List<RentOffer> findByFilter(RentOfferFilter offerFilter, RentOfferStatus offerStatus, Pageable pageable) {
        return findByFilter(offerFilter, Arrays.asList(offerStatus), pageable);
    }

    @Override
    public List<RentOffer> findByFilter(RentOfferFilter offerFilter, RentOfferStatus offerStatus, String excludedId, Pageable pageable) {
        return findByFilter(offerFilter, Arrays.asList(offerStatus), Arrays.asList(excludedId), pageable);
    }

    @Override
    public List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<RentOfferStatus> offerStatuses, Pageable pageable) {
        return findByFilter(offerFilter, offerStatuses, null, pageable);
    }

    @Override
    public List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<RentOfferStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable) {
        return findOffersByFilter(offerFilter, offerStatuses, excludedIds, pageable);
    }

    @Override
    public void updateBasePriceByExchangeRate(RentOfferStatus status, RentOfferCurrency currency, RentOfferCurrency baseCurrency, double exchangeRate) {
        BasicDBObject query = new BasicDBObject();
        query.put("status", status.name());
        query.put("price.currency", currency.name());
        BasicDBObject projection = new BasicDBObject();
        projection.put("_id", 1);
        projection.put("price.amount", 1);
        final DBCollection collection = mongoTemplate.getCollection(RentOffer.COLLECTION_NAME);
        DBCursor cursor = collection.find(query, projection);
        try {
            while (cursor.hasNext()) {
                final DBObject doc = cursor.next();
                try {
                    final DBObject price = (DBObject) doc.get("price");
                    final BasicDBObject fields = new BasicDBObject();
                    fields.put("price.baseAmount", exchangeRate * Double.valueOf("" + price.get("amount")));
                    fields.put("price.baseCurrency", baseCurrency.name());
                    fields.put("price.last_modified_date", new Date());
                    collection.update(new BasicDBObject("_id", doc.get("_id")), new BasicDBObject("$set", fields));
                } catch (Exception e) {
                    log.error("Error price update doc = {}", doc, e);
                }
            }
        } catch (Exception e) {
            log.error("ForEach update with currency = {}, baseCurrency = {}, exchangeRate = {} failed: ", currency, baseCurrency, exchangeRate, e);
        } finally {
            cursor.close();
        }
    }

    private Query buildQueryByFilter(RentOfferFilter offerFilter, List<RentOfferStatus> statusList, Collection<String> excludedIds, Pageable pageable) {
        Query query = new Query();
        if (!StringUtils.isEmpty(offerFilter.getQuery())) {
            TextCriteria textCriteria = TextCriteria.forLanguage("russian");
            if (pageable != null && pageable.getSort() != null) {
                textCriteria.matchingPhrase(offerFilter.getQuery());
            } else {
                textCriteria.matching(offerFilter.getQuery());
            }
            query.addCriteria(textCriteria);
        }
        if (excludedIds != null && excludedIds.size() > 0) {
            query.addCriteria(Criteria.where("_id").nin(excludedIds));
        }
        if (offerFilter.getSeoUrls() != null && offerFilter.getSeoUrls().length > 0) {
            query.addCriteria(Criteria.where("seoUrl").in(offerFilter.getSeoUrls()));
        }
        if (offerFilter.getDate() != null) {
            final RentOfferDateFilter dateFilter = offerFilter.getDate();
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
                                .map(RentOfferStatus::toString)
                                .collect(Collectors.toList())));
            }
        } else {
            query.addCriteria(Criteria.where("status").is(RentOfferStatus.ACTIVE));
        }
        //todo maybe need change how as new  categories with sort vdvorak
        if (offerFilter.getCategories() != null) {
            query.addCriteria(Criteria.where("categories").all(offerFilter.getCategories()));
        }

        //filter by authorId
        if (offerFilter.getRentOfferAuthorFilter() != null && offerFilter.getRentOfferAuthorFilter().getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").in(offerFilter.getRentOfferAuthorFilter().getAuthorId()));
        }
        //todo vdvorak
        RentOfferCoordinatesFilter coordinates = offerFilter.getCoordinates();
        RentOfferAddressFilter addressFilter = offerFilter.getAddress();
        if (coordinates != null || addressFilter != null) {

            if (isValidCoordinates(coordinates)) {
                query.addCriteria(Criteria.where("address.lat").gte(coordinates.getMinYX()[0]).lte(coordinates.getMaxYX()[0]));
                query.addCriteria(Criteria.where("address.lng").gte(coordinates.getMinYX()[1]).lte(coordinates.getMaxYX()[1]));
            } else if (addressFilter != null) {

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
        }

        if (offerFilter.getPrice() != null) {
            RentOfferMoneyFilter price = offerFilter.getPrice();
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
            for (RentOfferAttributeFilter attrFilter : offerFilter.getAttrs()) {
                query.addCriteria(Criteria.where("attrs." + attrFilter.getKey() + ".selected.key").in(attrFilter.getVals().split(",")));
            }
        }
        if (offerFilter.getMultiAttrs() != null) {
            for (RentOfferAttributeFilter attrFilter : offerFilter.getMultiAttrs()) {
                query.addCriteria(Criteria.where("multiAttrs." + attrFilter.getKey() + ".selected").elemMatch(Criteria.where("key").in(attrFilter.getVals().split(","))));
            }
        }
        if (offerFilter.getNumAttrs() != null) {
            for (RentOfferNumericAttributeFilter filter : offerFilter.getNumAttrs()) {
                if (filter.getFrom() != null && filter.getTo() != null) {
                    query.addCriteria(Criteria.where("numAttrs." + filter.getKey() + ".selectedDouble").gte(filter.getFrom()).lte(filter.getTo()));
                } else {
                    if (filter.getFrom() != null) {
                        query.addCriteria(Criteria.where("numAttrs." + filter.getKey() + ".selectedDouble").gte(filter.getFrom()));
                    }
                    if (filter.getTo() != null) {
                        query.addCriteria(Criteria.where("numAttrs." + filter.getKey() + ".selectedDouble").lte(filter.getTo()));
                    }
                }
            }
        }
        if (offerFilter.getBoolAttrs() != null) {
            for (RentOfferBooleanAttributeFilter filter : offerFilter.getBoolAttrs()) {
                query.addCriteria(Criteria.where("boolAttrs." + filter.getKey() + ".selected").is(filter.getVal()));
            }
        }
        return query.with(pageable);
    }

    private long countOffersByFilter(RentOfferFilter offerFilter, List<RentOfferStatus> statusList, Collection<String> excludedIds, Pageable pageable) {
        Query query = buildQueryByFilter(offerFilter, statusList, excludedIds, pageable);
        return mongoTemplate.count(query, RentOffer.class);
    }

    private List<RentOffer> findOffersByFilter(RentOfferFilter offerFilter, List<RentOfferStatus> statusList, Collection<String> excludedIds, Pageable pageable) {
        Query query = buildQueryByFilter(offerFilter, statusList, excludedIds, pageable);
        return mongoTemplate.find(query, RentOffer.class);
    }

    public List<RentOfferCategoryCount> searchCategoriesByString(String query, int page, int size) {
        final String[] words = query.split(" ");
        final Criteria[] criterias = new Criteria[words.length];
        for (int i = 0; i < words.length; i++) {
            criterias[i] = Criteria.where("title").regex("(?i:.*" + words[i] + ".*)");
        }
        //use aggregation function use Criteria JSR specification //todo vdvorak
        Aggregation agg = newAggregation(
                match(Criteria.where("status").is(RentOfferStatus.ACTIVE.toString())),
                match(new Criteria().andOperator(criterias)),
                group("categoriesRegExp").count().as("count"),
                project("count").and("categoriesRegExp").previousOperation(),
                sort(Sort.Direction.DESC, "count"),
                skip(page * size),
                limit(size)
        );
        //Convert the aggregation result into a List
        AggregationResults<RentOfferCategoryCount> groupResults = mongoTemplate.aggregate(agg, RentOffer.class, RentOfferCategoryCount.class);
        List<RentOfferCategoryCount> result = groupResults.getMappedResults();
        return result;
    }

    private boolean isValidCoordinates(RentOfferCoordinatesFilter coordinates) {
        return coordinates != null
                && coordinates.getMaxYX() != null
                && coordinates.getMaxYX().length == 2
                && coordinates.getMaxYX() != null
                && coordinates.getMinYX().length == 2
                && Math.abs(coordinates.getMaxYX()[0].doubleValue()) - Math.abs(coordinates.getMinYX()[0].doubleValue()) <= COORDINATES_MAX_DIFF_LAT
                && Math.abs(coordinates.getMaxYX()[1].doubleValue()) - Math.abs(coordinates.getMinYX()[1].doubleValue()) <= COORDINATES_MAX_DIFF_LON;
    }

}