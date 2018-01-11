package ua.com.gup.server.repository.impl;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.common.model.enumeration.CommonCurrency;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.common.repository.impl.CommonOfferRepositoryImpl;
import ua.com.gup.model.xchangerate.api.CurrencyNotSupportedException;
import ua.com.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.gup.model.xchangerate.service.ServiceException;
import ua.com.gup.model.xchangerate.storage.StorageException;
import ua.com.gup.model.xchangerate.util.Currency;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.other.EntityPage;
import ua.com.gup.server.repository.OfferRepository;
import ua.com.gup.util.CurrencyConvertUtil;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfferRepositoryImpl extends CommonOfferRepositoryImpl<Offer,OfferFilter>
        implements OfferRepository, CommonOfferRepository<Offer, OfferFilter> {

    private final Logger log = LoggerFactory.getLogger(OfferRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public OfferRepositoryImpl() {
        super(Offer.class);
    }

    @PostConstruct
    public void createIndex() {
        TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .named(Offer.COLLECTION_NAME + "_TextIndex")
                .onField("$**")
                .withDefaultLanguage("russian")
                .build();
        mongoTemplate.indexOps(Offer.class).ensureIndex(textIndex);
    }





    @Override
    public EntityPage<Offer> findOffersWithOptions(OfferFilterOptions offerFilterOptions) {
        EntityPage<Offer> offerEntityPage = new EntityPage<>();
        Query query, queryUSD, queryEUR, queryUAH;
        List<Offer> offerListUSD, offerListEUR, offerListUAH;
        List<Offer> offerListAll = new ArrayList<Offer>();

        if (offerFilterOptions.getPrice() != null && offerFilterOptions.getPrice().getCurrency() != null) {
            try {
                if (offerFilterOptions.getPrice().getCurrency().toString().equals("USD")) {
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.USD, Currency.USD).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.USD, Currency.USD).longValue());
                    queryUSD = queryPreparator(offerFilterOptions);
                    offerListUSD = mongoTemplate.find(queryUSD, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.USD, Currency.EUR).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.USD, Currency.EUR).longValue());
                    queryEUR = queryPreparator(offerFilterOptions);
                    offerListEUR = mongoTemplate.find(queryEUR, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.USD, Currency.UAH).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.USD, Currency.UAH).longValue());
                    offerFilterOptions.getPrice().setCurrency(CommonCurrency.UAH);
                    queryUAH = queryPreparator(offerFilterOptions);
                    offerListUAH = mongoTemplate.find(queryUAH, Offer.class);
                    offerListAll.addAll(offerListUSD);
                    offerListAll.addAll(offerListEUR);
                    offerListAll.addAll(offerListUAH);
                } else if (offerFilterOptions.getPrice().getCurrency().toString().equals("EUR")) {
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.EUR, Currency.USD).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.EUR, Currency.USD).longValue());
                    offerFilterOptions.getPrice().setCurrency(CommonCurrency.USD);
                    queryUSD = queryPreparator(offerFilterOptions);
                    offerListUSD = mongoTemplate.find(queryUSD, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.EUR, Currency.EUR).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.EUR, Currency.EUR).longValue());
                    offerFilterOptions.getPrice().setCurrency(CommonCurrency.EUR);
                    queryEUR = queryPreparator(offerFilterOptions);
                    offerListEUR = mongoTemplate.find(queryEUR, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.EUR, Currency.UAH).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.EUR, Currency.UAH).longValue());
                    offerFilterOptions.getPrice().setCurrency(CommonCurrency.UAH);
                    queryUAH = queryPreparator(offerFilterOptions);
                    offerListUAH = mongoTemplate.find(queryUAH, Offer.class);
                    offerListAll.addAll(offerListUSD);
                    offerListAll.addAll(offerListEUR);
                    offerListAll.addAll(offerListUAH);
                } else if (offerFilterOptions.getPrice().getCurrency().toString().equals("UAH")) {
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.UAH, Currency.USD).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.UAH, Currency.USD).longValue());
                    offerFilterOptions.getPrice().setCurrency(CommonCurrency.USD);
                    queryUSD = queryPreparator(offerFilterOptions);
                    offerListUSD = mongoTemplate.find(queryUSD, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.UAH, Currency.EUR).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.UAH, Currency.EUR).longValue());
                    offerFilterOptions.getPrice().setCurrency(CommonCurrency.EUR);
                    queryEUR = queryPreparator(offerFilterOptions);
                    offerListEUR = mongoTemplate.find(queryEUR, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.UAH, Currency.UAH).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.UAH, Currency.UAH).longValue());
                    offerFilterOptions.getPrice().setCurrency(CommonCurrency.UAH);
                    queryUAH = queryPreparator(offerFilterOptions);
                    offerListUAH = mongoTemplate.find(queryUAH, Offer.class);
                    offerListAll.addAll(offerListUSD);
                    offerListAll.addAll(offerListEUR);
                    offerListAll.addAll(offerListUAH);
                }
            } catch (ServiceException | StorageException | CurrencyNotSupportedException | EndpointException | JSONException e) {
                e.getStackTrace();
            }
            offerEntityPage.setEntities(offerListAll);
            offerEntityPage.setTotalEntities(offerListAll.size());
        } else {
            query = queryPreparator(offerFilterOptions);
            List<Offer> offerList = mongoTemplate.find(query, Offer.class);

            offerEntityPage.setEntities(offerList);
            offerEntityPage.setTotalEntities(offerList.size());
        }

        return offerEntityPage;
    }

    private Query queryPreparator(OfferFilterOptions offerFO) {

        Query query = new Query();

        if (offerFO.getId() != null) {
            query.addCriteria(Criteria.where("id").is(offerFO.getId()));
        }


        //ToDo In the future this maybe will be work. But now we don't show deleted offers.
        /*if (offerFO.isDeleted()) {
            query.addCriteria(Criteria.where("deleted").ne(true));
        } else {
            query.addCriteria(Criteria.where("deleted").is(false));
        }*/


        if (offerFO.getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").is(offerFO.getAuthorId()));
        }

        if (offerFO.getLastOfferModerationReport() != null) {
            //todo not use in new model maybe add
            /*if (offerFO.getLastOfferModerationReport().getRefusalReasons() != null){
                query.addCriteria(Criteria.where("offerModerationReports.moderationStatus").is(offerFO.getLastOfferModerationReport().getModerationStatus()));
            }*/

            if (offerFO.getLastOfferModerationReport().getLastModifiedDate() != null) {
                query.addCriteria(Criteria.where("offerModerationReports.lastModifiedDate").is(offerFO.getLastOfferModerationReport().getLastModifiedDate()));
            }
        }

        if (offerFO.isOfferModifiedAfterModeratorCheck()) {
            query.addCriteria(Criteria.where("offerModerationReports.lastModifiedDate").ne(null));
        }

        //todo not use baybe add this criteria
        /*if (offerFO.getActive() != null) {
            query.addCriteria(Criteria.where("active").is(offerFO.getActive()));
        }*/
        // if "true" show only new offer,
        // if "else" show only used offer,
        // otherwise show both of them
        /*if (offerFO.getUsed() == Boolean.TRUE) {
            query.addCriteria(Criteria.where("used").is(offerFO.getUsed()));
        } else if (offerFO.getUsed() == Boolean.FALSE) {
            query.addCriteria(Criteria.where("used").is(offerFO.getUsed()));
        }*/

        if (offerFO.getShowReserved() == Boolean.TRUE) {
            query.addCriteria(Criteria.where("reservation").ne(null));
        } else {
            query.addCriteria(Criteria.where("reservation").is(null)); // not reserved
        }

        /* ToDo it is for old impl of search, where we could find offer with area, city and country */
        if (offerFO.getAddresses() != null) {
            List<Criteria> criteriasAddresses = new ArrayList<>();
            Criteria criteriaAddresses = new Criteria();

            for (Address address : offerFO.getAddresses()) {

                if (address.getCity() != null) {
                    criteriasAddresses.add(Criteria.where("address.city").is(address.getCity()));
                }

            }

            Criteria[] criterias = criteriasAddresses.toArray(new Criteria[criteriasAddresses.size()]);
            criteriaAddresses.orOperator(criterias);
            query.addCriteria(criteriaAddresses);
        } else if (offerFO.getAddress() != null) {

            if (offerFO.getAddress().getCity() != null) {
                query.addCriteria(Criteria.where("address.city").is(offerFO.getAddress().getCity()));
            }

        }

        if (offerFO.getSearchField() != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("title").regex("(?i:.*" + offerFO.getSearchField() + ".*)"),
                    Criteria.where("description").regex("(?i:.*" + offerFO.getSearchField() + ".*)")));
        }

        if (offerFO.getCategories() != null) {
            query.addCriteria(Criteria.where("categories").all(offerFO.getCategories()));
        }

        //todo not use baybe add this criteria
        /*if (offerFO.getProperties() != null) {
            query.addCriteria(Criteria.where("properties").all(offerFO.getProperties()));
        }*/

        if (offerFO.getPropertiesInterval() != null) {
            List<DBObject> dbObjects = new ArrayList<>();

            offerFO.getPropertiesInterval().forEach(propInterval -> {
                if (propInterval.getKey() != null) {
                    DBObject dbObject = new BasicDBObject();
                    dbObject.put("key", propInterval.getKey());

                    DBObject comparison = new BasicDBObject();
                    if (propInterval.getFrom() != null) {
                        comparison.put("$gte", propInterval.getFrom());
                    }
                    if (propInterval.getTo() != null) {
                        comparison.put("$lte", propInterval.getTo());
                    }
                    dbObject.put("value", comparison); //TODO: (PropertyRange) valuee ...

                    DBObject elementQuery = new BasicDBObject();
                    elementQuery.put("$elemMatch", dbObject);
                    dbObjects.add(elementQuery);
                }
            });

            Criteria criteria = Criteria.where("properties").all(dbObjects.toArray(new DBObject[dbObjects.size()]));
            query.addCriteria(criteria);
        }

        /*if (offerFO.isPriceWithVat()){
            query.addCriteria(Criteria.where("priceWithVat").is(true));
        }*/

        if (offerFO.getFromPrice() != null && offerFO.getToPrice() != null) {
            query.addCriteria(Criteria.where("price").gte(offerFO.getFromPrice()).lte(offerFO.getToPrice()));
        } else if (offerFO.getFromPrice() != null) {
            query.addCriteria(Criteria.where("price").gte(offerFO.getFromPrice()));
        } else if (offerFO.getToPrice() != null) {
            query.addCriteria(Criteria.where("price").lte(offerFO.getToPrice()));
        }

        if (offerFO.getPrice() != null && offerFO.getPrice().getCurrency() != null) {
            query.addCriteria(Criteria.where("currency").is(offerFO.getPrice().getCurrency()));
        }

        if (offerFO.getCreatedDateSortDirection() != null) {
            query.with(new Sort(Sort.Direction.fromString(offerFO.getCreatedDateSortDirection()), "createdDate"));
        } else if (offerFO.getPriceSortDirection() != null) {
            query.with(new Sort(Sort.Direction.fromString(offerFO.getPriceSortDirection()), "price"));
        }

        query.skip(offerFO.getSkip());
        query.limit(offerFO.getLimit());

        return query;
    }

}
