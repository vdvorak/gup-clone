package ua.com.gup.rent.repository.rent.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;
import ua.com.gup.rent.repository.rent.RentOfferRepository;

import javax.annotation.PostConstruct;

@Repository
public class RentOfferRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOffer, String> implements RentOfferRepository {

    public RentOfferRepositoryImpl() {
        super(RentOffer.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOffer.class)) {
            mongoTemplate.createCollection(RentOffer.class);
        }
    }

    @Override
    public void deleteById(String rentObjectId) {
        Query deleteQuery = new Query(Criteria.where("id").is(rentObjectId));
        mongoTemplate.remove(deleteQuery, RentOffer.class);
    }

    @Override
    public boolean isOwner(String rentObjectId, String userId) {
        Query exists = new Query(Criteria.where("id").is(rentObjectId).and("ownerId").is(userId));
        return mongoTemplate.exists(exists, RentOffer.class);
    }
}
