package ua.com.gup.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.CategoryAttribute;
import ua.com.gup.domain.category.attribute.CategoryAttributeValue;
import ua.com.gup.repository.CategoryAttributeRepository;
import ua.com.gup.service.CategoryAttributeService;
import ua.com.gup.service.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.service.dto.category.tree.CategoryAttributeValidatorDTO;
import ua.com.gup.service.dto.category.tree.CategoryAttributeValueDTO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing CategoryAttribute.
 */
@Service
public class CategoryAttributeServiceImpl implements CategoryAttributeService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryAttributeRepository categoryAttributeRepository;

    private Map<Integer, LinkedHashSet<CategoryAttributeDTO>> categoryAttributeCache = new ConcurrentHashMap<>();

    @Autowired
    public CategoryAttributeServiceImpl(CategoryAttributeRepository categoryAttributeRepository) {
        this.categoryAttributeRepository = categoryAttributeRepository;
    }

    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttribute the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttribute save(CategoryAttribute categoryAttribute) {
        log.debug("Request to save CategoryAttribute : {}", categoryAttribute);
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
