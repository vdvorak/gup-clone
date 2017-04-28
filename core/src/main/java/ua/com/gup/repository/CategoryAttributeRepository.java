package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.category.CategoryAttribute;

/**
 * Repository for the category attribute entity.
 */
@SuppressWarnings("unused")
public interface CategoryAttributeRepository extends MongoRepository<CategoryAttribute, String> {
}
