package ua.com.gup.repository.dao.seoSequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Sequence;

/**
 * Repository for the sequence entity.
 */

public interface SequenceRepository extends MongoRepository<Sequence, String>, SequenceRepositoryCustom {


}
