package ua.com.gup.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.category.Category;
import ua.com.gup.repository.CategoryRepository;
import ua.com.gup.service.CategoryService;

import java.util.List;

/**
 * Service Implementation for managing Category.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Save a category.
     *
     * @param category the entity to save
     * @return the persisted entity
     */
    @Override
    public Category save(Category category) {
        log.debug("Request to save Category : {}", category);
        return categoryRepository.save(category);
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities
     */
    @Override
    public List<Category> findAll() {
        log.debug("Request to get all Categories by filter");
        return categoryRepository.findAll();
    }

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Category findOne(String id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findOne(id);
    }


    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.delete(id);
    }
}
