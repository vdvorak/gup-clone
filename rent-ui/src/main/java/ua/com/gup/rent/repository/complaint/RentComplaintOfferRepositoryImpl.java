package ua.com.gup.rent.repository.complaint;


import org.springframework.stereotype.Repository;
import ua.com.gup.common.repository.impl.CommonComplaintOfferRepositoryImpl;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOffer;

@Repository
public class RentComplaintOfferRepositoryImpl extends CommonComplaintOfferRepositoryImpl<RentComplaintOffer> implements RentComplaintOfferRepository {

    protected RentComplaintOfferRepositoryImpl() {
        super(RentComplaintOffer.class);
    }
}
