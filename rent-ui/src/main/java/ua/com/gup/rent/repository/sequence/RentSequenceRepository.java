package ua.com.gup.rent.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.sequence.RentOfferSequence;

/**
 * Repository for the sequence entity.
 */

public interface RentSequenceRepository extends MongoRepository<RentOfferSequence, String> {


}
