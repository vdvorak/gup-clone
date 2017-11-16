package ua.com.gup.rent.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.sequence.Sequence;

/**
 * Repository for the sequence entity.
 */

public interface SequenceRepository extends MongoRepository<Sequence, String> {


}
