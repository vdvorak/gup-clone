package ua.com.gup.rent.service.category;


import ua.com.gup.rent.model.mongo.category.RentCategory;
import ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentCategoryUpdateDTO;
import ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RentCategory.
 */
public interface RentCategoryService {
    /**
     * Save a category.
     *
     * @param rentCategoryCreateDTO the entity to save
     * @return the persisted entity
     */
    RentCategory save(RentCategoryCreateDTO rentCategoryCreateDTO);

    /**
     * Save a category.
     *
     * @param rentCategoryUpdateDTO the entity to save
     * @return the persisted entity
     */
    RentCategory save(RentCategoryUpdateDTO rentCategoryUpdateDTO);

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    List<RentCategory> findAll();

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    Collection<RentCategoryTreeDTO> findAllTreeView(String lang);

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RentCategory findOne(String id);


    Optional<RentCategory> findOneByCode(int code);

    /**
     * Get categories by "codes".
     * Order by key.asc
     *
     * @param codes the codes list of the entity
     * @return the entity
     */

    List<RentCategory> findByCodeInOrderByCodeAsc(List<Integer> codes);


    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Get rent categories from cache.
     *
     * @param code the code of the entity
     */
    LinkedList<RentCategory> getRentCategories(int code);

    /**
     * Get set of ids rent categories from cache.
     *
     * @param code the code of the entity
     */
    List<Integer> getRentCategoriesIds(int code);

    /**
     * Exists in cache.
     *
     * @param code the code of the entity
     */
    boolean exists(int code);
}
