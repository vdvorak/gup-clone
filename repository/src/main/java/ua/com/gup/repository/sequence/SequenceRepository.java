package ua.com.gup.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.sequence.Sequence;

/**
 * Repository for the sequence entity.
 */

public interface SequenceRepository extends MongoRepository<Sequence, String> {


}
