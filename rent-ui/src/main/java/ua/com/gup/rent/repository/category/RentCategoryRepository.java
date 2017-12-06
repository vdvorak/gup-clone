package ua.com.gup.rent.repository.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.category.RentCategory;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the category entity.
 */
public interface RentCategoryRepository extends MongoRepository<RentCategory, String> {
    Optional<RentCategory> findOneByCode(Integer code);

    Optional<List<RentCategory>> findByCodeInOrderByCodeAsc(List<Integer> codes);
}