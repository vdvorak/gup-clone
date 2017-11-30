package ua.com.gup.rent.service.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.dto.category.attribute.CategoriesSort;
import ua.com.gup.rent.dto.category.attribute.CategoryAttributeValue;
import ua.com.gup.rent.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.rent.dto.category.tree.CategoryAttributeValidatorDTO;
import ua.com.gup.rent.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.rent.mapper.RentCategoryAttributeMapper;
import ua.com.gup.rent.model.mongo.category.attribute.CategoryAttribute;
import ua.com.gup.rent.repository.category.attribute.CategoryAttributeRepository;
import ua.com.gup.rent.service.abstracted.GenericServiceImpl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing CategoryAttribute.
 */
@Service
public class CategoryAttributeServiceImpl extends GenericServiceImpl<ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO,String> implements CategoryAttributeService {

    private final Logger logger = LoggerFactory.getLogger(CategoryAttributeServiceImpl.class);

    @Autowired
    private RentCategoryAttributeMapper rentCategoryAttributeMapper;
    //use for sorted category_sort asc
    private Map<Integer, SortedSet<CategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<Integer, SortedSet<CategoryAttributeDTO>>();

    @Autowired
    public CategoryAttributeServiceImpl(CategoryAttributeRepository categoryAttributeRepository) {
        super(categoryAttributeRepository);
    }
    /**
     * Save a categoryAttribute.
     *
     * @param rentCategoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttribute save(ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
        logger.debug("Request to save CategoryAttribute : {}", rentCategoryAttributeCreateDTO);
        final CategoryAttribute attribute = rentCategoryAttributeMapper.categoryAttributeCreateDTOToCategoryAttribute(rentCategoryAttributeCreateDTO);
        final CategoryAttribute saved = ((CategoryAttributeRepository)getRepository()).save(attribute);
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
    public CategoryAttribute save(ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        logger.debug("Request to save CategoryAttribute : {}", rentCategoryAttributeUpdateDTO);
        final CategoryAttribute categoryAttribute = rentCategoryAttributeMapper.categoryAttributeUpdateDTOToCategoryAttribute(rentCategoryAttributeUpdateDTO);
        final CategoryAttribute saved =  ((CategoryAttributeRepository)getRepository()).save(categoryAttribute);
        clearCache();
        return saved;
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<CategoryAttribute> findAll() {
        logger.debug("Request to get all Categories by filter");
        return  ((CategoryAttributeRepository)getRepository()).findAll();
    }

    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public CategoryAttribute findOne(String id) {
        logger.debug("Request to get CategoryAttribute : {}", id);
        return  ((CategoryAttributeRepository)getRepository()).findOne(id);
    }

    /**
     * Get the "code" categoryAttribute.
     *
     * @param code the code of the entity
     * @return the entity
     */
    @Override
    public Optional<CategoryAttribute> findOneByCode(int code) {
        logger.debug("Request to get CategoryAttribute : {}", code);
        return  ((CategoryAttributeRepository)getRepository()).findOneByCode(code);
    }

    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        logger.debug("Request to delete CategoryAttribute : {}", id);
        ((CategoryAttributeRepository)getRepository()).delete(id);
        clearCache();
    }

    /**
     * Get the categoryAttribute by category code.
     *
     * @return the entity
     */
    @Override
    public Map<Integer, SortedSet<CategoryAttributeDTO>> findAllCategoryAttributeDTO() {
        if (categoryAttributeCache.size() == 0) {
            warmCache();
        }
        return categoryAttributeCache;
    }

    private void warmCache() {
        //get all category_attribute
        final List<CategoryAttribute> categoryAttributes =  ((CategoryAttributeRepository)getRepository()).findAll();
         //remove category_attribute if is not active
        categoryAttributes.removeIf(c -> !c.isActive());
          //get category attribute
        for (CategoryAttribute categoryAttribute : categoryAttributes) {
            for (CategoriesSort categorySort : categoryAttribute.getCategoriesSort()) {
                //if not exists put categoryAttribute to cache
                if (!categoryAttributeCache.containsKey(categorySort.getCode_category())) {
                    //sort by category_sort asc
                    categoryAttributeCache.put(categorySort.getCode_category(), new TreeSet<CategoryAttributeDTO>(Comparator.comparing(CategoryAttributeDTO::getCategory_sort)));
                }

                CategoryAttributeDTO attributeDTO = new CategoryAttributeDTO();

                attributeDTO.setCode(categoryAttribute.getCode());
                attributeDTO.setActive(categoryAttribute.isActive());
                attributeDTO.setKey(categoryAttribute.getKey());
                attributeDTO.setTitle(categoryAttribute.getTitle());
                attributeDTO.setUnit(categoryAttribute.getUnit());
                attributeDTO.setType(categoryAttribute.getType());
                //add sorted number [1-hight 100-low]
                attributeDTO.setCategory_sort(categorySort.getOrder_category());

                CategoryAttributeValidatorDTO validatorDTO = new CategoryAttributeValidatorDTO();
                validatorDTO.setMin(categoryAttribute.getValidator().getMin());
                validatorDTO.setMax(categoryAttribute.getValidator().getMax());

                boolean exceptThis = categoryAttribute.getValidator().getExcept().contains(categorySort.getCode_category());

                validatorDTO.setRequired(categoryAttribute.getValidator().isRequired() ^ exceptThis);
                attributeDTO.setValidator(validatorDTO);

                LinkedHashSet<CategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();

                for (CategoryAttributeValue attributeValue : categoryAttribute.getValues()) {
                    if (!attributeValue.getExceptCategory().contains(categorySort.getCode_category())) {
                        CategoryAttributeValueDTO valueDTO = new CategoryAttributeValueDTO();
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
