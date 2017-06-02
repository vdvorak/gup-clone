package ua.com.gup.repository;


import ua.com.gup.domain.complaint.ComplaintOffer;
import ua.com.gup.domain.complaint.ComplaintOfferStatus;
import ua.com.gup.domain.complaint.ComplaintOfferType;

import java.util.List;

/**
 * Repository for the complaint entity.
 */
public interface ComplaintOfferRepository {

    void save(ComplaintOffer complaintOffer);

    void update(ComplaintOffer complaintOffer);

    ComplaintOffer findById(String id);

    boolean exists(String id);

    List<ComplaintOffer> findAll();

    List<ComplaintOffer> findAllByOfferId(String offerId);

    List<ComplaintOffer> findAllByInitiatorId(String initiatorId);

    List<ComplaintOffer> findAllByStatus(ComplaintOfferStatus status);

}
