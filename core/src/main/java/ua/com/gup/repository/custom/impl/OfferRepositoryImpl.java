package ua.com.gup.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import ua.com.gup.domain.Offer;
import ua.com.gup.repository.custom.OfferRepositoryCustom;

import java.util.List;

public class OfferRepositoryImpl implements OfferRepositoryCustom {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public OfferRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<Offer> findAll(Aggregation aggregation) {
        return mongoTemplate.aggregate(aggregation, Offer.class, Offer.class).getMappedResults();
    }
}
