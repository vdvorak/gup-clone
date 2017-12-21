package ua.com.gup.rent.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentOfferCategoryMapper;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.repository.category.RentOfferCategoryRepository;
import ua.com.gup.rent.service.category.attribute.RentOfferCategoryAttributeService;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryUpdateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeValueDTO;
import ua.com.gup.rent.service.dto.category.tree.RentOfferCategoryTreeDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RentOfferCategoryShort.
 */
@Service
public class RentOfferCategoryServiceImpl implements RentOfferCategoryService {

    private final Logger logger = LoggerFactory.getLogger(RentOfferCategoryServiceImpl.class);
    private final Map<Integer, LinkedList<RentOfferCategory>> rentCategoryCache = new ConcurrentHashMap<>();
    @Autowired
    private RentOfferCategoryRepository rentOfferCategoryRepository;
    @Autowired
    private RentOfferCategoryAttributeService rentOfferCategoryAttributeService;
    @Autowired
    private RentOfferCategoryMapper rentOfferCategoryMapper;

    private RentOfferCategoryTreeDTO categoryToCategoryTreeDTO(RentOfferCategory rentOfferCategory, String lang) {
        RentOfferCategoryTreeDTO rentOfferCategoryTreeDTO = new RentOfferCategoryTreeDTO(lang);
        rentOfferCategoryTreeDTO.setCode(rentOfferCategory.getCode());
        rentOfferCategoryTreeDTO.setActive(rentOfferCategory.isActive());
        rentOfferCategoryTreeDTO.setKey(rentOfferCategory.getKey());
        rentOfferCategoryTreeDTO.setTitle(rentOfferCategory.getTitle());
        rentOfferCategoryTreeDTO.setDescription(rentOfferCategory.getDescription());
        rentOfferCategoryTreeDTO.setColor(rentOfferCategory.getColor());
        rentOfferCategoryTreeDTO.setOrder(rentOfferCategory.getOrder());
        return rentOfferCategoryTreeDTO;
    }

    /**
     * Save a category.
     *
     * @param rentOfferCategoryCreateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentOfferCategory save(RentOfferCategoryCreateDTO rentOfferCategoryCreateDTO) {
        logger.debug("Request to save RentOfferCategoryShort : {}", rentOfferCategoryCreateDTO);
        final RentOfferCategory rentOfferCategory = rentOfferCategoryRepository.save(rentOfferCategoryMapper.categoryCreateDTOToCategory(rentOfferCategoryCreateDTO));
        clearCache();
        return rentOfferCategory;
    }

    /**
     * Save a category.
     *
     * @param rentOfferCategoryUpdateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RentOfferCategory save(RentOfferCategoryUpdateDTO rentOfferCategoryUpdateDTO) {
        logger.debug("Request to save RentOfferCategoryShort : {}", rentOfferCategoryUpdateDTO);
        final RentOfferCategory rentOfferCategory = rentOfferCategoryRepository.save(rentOfferCategoryMapper.categoryUpdateDTOToCategory(rentOfferCategoryUpdateDTO));
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
        return rentOfferCategoryRepository.findAll();
    }

    /**
     * Get all the categories in tree view.
     *
     * @return the list of entities
     */
    @Override
    public Collection<RentOfferCategoryTreeDTO> findAllTreeView(String lang) {
        logger.debug("Request to get all Categories in tree view");
        final List<RentOfferCategory> categoriesList = rentOfferCategoryRepository.findAll(new Sort(Sort.Direction.ASC, "order"));
        categoriesList.removeIf(c -> !c.isActive());

        final Map<Integer, RentOfferCategoryTreeDTO> categories = new LinkedHashMap<>();

        for (RentOfferCategory rentOfferCategory : categoriesList) {
            categories.put(rentOfferCategory.getCode(), categoryToCategoryTreeDTO(rentOfferCategory, lang));
        }

        //get parent and add child category
        for (RentOfferCategory rentOfferCategory : categoriesList) {
            if (categories.containsKey(rentOfferCategory.getParent()))
                categories.get(rentOfferCategory.getParent()).getChildren().add(categories.get(rentOfferCategory.getCode()));
        }
        //get all category_attribute for sort
        final Map<Integer, SortedSet<RentOfferCategoryAttributeDTO>> categoryAttributeDTOs = rentOfferCategoryAttributeService.findAllCategoryAttributeDTO();
        //for by get  category code in array add value
        for (Integer code : categoryAttributeDTOs.keySet()) {
            final Set<RentOfferCategoryAttributeDTO> attributes = categoryAttributeDTOs.get(code);
            for (RentOfferCategoryAttributeDTO attributeDTO : attributes) {
                SortedSet<RentOfferCategoryAttributeValueDTO> sortedSet = new TreeSet<>(Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, "")));
                sortedSet.addAll(attributeDTO.getValues());
                attributeDTO.setValues(sortedSet);
            }
        }
        //for by get category by_code in array and  add category_attributes
        for (Integer code : categories.keySet()) {
            logger.debug("category {} add  attributes {}", code, categoryAttributeDTOs.get(code));
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
                .sorted(RentOfferCategoryTreeDTO.getCategoryTreeDTOComparator(lang))
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
        logger.debug("Request to get RentOfferCategoryShort : {}", id);
        return rentOfferCategoryRepository.findOne(id);
    }


    @Override
    public Optional<RentOfferCategory> findOneByCode(int code) {
        logger.debug("Request to get RentOfferCategoryShort : {}", code);
        return rentOfferCategoryRepository.findOneByCode(code);
    }

    @Override
    public List<RentOfferCategory> findByCodeInOrderByCodeAsc(List<Integer> codes) {
        logger.debug("Request to get Categories : {}", codes);
        Optional<List<RentOfferCategory>> optional = rentOfferCategoryRepository.findByCodeInOrderByCodeAsc(codes);
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
        logger.debug("Request to delete RentOfferCategoryShort : {}", id);
        rentOfferCategoryRepository.delete(id);
        clearCache();
    }

    /**
     * Get rent categories from cache.
     *
     * @param code the code of the entity
     */
    @Override
    public LinkedList<RentOfferCategory> getRentOfferCategories(int code) {
        if (rentCategoryCache.size() == 0) {
            warmCache();
        }
        return rentCategoryCache.get(code);
    }


    @Override
    public List<Integer> getRentOfferCategoriesIds(int code) {
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
        final List<RentOfferCategory> categories = rentOfferCategoryRepository.findAll();
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
