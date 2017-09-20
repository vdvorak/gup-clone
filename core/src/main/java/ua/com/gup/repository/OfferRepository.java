package ua.com.gup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.repository.custom.OfferRepositoryCustom;

import java.util.Optional;

/**
 * Repository for the offer entity.
 */
public interface OfferRepository extends MongoRepository<Offer, String>, OfferRepositoryCustom {

    Optional<Offer> findOneBySeoUrl(String seoUrl);

    Page<Offer> findAllByStatusAndAuthorId(OfferStatus status, String authorId, Pageable pageable);

    Page<Offer> findAllByStatus(OfferStatus status, Pageable pageable);

}
