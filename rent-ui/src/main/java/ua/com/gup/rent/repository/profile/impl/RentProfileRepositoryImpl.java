package ua.com.gup.rent.repository.profile.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.user.RentProfile;
import ua.com.gup.rent.repository.abstracted.GenericRepositoryImpl;
import ua.com.gup.rent.repository.profile.RentProfileRepository;

import javax.annotation.PostConstruct;

@Repository
public class RentProfileRepositoryImpl extends GenericRepositoryImpl<RentProfile, String> implements RentProfileRepository {

    public RentProfileRepositoryImpl() {
        super(RentProfile.class);
    }


    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentProfile.class)) {
            mongoTemplate.createCollection(RentProfile.class);
        }
    }

    @Override
    public RentProfile loadByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, RentProfile.class);
    }
}
