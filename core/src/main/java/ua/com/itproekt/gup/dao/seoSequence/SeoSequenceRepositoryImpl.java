package ua.com.itproekt.gup.dao.seoSequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.exception.SequenceException;
import ua.com.itproekt.gup.model.seosequence.SeoSequence;

@Repository
public class SeoSequenceRepositoryImpl implements SeoSequenceRepository {

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

        SeoSequence seoSequence = mongoTemplate.findAndModify(query, update, options, SeoSequence.class);

        if (seoSequence == null) {
            throw new SequenceException("Unable to get SEO sequence id for key : " + key);
        }

        return seoSequence.getSeoKey();
    }
}
