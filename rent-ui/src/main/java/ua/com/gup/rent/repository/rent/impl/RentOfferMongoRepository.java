package ua.com.gup.rent.repository.rent.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.repository.CommonOfferMongoRepository;
import ua.com.gup.rent.model.mongo.rent.RentOffer;

import java.util.Optional;

/**
 * Repository for the offer entity.
 */
public interface RentOfferMongoRepository extends CommonOfferMongoRepository<RentOffer>{
}
