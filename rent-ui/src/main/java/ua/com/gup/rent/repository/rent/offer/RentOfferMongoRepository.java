package ua.com.gup.rent.repository.rent.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.model.mongo.rent.RentOffer;

import java.util.Optional;

/**
 * Repository for the offer entity.
 */
public interface RentOfferMongoRepository extends MongoRepository<RentOffer, String> {

    Optional<RentOffer> findOneBySeoUrl(String seoUrl);

    Page<RentOffer> findAllByStatusAndAuthorId(RentOfferStatus status, String authorId, Pageable pageable);

    Page<RentOffer> findAllByStatus(RentOfferStatus status, Pageable pageable);

    Optional<RentOffer> findOfferByIdAndAuthorId(String offerId, String authorId);
    
    Boolean existsByIdAndStatus(String id, RentOfferStatus status);

}
