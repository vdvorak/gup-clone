package ua.com.gup.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.gup.domain.Category;
import ua.com.gup.domain.CategoryAttribute;
import ua.com.gup.service.CategoryAttributeService;
import ua.com.gup.service.CategoryService;
import ua.com.gup.dto.category.CategoryAttributeCreateDTO;
import ua.com.gup.dto.category.CategoryAttributeUpdateDTO;
import ua.com.gup.dto.category.CategoryCreateDTO;
import ua.com.gup.dto.category.CategoryUpdateDTO;
import ua.com.gup.dto.category.tree.CategoryTreeDTO;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.web.rest.util.HeaderUtil;
import ua.com.gup.web.rest.util.MD5Util;
import ua.com.gup.web.rest.util.ResponseUtil;
import ua.com.gup.web.rest.validator.CategoryAttributeDTOValidator;
import ua.com.gup.web.rest.validator.CategoryDTOValidator;
import ua.com.gup.model.profiles.UserRole;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

    private static final String ENTITY_NAME = "category";
    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);
    private Map<String, String> categoriesTreeViewETagMap = new ConcurrentHashMap<>();
    private Map<String, ResponseEntity<Collection<CategoryTreeDTO>>> cacheCategoriesTreeViewResponseMap = new ConcurrentHashMap<>();

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryAttributeService categoryAttributeService;

    @Autowired
    private CategoryDTOValidator categoryDTOValidator;

    @Autowired
    private CategoryAttributeDTOValidator categoryAttributeDTOValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            final Class<?> clazz = binder.getTarget().getClass();
            if (CategoryCreateDTO.class.equals(clazz) || CategoryUpdateDTO.class.equals(clazz)) {
                binder.addValidators(categoryDTOValidator);
            }
            if (CategoryAttributeCreateDTO.class.equals(clazz) || CategoryAttributeUpdateDTO.class.equals(clazz)) {
                binder.addValidators(categoryAttributeDTOValidator);
            }
        }
    }

    /**
     * POST  /category-attributes : Create a new categoryAttribute.
     *
     * @param categoryAttribute the categoryAttribute to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryAttribute, or with status 400 (Bad Request) if the categoryAttribute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/category-attributes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryAttribute> createCategory(@Valid @RequestBody CategoryAttributeCreateDTO categoryAttribute) throws URISyntaxException {
        log.debug("REST request to save new CategoryAttribute : {}", categoryAttribute);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        CategoryAttribute result = categoryAttributeService.save(categoryAttribute);
        clearCache();
        return ResponseEntity.created(new URI("/api/categoryAttribute/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET  /category-attributes/:id : get the categoryAttribute by id.
     *
     * @param id the id of the CategoryAttribute to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the CategoryAttribute, or with status 404 (Not Found)
     */
    @CrossOrigin
    @RequestMapping(value = "/category-attributes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryAttribute> getCategoryAttributes(@PathVariable String id) {
        log.debug("REST request to get CategoryAttribute : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final CategoryAttribute categoryAttribute = categoryAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(categoryAttribute));
    }

    /**
     * PUT  /category-attributes : Updates an existing categoryAttribute.
     *
     * @param categoryAttribute the categoryAttribute to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryAttribute,
     * or with status 400 (Bad Request) if the categoryAttribute is not valid,
     * or with status 500 (Internal Server Error) if the categoryAttribute couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/category-attributes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryAttribute> updateCategory(@Valid @RequestBody CategoryAttributeUpdateDTO categoryAttribute) throws URISyntaxException {
        log.debug("REST request to update CategoryAttribute : {}", categoryAttribute);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        CategoryAttribute result = categoryAttributeService.save(categoryAttribute);
        clearCache();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * DELETE  /category-attributes/:id : delete the "id" categoryAttribute.
     *
     * @param id the id of the categoryAttribute to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @CrossOrigin
    @RequestMapping(value = "/category-attributes/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCategoryAttribute(@PathVariable String id) {
        log.debug("REST request to delete CategoryAttribute : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        categoryAttributeService.delete(id);
        clearCache();
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /category-attributes : get all the categoryAttribute.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @RequestMapping(value = "/category-attributes/", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryAttribute>> getAllCategoryAttributes() {
        log.debug("REST request to get a page of Categories");
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final List<CategoryAttribute> categoryAttribute = categoryAttributeService.findAll();
        return ResponseEntity.ok().body(categoryAttribute);
    }

    /**
     * POST  /categories : Create a new category.
     *
     * @param categoryCreateDTO the category to create
     * @return the ResponseEntity with status 201 (Created) and with body the new category, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) throws URISyntaxException {
        log.debug("REST request to save new Category : {}", categoryCreateDTO);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        Category result = categoryService.save(categoryCreateDTO);
        clearCache();
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET  /categories/:id : get the category by id.
     *
     * @param id the id of the Category to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the Category, or with status 404 (Not Found)
     */
    @CrossOrigin
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        log.debug("REST request to get Category : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final Category category = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(category));
    }

    /**
     * PUT  /categories : Updates an existing category.
     *
     * @param categoryUpdateDTO the category to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated category,
     * or with status 400 (Bad Request) if the category is not valid,
     * or with status 500 (Internal Server Error) if the category couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/categories", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) throws URISyntaxException {
        log.debug("REST request to update Category : {}", categoryUpdateDTO);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        Category result = categoryService.save(categoryUpdateDTO);
        clearCache();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * DELETE  /categories/:id : delete the "id" category.
     *
     * @param id the id of the category to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @CrossOrigin
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        log.debug("REST request to delete Category : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        categoryService.delete(id);
        clearCache();
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @RequestMapping(value = "/categories/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        log.debug("REST request to get all Categories");
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    /**
     * GET  /categories/tree-view : get all the categories in tree view.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @RequestMapping(value = "/categories/tree-view", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CategoryTreeDTO>> getAllCategoriesTreeView(@RequestParam(defaultValue = "ru") String lang, WebRequest webRequest) {
        log.debug("REST request to get categories in tree view");
        if (webRequest.checkNotModified(categoriesTreeViewETagMap.getOrDefault(lang, "defaultValue"))) {
            return null;
        }
        if (cacheCategoriesTreeViewResponseMap.get(lang) == null) {
            findAllTreeView(lang);
        }
        return cacheCategoriesTreeViewResponseMap.get(lang);
    }

    private void findAllTreeView(String lang) {
        Collection<CategoryTreeDTO> categoriesTreeView = categoryService.findAllTreeView(lang);
        final ObjectWriter ow = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .modules(new JavaTimeModule()).build().writer();
        String json = "";
        try {
            json = ow.writeValueAsString(categoriesTreeView);
        } catch (JsonProcessingException e) {
            log.error("Json processing exception", e);
        }
        categoriesTreeViewETagMap.put(lang, MD5Util.getMD5Hex(json));
        cacheCategoriesTreeViewResponseMap.put(lang, ResponseEntity
                .ok()
                .eTag(categoriesTreeViewETagMap.get(lang))
                .body(categoriesTreeView));
    }

    private void clearCache() {
        cacheCategoriesTreeViewResponseMap.clear();
        categoriesTreeViewETagMap.clear();
    }
}
