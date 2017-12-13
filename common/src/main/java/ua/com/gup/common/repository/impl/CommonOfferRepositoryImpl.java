/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.repository.impl;

import com.mongodb.BasicDBObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.repository.CommonOfferRepository;

public abstract class CommonOfferRepositoryImpl<T  extends CommonRentOffer> implements CommonOfferRepository<T> {

    @Autowired
    private MongoTemplate mongoTemplate;
    private final Class clazz;

    public Class getClazz() {
        return clazz;
    }
    

    public CommonOfferRepositoryImpl(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<ImageStorage> findOfferImages(String offerId) {
        Criteria criteria = Criteria.where("_id").is(offerId);
        Query query = new Query(criteria);
        T offer = (T) mongoTemplate.findOne(query, getClazz());
        return offer.getImages();
    }

    @Override
    public ImageStorage findOfferImage(String offerId, String imageId) {

        Criteria criteria = Criteria.where("_id").is(offerId)
                .andOperator(Criteria.where("images._id").is(imageId));
        Query query = new Query(criteria);
        query.fields().exclude("_id").include("images.$");
        T offer = (T) mongoTemplate.findOne(query, getClazz());
        return offer.getImages().get(0);
    }

    @Override
    public Boolean isExistsOfferImage(String offerId, String imageId) {
        Criteria criteria = Criteria.where("_id").is(offerId)
                .andOperator(Criteria.where("images._id").is(imageId));
        Query query = new Query(criteria);
        return mongoTemplate.exists(query, getClazz());

    }

    @Override
    public void deleteOfferImage(ImageStorage image) {
        Query query = Query.query(Criteria
                .where("images")
                .elemMatch(Criteria.where("_id").is(image.getId()))
        );
        Update update = new Update().pull("images", new BasicDBObject("_id", image.getId()));
        mongoTemplate.updateMulti(query, update, getClazz());
    }

    @Override
    public T findOne(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return (T) mongoTemplate.findOne(query, getClazz());
    }

    @Override
    public void save(T offer) {
        mongoTemplate.save(offer);
    }

    @Override
    public boolean exists(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.exists(query, clazz);
    }

}
