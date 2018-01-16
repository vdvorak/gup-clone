package ua.com.gup.rent.repository;

import ua.com.gup.rent.model.mongo.rent.ElasticRentOffer;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

public interface RentOfferElasticRepository extends RentOfferGenericRepository<ElasticRentOffer, String> {
}
