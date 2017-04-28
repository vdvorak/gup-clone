package ua.com.gup.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.domain.category.CategoryAttribute;
import ua.com.gup.service.CategoryAttributeService;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.web.rest.util.HeaderUtil;
import ua.com.gup.web.rest.util.ResponseUtil;
import ua.com.itproekt.gup.model.profiles.UserRole;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CategoryAttribute.
 */
@RestController
@RequestMapping("/api")
public class CategoryAttributeResource {

    private static final String ENTITY_NAME = "categoryAttribute";
    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    @Autowired
    private CategoryAttributeService categoryAttributeService;

    /**
     * POST  /categoryAttributes : Create a new categoryAttribute.
     *
     * @param categoryAttribute the categoryAttribute to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryAttribute, or with status 400 (Bad Request) if the categoryAttribute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/categoryAttributes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryAttribute> createCategory(@Valid @RequestBody CategoryAttribute categoryAttribute) throws URISyntaxException {
        log.debug("REST request to save new CategoryAttribute : {}", categoryAttribute);
//        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
//        }
        CategoryAttribute result = categoryAttributeService.save(categoryAttribute);
        return ResponseEntity.created(new URI("/api/categoryAttribute/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET  /categoryAttributes/:id : get the categoryAttribute by id.
     *
     * @param id the id of the CategoryAttribute to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the CategoryAttribute, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/categoryAttributes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryAttribute> getCategory(@PathVariable String id) {
        log.debug("REST request to get CategoryAttribute : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final CategoryAttribute categoryAttribute = categoryAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(categoryAttribute));
    }

    /**
     * PUT  /categoryAttributes : Updates an existing categoryAttribute.
     *
     * @param categoryAttribute the categoryAttribute to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryAttribute,
     * or with status 400 (Bad Request) if the categoryAttribute is not valid,
     * or with status 500 (Internal Server Error) if the categoryAttribute couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/categoryAttributes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryAttribute> updateCategory(@Valid @RequestBody CategoryAttribute categoryAttribute) throws URISyntaxException {
        log.debug("REST request to update CategoryAttribute : {}", categoryAttribute);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        CategoryAttribute result = categoryAttributeService.save(categoryAttribute);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * DELETE  /categoryAttributes/:id : delete the "id" categoryAttribute.
     *
     * @param id the id of the categoryAttribute to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/categoryAttributes/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        log.debug("REST request to delete CategoryAttribute : {}", id);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        categoryAttributeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /categoryAttributes : get all the categoryAttribute.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/categoryAttributes/", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryAttribute>> getAllCattegories() {
        log.debug("REST request to get a page of Categories");
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }
        final List<CategoryAttribute> categoryAttribute = categoryAttributeService.findAll();
        return ResponseEntity.ok().body(categoryAttribute);
    }
}
