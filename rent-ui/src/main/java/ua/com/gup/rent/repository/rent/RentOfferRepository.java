package ua.com.gup.rent.repository.rent;

import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

public interface RentOfferRepository extends RentOfferGenericRepository<RentOffer, String> {
    void deleteById(String rentObjectId);

    boolean isOwner(String rentObjectId, String userId);
}
