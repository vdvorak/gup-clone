package ua.com.gup.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.Category;
import ua.com.gup.domain.offer.model.OfferCategory;
import ua.com.gup.repository.dao.category.CategoryRepository;
import ua.com.gup.dto.category.CategoryCreateDTO;
import ua.com.gup.dto.category.CategoryUpdateDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.dto.category.tree.CategoryTreeDTO;
import ua.com.gup.mapper.CategoryMapper;
import ua.com.gup.service.category.attribute.CategoryAttributeService;

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
        categoriesList.removeIf(c -> !c.isActive());
        final Map<Integer, CategoryTreeDTO> categories = new LinkedHashMap<>();
        for (Category category : categoriesList) {
            categories.put(category.getCode(), categoryToCategoryTreeDTO(category, lang));
        }
        for (Category category : categoriesList) {
            if (categories.containsKey(category.getParent()))
                categories.get(category.getParent()).getChildren().add(categories.get(category.getCode()));
        }
        final Map<Integer, LinkedHashSet<CategoryAttributeDTO>> categoryAttributeDTOs = categoryAttributeService.findAllCategoryAttributeDTO();
        for (Integer code : categoryAttributeDTOs.keySet()) {
            final LinkedHashSet<CategoryAttributeDTO> attributes = categoryAttributeDTOs.get(code);
            for (CategoryAttributeDTO attributeDTO : attributes) {
                TreeSet<CategoryAttributeValueDTO> sortedSet = new TreeSet<>(
                        Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, "")));
                sortedSet.addAll(attributeDTO.getValues());
                attributeDTO.setValues(sortedSet);
            }
        }
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
