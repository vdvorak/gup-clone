package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.CategoryAttribute;

import java.util.Optional;

/**
 * Repository for the category attribute entity.
 */
@SuppressWarnings("unused")
public interface CategoryAttributeRepository extends MongoRepository<CategoryAttribute, String> {

    Optional<CategoryAttribute> findOneByCode(int code);

}
