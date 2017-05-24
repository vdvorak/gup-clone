package ua.com.gup.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.Category;
import ua.com.gup.domain.CategoryAttribute;
import ua.com.gup.domain.category.attribute.CategoryAttributeValue;
import ua.com.gup.domain.offer.OfferCategory;
import ua.com.gup.repository.CategoryRepository;
import ua.com.gup.service.CategoryAttributeService;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.category.CategoryCreateDTO;
import ua.com.gup.service.dto.category.CategoryUpdateDTO;
import ua.com.gup.service.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.service.dto.category.tree.CategoryAttributeValidatorDTO;
import ua.com.gup.service.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.service.dto.category.tree.CategoryTreeDTO;
import ua.com.gup.service.mapper.CategoryMapper;

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

    private final CategoryAttributeService categoryAttributeService;

    private final CategoryMapper categoryMapper;

    private final Map<Integer, LinkedList<OfferCategory>> offerCategoryCache = new ConcurrentHashMap<>();

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryAttributeService categoryAttributeService, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryAttributeService = categoryAttributeService;
        this.categoryMapper = categoryMapper;
    }

    private CategoryTreeDTO categoryToCategoryTreeDTO(Category category, String lang) {
        CategoryTreeDTO categoryTreeDTO = new CategoryTreeDTO(lang);
        categoryTreeDTO.setCode(category.getCode());
        categoryTreeDTO.setActive(category.isActive());
        categoryTreeDTO.setKey(category.getKey());
        categoryTreeDTO.setTitle(category.getTitle());
        categoryTreeDTO.setDescription(category.getDescription());
        return categoryTreeDTO;
    }

    /**
     * Save a category.
     *
     * @param categoryCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Category save(CategoryCreateDTO categoryCreateDTO) {
        log.debug("Request to save Category : {}", categoryCreateDTO);
        final Category category = categoryRepository.save(categoryMapper.categoryCreateDTOToCategory(categoryCreateDTO));
        clearCache();
        return category;
    }

    /**
     * Save a category.
     *
     * @param categoryUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Category save(CategoryUpdateDTO categoryUpdateDTO) {
        log.debug("Request to save Category : {}", categoryUpdateDTO);
        final Category category = categoryRepository.save(categoryMapper.categoryUpdateDTOToCategory(categoryUpdateDTO));
        clearCache();
        return category;
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
    public Collection<CategoryTreeDTO> findAllTreeView(String lang) {
        log.debug("Request to get all Categories in tree view");
        final List<Category> categoriesList = categoryRepository.findAll();
        final Map<Integer, CategoryTreeDTO> categories = new LinkedHashMap<>();
        for (Category category : categoriesList) {
            categories.put(category.getCode(), categoryToCategoryTreeDTO(category, lang));
        }
        for (Category category : categoriesList) {
            if (categories.containsKey(category.getParent())) {
                Set<CategoryTreeDTO> children = categories.get(category.getParent()).getChildren();
                children.add(categories.get(category.getCode()));
            }
        }
        final Map<Integer, LinkedHashSet<CategoryAttributeDTO>> categoryAttributeDTOs = categoryAttributeService.findAllCategoryAttributeDTO();
        for (Integer code : categories.keySet()) {
            if (categoryAttributeDTOs.containsKey(code)) {
                categories.get(code).setAttrs(categoryAttributeDTOs.get(code));
            }
        }
        final Set<Integer> firstLevelCategories = categoriesList.stream()
                .filter((Category c) -> c.getParent() == 0)
                .map(Category::getCode)
                .collect(Collectors.toSet());
        return categories.entrySet().stream()
                .filter(e -> firstLevelCategories.contains(e.getKey()))
                .map(e -> e.getValue())
                .sorted(CategoryTreeDTO.getCategoryTreeDTOComparator(lang))
                .collect(Collectors.toList());
    }

//    private Map<Integer, TreeSet<CategoryAttributeDTO>> getCategoryAttributeDTOs(String lang) {
//        final List<CategoryAttribute> categoryAttributes = categoryAttributeService.findAll();
//        Map<Integer, TreeSet<CategoryAttributeDTO>> categoryAttributeDTOs = new ConcurrentHashMap<>();
//        categoryAttributes.removeIf(c -> !c.isActive());
//        for (CategoryAttribute categoryAttribute : categoryAttributes) {
//            for (Integer category : categoryAttribute.getCategories()) {
//                if (!categoryAttributeDTOs.containsKey(category)) {
//                    categoryAttributeDTOs.put(category, new TreeSet<>(Comparator.comparing(CategoryAttributeDTO::getTitle)));
//                }
//                CategoryAttributeDTO attributeDTO = new CategoryAttributeDTO();
//                attributeDTO.setCode(categoryAttribute.getCode());
//                attributeDTO.setActive(categoryAttribute.isActive());
//                attributeDTO.setKey(categoryAttribute.getKey());
//                attributeDTO.setTitle(categoryAttribute.getTitle().get(lang));
//                attributeDTO.setUnit(categoryAttribute.getUnit().get(lang));
//                attributeDTO.setType(categoryAttribute.getType());
//                CategoryAttributeValidatorDTO validatorDTO = new CategoryAttributeValidatorDTO();
//                validatorDTO.setMin(categoryAttribute.getValidator().getMin());
//                validatorDTO.setMax(categoryAttribute.getValidator().getMax());
//                boolean exceptThis = categoryAttribute.getValidator().getExcept().contains(category);
//                validatorDTO.setRequired(categoryAttribute.getValidator().isRequired() ^ exceptThis);
//                attributeDTO.setValidator(validatorDTO);
//                LinkedHashSet<CategoryAttributeValueDTO> valueDTOS = new LinkedHashSet<>();
//                for (CategoryAttributeValue attributeValue : categoryAttribute.getValues()) {
//                    if (!attributeValue.getExceptCategory().contains(category)) {
//                        CategoryAttributeValueDTO valueDTO = new CategoryAttributeValueDTO();
//                        valueDTO.setKey(attributeValue.getKey());
//                        valueDTO.setTitle(attributeValue.getTitle().get(lang));
//                        valueDTOS.add(valueDTO);
//                    }
//                }
//                attributeDTO.setValues(valueDTOS);
//                categoryAttributeDTOs.get(category).add(attributeDTO);
//            }
//        }
//        return categoryAttributeDTOs;
//    }

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
     * Get the "code" category.
     *
     * @param code the code of the entity
     * @return the entity
     */
    @Override
    public Optional<Category> findOneByCode(int code) {
        log.debug("Request to get Category : {}", code);
        return categoryRepository.findOneByCode(code);
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
                offerCategory.setKey(current.getKey());
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
