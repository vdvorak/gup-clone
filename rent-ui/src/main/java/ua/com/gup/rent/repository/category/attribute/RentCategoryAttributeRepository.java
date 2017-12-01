package ua.com.gup.rent.repository.category.attribute;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository for the category attribute entity.
 */

public interface RentCategoryAttributeRepository extends MongoRepository<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute, String>, ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute, String> {

    Optional<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> findOneByCode(int code);

}
