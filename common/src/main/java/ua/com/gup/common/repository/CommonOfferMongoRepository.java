package ua.com.gup.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.mongo.CommonRentOffer;

import java.util.Collection;
import java.util.Optional;

public interface CommonOfferMongoRepository<T extends CommonRentOffer> extends MongoRepository<T, String> {

    Optional<T> findOneBySeoUrl(String seoUrl);

    Page<T> findAllByStatusAndAuthorId(CommonStatus status, String authorId, Pageable pageable);

    Page<T> findAllByStatus(CommonStatus status, Pageable pageable);

    Optional<T> findOfferByIdAndAuthorId(String offerId, String authorId);

    Page<T> findAllByAuthorId(String authorId, Pageable pageable);

    Boolean existsByIdAndStatus(String id, CommonStatus status);

    Boolean existsByIdAndAuthorId(String offerId, Collection<String> authorIds);
}
