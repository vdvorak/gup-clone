package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Offer;
import ua.com.gup.repository.custom.OfferRepositoryCustom;

/**
 * Spring Data MongoDB repository for the Offer entity.
 */
@SuppressWarnings("unused")
public interface OfferRepository extends MongoRepository<Offer,String> {

}
