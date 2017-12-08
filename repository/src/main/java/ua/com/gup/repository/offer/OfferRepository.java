package ua.com.gup.repository.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.mongo.composition.domain.offer.Offer;

import java.util.Optional;

/**
 * Repository for the offer entity.
 */
public interface OfferRepository extends MongoRepository<Offer, String> {

    Optional<Offer> findOneBySeoUrl(String seoUrl);

    Page<Offer> findAllByStatusAndAuthorId(CommonStatus status, String authorId, Pageable pageable);

    Page<Offer> findAllByStatus(CommonStatus status, Pageable pageable);

    Optional<Offer> findOfferByIdAndAuthorId(String offerId, String authorId);
    
    Boolean existsByIdAndStatus(String id, CommonStatus status);

}
