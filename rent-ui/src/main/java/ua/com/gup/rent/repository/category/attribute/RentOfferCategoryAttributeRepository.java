package ua.com.gup.rent.repository.category.attribute;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.Optional;

/**
 * Repository for the category attribute entity.
 */

public interface RentOfferCategoryAttributeRepository extends MongoRepository<RentOfferCategoryAttribute, String>, RentOfferGenericRepository<RentOfferCategoryAttribute, String> {

    Optional<RentOfferCategoryAttribute> findOneByCode(int code);

}
