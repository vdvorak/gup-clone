package ua.com.gup.rent.service.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentCategoryAttributeMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing RentCategoryAttribute.
 */
@Service
public class RentCategoryAttributeServiceImplRentRent extends ua.com.gup.rent.service.abstracted.RentRentGenericServiceImpl<ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO,String> implements RentCategoryAttributeServiceRent {

    private final Logger logger = LoggerFactory.getLogger(RentCategoryAttributeServiceImplRentRent.class);

    @Autowired
    private RentCategoryAttributeMapper rentCategoryAttributeMapper;
    //use for sorted category_sort asc
    private Map<Integer, SortedSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<Integer, SortedSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO>>();

    @Autowired
    public RentCategoryAttributeServiceImplRentRent(ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository rentCategoryAttributeRepository) {
        super(rentCategoryAttributeRepository);
    }
    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute save(ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
        logger.debug("Request to save RentCategoryAttribute : {}", rentCategoryAttributeCreateDTO);
        final ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute attribute = rentCategoryAttributeMapper.categoryAttributeCreateDTOToCategoryAttribute(rentCategoryAttributeCreateDTO);
        final ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute saved = ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).save(attribute);
        clearCache();
        return saved;
    }

    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute save(ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        logger.debug("Request to save RentCategoryAttribute : {}", rentCategoryAttributeUpdateDTO);
        final ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute rentCategoryAttribute = rentCategoryAttributeMapper.categoryAttributeUpdateDTOToCategoryAttribute(rentCategoryAttributeUpdateDTO);
        final ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute saved =  ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).save(rentCategoryAttribute);
        clearCache();
        return saved;
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> findAll() {
        logger.debug("Request to get all Categories by filter");
        return  ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).findAll();
    }

    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute findOne(String id) {
        logger.debug("Request to get RentCategoryAttribute : {}", id);
        return  ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).findOne(id);
    }

    /**
     * Get the "code" categoryAttribute.
     *
     * @param code the code of the entity
     * @return the entity
     */
    @Override
    public Optional<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> findOneByCode(int code) {
        logger.debug("Request to get RentCategoryAttribute : {}", code);
        return  ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).findOneByCode(code);
    }

    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        logger.debug("Request to delete RentCategoryAttribute : {}", id);
        ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).delete(id);
        clearCache();
    }

    /**
     * Get the categoryAttribute by category code.
     *
     * @return the entity
     */
    @Override
    public Map<Integer, SortedSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO>> findAllCategoryAttributeDTO() {
        if (categoryAttributeCache.size() == 0) {
            warmCache();
        }
        return categoryAttributeCache;
    }

    private void warmCache() {
        //get all category_attribute
        final List<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> rentCategoryAttributes =  ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).findAll();
         //remove category_attribute if is not active
        rentCategoryAttributes.removeIf(c -> !c.isActive());
          //get category attribute
        for (ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute rentCategoryAttribute : rentCategoryAttributes) {
            for (ua.com.gup.rent.dto.category.attribute.RentCategoriesSort categorySort : rentCategoryAttribute.getCategoriesSort()) {
                //if not exists put rentCategoryAttribute to cache
                if (!categoryAttributeCache.containsKey(categorySort.getCode_category())) {
                    //sort by category_sort asc
                    categoryAttributeCache.put(categorySort.getCode_category(), new TreeSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO>(Comparator.comparing(ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO::getCategory_sort)));
                }

                ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO attributeDTO = new ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO();

                attributeDTO.setCode(rentCategoryAttribute.getCode());
                attributeDTO.setActive(rentCategoryAttribute.isActive());
                attributeDTO.setKey(rentCategoryAttribute.getKey());
                attributeDTO.setTitle(rentCategoryAttribute.getTitle());
                attributeDTO.setUnit(rentCategoryAttribute.getUnit());
                attributeDTO.setType(rentCategoryAttribute.getType());
                //add sorted number [1-hight 100-low]
                attributeDTO.setCategory_sort(categorySort.getOrder_category());

                ua.com.gup.rent.dto.category.tree.RentCategoryAttributeValidatorDTO validatorDTO = new ua.com.gup.rent.dto.category.tree.RentCategoryAttributeValidatorDTO();
                validatorDTO.setMin(rentCategoryAttribute.getValidator().getMin());
                validatorDTO.setMax(rentCategoryAttribute.getValidator().getMax());

                boolean exceptThis = rentCategoryAttribute.getValidator().getExcept().contains(categorySort.getCode_category());

                validatorDTO.setRequired(rentCategoryAttribute.getValidator().isRequired() ^ exceptThis);
                attributeDTO.setValidator(validatorDTO);

                LinkedHashSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();

                for (ua.com.gup.rent.dto.category.attribute.RentCategoryAttributeValue attributeValue : rentCategoryAttribute.getValues()) {
                    if (!attributeValue.getExceptCategory().contains(categorySort.getCode_category())) {
                        ua.com.gup.rent.dto.category.tree.RentCategoryAttributeValueDTO valueDTO = new ua.com.gup.rent.dto.category.tree.RentCategoryAttributeValueDTO();
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
