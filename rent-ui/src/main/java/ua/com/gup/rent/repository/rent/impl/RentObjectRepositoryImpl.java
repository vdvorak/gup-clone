package ua.com.gup.rent.repository.rent.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.rent.RentObject;
import ua.com.gup.rent.repository.rent.RentObjectRepository;

import javax.annotation.PostConstruct;

@Repository
public class RentObjectRepositoryImpl extends ua.com.gup.rent.repository.abstracted.RentGenericRepositoryImpl<ua.com.gup.rent.model.mongo.rent.RentObject, String> implements RentObjectRepository {

    public RentObjectRepositoryImpl() {
        super(RentObject.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentObject.class)) {
            mongoTemplate.createCollection(RentObject.class);
        }
    }

    @Override
    public void deleteById(String rentObjectId) {
        Query deleteQuery = new Query(Criteria.where("id").is(rentObjectId));
        mongoTemplate.remove(deleteQuery, RentObject.class);
    }

    @Override
    public boolean isOwner(String rentObjectId, String userId) {
        Query exists = new Query(Criteria.where("id").is(rentObjectId).and("ownerId").is(userId));
        return mongoTemplate.exists(exists, RentObject.class);
    }
}
