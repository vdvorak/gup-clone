package ua.com.gup.rent.repository.category.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.category.RentCategory;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentCategoryRepositoryImpl extends ua.com.gup.rent.repository.abstracted.RentGenericRepositoryImpl<RentCategory,String> {

    public RentCategoryRepositoryImpl() {
        super(RentCategory.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentCategory.class)) {
            mongoTemplate.createCollection(RentCategory.class);
        }
    }
}
