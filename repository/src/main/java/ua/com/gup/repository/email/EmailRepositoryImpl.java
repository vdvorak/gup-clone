package ua.com.gup.repository.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.mongo.composition.domain.email.EmailMessage;

import javax.annotation.PostConstruct;
import java.util.Date;

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

    private EmailMessage findById(EmailMessage message) {
        return mongoTemplate.findById(message.getId(), EmailMessage.class);
    }

    @Override
    public void removeMessage(EmailMessage message) {
        mongoTemplate.remove(message);
    }

    @Override
    public EmailMessage updateLastAttemptTimestamp(EmailMessage message) {
        Update update = new Update();
        update.set("lastAttemptTimestamp", new Date().getTime());
        Query query = new Query(Criteria.where("_id").is(message.getId()));
        return mongoTemplate.findAndModify(query, update, EmailMessage.class);
    }
}
