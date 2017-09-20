package ua.com.gup.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.category.attribute.CategoryAttribute;
import ua.com.gup.domain.category.attribute.CategoryAttributeValue;
import ua.com.gup.repository.CategoryAttributeRepository;
import ua.com.gup.service.CategoryAttributeService;
import ua.com.gup.dto.category.CategoryAttributeCreateDTO;
import ua.com.gup.dto.category.CategoryAttributeUpdateDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValidatorDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.service.mapper.CategoryAttributeMapper;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing CategoryAttribute.
 */
@Service
public class CategoryAttributeServiceImpl implements CategoryAttributeService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryAttributeRepository categoryAttributeRepository;

    private final CategoryAttributeMapper categoryAttributeMapper;

    private Map<Integer, LinkedHashSet<CategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<>();

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
        log.debug("Request to save CategoryAttribute : {}", categoryAttributeCreateDTO);
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
        log.debug("Request to save CategoryAttribute : {}", categoryAttributeUpdateDTO);
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
        log.debug("Request to get all Categories by filter");
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
        log.debug("Request to get CategoryAttribute : {}", id);
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
        log.debug("Request to get CategoryAttribute : {}", code);
        return categoryAttributeRepository.findOneByCode(code);
    }

    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete CategoryAttribute : {}", id);
        categoryAttributeRepository.delete(id);
        clearCache();
    }

    /**
     * Get the categoryAttribute by category code.
     *
     * @return the entity
     */
    @Override
    public Map<Integer, LinkedHashSet<CategoryAttributeDTO>> findAllCategoryAttributeDTO() {
        if (categoryAttributeCache.size() == 0) {
            warmCache();
        }
        return categoryAttributeCache;
    }

    private void warmCache() {
        final List<CategoryAttribute> categoryAttributes = categoryAttributeRepository.findAll();
        categoryAttributes.removeIf(c -> !c.isActive());
        for (CategoryAttribute categoryAttribute : categoryAttributes) {
            for (Integer category : categoryAttribute.getCategories()) {
                if (!categoryAttributeCache.containsKey(category)) {
                    categoryAttributeCache.put(category, new LinkedHashSet<>());
                }
                CategoryAttributeDTO attributeDTO = new CategoryAttributeDTO();
                attributeDTO.setCode(categoryAttribute.getCode());
                attributeDTO.setActive(categoryAttribute.isActive());
                attributeDTO.setKey(categoryAttribute.getKey());
                attributeDTO.setTitle(categoryAttribute.getTitle());
                attributeDTO.setUnit(categoryAttribute.getUnit());
                attributeDTO.setType(categoryAttribute.getType());
                CategoryAttributeValidatorDTO validatorDTO = new CategoryAttributeValidatorDTO();
                validatorDTO.setMin(categoryAttribute.getValidator().getMin());
                validatorDTO.setMax(categoryAttribute.getValidator().getMax());
                boolean exceptThis = categoryAttribute.getValidator().getExcept().contains(category);
                validatorDTO.setRequired(categoryAttribute.getValidator().isRequired() ^ exceptThis);
                attributeDTO.setValidator(validatorDTO);
                LinkedHashSet<CategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();
                for (CategoryAttributeValue attributeValue : categoryAttribute.getValues()) {
                    if (!attributeValue.getExceptCategory().contains(category)) {
                        CategoryAttributeValueDTO valueDTO = new CategoryAttributeValueDTO();
                        valueDTO.setKey(attributeValue.getKey());
                        valueDTO.setTitle(attributeValue.getTitle());
                        valueDTOS.add(valueDTO);
                    }
                }
                attributeDTO.setValues(valueDTOS);
                categoryAttributeCache.get(category).add(attributeDTO);
            }
        }
    }

    private void clearCache() {
        categoryAttributeCache.clear();
    }
}
