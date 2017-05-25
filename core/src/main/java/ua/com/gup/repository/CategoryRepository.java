package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Category;

import java.util.Optional;

/**
 * Repository for the category entity.
 */
@SuppressWarnings("unused")
public interface CategoryRepository extends MongoRepository<Category, String> {

    Optional<Category> findOneByCode(int code);

}
