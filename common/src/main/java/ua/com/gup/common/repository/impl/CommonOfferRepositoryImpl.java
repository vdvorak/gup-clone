/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.repository.impl;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.filter.*;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.model.offer.CommonCategoryCount;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.common.repository.CommonProfileRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public abstract class CommonOfferRepositoryImpl<T extends CommonRentOffer, F extends CommonOfferFilter> implements CommonOfferRepository<T, F> {

    private final Integer COORDINATES_MAX_DIFF_LAT = 6;
    private final Integer COORDINATES_MAX_DIFF_LON = 3;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CommonProfileRepository profileRepository;
    private final Class clazz;

    public Class getClazz() {
        return clazz;
    }


    public CommonOfferRepositoryImpl(Class clazz) {
        this.clazz = clazz;
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(getClazz())) {
            mongoTemplate.createCollection(getClazz());
        }
    }

    @Override
    public List<ImageStorage> findOfferImages(String offerId) {
        Criteria criteria = Criteria.where("_id").is(offerId);
        Query query = new Query(criteria);
        T offer = (T) mongoTemplate.findOne(query, getClazz());
        return offer.getImages();
    }

    @Override
    public ImageStorage findOfferImage(String offerId, String imageId) {

        Criteria criteria = Criteria.where("_id").is(offerId)
                .andOperator(Criteria.where("images._id").is(imageId));
        Query query = new Query(criteria);
        query.fields().exclude("_id").include("images.$");
        T offer = (T) mongoTemplate.findOne(query, getClazz());
        return offer.getImages().get(0);
    }

    @Override
    public Boolean isExistsOfferImage(String offerId, String imageId) {
        Criteria criteria = Criteria.where("_id").is(offerId)
                .andOperator(Criteria.where("images._id").is(imageId));
        Query query = new Query(criteria);
        return mongoTemplate.exists(query, getClazz());

    }

    @Override
    public void deleteOfferImage(ImageStorage image) {
        Query query = Query.query(Criteria
                .where("images")
                .elemMatch(Criteria.where("_id").is(image.getId()))
        );
        Update update = new Update().pull("images", new BasicDBObject("_id", image.getId()));
        mongoTemplate.updateMulti(query, update, getClazz());
    }

    @Override
    public T findOne(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return (T) mongoTemplate.findOne(query, getClazz());
    }

    @Override
    public void save(T offer) {
        mongoTemplate.save(offer);
    }

    @Override
    public boolean exists(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.exists(query, clazz);
    }


    public List<CommonCategoryCount> searchCategoriesByString(String query, int page, int size) {
        final String[] words = query.split(" ");
        final Criteria[] criterias = new Criteria[words.length];
        for (int i = 0; i < words.length; i++) {
            criterias[i] = Criteria.where("title").regex("(?i:.*" + words[i] + ".*)");
        }
        //use aggregation function use Criteria JSR specification //todo vdvorak
        Aggregation agg = newAggregation(
                match(Criteria.where("status").is(CommonStatus.ACTIVE.toString())),
                match(new Criteria().andOperator(criterias)),
                group("categoriesRegExp").count().as("count"),
                project("count").and("categoriesRegExp").previousOperation(),
                sort(Sort.Direction.DESC, "count"),
                skip(page * size),
                limit(size)
        );

        //Convert the aggregation result into a List
        AggregationResults<CommonCategoryCount> groupResults = mongoTemplate.aggregate(agg, getClazz(), CommonCategoryCount.class);
        List<CommonCategoryCount> result = groupResults.getMappedResults();
        return result;
    }

    @Override
    public boolean isOwner(String rentObjectId, String userId) {
        Query exists = new Query(Criteria.where("id").is(rentObjectId).and("ownerId").is(userId));
        return mongoTemplate.exists(exists, getClazz());
    }


    @Override
    public long countByFilter(F offerFilter, CommonStatus offerStatus) {
        return countOffersByFilter(offerFilter, Arrays.asList(offerStatus), null, null);
    }

    @Override
    public long countByFilter(OfferModeratorFilter filter) {
        Query query = buildQuery(filter);
        return mongoTemplate.count(query, clazz);
    }

    public List<T> searchByFilter(OfferModeratorFilter filter, Pageable pageable) {
        Query query = buildQuery(filter);
        query.with(pageable);
        return mongoTemplate.find(query, clazz);
    }


    private Query buildQuery(OfferModeratorFilter filter) {
        Query query = new Query();

        if (filter.getStatus() != null) {
            query.addCriteria(Criteria.where("status").is(filter.getStatus()));
        }

        if (!StringUtils.isEmpty(filter.getTitle())) {
            query.addCriteria(Criteria.where("title").regex(filter.getTitle(), "i"));
        }

        if (!StringUtils.isEmpty(filter.getAuthorId())) {
            String authorId = profileRepository.getIdByPulblicId(filter.getAuthorId());
            if (!StringUtils.isEmpty(filter.getAuthorId())) {
                query.addCriteria(Criteria.where("authorId").is(authorId));
            }
        }
        return query;
    }

    @Override
    public List<T> findByFilter(F offerFilter, CommonStatus offerStatus, Pageable pageable) {
        return findByFilter(offerFilter, Arrays.asList(offerStatus), pageable);
    }

    @Override
    public List<T> findByFilter(F offerFilter, CommonStatus offerStatus, String excludedId, Pageable pageable) {
        return findByFilter(offerFilter, Arrays.asList(offerStatus), Arrays.asList(excludedId), pageable);
    }

    @Override
    public List<T> findByFilter(F offerFilter, List<CommonStatus> offerStatuses, Pageable pageable) {
        return findByFilter(offerFilter, offerStatuses, null, pageable);
    }

    @Override
    public List<T> findByFilter(F offerFilter, List<CommonStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable) {
        return findOffersByFilter(offerFilter, offerStatuses, excludedIds, pageable);
    }


    private Query buildQueryByFilter(CommonOfferFilter offerFilter, List<CommonStatus> statusList, Collection<String> excludedIds, Pageable pageable) {
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
        if (statusList != null && statusList.size() > 0) {
            if (statusList.size() == 1) {
                query.addCriteria(Criteria.where("status").is(statusList.get(0).toString()));
            } else {
                query.addCriteria(Criteria.where("status").in(
                        statusList
                                .stream()
                                .map(CommonStatus::toString)
                                .collect(Collectors.toList())));
            }
        } else {
            query.addCriteria(Criteria.where("status").is(CommonStatus.ACTIVE));
        }
        //todo maybe need change how as new  categories with sort vdvorak
        if (offerFilter.getCategories() != null) {
            query.addCriteria(Criteria.where("categories").all(offerFilter.getCategories()));
        }

        //filter by authorId
        if (offerFilter.getAuthorFilter() != null && offerFilter.getAuthorFilter().getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").in(offerFilter.getAuthorFilter().getAuthorId()));
        }
        //todo vdvorak
        CommonCoordinatesFilter coordinates = offerFilter.getCoordinates();
        CommonAddressFilter addressFilter = offerFilter.getAddress();
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
            for (CommonAttributeFilter attrFilter : offerFilter.getAttrs()) {
                query.addCriteria(Criteria.where("attrs." + attrFilter.getKey() + ".selected.key").in(attrFilter.getVals().split(",")));
            }
        }
        if (offerFilter.getMultiAttrs() != null) {
            for (CommonAttributeFilter attrFilter : offerFilter.getMultiAttrs()) {
                query.addCriteria(Criteria.where("multiAttrs." + attrFilter.getKey() + ".selected").elemMatch(Criteria.where("key").in(attrFilter.getVals().split(","))));
            }
        }
        if (offerFilter.getNumAttrs() != null) {
            for (NumericAttributeFilter filter : offerFilter.getNumAttrs()) {
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
            for (BooleanAttributeFilter filter : offerFilter.getBoolAttrs()) {
                query.addCriteria(Criteria.where("boolAttrs." + filter.getKey() + ".selected").is(filter.getVal()));
            }
        }
        return query.with(pageable);
    }

    private long countOffersByFilter(CommonOfferFilter offerFilter, List<CommonStatus> statusList, Collection<String> excludedIds, Pageable pageable) {
        Query query = buildQueryByFilter(offerFilter, statusList, excludedIds, pageable);
        return mongoTemplate.count(query, getClazz());
    }

    private List<T> findOffersByFilter(CommonOfferFilter offerFilter, List<CommonStatus> statusList, Collection<String> excludedIds, Pageable pageable) {
        Query query = buildQueryByFilter(offerFilter, statusList, excludedIds, pageable);
        return mongoTemplate.find(query, getClazz());
    }

    @Override
    public String getOfferIdBySeoUrl(String seoUrl) {
        Criteria criteria = Criteria.where("seoUrl").is(seoUrl);
        Query query = new Query(criteria);
        query.fields().include("_id");
        T offer = (T) mongoTemplate.findOne(query, getClazz());
        return offer.getId();
    }

    private boolean isValidCoordinates(CommonCoordinatesFilter coordinates) {
        return coordinates != null
                && coordinates.getMaxYX() != null
                && coordinates.getMaxYX().length == 2
                && coordinates.getMaxYX() != null
                && coordinates.getMinYX().length == 2
                && Math.abs(coordinates.getMaxYX()[0].doubleValue()) - Math.abs(coordinates.getMinYX()[0].doubleValue()) <= COORDINATES_MAX_DIFF_LAT
                && Math.abs(coordinates.getMaxYX()[1].doubleValue()) - Math.abs(coordinates.getMinYX()[1].doubleValue()) <= COORDINATES_MAX_DIFF_LON;
    }

}
