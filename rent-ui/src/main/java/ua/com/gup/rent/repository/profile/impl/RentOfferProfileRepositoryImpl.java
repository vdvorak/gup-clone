package ua.com.gup.rent.repository.profile.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;

import javax.annotation.PostConstruct;

@Repository
public class RentOfferProfileRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferProfile, String> implements RentOfferProfileRepository {

    public RentOfferProfileRepositoryImpl() {
        super(RentOfferProfile.class);
    }


    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferProfile.class)) {
            mongoTemplate.createCollection(RentOfferProfile.class);
        }
    }


    @Override
    public RentOfferProfile findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findByPublicId(String id) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }


    public void save(RentOfferProfile profile) {
        mongoTemplate.save(profile);
    }
}
