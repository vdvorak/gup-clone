package ua.com.gup.rent.repository.category.attribute.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentOfferCategoryAttributeRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferCategoryAttribute,String> {

    public RentOfferCategoryAttributeRepositoryImpl() {
        super(RentOfferCategoryAttribute.class);
    }
    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferCategoryAttribute.class)) {
            mongoTemplate.createCollection(RentOfferCategoryAttribute.class);
        }
    }
}
