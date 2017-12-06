package ua.com.gup.rent.service.category.attribute;


import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.service.abstracted.generic.RentGenericService;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeCreateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeUpdateDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

/**
 * Service Interface for managing RentOfferCategoryAttribute.
 */
public interface RentCategoryAttributeService extends RentGenericService<RentCategoryAttributeCreateDTO, String> {
    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
     RentOfferCategoryAttribute save(RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO);
    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeUpdateDTO the entity to save
     * @return the persisted entity
     */
    RentOfferCategoryAttribute save(RentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO);
    /**
     * Get all the categoryAttributes.
     *
     * @return the list of entities
     */
    List<RentOfferCategoryAttribute> findAll();
    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
     RentOfferCategoryAttribute findOne(String id);
    /**
     * Get the "code" categoryAttribute.
     *
     * @param code the code of the entity
     * @return the entity
     */
    Optional<RentOfferCategoryAttribute> findOneByCode(int code);
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
    Map<Integer, SortedSet<RentCategoryAttributeDTO>> findAllCategoryAttributeDTO();
}