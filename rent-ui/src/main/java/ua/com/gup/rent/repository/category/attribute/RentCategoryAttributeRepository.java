package ua.com.gup.rent.repository.category.attribute;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute;
import ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository;

import java.util.Optional;

/**
 * Repository for the category attribute entity.
 */

public interface RentCategoryAttributeRepository extends MongoRepository<RentCategoryAttribute, String>, RentGenericRepository<RentCategoryAttribute, String> {

    Optional<RentCategoryAttribute> findOneByCode(int code);

}
