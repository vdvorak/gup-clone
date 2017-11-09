package ua.com.gup.service.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.dto.category.CategoryAttributeCreateDTO;
import ua.com.gup.dto.category.CategoryAttributeUpdateDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValidatorDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.mapper.CategoryAttributeMapper;
import ua.com.gup.mongo.composition.domain.category.attribute.CategoryAttribute;
import ua.com.gup.mongo.model.category.attribute.CategoriesSort;
import ua.com.gup.mongo.model.category.attribute.CategoryAttributeValue;
import ua.com.gup.repository.category.attribute.CategoryAttributeRepository;
import ua.com.gup.service.category.CategoryServiceImpl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing CategoryAttribute.
 */
@Service
public class CategoryAttributeServiceImpl implements CategoryAttributeService {

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryAttributeRepository categoryAttributeRepository;

    private final CategoryAttributeMapper categoryAttributeMapper;

    private Map<Integer, SortedSet<CategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<>();

    @Autowired
    public CategoryAttributeServiceImpl(CategoryAttributeRepository categoryAttributeRepository, CategoryAttributeMapper categoryAttributeMapper) {
        this.categoryAttributeRepository = categoryAttributeRepository;
        this.categoryAttributeMapper = categoryAttributeMapper;
    }

    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttributeCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttribute save(CategoryAttributeCreateDTO categoryAttributeCreateDTO) {
        logger.debug("Request to save CategoryAttribute : {}", categoryAttributeCreateDTO);
        final CategoryAttribute attribute = categoryAttributeMapper.categoryAttributeCreateDTOToCategoryAttribute(categoryAttributeCreateDTO);
        final CategoryAttribute saved = categoryAttributeRepository.save(attribute);
        clearCache();
        return saved;
    }

    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttributeUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttribute save(CategoryAttributeUpdateDTO categoryAttributeUpdateDTO) {
        logger.debug("Request to save CategoryAttribute : {}", categoryAttributeUpdateDTO);
        final CategoryAttribute categoryAttribute = categoryAttributeMapper.categoryAttributeUpdateDTOToCategoryAttribute(categoryAttributeUpdateDTO);
        final CategoryAttribute saved = categoryAttributeRepository.save(categoryAttribute);
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
        return categoryAttributeRepository.findAll();
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
        return categoryAttributeRepository.findOne(id);
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
        return categoryAttributeRepository.findOneByCode(code);
    }

    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        logger.debug("Request to delete CategoryAttribute : {}", id);
        categoryAttributeRepository.delete(id);
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
        final List<CategoryAttribute> categoryAttributes = categoryAttributeRepository.findAll();
         //remove if is not active
        categoryAttributes.removeIf(c -> !c.isActive());
          //get category attribute
        for (CategoryAttribute categoryAttribute : categoryAttributes) {
           //for (Integer category : categoryAttribute.getCategories()) {
            for (CategoriesSort categorySort : categoryAttribute.getCategoriesSort()) {

                if (!categoryAttributeCache.containsKey(categorySort.getCode_category())) {
                    categoryAttributeCache.put(categorySort.getCode_category(), new TreeSet<CategoryAttributeDTO>());
                }

                CategoryAttributeDTO attributeDTO = new CategoryAttributeDTO();

                attributeDTO.setCode(categoryAttribute.getCode());
                attributeDTO.setActive(categoryAttribute.isActive());
                attributeDTO.setKey(categoryAttribute.getKey());
                attributeDTO.setTitle(categoryAttribute.getTitle());
                attributeDTO.setUnit(categoryAttribute.getUnit());
                attributeDTO.setType(categoryAttribute.getType());
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
