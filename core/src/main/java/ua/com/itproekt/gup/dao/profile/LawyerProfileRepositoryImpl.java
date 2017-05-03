package ua.com.itproekt.gup.dao.profile;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class LawyerProfileRepositoryImpl implements LawyerProfileRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Profile.class)) {
            mongoTemplate.createCollection(Profile.class);
        }
    }

    @Override
    public LawyerProfile findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, LawyerProfile.class);
    }

    @Override
    public LawyerProfile findByPublicId(String id) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.findOne(query, LawyerProfile.class);
    }

    @Override
    public void createProfile(LawyerProfile lawyerProfile) {
        //TODO...
    }

    @Override
    public LawyerProfile findProfileAndUpdate(LawyerProfile lawyerProfile) {
        //TODO...
        return null;
    }

    @Override
    public int deleteProfileById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        WriteResult result = mongoTemplate.remove(query, Profile.class);
        return result.getN();
    }

    @Override
    public boolean profileExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profilePublicExists(String id) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public LawyerProfile findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, LawyerProfile.class);
    }

    @Override
    public LawyerProfile findByEmail(String email) {
        Query query = new Query( Criteria.where("email").regex(email.toString(), "i"));
        return mongoTemplate.findOne(query, LawyerProfile.class);
    }

    @Override
    public LawyerProfile findProfileByMainPhone(String mainPhone) {
        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhone));
        return mongoTemplate.findOne(query, LawyerProfile.class);
    }

}
