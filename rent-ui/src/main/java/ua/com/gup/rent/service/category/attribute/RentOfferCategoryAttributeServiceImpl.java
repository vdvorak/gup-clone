package ua.com.gup.rent.service.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.common.model.category.attribute.CategoriesSort;
import ua.com.gup.common.model.category.attribute.OfferCategoryAttributeValue;
import ua.com.gup.rent.mapper.RentOfferCategoryAttributeMapper;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.repository.category.attribute.RentOfferCategoryAttributeRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.category.attribute.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing RentOfferCategoryAttribute.
 */
@Service
public class RentOfferCategoryAttributeServiceImpl extends RentOfferGenericServiceImpl<RentOfferCategoryAttribute, String> implements RentOfferCategoryAttributeService {

    private final Logger logger = LoggerFactory.getLogger(RentOfferCategoryAttributeServiceImpl.class);

    @Autowired
    private RentOfferCategoryAttributeMapper rentOfferCategoryAttributeMapper;
    //use for sorted category_sort asc

    private Map<Integer, SortedSet<RentOfferCategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<>();

    @Autowired
    public RentOfferCategoryAttributeServiceImpl(RentOfferCategoryAttributeRepository rentCategoryAttributeRepository) {
        super(rentCategoryAttributeRepository);
    }

    /**
     * Save a categoryAttribute.
     *
     * @param rentOfferCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentOfferCategoryAttribute save(RentOfferCategoryAttributeCreateDTO rentOfferCategoryAttributeCreateDTO) {
        logger.debug("Request to save RentOfferCategoryAttribute : {}", rentOfferCategoryAttributeCreateDTO);
        RentOfferCategoryAttribute attribute = rentOfferCategoryAttributeMapper.categoryAttributeCreateDTOToCategoryAttribute(rentOfferCategoryAttributeCreateDTO);
        final RentOfferCategoryAttribute saved = ((RentOfferCategoryAttributeRepository) getRepository()).save(attribute);
        clearCache();
        return saved;
    }

    /**
     * Save a categoryAttribute.
     *
     * @param rentOfferCategoryAttributeUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentOfferCategoryAttribute save(RentOfferCategoryAttributeUpdateDTO rentOfferCategoryAttributeUpdateDTO) {
        logger.debug("Request to update RentOfferCategoryAttribute : {}", rentOfferCategoryAttributeUpdateDTO);
        final RentOfferCategoryAttribute rentOfferCategoryAttribute = rentOfferCategoryAttributeMapper.categoryAttributeUpdateDTOToCategoryAttribute(rentOfferCategoryAttributeUpdateDTO);
        final RentOfferCategoryAttribute saved = ((RentOfferCategoryAttributeRepository) getRepository()).save(rentOfferCategoryAttribute);
        clearCache();
        return saved;
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<RentOfferCategoryAttribute> findAll() {
        logger.debug("Request to get all Categories by filter");
        return ((RentOfferCategoryAttributeRepository) getRepository()).findAll();
    }

    /**
     * Get the "code" categoryAttribute.
     *
     * @param code the code of the entity
     * @return the entity
     */
    @Override
    public Optional<RentOfferCategoryAttribute> findOneByCode(int code) {
        logger.debug("Request to get RentOfferCategoryAttribute : {}", code);
        return ((RentOfferCategoryAttributeRepository) getRepository()).findOneByCode(code);
    }

    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        logger.debug("Request to delete RentOfferCategoryAttribute : {}", id);
        ((RentOfferCategoryAttributeRepository) getRepository()).delete(id);
        clearCache();
    }

    /**
     * Get the categoryAttribute by category code.
     *
     * @return the entity
     */
    @Override
    public Map<Integer, SortedSet<RentOfferCategoryAttributeDTO>> findAllCategoryAttributeDTO() {
        if (categoryAttributeCache.size() == 0) {
            warmCache();
        }
        return categoryAttributeCache;
    }

    private void warmCache() {
        //get all attributes
        final List<RentOfferCategoryAttribute> rentOfferCategoryAttributes = ((RentOfferCategoryAttributeRepository) getRepository()).findAll();
        //remove attributes is active false
        rentOfferCategoryAttributes.removeIf(c -> !c.isActive());

        for (RentOfferCategoryAttribute rentOfferCategoryAttribute : rentOfferCategoryAttributes) {

            RentOfferCategoryAttributeDTO attributeDTO = new RentOfferCategoryAttributeDTO();
            attributeDTO.setCode(rentOfferCategoryAttribute.getCode());
            attributeDTO.setActive(rentOfferCategoryAttribute.isActive());
            attributeDTO.setKey(rentOfferCategoryAttribute.getKey());
            attributeDTO.setTitle(rentOfferCategoryAttribute.getTitle());
            attributeDTO.setUnit(rentOfferCategoryAttribute.getUnit());
            attributeDTO.setType(rentOfferCategoryAttribute.getType());

            RentOfferCategoryAttributeValidatorDTO validatorDTO = new RentOfferCategoryAttributeValidatorDTO();
            validatorDTO.setMin(rentOfferCategoryAttribute.getValidator().getMin());
            validatorDTO.setMax(rentOfferCategoryAttribute.getValidator().getMax());


            for (CategoriesSort categorySort : rentOfferCategoryAttribute.getCategories_sort()) {
                //check cache if exists category
                if (!categoryAttributeCache.containsKey(categorySort.getCode_category())) {
                    categoryAttributeCache.put(categorySort.getCode_category(), new TreeSet<>(Comparator.comparingInt(RentOfferCategoryAttributeDTO::getCategory_sort)));
                }
                attributeDTO.setCategory_sort(categorySort.getOrder_category());

                boolean exceptThis = rentOfferCategoryAttribute.getValidator().getExcept().contains(categorySort.getCode_category());

                validatorDTO.setRequired(rentOfferCategoryAttribute.getValidator().isRequired() ^ exceptThis);
                attributeDTO.setValidator(validatorDTO);

                LinkedHashSet<RentOfferCategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();

                for (OfferCategoryAttributeValue attributeValue : rentOfferCategoryAttribute.getValues()) {
                    if (!attributeValue.getExceptCategory().contains(categorySort.getCode_category())) {
                        RentOfferCategoryAttributeValueDTO valueDTO = new RentOfferCategoryAttributeValueDTO();
                        valueDTO.setKey(attributeValue.getKey());
                        valueDTO.setTitle(attributeValue.getTitle());
                        valueDTOS.add(valueDTO);
                    }
                }
                attributeDTO.setValues(valueDTOS);
                categoryAttributeCache.get(categorySort.getCode_category()).add(attributeDTO);
            }
        }
    }

    private void clearCache() {
        categoryAttributeCache.clear();
    }
}