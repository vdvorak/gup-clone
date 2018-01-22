package ua.com.gup.common.repository;


import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.complaint.CommonComplaint;
import ua.com.gup.common.model.complaint.ComplaintFilter;
import ua.com.gup.common.model.complaint.ComplaintOfferStatus;

import java.util.List;

/**
 * Repository for the complaint entity.
 */
public interface CommonComplaintOfferRepository<T  extends CommonComplaint> {

    void save(T complaintOffer);

    void update(T complaintOffer);

    T findById(String id);

    boolean exists(String id);

    List<T> findAll();

    List<T> findAllByOfferId(String offerId);

    List<T> findAllByInitiatorId(String initiatorId);

    boolean isComplaintAvailableByOfferIdAndUserPublicId(String offerId, String userPublicId);

    List<T> findAllByStatus(ComplaintOfferStatus status);

    long countFilter(ComplaintFilter filter);

    List<T> findFilter(ComplaintFilter filter, Pageable pageable);
}
