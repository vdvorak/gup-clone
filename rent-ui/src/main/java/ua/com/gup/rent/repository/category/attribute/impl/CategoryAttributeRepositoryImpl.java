package ua.com.gup.rent.repository.category.attribute.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.category.attribute.CategoryAttribute;
import ua.com.gup.rent.repository.abstracted.GenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class CategoryAttributeRepositoryImpl extends GenericRepositoryImpl<CategoryAttribute,String> {

    public CategoryAttributeRepositoryImpl() {
        super(CategoryAttribute.class);
    }
    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(CategoryAttribute.class)) {
            mongoTemplate.createCollection(CategoryAttribute.class);
        }
    }
}
