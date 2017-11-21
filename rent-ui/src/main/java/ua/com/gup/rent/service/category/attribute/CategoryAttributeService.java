package ua.com.gup.rent.service.category.attribute;


import ua.com.gup.rent.dto.category.CategoryAttributeCreateDTO;
import ua.com.gup.rent.dto.category.CategoryAttributeUpdateDTO;
import ua.com.gup.rent.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.rent.model.mongo.category.attribute.CategoryAttribute;
import ua.com.gup.rent.service.abstracted.generic.GenericService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

/**
 * Service Interface for managing CategoryAttribute.
 */
public interface CategoryAttributeService extends GenericService<CategoryAttributeCreateDTO, String> {
    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    CategoryAttribute save(CategoryAttributeCreateDTO categoryAttributeCreateDTO);

    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttributeUpdateDTO the entity to save
     * @return the persisted entity
     */
    CategoryAttribute save(CategoryAttributeUpdateDTO categoryAttributeUpdateDTO);

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
     * Get the "code" categoryAttribute.
     *
     * @param code the code of the entity
     * @return the entity
     */
    Optional<CategoryAttribute> findOneByCode(int code);


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
    Map<Integer, SortedSet<CategoryAttributeDTO>> findAllCategoryAttributeDTO();
}
