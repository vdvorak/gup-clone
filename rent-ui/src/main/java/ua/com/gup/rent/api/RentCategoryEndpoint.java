package ua.com.gup.rent.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.gup.rent.validator.category.attribute.RentCategoryAttributeDTOValidator;
import ua.com.gup.rent.validator.category.RentCategoryDTOValidator;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(path = "/api")
public class RentCategoryEndpoint {

    private static final String ENTITY_NAME = "rent.category";
    private final Logger logger = LoggerFactory.getLogger(RentCategoryEndpoint.class);
    private Map<String, String> categoriesTreeViewETagMap = new ConcurrentHashMap<>();
    private Map<String, ResponseEntity<Collection<ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO>>> cacheCategoriesTreeViewResponseMap = new ConcurrentHashMap<>();

    @Autowired
    private ua.com.gup.rent.service.category.RentCategoryService rentCategoryService;

    @Autowired
    private ua.com.gup.rent.service.category.attribute.RentCategoryAttributeServiceRent rentCategoryAttributeService;

    @Autowired
    private RentCategoryDTOValidator rentCategoryDTOValidator;

    @Autowired
    private RentCategoryAttributeDTOValidator rentCategoryAttributeDTOValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            final Class<?> clazz = binder.getTarget().getClass();
            if (ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO.class.equals(clazz) || ua.com.gup.rent.service.dto.category.RentRentCategoryUpdateDTO.class.equals(clazz)) {
                binder.addValidators(rentCategoryDTOValidator);
            }
            if (ua.com.gup.rent.service.dto.category.RentCategoryAttributeCreateDTO.class.equals(clazz) || ua.com.gup.rent.service.dto.category.RentRentCategoryAttributeUpdateDTO.class.equals(clazz)) {
                binder.addValidators(rentCategoryAttributeDTOValidator);
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
    @RequestMapping(value = "/category-attributes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

    /**
     *  if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> createCategory(@Valid @RequestBody ua.com.gup.rent.service.dto.category.RentCategoryAttributeCreateDTO categoryAttribute) throws URISyntaxException {
        logger.debug("REST request to save new RentCategoryAttribute : {}", categoryAttribute);
        ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute result = rentCategoryAttributeService.save(categoryAttribute);
        clearCache();
        return ResponseEntity.created(new URI("/categoryAttribute/" + result.getId()))
                .headers(ua.com.gup.rent.util.RentHeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET  /category-attributes/:id : get the categoryAttribute by id.
     *
     * @param id the id of the RentCategoryAttribute to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the RentCategoryAttribute, or with status 404 (Not Found)
     */

    @RequestMapping(value = "/category-attributes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *     if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> getCategoryAttributes(@PathVariable String id) {
        logger.debug("REST request to get RentCategoryAttribute : {}", id);
        final ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute rentCategoryAttribute = rentCategoryAttributeService.findOne(id);
        return ua.com.gup.rent.util.RentResponseUtil.wrapOrNotFound(Optional.ofNullable(rentCategoryAttribute));
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
    @RequestMapping(value = "/category-attributes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *         if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }

     */
    public ResponseEntity<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> updateCategory(@Valid @RequestBody ua.com.gup.rent.service.dto.category.RentRentCategoryAttributeUpdateDTO categoryAttribute) throws URISyntaxException {
        logger.debug("REST request to update RentCategoryAttribute : {}", categoryAttribute);
        ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute result = rentCategoryAttributeService.save(categoryAttribute);
        clearCache();
        return ua.com.gup.rent.util.RentResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * DELETE  /category-attributes/:id : delete the "id" categoryAttribute.
     *
     * @param id the id of the categoryAttribute to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/category-attributes/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *  if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<Void> deleteCategoryAttribute(@PathVariable String id) {
        logger.debug("REST request to delete RentCategoryAttribute : {}", id);
        rentCategoryAttributeService.delete(id);
        clearCache();
        return ResponseEntity.ok().headers(ua.com.gup.rent.util.RentHeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /category-attributes : get all the categoryAttribute.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */

    @RequestMapping(value = "/category-attributes/", method = RequestMethod.GET)
    /**
     *         if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }

     */

    public ResponseEntity<List<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute>> getAllCategoryAttributes() {
        logger.debug("REST request to get a page of Categories");
        final List<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> rentCategoryAttribute = rentCategoryAttributeService.findAll();
        return ResponseEntity.ok().body(rentCategoryAttribute);
    }

    /**
     * POST  /categories : Create a new category.
     *
     * @param rentCategoryCreateDTO the category to create
     * @return the ResponseEntity with status 201 (Created) and with body the new category, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @RequestMapping(value = "/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *  if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<ua.com.gup.rent.model.mongo.category.RentCategory> createCategory(@Valid @RequestBody ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO rentCategoryCreateDTO) throws URISyntaxException {
        logger.debug("REST request to save new RentCategory : {}", rentCategoryCreateDTO);
        ua.com.gup.rent.model.mongo.category.RentCategory result = rentCategoryService.save(rentCategoryCreateDTO);
        clearCache();
        return ResponseEntity.created(new URI("/categories/" + result.getId()))
                .headers(ua.com.gup.rent.util.RentHeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET  /categories/:id : get the category by id.
     *
     * @param id the id of the RentCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the RentCategory, or with status 404 (Not Found)
     */

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *         if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }

     */
    public ResponseEntity<ua.com.gup.rent.model.mongo.category.RentCategory> getCategory(@PathVariable String id) {
        logger.debug("REST request to get RentCategory : {}", id);

        final ua.com.gup.rent.model.mongo.category.RentCategory rentCategory = rentCategoryService.findOne(id);
        return ua.com.gup.rent.util.RentResponseUtil.wrapOrNotFound(Optional.ofNullable(rentCategory));
    }

    /**
     * PUT  /categories : Updates an existing category.
     *
     * @param rentCategoryUpdateDTO the category to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated category,
     * or with status 400 (Bad Request) if the category is not valid,
     * or with status 500 (Internal Server Error) if the category couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @RequestMapping(value = "/categories", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *  if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<ua.com.gup.rent.model.mongo.category.RentCategory> updateCategory(@Valid @RequestBody ua.com.gup.rent.service.dto.category.RentRentCategoryUpdateDTO rentCategoryUpdateDTO) throws URISyntaxException {
        logger.debug("REST request to update RentCategory : {}", rentCategoryUpdateDTO);

        ua.com.gup.rent.model.mongo.category.RentCategory result = rentCategoryService.save(rentCategoryUpdateDTO);
        clearCache();
        return ua.com.gup.rent.util.RentResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * DELETE  /categories/:id : delete the "id" category.
     *
     * @param id the id of the category to delete
     * @return the ResponseEntity with status 200 (OK)
     */

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *   if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        logger.debug("REST request to delete RentCategory : {}", id);
        rentCategoryService.delete(id);
        clearCache();
        return ResponseEntity.ok().headers(ua.com.gup.rent.util.RentHeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */

    @RequestMapping(value = "/categories/", method = RequestMethod.GET)
    /**
     *  if (!SecurityUtils.isCurrentUserInRole(RentUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<List<ua.com.gup.rent.model.mongo.category.RentCategory>> getAllCategories() {
        logger.debug("REST request to get all Categories");

        final List<ua.com.gup.rent.model.mongo.category.RentCategory> categories = rentCategoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    /**
     * GET  /categories/tree-view : get all the categories in tree view.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */

    @RequestMapping(value = "/categories/tree-view", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO>> getAllCategoriesTreeView(@RequestParam(defaultValue = "ru") String lang, WebRequest webRequest) {
        logger.debug("REST request to get categories in tree view");
        if (webRequest.checkNotModified(categoriesTreeViewETagMap.getOrDefault(lang, "defaultValue"))) {
            return null;
        }
        if (cacheCategoriesTreeViewResponseMap.get(lang) == null) {
            findAllTreeView(lang);
        }
        return cacheCategoriesTreeViewResponseMap.get(lang);
    }

    private void findAllTreeView(String lang) {
        Collection<ua.com.gup.rent.service.dto.category.tree.RentCategoryTreeDTO> categoriesTreeView = rentCategoryService.findAllTreeView(lang);
        final ObjectWriter ow = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .modules(new JavaTimeModule()).build().writer();
        String json = "";
        try {
            json = ow.writeValueAsString(categoriesTreeView);
        } catch (JsonProcessingException e) {
            logger.error("Json processing exception", e);
        }
        categoriesTreeViewETagMap.put(lang, ua.com.gup.rent.util.RentMD5Util.getMD5Hex(json));
        cacheCategoriesTreeViewResponseMap.put(lang, ResponseEntity.ok().eTag(categoriesTreeViewETagMap.get(lang)).body(categoriesTreeView));
    }

    private void clearCache() {
        cacheCategoriesTreeViewResponseMap.clear();
        categoriesTreeViewETagMap.clear();
    }
}
