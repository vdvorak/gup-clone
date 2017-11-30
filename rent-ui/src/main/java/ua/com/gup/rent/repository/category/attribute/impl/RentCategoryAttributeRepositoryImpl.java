package ua.com.gup.rent.repository.category.attribute.impl;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentCategoryAttributeRepositoryImpl extends ua.com.gup.rent.repository.abstracted.RentGenericRepositoryImpl<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute,String> {

    public RentCategoryAttributeRepositoryImpl() {
        super(ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute.class);
    }
    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute.class)) {
            mongoTemplate.createCollection(ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute.class);
        }
    }
}
