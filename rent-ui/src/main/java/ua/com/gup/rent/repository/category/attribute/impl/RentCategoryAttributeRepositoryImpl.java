package ua.com.gup.rent.repository.category.attribute.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute;
import ua.com.gup.rent.repository.abstracted.RentGenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentCategoryAttributeRepositoryImpl extends RentGenericRepositoryImpl<RentCategoryAttribute,String> {

    public RentCategoryAttributeRepositoryImpl() {
        super(RentCategoryAttribute.class);
    }
    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentCategoryAttribute.class)) {
            mongoTemplate.createCollection(RentCategoryAttribute.class);
        }
    }
}
