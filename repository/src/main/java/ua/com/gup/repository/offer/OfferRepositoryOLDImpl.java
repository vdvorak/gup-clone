package ua.com.gup.repository.offer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.config.mongo.MongoTemplateOperations;
import ua.com.gup.domain.offer.Offer;
import ua.com.gup.model.EntityPage;
import ua.com.gup.model.OfferUserContactInfo;
import ua.com.gup.model.filter.OfferFilterOptions;
import ua.com.gup.model.offer.Address;
import ua.com.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.gup.model.xchangerate.api.CurrencyNotSupportedException;
import ua.com.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.gup.model.xchangerate.service.ServiceException;
import ua.com.gup.model.xchangerate.storage.StorageException;
import ua.com.gup.model.xchangerate.util.Currency;
import ua.com.gup.util.CurrencyConvertUtil;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OfferRepositoryOLDImpl implements OfferRepositoryOLD {


    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Offer.class)) {
            mongoTemplate.createCollection(Offer.class);
        }
    }

    @Override
    public void create(Offer offer) {
        mongoTemplate.insert(offer);
    }

    @Override
    public Offer findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Offer.class);
    }

    @Override
    public Offer findBySeoKey(String seoKey) {
        Query query = new Query(Criteria.where("seoKey").is(seoKey));
        return mongoTemplate.findOne(query, Offer.class);
    }

    @Override
    public Offer findBySeoUrl(String seoUrl) {
        Query query = new Query(Criteria.where("seoUrl").is(seoUrl));
        return mongoTemplate.findOne(query, Offer.class);
    }

    @Override
    public Offer findAndUpdate(Offer offer) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(offer);
    }

    @Override
    public int delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, Offer.class);
        return result.getN();
    }

    @Override
    public boolean offerExists(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Offer.class);
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
                    offerFilterOptions.getPrice().setCurrency(ua.com.gup.model.enumeration.Currency.UAH);
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
                    offerFilterOptions.getPrice().setCurrency(ua.com.gup.model.enumeration.Currency.USD);
                    queryUSD = queryPreparator(offerFilterOptions);
                    offerListUSD = mongoTemplate.find(queryUSD, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.EUR, Currency.EUR).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.EUR, Currency.EUR).longValue());
                    offerFilterOptions.getPrice().setCurrency(ua.com.gup.model.enumeration.Currency.EUR);
                    queryEUR = queryPreparator(offerFilterOptions);
                    offerListEUR = mongoTemplate.find(queryEUR, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.EUR, Currency.UAH).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.EUR, Currency.UAH).longValue());
                    offerFilterOptions.getPrice().setCurrency(ua.com.gup.model.enumeration.Currency.UAH);
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
                    offerFilterOptions.getPrice().setCurrency(ua.com.gup.model.enumeration.Currency.USD);
                    queryUSD = queryPreparator(offerFilterOptions);
                    offerListUSD = mongoTemplate.find(queryUSD, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.UAH, Currency.EUR).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.UAH, Currency.EUR).longValue());
                    offerFilterOptions.getPrice().setCurrency(ua.com.gup.model.enumeration.Currency.EUR);
                    queryEUR = queryPreparator(offerFilterOptions);
                    offerListEUR = mongoTemplate.find(queryEUR, Offer.class);
                    if (offerFilterOptions.getFromPrice() != null)
                        offerFilterOptions.setFromPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getFromPrice())), Currency.UAH, Currency.UAH).longValue());
                    if (offerFilterOptions.getToPrice() != null)
                        offerFilterOptions.setToPrice(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFilterOptions.getToPrice())), Currency.UAH, Currency.UAH).longValue());
                    offerFilterOptions.getPrice().setCurrency(ua.com.gup.model.enumeration.Currency.UAH);
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

    @Override
    public EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, String excludeOfferId) {

        Query query = queryPreparator(offerFilterOptions);

        query.addCriteria(Criteria.where("id").ne(excludeOfferId));
        return new EntityPage<>(mongoTemplate.count(query, Offer.class), mongoTemplate.find(query, Offer.class));
    }


    @Override
    public EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, Collection<String> excludeOffersId) {

        Query query = queryPreparator(offerFilterOptions);

        query.addCriteria(Criteria.where("id").nin(excludeOffersId));
        return new EntityPage<>(mongoTemplate.count(query, Offer.class), mongoTemplate.find(query, Offer.class));
    }

    @Override
    public void deleteReservation(String offerId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(offerId)),
                new Update().set("reservation", null),
                Offer.class);
    }

    @Override
    public void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(offerId)),
                new Update().push("rent.rentedOfferPeriodInfo", rentedOfferPeriodInfo),
                Offer.class);
    }


    @Override
    public void deleteRent(String offerId, String rentId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(offerId)),
                new Update().pull("rent.rentedOfferPeriodInfo", Query.query(Criteria.where("id").is(rentId))),
                Offer.class);
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        String searchFieldRegex = "(?i:.*" + name + ".*)";
        Query query = new Query();

        query.addCriteria(new Criteria().orOperator(Criteria.where("title").regex(searchFieldRegex)));

        query.fields().include("title");
        query.skip(0);
        query.limit(10);

        mongoTemplate.find(query, OfferUserContactInfo.class).stream().map(OfferUserContactInfo::getContactName).collect(Collectors.toSet());

        ////////////////////////////////////////////////
//        OfferUserContactInfo userInfo;
//        userInfo.getPhoneNumbers();
//        userInfo.getContactName();
        ////////////////////////////////////////////////
//        mongoTemplate.find(query, OfferUserContactInfo.class).stream().map(Offer::OfferUserContactInfo.getContactName).collect(Collectors.toSet());
        ////////////////////////////////////////////////

        return mongoTemplate.find(query, Offer.class).stream().map(Offer::getTitle).collect(Collectors.toSet());
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
