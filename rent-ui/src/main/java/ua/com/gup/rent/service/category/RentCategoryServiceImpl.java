package ua.com.gup.rent.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentCategoryMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RentCategory.
 */
@Service
public class RentCategoryServiceImpl implements RentCategoryService {

    private final Logger logger = LoggerFactory.getLogger(RentCategoryServiceImpl.class);

    @Autowired
    private ua.com.gup.rent.repository.category.RentCategoryRepository rentCategoryRepository;
    @Autowired
    private ua.com.gup.rent.service.category.attribute.RentCategoryAttributeServiceRent rentCategoryAttributeService;
    @Autowired
    private RentCategoryMapper rentCategoryMapper;

    private final Map<Integer, LinkedList<ua.com.gup.rent.model.rent.RentCategory>> rentCategoryCache = new ConcurrentHashMap<>();


    private ua.com.gup.rent.dto.category.tree.RentCategoryTreeDTO categoryToCategoryTreeDTO(ua.com.gup.rent.model.mongo.category.RentCategory rentCategory, String lang) {
        ua.com.gup.rent.dto.category.tree.RentCategoryTreeDTO rentCategoryTreeDTO = new ua.com.gup.rent.dto.category.tree.RentCategoryTreeDTO(lang);
        rentCategoryTreeDTO.setCode(rentCategory.getCode());
        rentCategoryTreeDTO.setActive(rentCategory.isActive());
        rentCategoryTreeDTO.setKey(rentCategory.getKey());
        rentCategoryTreeDTO.setTitle(rentCategory.getTitle());
        rentCategoryTreeDTO.setDescription(rentCategory.getDescription());
        rentCategoryTreeDTO.setColor(rentCategory.getColor());
        rentCategoryTreeDTO.setOrder(rentCategory.getOrder());
        return rentCategoryTreeDTO;
    }

    /**
     * Save a category.
     *
     * @param rentCategoryCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ua.com.gup.rent.model.mongo.category.RentCategory save(ua.com.gup.rent.dto.category.RentCategoryCreateDTO rentCategoryCreateDTO) {
        logger.debug("Request to save RentCategory : {}", rentCategoryCreateDTO);
        final ua.com.gup.rent.model.mongo.category.RentCategory rentCategory =  rentCategoryRepository.save(rentCategoryMapper.categoryCreateDTOToCategory(rentCategoryCreateDTO));
        clearCache();
        return rentCategory;
    }

    /**
     * Save a category.
     *
     * @param rentCategoryUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ua.com.gup.rent.model.mongo.category.RentCategory save(ua.com.gup.rent.dto.category.RentRentCategoryUpdateDTO rentCategoryUpdateDTO) {
        logger.debug("Request to save RentCategory : {}", rentCategoryUpdateDTO);
        final ua.com.gup.rent.model.mongo.category.RentCategory rentCategory = rentCategoryRepository.save(rentCategoryMapper.categoryUpdateDTOToCategory(rentCategoryUpdateDTO));
        clearCache();
        return rentCategory;
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<ua.com.gup.rent.model.mongo.category.RentCategory> findAll() {
        logger.debug("Request to get all Categories by filter");
        return rentCategoryRepository.findAll();
    }

    /**
     * Get all the categories in tree view.
     *
     * @return the list of entities
     */
    @Override
    public Collection<ua.com.gup.rent.dto.category.tree.RentCategoryTreeDTO> findAllTreeView(String lang) {
        logger.debug("Request to get all Categories in tree view");
        //get all category and sort asc by field order
        final List<ua.com.gup.rent.model.mongo.category.RentCategory> categoriesList = rentCategoryRepository.findAll(new Sort(Sort.Direction.ASC, "order"));
        //remove if active false
        categoriesList.removeIf(c -> !c.isActive());

        final Map<Integer, ua.com.gup.rent.dto.category.tree.RentCategoryTreeDTO> categories = new LinkedHashMap<>();

        for (ua.com.gup.rent.model.mongo.category.RentCategory rentCategory : categoriesList) {
            categories.put(rentCategory.getCode(), categoryToCategoryTreeDTO(rentCategory, lang));
        }

        //get parent and add child category
        for (ua.com.gup.rent.model.mongo.category.RentCategory rentCategory : categoriesList) {
            if (categories.containsKey(rentCategory.getParent()))
                categories.get(rentCategory.getParent()).getChildren().add(categories.get(rentCategory.getCode()));
        }
        //get all category_attribute for sort
        final Map<Integer, SortedSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO>> categoryAttributeDTOs = rentCategoryAttributeService.findAllCategoryAttributeDTO();
        //for by get  category code in array add value
        for (Integer code : categoryAttributeDTOs.keySet()) {
         final SortedSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO> attributes = categoryAttributeDTOs.get(code);
            for (ua.com.gup.rent.dto.category.tree.RentCategoryAttributeDTO attributeDTO : attributes) {
                SortedSet<ua.com.gup.rent.dto.category.tree.RentCategoryAttributeValueDTO> sortedSet = new TreeSet<>(Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, "")));
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
                                                                  .filter((ua.com.gup.rent.model.mongo.category.RentCategory c) -> c.getParent() == 0)
                                                                  .map(ua.com.gup.rent.model.mongo.category.RentCategory::getCode)
                                                                  .collect(Collectors.toSet());
        //return sorted categoryDTO
        return categories.entrySet().stream()
                                        .filter(e -> firstLevelCategories.contains(e.getKey()))
                                        .map(e -> e.getValue())
                                        .sorted(ua.com.gup.rent.dto.category.tree.RentCategoryTreeDTO.getCategoryTreeDTOComparator(lang))
                                        .collect(Collectors.toList());
    }

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public ua.com.gup.rent.model.mongo.category.RentCategory findOne(String id) {
        logger.debug("Request to get RentCategory : {}", id);
        return rentCategoryRepository.findOne(id);
    }


    @Override
    public Optional<ua.com.gup.rent.model.mongo.category.RentCategory> findOneByCode(int code) {
        logger.debug("Request to get RentCategory : {}", code);
        return rentCategoryRepository.findOneByCode(code);
    }

    @Override
    public List<ua.com.gup.rent.model.mongo.category.RentCategory> findByCodeInOrderByCodeAsc(List<Integer> codes) {
        logger.debug("Request to get Categories : {}", codes);
        Optional<List<ua.com.gup.rent.model.mongo.category.RentCategory>> optional = rentCategoryRepository.findByCodeInOrderByCodeAsc(codes);
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
        logger.debug("Request to delete RentCategory : {}", id);
        rentCategoryRepository.delete(id);
        clearCache();
    }

    /**
     * Get rent categories from cache.
     *
     * @param code the code of the entity
     */
    @Override
    public LinkedList<ua.com.gup.rent.model.rent.RentCategory> getRentCategories(int code) {
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
        final List<ua.com.gup.rent.model.mongo.category.RentCategory> categories = rentCategoryRepository.findAll();
        final Set<Integer> parentCategories = categories.stream().map(ua.com.gup.rent.model.mongo.category.RentCategory::getParent).collect(Collectors.toSet());
        final Map<Integer, ua.com.gup.rent.model.mongo.category.RentCategory> categoriesMap = categories.stream().collect(Collectors.toMap(ua.com.gup.rent.model.mongo.category.RentCategory::getCode, Function.identity()));
        categories.removeIf(c -> parentCategories.contains(c.getCode()));
        final Map<Integer, LinkedList<ua.com.gup.rent.model.rent.RentCategory>> rentCategoriesMap = new HashMap();
        for (ua.com.gup.rent.model.mongo.category.RentCategory category : categories) {
            int code = category.getCode();
            LinkedList<ua.com.gup.rent.model.rent.RentCategory> linkedList = new LinkedList<>();
            while (code != 0) {
                ua.com.gup.rent.model.mongo.category.RentCategory current = categoriesMap.get(code);
                ua.com.gup.rent.model.rent.RentCategory rentCategory = new ua.com.gup.rent.model.rent.RentCategory();
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
