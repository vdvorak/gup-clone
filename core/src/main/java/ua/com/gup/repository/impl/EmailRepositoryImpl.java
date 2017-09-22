package ua.com.gup.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.model.email.EmailMessage;
import ua.com.gup.repository.EmailRepository;

import javax.annotation.PostConstruct;

@Repository
public class EmailRepositoryImpl implements EmailRepository {


    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(EmailMessage.class)) {
            mongoTemplate.createCollection(EmailMessage.class);
        }
    }

    @Override
    public void createMessage(EmailMessage message) {
        mongoTemplate.insert(message);
    }

    @Override
    public EmailMessage findOneMessage() {
        Query query = new Query();
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC, "lastAttemptTimestamp"));
        return mongoTemplate.findOne(query, EmailMessage.class);
    }

    @Override
    public void removeMessage(EmailMessage message) {
        mongoTemplate.remove(message);
    }
}
