package ua.com.gup.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.category.CategoryAttribute;
import ua.com.gup.repository.CategoryAttributeRepository;
import ua.com.gup.service.CategoryAttributeService;

import java.util.List;

/**
 * Service Implementation for managing CategoryAttribute.
 */
@Service
public class CategoryAttributeServiceImpl implements CategoryAttributeService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryAttributeRepository categoryAttributeRepository;

    @Autowired
    public CategoryAttributeServiceImpl(CategoryAttributeRepository categoryAttributeRepository) {
        this.categoryAttributeRepository = categoryAttributeRepository;
    }

    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttribute the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttribute save(CategoryAttribute categoryAttribute) {
        log.debug("Request to save CategoryAttribute : {}", categoryAttribute);
        return categoryAttributeRepository.save(categoryAttribute);
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<CategoryAttribute> findAll() {
        log.debug("Request to get all Categories by filter");
        return categoryAttributeRepository.findAll();
    }

    /**
     * Get the "id" categoryAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public CategoryAttribute findOne(String id) {
        log.debug("Request to get CategoryAttribute : {}", id);
        return categoryAttributeRepository.findOne(id);
    }


    /**
     * Delete the "id" categoryAttribute.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete CategoryAttribute : {}", id);
        categoryAttributeRepository.delete(id);
    }
}
