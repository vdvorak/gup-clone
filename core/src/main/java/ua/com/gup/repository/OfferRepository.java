package ua.com.gup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Offer;
import ua.com.gup.repository.custom.OfferRepositoryCustom;

import java.util.Optional;

/**
 * Repository for the offer entity.
 */
@SuppressWarnings("unused")
public interface OfferRepository extends MongoRepository<Offer, String>, OfferRepositoryCustom {

    Optional<Offer> findOneBySeoUrl(String seoUrl);

}
