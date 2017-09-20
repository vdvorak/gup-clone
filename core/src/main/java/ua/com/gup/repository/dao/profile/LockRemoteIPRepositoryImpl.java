package ua.com.gup.repository.dao.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

import ua.com.gup.model.profiles.LockRemoteIP;


@Repository
public class LockRemoteIPRepositoryImpl implements LockRemoteIPRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(LockRemoteIPRepository.class)) {
            mongoTemplate.createCollection(LockRemoteIPRepository.class);
        }
    }

    @Override
    public void createLockRemoteIP(LockRemoteIP lockRemoteIP) {
        mongoTemplate.insert(lockRemoteIP);
    }

    @Override
    public LockRemoteIP findLockRemoteIPById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, LockRemoteIP.class);
    }

    @Override
    public LockRemoteIP findLockRemoteIPByIp(String ip) {
        Query query = new Query(Criteria.where("ip").is(ip));
        return mongoTemplate.findOne(query, LockRemoteIP.class);
    }

    @Override
    public boolean lockRemoteIPExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, LockRemoteIP.class);
    }


    @Override
    public void updateLockRemoteIP(LockRemoteIP lockRemoteIP) {
        mongoTemplate.save(lockRemoteIP);
    }
}
