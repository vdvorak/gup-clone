package ua.com.gup.rent.service.category;


import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryUpdateDTO;
import ua.com.gup.rent.service.dto.category.tree.RentOfferCategoryTreeDTO;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RentOfferCategoryShort.
 */
public interface RentOfferCategoryService {
    /**
     * Save a category.
     *
     * @param rentOfferCategoryCreateDTO the entity to save
     * @return the persisted entity
     */
    RentOfferCategory save(RentOfferCategoryCreateDTO rentOfferCategoryCreateDTO);

    /**
     * Save a category.
     *
     * @param rentOfferCategoryUpdateDTO the entity to save
     * @return the persisted entity
     */
    RentOfferCategory save(RentOfferCategoryUpdateDTO rentOfferCategoryUpdateDTO);

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    List<RentOfferCategory> findAll();

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    Collection<RentOfferCategoryTreeDTO> findAllTreeView(String lang);

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RentOfferCategory findOne(String id);


    Optional<RentOfferCategory> findOneByCode(int code);

    /**
     * Get categories by "codes".
     * Order by key.asc
     *
     * @param codes the codes list of the entity
     * @return the entity
     */

    List<RentOfferCategory> findByCodeInOrderByCodeAsc(List<Integer> codes);


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
    LinkedList<RentOfferCategory> getRentOfferCategories(int code);

    /**
     * Get set of ids rent categories from cache.
     *
     * @param code the code of the entity
     */
    List<Integer> getRentOfferCategoriesIds(int code);

    /**
     * Exists in cache.
     *
     * @param code the code of the entity
     */
    boolean exists(int code);

    boolean existsByKey(String key);
}
