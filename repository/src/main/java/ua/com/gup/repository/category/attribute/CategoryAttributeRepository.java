package ua.com.gup.repository.category.attribute;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.category.attribute.CategoryAttribute;

import java.util.Optional;

/**
 * Repository for the category attribute entity.
 */

public interface CategoryAttributeRepository extends MongoRepository<CategoryAttribute, String> {

    Optional<CategoryAttribute> findOneByCode(int code);

}
