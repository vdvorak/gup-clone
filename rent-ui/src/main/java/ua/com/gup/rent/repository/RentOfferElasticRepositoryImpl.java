package ua.com.gup.rent.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ua.com.gup.rent.model.mongo.rent.ElasticRentOffer;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

@Repository
public class RentOfferElasticRepositoryImpl
        extends RentOfferGenericRepositoryImpl<ElasticRentOffer, String>
        implements RentOfferElasticRepository {


    public RentOfferElasticRepositoryImpl() {
        super(ElasticRentOffer.class);
    }

    @Override
    public void updateByRentOffer(RentOffer rentOffer) {
        Query find = new Query(Criteria.where("rentOfferId").is(rentOffer.getId()));
        Update update = new Update();
        update.set("title", rentOffer.getTitle());
        update.set("description", StringUtils.isEmpty(rentOffer.getDescription()) ? "" : rentOffer.getTitle());
        update.set("price", rentOffer.getPrice());
        update.set("seoUrl", rentOffer.getSeoUrl());
        update.set("lastModifiedDate", rentOffer.getLastModifiedDate());
        update.set("address", rentOffer.getAddress());
        update.set("status", rentOffer.getStatus());
        update.set("categories", rentOffer.getCategories());
        update.set("attrs", rentOffer.getAttrs());
        update.set("multiAttrs", rentOffer.getMultiAttrs());
        update.set("numAttrs", rentOffer.getNumAttrs());
        update.set("boolAttrs", rentOffer.getBoolAttrs());
        mongoTemplate.updateMulti(find, update, ElasticRentOffer.class);
    }
}
