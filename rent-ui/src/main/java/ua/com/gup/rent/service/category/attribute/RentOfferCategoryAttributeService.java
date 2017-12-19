package ua.com.gup.rent.service.category.attribute;


import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeCreateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeUpdateDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Service Interface for managing RentOfferCategoryAttribute.
 */
public interface RentOfferCategoryAttributeService extends RentOfferGenericService<RentOfferCategoryAttributeCreateDTO, String> {
    /**
     * Save a categoryAttribute.
     *
     * @param rentOfferCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
     RentOfferCategoryAttribute save(RentOfferCategoryAttributeCreateDTO rentOfferCategoryAttributeCreateDTO);
    /**
     * Save a categoryAttribute.
     *
     * @param rentOfferCategoryAttributeUpdateDTO the entity to save
     * @return the persisted entity
     */
    RentOfferCategoryAttribute save(RentOfferCategoryAttributeUpdateDTO rentOfferCategoryAttributeUpdateDTO);
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
    Map<Integer, Set<RentOfferCategoryAttributeDTO>> findAllCategoryAttributeDTO();
}