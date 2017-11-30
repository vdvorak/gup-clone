package ua.com.gup.rent.repository.category.impl;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentCategoryRepositoryImpl extends ua.com.gup.rent.repository.abstracted.RentGenericRepositoryImpl<ua.com.gup.rent.model.mongo.category.RentCategory,String> {

    public RentCategoryRepositoryImpl() {
        super(ua.com.gup.rent.model.mongo.category.RentCategory.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(ua.com.gup.rent.model.mongo.category.RentCategory.class)) {
            mongoTemplate.createCollection(ua.com.gup.rent.model.mongo.category.RentCategory.class);
        }
    }
}
