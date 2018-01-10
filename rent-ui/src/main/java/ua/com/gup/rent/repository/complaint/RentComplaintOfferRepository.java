package ua.com.gup.rent.repository.complaint;


import ua.com.gup.rent.model.mongo.complaint.RentComplaintOffer;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOfferStatus;

import java.util.List;


public interface RentComplaintOfferRepository {

    void save(RentComplaintOffer complaintOffer);

    void update(RentComplaintOffer complaintOffer);

    RentComplaintOffer findById(String id);

    boolean exists(String id);

    List<RentComplaintOffer> findAll();

    List<RentComplaintOffer> findAllByOfferId(String offerId);

    List<RentComplaintOffer> findAllByInitiatorId(String initiatorId);

    List<RentComplaintOffer> findAllByStatus(RentComplaintOfferStatus status);

}
