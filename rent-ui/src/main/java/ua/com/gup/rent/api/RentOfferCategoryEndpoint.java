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
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
import ua.com.gup.rent.service.category.attribute.RentOfferCategoryAttributeService;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryUpdateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeCreateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeUpdateDTO;
import ua.com.gup.rent.service.dto.category.tree.RentOfferCategoryTreeDTO;
import ua.com.gup.rent.validator.category.RentOfferCategoryDTOValidator;
import ua.com.gup.rent.validator.category.attribute.RentOfferCategoryAttributeDTOValidator;

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
public class RentOfferCategoryEndpoint {

    private static final String ENTITY_NAME = "rent.category";
    private final Logger logger = LoggerFactory.getLogger(RentOfferCategoryEndpoint.class);
    private Map<String, String> categoriesTreeViewETagMap = new ConcurrentHashMap<>();
    private Map<String, ResponseEntity<Collection<RentOfferCategoryTreeDTO>>> cacheCategoriesTreeViewResponseMap = new ConcurrentHashMap<>();

    @Autowired
    private RentOfferCategoryService rentOfferCategoryService;

    @Autowired
    private RentOfferCategoryAttributeService rentOfferCategoryAttributeService;

    @Autowired
    private RentOfferCategoryDTOValidator rentOfferCategoryDTOValidator;

    @Autowired
    private RentOfferCategoryAttributeDTOValidator rentOfferCategoryAttributeDTOValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            final Class<?> clazz = binder.getTarget().getClass();
            if (RentOfferCategoryCreateDTO.class.equals(clazz) || RentOfferCategoryUpdateDTO.class.equals(clazz)) {
                binder.addValidators(rentOfferCategoryDTOValidator);
            }
            if (RentOfferCategoryAttributeCreateDTO.class.equals(clazz) || RentOfferCategoryAttributeUpdateDTO.class.equals(clazz)) {
                binder.addValidators(rentOfferCategoryAttributeDTOValidator);
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
     *  if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<RentOfferCategoryAttribute> createCategory(@Valid @RequestBody RentOfferCategoryAttributeCreateDTO categoryAttribute) throws URISyntaxException {
        logger.debug("REST request to save new RentOfferCategoryAttribute : {}", categoryAttribute);
        RentOfferCategoryAttribute result = rentOfferCategoryAttributeService.save(categoryAttribute);
        clearCache();
        return ResponseEntity.created(new URI("/categoryAttribute/" + result.getId()))
                .body(result);
    }

    /**
     * GET  /category-attributes/:id : get the categoryAttribute by id.
     *
     * @param id the id of the RentOfferCategoryAttribute to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the RentOfferCategoryAttribute, or with status 404 (Not Found)
     */

    @RequestMapping(value = "/category-attributes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *     if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<RentOfferCategoryAttribute> getCategoryAttributes(@PathVariable String id) {
        logger.debug("REST request to get RentOfferCategoryAttribute : {}", id);
        final RentOfferCategoryAttribute rentOfferCategoryAttribute = rentOfferCategoryAttributeService.findOne(id);
        return ua.com.gup.rent.util.RentResponseUtil.wrapOrNotFound(Optional.ofNullable(rentOfferCategoryAttribute));
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
     *         if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }

     */
    public ResponseEntity<RentOfferCategoryAttribute> updateCategory(@Valid @RequestBody RentOfferCategoryAttributeUpdateDTO categoryAttribute) throws URISyntaxException {
        logger.debug("REST request to update RentOfferCategoryAttribute : {}", categoryAttribute);
        RentOfferCategoryAttribute result = rentOfferCategoryAttributeService.save(categoryAttribute);
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
     *  if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<Void> deleteCategoryAttribute(@PathVariable String id) {
        logger.debug("REST request to delete RentOfferCategoryAttribute : {}", id);
        rentOfferCategoryAttributeService.delete(id);
        clearCache();
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /category-attributes : get all the categoryAttribute.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */

    @RequestMapping(value = "/category-attributes/", method = RequestMethod.GET)
    /**
     *         if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }

     */

    public ResponseEntity<List<RentOfferCategoryAttribute>> getAllCategoryAttributes() {
        logger.debug("REST request to get a page of Categories");
        final List<RentOfferCategoryAttribute> rentOfferCategoryAttribute = rentOfferCategoryAttributeService.findAll();
        return ResponseEntity.ok().body(rentOfferCategoryAttribute);
    }

    /**
     * POST  /categories : Create a new category.
     *
     * @param rentOfferCategoryCreateDTO the category to create
     * @return the ResponseEntity with status 201 (Created) and with body the new category, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @RequestMapping(value = "/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *  if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<RentOfferCategory> createCategory(@Valid @RequestBody RentOfferCategoryCreateDTO rentOfferCategoryCreateDTO) throws URISyntaxException {
        logger.debug("REST request to save new RentOfferCategoryShort : {}", rentOfferCategoryCreateDTO);
        RentOfferCategory result = rentOfferCategoryService.save(rentOfferCategoryCreateDTO);
        clearCache();
        return ResponseEntity.created(new URI("/categories/" + result.getId()))
                .body(result);
    }

    /**
     * GET  /categories/:id : get the category by id.
     *
     * @param id the id of the RentOfferCategoryShort to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the RentOfferCategoryShort, or with status 404 (Not Found)
     */

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *         if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }

     */
    public ResponseEntity<RentOfferCategory> getCategory(@PathVariable String id) {
        logger.debug("REST request to get RentOfferCategoryShort : {}", id);

        final RentOfferCategory rentOfferCategory = rentOfferCategoryService.findOne(id);
        return ua.com.gup.rent.util.RentResponseUtil.wrapOrNotFound(Optional.ofNullable(rentOfferCategory));
    }

    /**
     * PUT  /categories : Updates an existing category.
     *
     * @param rentOfferCategoryUpdateDTO the category to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated category,
     * or with status 400 (Bad Request) if the category is not valid,
     * or with status 500 (Internal Server Error) if the category couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @RequestMapping(value = "/categories", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     *  if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<RentOfferCategory> updateCategory(@Valid @RequestBody RentOfferCategoryUpdateDTO rentOfferCategoryUpdateDTO) throws URISyntaxException {
        logger.debug("REST request to update RentOfferCategoryShort : {}", rentOfferCategoryUpdateDTO);

        RentOfferCategory result = rentOfferCategoryService.save(rentOfferCategoryUpdateDTO);
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
     *   if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        logger.debug("REST request to delete RentOfferCategoryShort : {}", id);
        rentOfferCategoryService.delete(id);
        clearCache();
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */

    @RequestMapping(value = "/categories/", method = RequestMethod.GET)
    /**
     *  if (!SecurityUtils.isCurrentUserInRole(CommonUserRole.ROLE_ADMIN.name())) {
     return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
     }
     */
    public ResponseEntity<List<RentOfferCategory>> getAllCategories() {
        logger.debug("REST request to get all Categories");

        final List<RentOfferCategory> categories = rentOfferCategoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    /**
     * GET  /categories/tree-view : get all the categories in tree view.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */

    @RequestMapping(value = "/categories/tree-view", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<RentOfferCategoryTreeDTO>> getAllCategoriesTreeView(@RequestParam(defaultValue = "ru") String lang, WebRequest webRequest) {
        logger.debug("REST request to get rent categories in tree view");
        if (webRequest.checkNotModified(categoriesTreeViewETagMap.getOrDefault(lang, "defaultValue"))) {
            return null;
        }
        if (cacheCategoriesTreeViewResponseMap.get(lang) == null) {
            findAllTreeView(lang);
        }
        return cacheCategoriesTreeViewResponseMap.get(lang);
    }

    private void findAllTreeView(String lang) {
        Collection<RentOfferCategoryTreeDTO> categoriesTreeView = rentOfferCategoryService.findAllTreeView(lang);
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
