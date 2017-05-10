package ua.com.gup.service;


import ua.com.gup.domain.OfferCategory;
import ua.com.gup.domain.category.Category;
import ua.com.gup.service.dto.CategoryTreeDTO;

import java.util.Collection;
import java.util.LinkedList;
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
     * Get all the categories.
     *
     * @return the list of entities
     */
    List<Category> findAll();

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    Collection<CategoryTreeDTO> findAllTreeView();

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Category findOne(String id);


    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Get offer categories from cache.
     *
     * @param code the code of the entity
     */
    LinkedList<OfferCategory> getOfferCategories(int code);

    /**
     * Exists in cache.
     *
     * @param code the code of the entity
     */
    boolean exists(int code);
}
