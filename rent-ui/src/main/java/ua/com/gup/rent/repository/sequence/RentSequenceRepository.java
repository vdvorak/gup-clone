package ua.com.gup.rent.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for the sequence entity.
 */

public interface RentSequenceRepository extends MongoRepository<ua.com.gup.rent.model.mongo.sequence.RentSequence, String> {


}
