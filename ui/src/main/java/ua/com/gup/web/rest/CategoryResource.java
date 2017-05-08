package ua.com.gup.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.gup.domain.category.Category;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.CategoryTreeDTO;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.web.rest.util.HeaderUtil;
import ua.com.gup.web.rest.util.MD5Util;
import ua.com.gup.web.rest.util.ResponseUtil;
import ua.com.itproekt.gup.model.profiles.UserRole;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

    private static final String ENTITY_NAME = "category";
    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);
    private String categoriesTreeViewETag = "categoriesTreeViewEtag";
    private ResponseEntity<String> cacheCategoriesTreeViewResponse;

    @Autowired
    private CategoryService categoryService;

    /**
     * POST  /categories : Create a new category.
     *
     * @param category the category to create
     * @return the ResponseEntity with status 201 (Created) and with body the new category, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to save new Category : {}", category);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        Category result = categoryService.save(category);
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
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        log.debug("REST request to get Category : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final Category category = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(category));
    }

    /**
     * PUT  /categories : Updates an existing category.
     *
     * @param category the category to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated category,
     * or with status 400 (Bad Request) if the category is not valid,
     * or with status 500 (Internal Server Error) if the category couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/categories", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to update Category : {}", category);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        Category result = categoryService.save(category);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * DELETE  /categories/:id : delete the "id" category.
     *
     * @param id the id of the category to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        log.debug("REST request to delete Category : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        categoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/categories/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCattegories() {
        log.debug("REST request to get all Categories");
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }


    /**
     * PUT  /categories : Updates cache.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the updated category,
     * or with status 400 (Bad Request) if the category is not valid,
     * or with status 500 (Internal Server Error) if the category couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/categories/update-cache", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCategoriesTreeCache() throws URISyntaxException {
        log.debug("REST request to update cache Categor");
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        findAllTreeView();
        return ResponseEntity.ok().body(null);
    }

    /**
     * GET  /categories/tree-view : get all the categories in tree view.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/categories/tree-view", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public ResponseEntity<String> getAllCategoriesTreeView(WebRequest webRequest) {
        log.debug("REST request to get categories in tree view");
        if (webRequest.checkNotModified(categoriesTreeViewETag)) {
            return null;
        }
        if(cacheCategoriesTreeViewResponse == null) {
            findAllTreeView();
        }
        return cacheCategoriesTreeViewResponse;
    }

    private void findAllTreeView() {
        Collection<CategoryTreeDTO> categoriesTreeView = categoryService.findAllTreeView();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        final ObjectWriter ow = mapper.writer();
        String json = "";
        try {
             json = ow.writeValueAsString(categoriesTreeView);
        } catch (JsonProcessingException e) {
            log.error("Json processing exception", e);
        }
        categoriesTreeViewETag = MD5Util.getMD5Hex(json);
        cacheCategoriesTreeViewResponse = ResponseEntity
                .ok()
                .header("Content-Type", "text/html; charset=utf-8")
                .eTag(categoriesTreeViewETag)
                .body(json);
    }
}
