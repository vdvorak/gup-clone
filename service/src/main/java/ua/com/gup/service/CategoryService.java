package ua.com.gup.service;


import ua.com.gup.domain.category.Category;

import java.util.List;

/**
 * Service Interface for managing Category.
 */
public interface CategoryService {
    /**
     * Save a category.
     *
     * @param category the entity to save
     * @return the persisted entity
     */
    Category save(Category category);
    
    /**
     *  Get all the categories.
     *
     *  @return the list of entities
     */
    List<Category> findAll();

    /**
     *  Get the "id" category.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Category findOne(String id);
    

    /**
     *  Delete the "id" category.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
