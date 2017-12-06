package ua.com.gup.rent.repository.sequence.seo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.sequence.seo.RentOfferSeoSequence;
import ua.com.gup.rent.repository.exception.SequenceException;

@Repository
public class RentSeoSequenceRepositoryImp implements RentSeoSequenceRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long getNextSequenceId(String key) {
        //get sequence id
        Query query = new Query(Criteria.where("_id").is(key));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        RentOfferSeoSequence rentOfferSeoSequence = mongoTemplate.findAndModify(query, update, options, RentOfferSeoSequence.class);

        if (rentOfferSeoSequence == null) {
            throw new SequenceException("Unable to get SEO sequence id for key : " + key);
        }

        return rentOfferSeoSequence.getSeoKey();
    }
}
