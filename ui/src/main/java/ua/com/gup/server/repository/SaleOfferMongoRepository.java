package ua.com.gup.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.common.repository.CommonOfferMongoRepository;
import ua.com.gup.mongo.composition.domain.offer.Offer;

public interface SaleOfferMongoRepository
        extends CommonOfferMongoRepository<Offer>{
}
