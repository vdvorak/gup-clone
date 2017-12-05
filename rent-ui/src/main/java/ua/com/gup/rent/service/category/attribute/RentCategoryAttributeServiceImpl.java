package ua.com.gup.rent.service.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentCategoryAttributeMapper;
import ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute;
import ua.com.gup.rent.model.rent.category.RentCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentCategoryAttributeValue;
import ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository;
import ua.com.gup.rent.service.abstracted.RentGenericServiceImpl;
import ua.com.gup.rent.service.dto.category.attribute.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing RentCategoryAttribute.
 */
@Service
public class RentCategoryAttributeServiceImpl extends RentGenericServiceImpl<RentCategoryAttributeCreateDTO,String> implements RentCategoryAttributeService {

    private final Logger logger = LoggerFactory.getLogger(RentCategoryAttributeServiceImpl.class);

    @Autowired
    private RentCategoryAttributeMapper rentCategoryAttributeMapper;
    //use for sorted category_sort asc
    private Map<Integer, SortedSet<RentCategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<Integer, SortedSet<RentCategoryAttributeDTO>>();

    @Autowired
    public RentCategoryAttributeServiceImpl(RentCategoryAttributeRepository rentCategoryAttributeRepository) {
        super(rentCategoryAttributeRepository);
    }
    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentCategoryAttribute save(RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
        logger.debug("Request to save RentCategoryAttribute : {}", rentCategoryAttributeCreateDTO);
        final RentCategoryAttribute attribute = rentCategoryAttributeMapper.categoryAttributeCreateDTOToCategoryAttribute(rentCategoryAttributeCreateDTO);
        final RentCategoryAttribute saved = ((RentCategoryAttributeRepository)getRepository()).save(attribute);
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
    public RentCategoryAttribute save(RentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        logger.debug("Request to save RentCategoryAttribute : {}", rentCategoryAttributeUpdateDTO);
        final RentCategoryAttribute rentCategoryAttribute = rentCategoryAttributeMapper.categoryAttributeUpdateDTOToCategoryAttribute(rentCategoryAttributeUpdateDTO);
        final RentCategoryAttribute saved =  ((ua.com.gup.rent.repository.category.attribute.RentCategoryAttributeRepository)getRepository()).save(rentCategoryAttribute);
        clearCache();
        return saved;
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<RentCategoryAttribute> findAll() {
        logger.debug("Request to get all Categories by filter");
        return  ((RentCategoryAttributeRepository)getRepository()).findAll();
    }

    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public RentCategoryAttribute findOne(String id) {
        logger.debug("Request to get RentCategoryAttribute : {}", id);
        return  ((RentCategoryAttributeRepository)getRepository()).findOne(id);
    }

    /**
     * Get the "code" categoryAttribute.
     *
     * @param code the code of the entity
     * @return the entity
     */
    @Override
    public Optional<RentCategoryAttribute> findOneByCode(int code) {
        logger.debug("Request to get RentCategoryAttribute : {}", code);
        return  ((RentCategoryAttributeRepository)getRepository()).findOneByCode(code);
    }

    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        logger.debug("Request to delete RentCategoryAttribute : {}", id);
        ((RentCategoryAttributeRepository)getRepository()).delete(id);
        clearCache();
    }

    /**
     * Get the categoryAttribute by category code.
     *
     * @return the entity
     */
    @Override
    public Map<Integer, SortedSet<RentCategoryAttributeDTO>> findAllCategoryAttributeDTO() {
        if (categoryAttributeCache.size() == 0) {
            warmCache();
        }
        return categoryAttributeCache;
    }

    private void warmCache() {
        final List<RentCategoryAttribute> rentCategoryAttributes =  ((RentCategoryAttributeRepository)getRepository()).findAll();
        rentCategoryAttributes.removeIf(c -> !c.isActive());
        for (RentCategoryAttribute rentCategoryAttribute : rentCategoryAttributes) {
            for (RentCategoriesSort categorySort : rentCategoryAttribute.getCategoriesSort()) {
                if (!categoryAttributeCache.containsKey(categorySort.getCode_category())) {
                    categoryAttributeCache.put(categorySort.getCode_category(), new TreeSet<RentCategoryAttributeDTO>(Comparator.comparing(RentCategoryAttributeDTO::getCategory_sort)));
                }

                RentCategoryAttributeDTO attributeDTO = new RentCategoryAttributeDTO();
                attributeDTO.setCode(rentCategoryAttribute.getCode());
                attributeDTO.setActive(rentCategoryAttribute.isActive());
                attributeDTO.setKey(rentCategoryAttribute.getKey());
                attributeDTO.setTitle(rentCategoryAttribute.getTitle());
                attributeDTO.setUnit(rentCategoryAttribute.getUnit());
                attributeDTO.setType(rentCategoryAttribute.getType());
                attributeDTO.setCategory_sort(categorySort.getOrder_category());

                RentCategoryAttributeValidatorDTO validatorDTO = new RentCategoryAttributeValidatorDTO();
                validatorDTO.setMin(rentCategoryAttribute.getValidator().getMin());
                validatorDTO.setMax(rentCategoryAttribute.getValidator().getMax());

                boolean exceptThis = rentCategoryAttribute.getValidator().getExcept().contains(categorySort.getCode_category());

                validatorDTO.setRequired(rentCategoryAttribute.getValidator().isRequired() ^ exceptThis);
                attributeDTO.setValidator(validatorDTO);

                LinkedHashSet<RentCategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();

                for (RentCategoryAttributeValue attributeValue : rentCategoryAttribute.getValues()) {
                    if (!attributeValue.getExceptCategory().contains(categorySort.getCode_category())) {
                        RentCategoryAttributeValueDTO valueDTO = new RentCategoryAttributeValueDTO();
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