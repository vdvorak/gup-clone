package ua.com.gup.service.seosequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.exception.PublicProfileSequenceException;
import ua.com.gup.model.seosequence.PublicProfileSequence;

@Repository
public class PublicProfileSequenceImpl implements PublicProfileSequenceService {

    @Autowired
    private MongoOperations mongoOperations;

    public Long getNextSequenceId(String key) {
        Query query = new Query(Criteria.where("id").is(key));

        Update update = new Update();
        update.inc("sequence", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        PublicProfileSequence sequence = mongoOperations.findAndModify(query, update, options, PublicProfileSequence.class);

        if(sequence == null) throw new PublicProfileSequenceException("Unable to get sequence for key: " + key);

        return sequence.getSequence();
    }

}
