package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.Sequence;
import ua.com.gup.repository.custom.SequenceRepositoryCustom;

/**
 * Repository for the sequence entity.
 */
@SuppressWarnings("unused")
public interface SequenceRepository  extends MongoRepository<Sequence,String>, SequenceRepositoryCustom{


}
