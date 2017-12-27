package ua.com.gup.rent.repository.rent.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.enumeration.CommonCurrency;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.filter.CommonAddressFilter;
import ua.com.gup.common.model.filter.CommonAttributeFilter;
import ua.com.gup.common.model.filter.CommonCoordinatesFilter;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.common.repository.impl.CommonOfferRepositoryImpl;
import ua.com.gup.rent.filter.*;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.repository.rent.RentOfferRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RentOfferRepositoryImpl
        extends CommonOfferRepositoryImpl<RentOffer,RentOfferFilter>
        implements RentOfferRepository, CommonOfferRepository<RentOffer,RentOfferFilter> {


    private final Logger log = LoggerFactory.getLogger(RentOfferRepositoryImpl.class);
    private final Integer COORDINATES_MAX_DIFF_LAT = 6;
    private final Integer COORDINATES_MAX_DIFF_LON = 3;

    @Autowired
    private MongoTemplate mongoTemplate;

    public RentOfferRepositoryImpl() {
        super(RentOffer.class);
    }

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
    public void updateBasePriceByExchangeRate(CommonStatus status, CommonCurrency currency, CommonCurrency baseCurrency, double exchangeRate) {
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




}
