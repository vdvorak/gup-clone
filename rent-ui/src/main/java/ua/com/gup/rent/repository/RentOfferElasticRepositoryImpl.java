package ua.com.gup.rent.repository;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.rent.ElasticRentOffer;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

@Repository
public class RentOfferElasticRepositoryImpl
        extends RentOfferGenericRepositoryImpl<ElasticRentOffer, String>
        implements RentOfferElasticRepository {


    public RentOfferElasticRepositoryImpl() {
        super(ElasticRentOffer.class);
    }
}
