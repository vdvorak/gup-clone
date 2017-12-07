package ua.com.gup.rent.repository.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the category entity.
 */
public interface RentOfferCategoryRepository extends MongoRepository<RentOfferCategory, String> {
    Optional<RentOfferCategory> findOneByCode(Integer code);

    Optional<List<RentOfferCategory>> findByCodeInOrderByCodeAsc(List<Integer> codes);
}