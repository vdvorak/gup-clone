package ua.com.itproekt.gup.dao.offers;

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
import ua.com.itproekt.gup.model.offer.*;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.xchangerate.api.CurrencyNotSupportedException;
import ua.com.itproekt.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.itproekt.gup.model.xchangerate.service.ServiceException;
import ua.com.itproekt.gup.model.xchangerate.storage.StorageException;
import ua.com.itproekt.gup.util.CurrencyConvertUtil;
import ua.com.itproekt.gup.model.xchangerate.util.Currency;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OfferRepositoryImpl implements OfferRepository {


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
        Query query = queryPreparator(offerFilterOptions);

        List<Offer> offerList = mongoTemplate.find(query, Offer.class);
        EntityPage<Offer> offerEntityPage = new EntityPage<>();

        offerEntityPage.setEntities(offerList);
        offerEntityPage.setTotalEntities(offerList.size());
        return offerEntityPage;
    }

    @Override
    public EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, String excludeOfferId) {

        Query query = queryPreparator(offerFilterOptions);

        query.addCriteria(Criteria.where("id").ne(excludeOfferId));
        return new EntityPage<>(mongoTemplate.count(query, Offer.class), mongoTemplate.find(query, Offer.class));
    }


    @Override
    public EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, List<String> excludeOffersId) {

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
    public void incViewsAtOne(String offerId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(offerId)),
                new Update().inc("views", 1),
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

        return mongoTemplate.find(query, Offer.class).stream().map(Offer::getTitle).collect(Collectors.toSet());
    }


    private Query queryPreparator(OfferFilterOptions offerFO) {

        Query query = new Query();

        if (offerFO.getId()!=null){
            query.addCriteria(Criteria.where("id").is(offerFO.getId()));
        }


        //ToDo In the future this maybe will be work. But now we don't show deleted offers.
        if (offerFO.isDeleted()) {
            query.addCriteria(Criteria.where("deleted").ne(true));
        } else {
            query.addCriteria(Criteria.where("deleted").is(false));
        }


        if (offerFO.getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").is(offerFO.getAuthorId()));
        }

        if (offerFO.getOfferModerationReports() != null) {
            if (offerFO.getOfferModerationReports().getModerationStatus() != null){
                query.addCriteria(Criteria.where("offerModerationReports.moderationStatus").is(offerFO.getOfferModerationReports().getModerationStatus()));
            }

            if (offerFO.getOfferModerationReports().getLastModifiedDate() != null){
                query.addCriteria(Criteria.where("offerModerationReports.lastModifiedDate").is(offerFO.getOfferModerationReports().getLastModifiedDate()));
            }
        }

        if (offerFO.isOfferModifiedAfterModeratorCheck()){
            query.addCriteria(Criteria.where("offerModerationReports.lastModifiedDate").ne(null));
        }

        if (offerFO.getActive() != null) {
            query.addCriteria(Criteria.where("active").is(offerFO.getActive()));
        }
        // if "true" show only new offer,
        // if "else" show only used offer,
        // otherwise show both of them
        if (offerFO.getUsed() == Boolean.TRUE) {
            query.addCriteria(Criteria.where("used").is(offerFO.getUsed()));
        } else if (offerFO.getUsed() == Boolean.FALSE) {
            query.addCriteria(Criteria.where("used").is(offerFO.getUsed()));
        }

        if (offerFO.getShowReserved() == Boolean.TRUE) {
            query.addCriteria(Criteria.where("reservation").ne(null));
        } else {
            query.addCriteria(Criteria.where("reservation").is(null)); // not reserved
        }


        /* ToDo it is for search in multiply city. Ask Sasha... */
//        Set<String> cityList = offerFO.getCityList();
//        if (offerFO.getCityList() != null) {
//            List<Criteria> criteriaCity = new ArrayList<>();
//
//            for (String city : cityList) {
//                criteriaCity.add(Criteria.where("address.city").is(city));
//            }
//
//            Criteria[] criteriaArr = criteriaCity.toArray(new Criteria[cityList.size()]);
//
//            query.addCriteria(new Criteria().orOperator(criteriaArr));
//        }





//        query.addCriteria(Criteria.where("address.area").is("2"));

//        Criteria criteria2 = new Criteria();
//        criteria2.orOperator(Criteria.where("address.area").is("2"),
//                Criteria.where("address.area").is("13"));
//        query.addCriteria(criteria2);

//        Criteria criteria2 = new Criteria();
//        Criteria[] criterias = {Criteria.where("address.area").is("2"), Criteria.where("address.area").is("13")};
//        criteria2.orOperator(criterias);
//        query.addCriteria(criteria2);

//        List<Criteria> criteriasList = new ArrayList<>();
//        criteriasList.add( Criteria.where("address.area").is("2") );
//        criteriasList.add(Criteria.where("address.area").is("13") );
//
//        Criteria criteria2 = new Criteria();
//        Criteria[] criterias = criteriasList.toArray( new Criteria[criteriasList.size()] );
//        criteria2.orOperator(criterias);
//        query.addCriteria(criteria2);

//        List<Criteria> criteriasList = new ArrayList<>();
//        Criteria criteria2 = new Criteria();
//
//        criteriasList.add( Criteria.where("address.area").is("2") );
//        criteriasList.add(Criteria.where("address.area").is("13") );
//
//        Criteria[] criterias = criteriasList.toArray( new Criteria[criteriasList.size()] );
//        criteria2.orOperator(criterias);
//        query.addCriteria(criteria2);

        /* ToDo it is for old impl of search, where we could find offer with area, city and country */
        if (offerFO.getAddresses() != null) {
            List<Criteria> criteriasAddresses = new ArrayList<>();
            Criteria criteriaAddresses = new Criteria();

            for (Address address : offerFO.getAddresses()){
//                if (address.getCountry() != null) {
//                     criteriasAddresses.add( Criteria.where("address.country").is(address.getCountry()) );
//                }

                if (address.getCity() != null) {
                    criteriasAddresses.add( Criteria.where("address.city").is(address.getCity()) );
                }

                if (address.getArea() != null) {
                    criteriasAddresses.add( Criteria.where("address.area").is(address.getArea()) );
                }
            }

            Criteria[] criterias = criteriasAddresses.toArray( new Criteria[criteriasAddresses.size()] );
            criteriaAddresses.orOperator(criterias);
            query.addCriteria(criteriaAddresses);
        } else if (offerFO.getAddress() != null) {
//            if (offerFO.getAddress().getCountry() != null) {
//                query.addCriteria(Criteria.where("address.country").is(offerFO.getAddress().getCountry()));
//            }

            if (offerFO.getAddress().getCity() != null) {
                query.addCriteria(Criteria.where("address.city").is(offerFO.getAddress().getCity()));
            }

            if (offerFO.getAddress().getArea() != null) {
                query.addCriteria(Criteria.where("address.area").is(offerFO.getAddress().getArea()));
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

        if (offerFO.getProperties() != null) {
            query.addCriteria(Criteria.where("properties").all(offerFO.getProperties()));
        }

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
                    dbObject.put("value", comparison);

                    DBObject elementQuery = new BasicDBObject();
                    elementQuery.put("$elemMatch", dbObject);
                    dbObjects.add(elementQuery);
                }
            });

            Criteria criteria = Criteria.where("properties").all(dbObjects.toArray(new DBObject[dbObjects.size()]));
            query.addCriteria(criteria);
        }


        if (offerFO.isPriceWithVat()){
            query.addCriteria(Criteria.where("priceWithVat").is(true));
        }


//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //TODO: to the currency conver..:
//        System.out.println(offerFO);  // USD, EUR, UAH  // fromPrice=733100, toPrice=5388300, currency=USD
        try {
            List<Criteria> criteriaList = new ArrayList<>();
            Criteria  currencyCriterias = new Criteria();

            if (offerFO.getCurrency().equals(Currency.USD)) {
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.USD, Currency.USD))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.USD, Currency.USD)));
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.USD, Currency.EUR))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.USD, Currency.EUR)));
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.USD, Currency.UAH))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.USD, Currency.UAH)));
            } else if (offerFO.getCurrency().equals(Currency.EUR)) {
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.EUR, Currency.USD))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.EUR, Currency.USD)));
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.EUR, Currency.EUR))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.EUR, Currency.EUR)));
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.EUR, Currency.UAH))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.EUR, Currency.UAH)));
            } else if (offerFO.getCurrency().equals(Currency.UAH)) {
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.UAH, Currency.USD))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.UAH, Currency.USD)));
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.UAH, Currency.EUR))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.UAH, Currency.EUR)));
                criteriaList.add( Criteria.where("price")
                        .gte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getFromPrice())), Currency.UAH, Currency.UAH))
                        .lte(CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal(String.valueOf(offerFO.getToPrice())), Currency.UAH, Currency.UAH)));
            }

            criteriaList.add(Criteria.where("currency").is(offerFO.getCurrency()));

            Criteria[] criterias = criteriaList.toArray( new Criteria[criteriaList.size()] );
            currencyCriterias.orOperator(criterias);
            query.addCriteria(currencyCriterias);
        } catch (ServiceException | StorageException | CurrencyNotSupportedException | EndpointException | JSONException e){
            e.getStackTrace();
        }
//        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        if (offerFO.getFromPrice() != null && offerFO.getToPrice() != null) {
            query.addCriteria(Criteria.where("price").gte(offerFO.getFromPrice()).lte(offerFO.getToPrice()));
        } else if (offerFO.getFromPrice() != null) {
            query.addCriteria(Criteria.where("price").gte(offerFO.getFromPrice()));
        } else if (offerFO.getToPrice() != null) {
            query.addCriteria(Criteria.where("price").lte(offerFO.getToPrice()));
        }

        if (offerFO.getCurrency() != null) {
            query.addCriteria(Criteria.where("currency").is(offerFO.getCurrency()));
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


    //ToDo delete this bulshit in the future
    @Override
    public void setMongoTemplateInstanceForTests(MongoTemplate mongoTemplateInstanceForTests) {
        this.mongoTemplate = mongoTemplateInstanceForTests;
        MongoTemplateOperations.setStaticMongoTemplate(mongoTemplateInstanceForTests);
    }
}
