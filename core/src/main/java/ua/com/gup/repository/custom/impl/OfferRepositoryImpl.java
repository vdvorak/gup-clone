package ua.com.gup.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import ua.com.gup.domain.Offer;
import ua.com.gup.repository.custom.OfferRepositoryCustom;

import java.util.List;

public class OfferRepositoryImpl implements OfferRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public List<Offer> search(Aggregation aggregation) {
        return mongoOperation.aggregate(aggregation, Offer.class, Offer.class).getMappedResults();
    }
}
