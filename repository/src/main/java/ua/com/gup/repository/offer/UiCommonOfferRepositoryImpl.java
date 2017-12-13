package ua.com.gup.repository.offer;

import org.springframework.stereotype.Repository;
import ua.com.gup.common.repository.impl.CommonOfferRepositoryImpl;
import ua.com.gup.mongo.composition.domain.offer.Offer;


@Repository
public class UiCommonOfferRepositoryImpl extends CommonOfferRepositoryImpl<Offer> {

    public UiCommonOfferRepositoryImpl() {
        super(Offer.class);
    }
}
