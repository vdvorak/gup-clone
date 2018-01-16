package ua.com.gup.rent.service.rent;


import ua.com.gup.rent.model.mongo.rent.RentOffer;

public interface RentOfferElasticService {

    void asyncSaveRentOfferElastic(RentOffer rentOffer);
}
