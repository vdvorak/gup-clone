package ua.com.gup.rent.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.dto.category.CategoryCreateDTO;
import ua.com.gup.rent.dto.category.CategoryUpdateDTO;
import ua.com.gup.rent.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.rent.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.rent.dto.category.tree.CategoryTreeDTO;
import ua.com.gup.rent.mapper.CategoryMapper;
import ua.com.gup.rent.model.mongo.category.Category;
import ua.com.gup.rent.model.rent.RentCategory;
import ua.com.gup.rent.repository.category.CategoryRepository;
import ua.com.gup.rent.service.category.attribute.CategoryAttributeService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Category.
 */
@Service
public class CategoryServiceImpl  implements CategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private  CategoryAttributeService categoryAttributeService;
    @Autowired
    private  CategoryMapper categoryMapper;

    private final Map<Integer, LinkedList<RentCategory>> rentCategoryCache = new ConcurrentHashMap<>();


    private CategoryTreeDTO categoryToCategoryTreeDTO(Category category, String lang) {
        CategoryTreeDTO categoryTreeDTO = new CategoryTreeDTO(lang);
        categoryTreeDTO.setCode(category.getCode());
        categoryTreeDTO.setActive(category.isActive());
        categoryTreeDTO.setKey(category.getKey());
        categoryTreeDTO.setTitle(category.getTitle());
        categoryTreeDTO.setDescription(category.getDescription());
        categoryTreeDTO.setColor(category.getColor());
        categoryTreeDTO.setOrder(category.getOrder());
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
        logger.debug("Request to save Category : {}", categoryCreateDTO);
        final Category category =  categoryRepository.save(categoryMapper.categoryCreateDTOToCategory(categoryCreateDTO));
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
        logger.debug("Request to save Category : {}", categoryUpdateDTO);
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
        logger.debug("Request to get all Categories by filter");
        return categoryRepository.findAll();
    }

    /**
     * Get all the categories in tree view.
     *
     * @return the list of entities
     */
    @Override
    public Collection<CategoryTreeDTO> findAllTreeView(String lang) {
        logger.debug("Request to get all Categories in tree view");
        //get all category and sort asc by field order
        final List<Category> categoriesList = categoryRepository.findAll(new Sort(Sort.Direction.ASC, "order"));
        //remove if active false
        categoriesList.removeIf(c -> !c.isActive());

        final Map<Integer, CategoryTreeDTO> categories = new LinkedHashMap<>();

        for (Category category : categoriesList) {
            categories.put(category.getCode(), categoryToCategoryTreeDTO(category, lang));
        }

        //get parent and add child category
        for (Category category : categoriesList) {
            if (categories.containsKey(category.getParent()))
                categories.get(category.getParent()).getChildren().add(categories.get(category.getCode()));
        }
        //get all category_attribute for sort
        final Map<Integer, SortedSet<CategoryAttributeDTO>> categoryAttributeDTOs = categoryAttributeService.findAllCategoryAttributeDTO();
        //for by get  category code in array add value
        for (Integer code : categoryAttributeDTOs.keySet()) {
         final SortedSet<CategoryAttributeDTO> attributes = categoryAttributeDTOs.get(code);
            for (CategoryAttributeDTO attributeDTO : attributes) {
                SortedSet<CategoryAttributeValueDTO> sortedSet = new TreeSet<>(Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, "")));
                sortedSet.addAll(attributeDTO.getValues());
                attributeDTO.setValues(sortedSet);
            }
        }
        //for by get category by_code in array and  add category_attributes
        for (Integer code : categories.keySet()) {
            if (categoryAttributeDTOs.containsKey(code)) {
                categories.get(code).setAttrs(categoryAttributeDTOs.get(code));
            }
        }

        //filter for first level category
        final Set<Integer> firstLevelCategories = categoriesList.stream()
                                                                  .filter((Category c) -> c.getParent() == 0)
                                                                  .map(Category::getCode)
                                                                  .collect(Collectors.toSet());
        //return sorted categoryDTO
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
        logger.debug("Request to get Category : {}", id);
        return categoryRepository.findOne(id);
    }


    @Override
    public Optional<Category> findOneByCode(int code) {
        logger.debug("Request to get Category : {}", code);
        return categoryRepository.findOneByCode(code);
    }

    @Override
    public List<Category> findByCodeInOrderByCodeAsc(List<Integer> codes) {
        logger.debug("Request to get Categories : {}", codes);
        Optional<List<Category>> optional = categoryRepository.findByCodeInOrderByCodeAsc(codes);
        if (optional.isPresent()) {
            return optional.get();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        logger.debug("Request to delete Category : {}", id);
        categoryRepository.delete(id);
        clearCache();
    }

    /**
     * Get rent categories from cache.
     *
     * @param code the code of the entity
     */
    @Override
    public LinkedList<RentCategory> getRentCategories(int code) {
        if (rentCategoryCache.size() == 0) {
            warmCache();
        }
        return rentCategoryCache.get(code);
    }


    @Override
    public List<Integer> getRentCategoriesIds(int code) {
        if (rentCategoryCache.size() == 0) {
            warmCache();
        }
        return rentCategoryCache.get(code).stream().map(rentCategory -> rentCategory.getCode()).collect(Collectors.toList());
    }

    /**
     * Exists in cache.
     *
     * @param code the code of the entity
     */
    @Override
    public boolean exists(int code) {
        if (rentCategoryCache.size() == 0) {
            warmCache();
        }
        return rentCategoryCache.containsKey(code);
    }

    private void warmCache() {
        final List<Category> categories = categoryRepository.findAll();
        final Set<Integer> parentCategories = categories.stream().map(Category::getParent).collect(Collectors.toSet());
        final Map<Integer, Category> categoriesMap = categories.stream().collect(Collectors.toMap(Category::getCode, Function.identity()));
        categories.removeIf(c -> parentCategories.contains(c.getCode()));
        final Map<Integer, LinkedList<RentCategory>> rentCategoriesMap = new HashMap();
        for (Category category : categories) {
            int code = category.getCode();
            LinkedList<RentCategory> linkedList = new LinkedList<>();
            while (code != 0) {
                Category current = categoriesMap.get(code);
                RentCategory rentCategory = new RentCategory();
                rentCategory.setCode(current.getCode());
                rentCategory.setKey(current.getKey());
                rentCategory.setTitle(current.getTitle());
                linkedList.add(0, rentCategory);
                code = current.getParent();
            }
            rentCategoriesMap.put(category.getCode(), linkedList);
        }
        rentCategoryCache.putAll(rentCategoriesMap);
    }

    private void clearCache() {
        rentCategoryCache.clear();
    }
}
