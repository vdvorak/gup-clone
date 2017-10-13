package ua.com.gup.repository.complaint;


import ua.com.gup.mongo.composition.domain.complaint.ComplaintOffer;
import ua.com.gup.mongo.model.enumeration.ComplaintOfferStatus;

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
