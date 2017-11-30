package ua.com.gup.rent.service.category.attribute;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

/**
 * Service Interface for managing RentCategoryAttribute.
 */
public interface RentCategoryAttributeServiceRent extends ua.com.gup.rent.service.abstracted.generic.RentGenericService<ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO, String> {
    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute save(ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO);

    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeUpdateDTO the entity to save
     * @return the persisted entity
     */
    ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute save(ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO);

    /**
     * Get all the categoryAttributes.
     *
     * @return the list of entities
     */
    List<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> findAll();

    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute findOne(String id);

    /**
     * Get the "code" categoryAttribute.
     *
     * @param code the code of the entity
     * @return the entity
     */
    Optional<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> findOneByCode(int code);


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
    Map<Integer, SortedSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO>> findAllCategoryAttributeDTO();
}
