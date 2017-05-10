package ua.com.gup.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.OfferCategory;
import ua.com.gup.domain.category.Category;
import ua.com.gup.domain.category.CategoryAttribute;
import ua.com.gup.domain.category.CategoryAttributeValue;
import ua.com.gup.repository.CategoryAttributeRepository;
import ua.com.gup.repository.CategoryRepository;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.CategoryAttributeDTO;
import ua.com.gup.service.dto.CategoryAttributeValidatorDTO;
import ua.com.gup.service.dto.CategoryAttributeValueDTO;
import ua.com.gup.service.dto.CategoryTreeDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Category.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private final CategoryAttributeRepository categoryAttributeRepository;

    private final Map<Integer, LinkedList<OfferCategory>> offerCategoryCache = new ConcurrentHashMap<>();

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryAttributeRepository categoryAttributeRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryAttributeRepository = categoryAttributeRepository;
    }

    private static CategoryTreeDTO categoryToCategoryTreeDTO(Category category) {
        CategoryTreeDTO categoryTreeDTO = new CategoryTreeDTO();
        categoryTreeDTO.setCode(category.getCode());
        categoryTreeDTO.setActive(category.isActive());
        categoryTreeDTO.setKey(category.getKey());
        categoryTreeDTO.setTitle(category.getTitle());
        categoryTreeDTO.setAttrs(new LinkedHashSet<>());
        categoryTreeDTO.setChildren(new LinkedHashSet<>());
        return categoryTreeDTO;
    }

    /**
     * Save a category.
     *
     * @param category the entity to save
     * @return the persisted entity
     */
    @Override
    public Category save(Category category) {
        log.debug("Request to save Category : {}", category);
        clearCache();
        return categoryRepository.save(category);
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<Category> findAll() {
        log.debug("Request to get all Categories by filter");
        return categoryRepository.findAll();
    }

    /**
     * Get all the categories in tree view.
     *
     * @return the list of entities
     */
    @Override
    public Collection<CategoryTreeDTO> findAllTreeView() {
        log.debug("Request to get all Categories in tree view");
        final List<Category> categoriesList = categoryRepository.findAll();
        final Map<Integer, CategoryTreeDTO> categories = new LinkedHashMap<>();
        for (Category category : categoriesList) {
            categories.put(category.getCode(), categoryToCategoryTreeDTO(category));
        }
        for (Category category : categoriesList) {
            if (categories.containsKey(category.getParent()))
                categories.get(category.getParent()).getChildren().add(categories.get(category.getCode()));
        }
        final List<CategoryAttribute> categoryAttributes = categoryAttributeRepository.findAll();
        for (CategoryAttribute categoryAttribute : categoryAttributes) {
            for (int categoryCode : categoryAttribute.getCategories()) {
                CategoryAttributeDTO categoryAttributeDTO = new CategoryAttributeDTO();
                categoryAttributeDTO.setCode(categoryAttribute.getCode());
                categoryAttributeDTO.setActive(categoryAttribute.isActive());
                categoryAttributeDTO.setKey(categoryAttribute.getKey());
                categoryAttributeDTO.setTitle(categoryAttribute.getTitle());
                CategoryAttributeValidatorDTO validatorDTO = new CategoryAttributeValidatorDTO();
                validatorDTO.setMin(categoryAttribute.getValidator().getMin());
                validatorDTO.setMax(categoryAttribute.getValidator().getMax());
                boolean exceptThis = categoryAttribute.getValidator().getExcept().contains(categoryCode);
                validatorDTO.setRequired(categoryAttribute.getValidator().isRequired() ^ exceptThis);
                categoryAttributeDTO.setValidator(validatorDTO);
                LinkedHashSet<CategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();
                for (CategoryAttributeValue categoryAttributeValue : categoryAttribute.getValues()) {
                    if (!categoryAttributeValue.getExceptCategory().contains(categoryCode)) {
                        CategoryAttributeValueDTO valueDTO = new CategoryAttributeValueDTO();
                        valueDTO.setKey(categoryAttributeValue.getKey());
                        valueDTO.setTitle(categoryAttributeValue.getTitle());
                    }
                }
                categoryAttributeDTO.setValues(valueDTOS);
                if (categories.containsKey(categoryCode)) {
                    categories.get(categoryCode).getAttrs().add(categoryAttributeDTO);
                }
            }
        }
        final Set<Integer> firstLevelCategories = categoriesList.stream()
                .filter((Category c) -> c.getParent() == 0)
                .map(Category::getCode)
                .collect(Collectors.toSet());
        return categories.entrySet().stream()
                .filter(e -> firstLevelCategories.contains(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Category findOne(String id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findOne(id);
    }


    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.delete(id);
        clearCache();
    }

    /**
     * Get offer categories from cache.
     *
     * @param code the code of the entity
     */
    @Override
    public LinkedList<OfferCategory> getOfferCategories(int code) {
        if (offerCategoryCache.size() == 0) {
            warmCache();
        }
        return offerCategoryCache.get(code);
    }

    /**
     * Exists in cache.
     *
     * @param code the code of the entity
     */
    @Override
    public boolean exists(int code) {
        if (offerCategoryCache.size() == 0) {
            warmCache();
        }
        return offerCategoryCache.containsKey(code);
    }

    private void warmCache() {
        final List<Category> categories = categoryRepository.findAll();
        final Set<Integer> parentCategories = categories.stream().map(Category::getParent).collect(Collectors.toSet());
        final Map<Integer, Category> categoriesMap = categories.stream().collect(Collectors.toMap(Category::getCode, Function.identity()));
        categories.removeIf(c -> parentCategories.contains(c.getCode()));
        final Map<Integer, LinkedList<OfferCategory>> offerCategoriesMap = new HashMap();
        for (Category category : categories) {
            int code = category.getCode();
            LinkedList<OfferCategory> linkedList = new LinkedList<>();
            while (code != 0) {
                Category current = categoriesMap.get(code);
                OfferCategory offerCategory = new OfferCategory();
                offerCategory.setCode(current.getCode());
                offerCategory.setTitle(current.getTitle());
                linkedList.add(0, offerCategory);
                code = current.getParent();
            }
            offerCategoriesMap.put(category.getCode(), linkedList);
        }
        offerCategoryCache.putAll(offerCategoriesMap);
    }

    private void clearCache() {
        offerCategoryCache.clear();
    }
}
