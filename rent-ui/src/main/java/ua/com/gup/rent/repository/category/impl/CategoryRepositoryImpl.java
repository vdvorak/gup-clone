package ua.com.gup.rent.repository.category.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.category.Category;
import ua.com.gup.rent.repository.abstracted.GenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class CategoryRepositoryImpl extends GenericRepositoryImpl<Category,String> {

    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Category.class)) {
            mongoTemplate.createCollection(Category.class);
        }
    }
}
