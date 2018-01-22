package ua.com.gup.repository.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.mongo.composition.domain.email.EmailMessage;
import ua.com.gup.mongo.model.offer.EmailStatus;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

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
    public List<EmailMessage> findAndModifyMessages(EmailStatus oldStatus, EmailStatus newStatus, int limit) {
        Query query = new Query(Criteria.where("status").is(oldStatus));
        query.limit(limit).with(new Sort(Sort.Direction.DESC, "lastAttemptTimestamp"));
        Update update = new Update();
        update.set("status", newStatus);
        return mongoTemplate.findAndModify(query, update, List.class);
    }

    @Override
    public void removeMessage(EmailMessage message) {
        mongoTemplate.remove(message);
    }

    @Override
    public EmailMessage updateStatusAndLastAttemptTimestamp(EmailMessage message) {
        Update update = new Update();
        update.set("lastAttemptTimestamp", new Date().getTime());
        Query query = new Query(Criteria.where("_id").is(message.getId()));
        return mongoTemplate.findAndModify(query, update, EmailMessage.class);
    }

    @Override
    public void save(EmailMessage message) {
        mongoTemplate.save(message);
    }
}
