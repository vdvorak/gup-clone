package ua.com.itproekt.gup.dao.offers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.offer.Rent2;

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
import ua.com.itproekt.gup.util.OfferUserContactInfo;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

@Repository
public class Rent2RepositoryImpl implements Rent2Repository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Rent2.class)) {
            mongoTemplate.createCollection(Rent2.class);
        }
    }

    @Override
    public void create(Rent2 rent) {
        mongoTemplate.insert(rent);
    }

    @Override
    public void update(Rent2 rent) {
        mongoTemplate.save(rent);
    }

    @Override
    public boolean exist(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Rent2.class);
    }

    @Override
    public Rent2 findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Rent2.class);
    }

    @Override
    public Rent2 findByOfferId(String offerId) {
        Query query = new Query(Criteria.where("offerId").is(offerId));
        return mongoTemplate.findOne(query, Rent2.class);
    }

    @Override
    public int delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, Rent2.class);
        return result.getN();
    }
}
