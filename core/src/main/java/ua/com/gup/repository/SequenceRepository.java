package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Sequence;
import ua.com.gup.repository.custom.SequenceRepositoryCustom;

/**
 * Repository for the sequence entity.
 */

public interface SequenceRepository extends MongoRepository<Sequence, String>, SequenceRepositoryCustom {


}
