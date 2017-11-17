package ua.com.gup.rent.repository.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.sequence.Sequence;

import javax.annotation.PostConstruct;


@Repository
public class SequenceRepositoryCustomerImplement implements SequenceRepositoryCustomer {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Sequence.class)) {
            mongoTemplate.createCollection(Sequence.class);
        }
    }

    @Override
    public long getNextSequenceValue(String sequenceId) {
        //get sequence id
        Query query = new Query(Criteria.where("_id").is(sequenceId));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("value", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //this is the magic happened.
        Sequence sequence =  mongoTemplate.findAndModify(query, update, options, Sequence.class);

        //if no sequence, throws RuntimeException
        //optional, just a way to tell user when the sequence is failed to generate.
        if (sequence == null) {
            throw new RuntimeException("Unable to get sequence value for id : " + sequenceId);
        }

        return sequence.getValue();
    }
}