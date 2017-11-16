package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.dto.category.CategoryCreateDTO;
import ua.com.gup.rent.dto.category.CategoryUpdateDTO;
import ua.com.gup.rent.model.mongo.category.Category;
import ua.com.gup.rent.service.sequence.SequenceService;

@Component
public class CategoryMapper {

    private static final String CATEGORY_SEQUENCE_ID = "category_sequence";

    @Autowired
    private SequenceService sequenceService;

    public Category categoryCreateDTOToCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        fromCategoryCreateDTOToCategory(categoryCreateDTO, category);
        category.setCode((int) sequenceService.getNextSequenceValue(CATEGORY_SEQUENCE_ID));
        return category;
    }

    public Category categoryUpdateDTOToCategory(CategoryUpdateDTO categoryUpdateDTO) {
        Category category = new Category();
        fromCategoryCreateDTOToCategory(categoryUpdateDTO, category);
        category.setCode(categoryUpdateDTO.getCode());
        category.setId(categoryUpdateDTO.getId());
        return category;
    }

    private void fromCategoryCreateDTOToCategory(CategoryCreateDTO source, Category target) {
        target.setActive(source.isActive());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setKey(source.getKey());
        target.setParent(source.getParent());
        target.setColor(source.getColor());
        target.setOrder(source.getOrder());
    }
}
