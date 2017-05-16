package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Category;

/**
 * Repository for the category entity.
 */
@SuppressWarnings("unused")
public interface CategoryRepository extends MongoRepository<Category, String> {
}
