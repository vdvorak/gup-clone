package ua.com.gup.service;


import ua.com.gup.domain.offer.model.OfferCategory;
import ua.com.gup.domain.Category;
import ua.com.gup.dto.category.CategoryCreateDTO;
import ua.com.gup.dto.category.CategoryUpdateDTO;
import ua.com.gup.dto.category.tree.CategoryTreeDTO;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Category.
 */
public interface CategoryService {
    /**
     * Save a category.
     *
     * @param categoryCreateDTO the entity to save
     * @return the persisted entity
     */
    Category save(CategoryCreateDTO categoryCreateDTO);

    /**
     * Save a category.
     *
     * @param categoryUpdateDTO the entity to save
     * @return the persisted entity
     */
    Category save(CategoryUpdateDTO categoryUpdateDTO);

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
    Collection<CategoryTreeDTO> findAllTreeView(String lang);

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Category findOne(String id);

    /**
     * Get the "code" category.
     *
     * @param code the id of the entity
     * @return the entity
     */
    Optional<Category> findOneByCode(int code);


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
