package ua.com.gup.rent.repository.category.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentOfferCategoryRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferCategory,String> {

    public RentOfferCategoryRepositoryImpl() {
        super(RentOfferCategory.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferCategory.class)) {
            mongoTemplate.createCollection(RentOfferCategory.class);
        }
    }
}
