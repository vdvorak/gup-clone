package ua.com.gup.service;

import ua.com.gup.domain.category.CategoryAttribute;
import ua.com.gup.service.dto.CategoryAttributeDTO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Service Interface for managing CategoryAttribute.
 */
public interface CategoryAttributeService {
    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttribute the entity to save
     * @return the persisted entity
     */
    CategoryAttribute save(CategoryAttribute categoryAttribute);

    /**
     * Get all the categoryAttributes.
     *
     * @return the list of entities
     */
    List<CategoryAttribute> findAll();

    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CategoryAttribute findOne(String id);


    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Get the categoryAttributeDTO.
     *
     * @return the entity
     */
    Map<Integer, LinkedHashSet<CategoryAttributeDTO>> findAllCategoryAttributeDTO();
}
