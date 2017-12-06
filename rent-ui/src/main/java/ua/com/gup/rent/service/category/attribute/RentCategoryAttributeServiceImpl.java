package ua.com.gup.rent.service.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentOfferCategoryAttributeMapper;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.model.rent.category.RentOfferCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryAttributeValue;
import ua.com.gup.rent.repository.category.attribute.RentOfferCategoryAttributeRepository;
import ua.com.gup.rent.service.abstracted.RentGenericServiceImpl;
import ua.com.gup.rent.service.dto.category.attribute.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing RentOfferCategoryAttribute.
 */
@Service
public class RentCategoryAttributeServiceImpl extends RentGenericServiceImpl<RentCategoryAttributeCreateDTO,String> implements RentCategoryAttributeService {

    private final Logger logger = LoggerFactory.getLogger(RentCategoryAttributeServiceImpl.class);

    @Autowired
    private RentOfferCategoryAttributeMapper rentOfferCategoryAttributeMapper;
    //use for sorted category_sort asc
    private Map<Integer, SortedSet<RentCategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<Integer, SortedSet<RentCategoryAttributeDTO>>();

    @Autowired
    public RentCategoryAttributeServiceImpl(RentOfferCategoryAttributeRepository rentCategoryAttributeRepository) {
        super(rentCategoryAttributeRepository);
    }
    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentOfferCategoryAttribute save(RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
        logger.debug("Request to save RentOfferCategoryAttribute : {}", rentCategoryAttributeCreateDTO);
        RentOfferCategoryAttribute attribute = rentOfferCategoryAttributeMapper.categoryAttributeCreateDTOToCategoryAttribute(rentCategoryAttributeCreateDTO);
        final RentOfferCategoryAttribute saved = ((RentOfferCategoryAttributeRepository)getRepository()).save(attribute);
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
    public RentOfferCategoryAttribute save(RentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        logger.debug("Request to save RentOfferCategoryAttribute : {}", rentCategoryAttributeUpdateDTO);
        final RentOfferCategoryAttribute rentOfferCategoryAttribute = rentOfferCategoryAttributeMapper.categoryAttributeUpdateDTOToCategoryAttribute(rentCategoryAttributeUpdateDTO);
        final RentOfferCategoryAttribute saved =  ((RentOfferCategoryAttributeRepository)getRepository()).save(rentOfferCategoryAttribute);
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
        return  ((RentOfferCategoryAttributeRepository)getRepository()).findAll();
    }

    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public RentOfferCategoryAttribute findOne(String id) {
        logger.debug("Request to get RentOfferCategoryAttribute : {}", id);
        return  ((RentOfferCategoryAttributeRepository)getRepository()).findOne(id);
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
        return  ((RentOfferCategoryAttributeRepository)getRepository()).findOneByCode(code);
    }

    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        logger.debug("Request to delete RentOfferCategoryAttribute : {}", id);
        ((RentOfferCategoryAttributeRepository)getRepository()).delete(id);
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
        final List<RentOfferCategoryAttribute> rentOfferCategoryAttributes =  ((RentOfferCategoryAttributeRepository)getRepository()).findAll();
        rentOfferCategoryAttributes.removeIf(c -> !c.isActive());
        for (RentOfferCategoryAttribute rentOfferCategoryAttribute : rentOfferCategoryAttributes) {
            for (RentOfferCategoriesSort categorySort : rentOfferCategoryAttribute.getCategoriesSort()) {
                if (!categoryAttributeCache.containsKey(categorySort.getCode_category())) {
                    categoryAttributeCache.put(categorySort.getCode_category(), new TreeSet<RentCategoryAttributeDTO>(Comparator.comparing(RentCategoryAttributeDTO::getCategory_sort)));
                }

                RentCategoryAttributeDTO attributeDTO = new RentCategoryAttributeDTO();
                attributeDTO.setCode(rentOfferCategoryAttribute.getCode());
                attributeDTO.setActive(rentOfferCategoryAttribute.isActive());
                attributeDTO.setKey(rentOfferCategoryAttribute.getKey());
                attributeDTO.setTitle(rentOfferCategoryAttribute.getTitle());
                attributeDTO.setUnit(rentOfferCategoryAttribute.getUnit());
                attributeDTO.setType(rentOfferCategoryAttribute.getType());
                attributeDTO.setCategory_sort(categorySort.getOrder_category());

                RentCategoryAttributeValidatorDTO validatorDTO = new RentCategoryAttributeValidatorDTO();
                validatorDTO.setMin(rentOfferCategoryAttribute.getValidator().getMin());
                validatorDTO.setMax(rentOfferCategoryAttribute.getValidator().getMax());

                boolean exceptThis = rentOfferCategoryAttribute.getValidator().getExcept().contains(categorySort.getCode_category());

                validatorDTO.setRequired(rentOfferCategoryAttribute.getValidator().isRequired() ^ exceptThis);
                attributeDTO.setValidator(validatorDTO);

                LinkedHashSet<RentCategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();

                for (RentOfferCategoryAttributeValue attributeValue : rentOfferCategoryAttribute.getValues()) {
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