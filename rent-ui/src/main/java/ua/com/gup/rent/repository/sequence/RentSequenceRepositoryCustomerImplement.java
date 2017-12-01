package ua.com.gup.rent.repository.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;


@Repository
public class RentSequenceRepositoryCustomerImplement implements RentSequenceRepositoryCustomer {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(ua.com.gup.rent.model.mongo.sequence.RentSequence.class)) {
            mongoTemplate.createCollection(ua.com.gup.rent.model.mongo.sequence.RentSequence.class);
        }
    }

    @Override
    public long getNextSequenceValue(String sequenceId) {
        //get rentSequence id
        Query query = new Query(Criteria.where("_id").is(sequenceId));

        //increase rentSequence id by 1
        Update update = new Update();
        update.inc("value", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //this is the magic happened.
        ua.com.gup.rent.model.mongo.sequence.RentSequence rentSequence =  mongoTemplate.findAndModify(query, update, options, ua.com.gup.rent.model.mongo.sequence.RentSequence.class);

        //if no rentSequence, throws RuntimeException
        //optional, just a way to tell user when the rentSequence is failed to generate.
        if (rentSequence == null) {
            throw new RuntimeException("Unable to get rentSequence value for id : " + sequenceId);
        }

        return rentSequence.getValue();
    }
}