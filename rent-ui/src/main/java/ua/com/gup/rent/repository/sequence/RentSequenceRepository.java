package ua.com.gup.rent.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.sequence.RentSequence;

/**
 * Repository for the sequence entity.
 */

public interface RentSequenceRepository extends MongoRepository<RentSequence, String> {


}
