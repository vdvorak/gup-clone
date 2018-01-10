package ua.com.gup.repository.complaint;


import org.springframework.stereotype.Repository;
import ua.com.gup.common.repository.impl.CommonComplaintOfferRepositoryImpl;
import ua.com.gup.mongo.composition.domain.complaint.ComplaintOffer;

@Repository
public class ComplaintOfferRepositoryImpl
        extends CommonComplaintOfferRepositoryImpl<ComplaintOffer>
        implements ComplaintOfferRepository {


    protected ComplaintOfferRepositoryImpl() {
        super(ComplaintOffer.class);
    }
}
