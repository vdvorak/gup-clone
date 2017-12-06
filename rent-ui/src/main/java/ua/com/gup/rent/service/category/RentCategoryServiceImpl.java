package ua.com.gup.rent.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentOfferCategoryMapper;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.repository.category.RentCategoryRepository;
import ua.com.gup.rent.service.category.attribute.RentCategoryAttributeService;
import ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentCategoryUpdateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeValueDTO;
import ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RentOfferCategory.
 */
@Service
public class RentCategoryServiceImpl implements RentCategoryService {

    private final Logger logger = LoggerFactory.getLogger(RentCategoryServiceImpl.class);

    @Autowired
    private RentCategoryRepository rentCategoryRepository;
    @Autowired
    private RentCategoryAttributeService rentCategoryAttributeService;
    @Autowired
    private RentOfferCategoryMapper rentOfferCategoryMapper;

    private final Map<Integer, LinkedList<RentOfferCategory>> rentCategoryCache = new ConcurrentHashMap<>();


    private RentCategoryTreeDTO categoryToCategoryTreeDTO(RentOfferCategory rentOfferCategory, String lang) {
        RentCategoryTreeDTO rentCategoryTreeDTO = new RentCategoryTreeDTO(lang);
        rentCategoryTreeDTO.setCode(rentOfferCategory.getCode());
        rentCategoryTreeDTO.setActive(rentOfferCategory.isActive());
        rentCategoryTreeDTO.setKey(rentOfferCategory.getKey());
        rentCategoryTreeDTO.setTitle(rentOfferCategory.getTitle());
        rentCategoryTreeDTO.setDescription(rentOfferCategory.getDescription());
        rentCategoryTreeDTO.setColor(rentOfferCategory.getColor());
        rentCategoryTreeDTO.setOrder(rentOfferCategory.getOrder());
        return rentCategoryTreeDTO;
    }

    /**
     * Save a category.
     *
     * @param rentCategoryCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentOfferCategory save(RentCategoryCreateDTO rentCategoryCreateDTO) {
        logger.debug("Request to save RentOfferCategory : {}", rentCategoryCreateDTO);
        final RentOfferCategory rentOfferCategory =  rentCategoryRepository.save(rentOfferCategoryMapper.categoryCreateDTOToCategory(rentCategoryCreateDTO));
        clearCache();
        return rentOfferCategory;
    }

    /**
     * Save a category.
     *
     * @param rentCategoryUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentOfferCategory save(RentCategoryUpdateDTO rentCategoryUpdateDTO) {
        logger.debug("Request to save RentOfferCategory : {}", rentCategoryUpdateDTO);
        final RentOfferCategory rentOfferCategory = rentCategoryRepository.save(rentOfferCategoryMapper.categoryUpdateDTOToCategory(rentCategoryUpdateDTO));
        clearCache();
        return rentOfferCategory;
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<RentOfferCategory> findAll() {
        logger.debug("Request to get all Categories by filter");
        return rentCategoryRepository.findAll();
    }

    /**
     * Get all the categories in tree view.
     *
     * @return the list of entities
     */
    @Override
    public Collection<ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO> findAllTreeView(String lang) {
        logger.debug("Request to get all Categories in tree view");
        final List<RentOfferCategory> categoriesList = rentCategoryRepository.findAll(new Sort(Sort.Direction.ASC, "order"));
        categoriesList.removeIf(c -> !c.isActive());

        final Map<Integer, ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO> categories = new LinkedHashMap<>();

        for (RentOfferCategory rentOfferCategory : categoriesList) {
            categories.put(rentOfferCategory.getCode(), categoryToCategoryTreeDTO(rentOfferCategory, lang));
        }

        //get parent and add child category
        for (RentOfferCategory rentOfferCategory : categoriesList) {
            if (categories.containsKey(rentOfferCategory.getParent()))
                categories.get(rentOfferCategory.getParent()).getChildren().add(categories.get(rentOfferCategory.getCode()));
        }
        //get all category_attribute for sort
        final Map<Integer, SortedSet<RentCategoryAttributeDTO>> categoryAttributeDTOs = rentCategoryAttributeService.findAllCategoryAttributeDTO();
        //for by get  category code in array add value
        for (Integer code : categoryAttributeDTOs.keySet()) {
         final SortedSet<RentCategoryAttributeDTO> attributes = categoryAttributeDTOs.get(code);
            for (RentCategoryAttributeDTO attributeDTO : attributes) {
                SortedSet<RentCategoryAttributeValueDTO> sortedSet = new TreeSet<>(Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, "")));
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
                                                                  .filter((RentOfferCategory c) -> c.getParent() == 0)
                                                                  .map(RentOfferCategory::getCode)
                                                                  .collect(Collectors.toSet());
        //return sorted categoryDTO
        return categories.entrySet().stream()
                                        .filter(e -> firstLevelCategories.contains(e.getKey()))
                                        .map(e -> e.getValue())
                                        .sorted(ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO.getCategoryTreeDTOComparator(lang))
                                        .collect(Collectors.toList());
    }

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public RentOfferCategory findOne(String id) {
        logger.debug("Request to get RentOfferCategory : {}", id);
        return rentCategoryRepository.findOne(id);
    }


    @Override
    public Optional<RentOfferCategory> findOneByCode(int code) {
        logger.debug("Request to get RentOfferCategory : {}", code);
        return rentCategoryRepository.findOneByCode(code);
    }

    @Override
    public List<RentOfferCategory> findByCodeInOrderByCodeAsc(List<Integer> codes) {
        logger.debug("Request to get Categories : {}", codes);
        Optional<List<RentOfferCategory>> optional = rentCategoryRepository.findByCodeInOrderByCodeAsc(codes);
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
        logger.debug("Request to delete RentOfferCategory : {}", id);
        rentCategoryRepository.delete(id);
        clearCache();
    }

    /**
     * Get rent categories from cache.
     *
     * @param code the code of the entity
     */
    @Override
    public LinkedList<RentOfferCategory> getRentCategories(int code) {
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
        final List<RentOfferCategory> categories = rentCategoryRepository.findAll();
        final Set<Integer> parentCategories = categories.stream().map(RentOfferCategory::getParent).collect(Collectors.toSet());
        final Map<Integer, RentOfferCategory> categoriesMap = categories.stream().collect(Collectors.toMap(RentOfferCategory::getCode, Function.identity()));
        categories.removeIf(c -> parentCategories.contains(c.getCode()));
        final Map<Integer, LinkedList<RentOfferCategory>> rentCategoriesMap = new HashMap();
        for (RentOfferCategory category : categories) {
            int code = category.getCode();
            LinkedList<RentOfferCategory> linkedList = new LinkedList<>();
            while (code != 0) {
                RentOfferCategory current = categoriesMap.get(code);
                RentOfferCategory rentOfferCategory = new RentOfferCategory();
                rentOfferCategory.setCode(current.getCode());
                rentOfferCategory.setKey(current.getKey());
                rentOfferCategory.setTitle(current.getTitle());
                linkedList.add(0, rentOfferCategory);
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
