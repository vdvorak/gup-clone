package ua.com.gup.rent.repository.rent.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.common.repository.impl.CommonOfferRepositoryImpl;
import ua.com.gup.rent.model.mongo.rent.RentOffer;

@Repository
public class RentCommonOfferRepositoryImpl extends CommonOfferRepositoryImpl<RentOffer> {

    public RentCommonOfferRepositoryImpl() {
        super(RentOffer.class);
    }
}
