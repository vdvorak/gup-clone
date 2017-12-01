package ua.com.gup.rent.repository.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the category entity.
 */
public interface RentCategoryRepository extends MongoRepository<ua.com.gup.rent.model.mongo.category.RentCategory, String> {
    Optional<ua.com.gup.rent.model.mongo.category.RentCategory> findOneByCode(Integer code);

    Optional<List<ua.com.gup.rent.model.mongo.category.RentCategory>> findByCodeInOrderByCodeAsc(List<Integer> codes);
}
