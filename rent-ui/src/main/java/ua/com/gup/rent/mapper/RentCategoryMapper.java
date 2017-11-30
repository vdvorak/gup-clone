package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.category.Category;
import ua.com.gup.rent.service.sequence.SequenceService;

@Component
public class RentCategoryMapper {

    private static final String CATEGORY_SEQUENCE_ID = "rent_category_sequence";

    @Autowired
    private SequenceService sequenceService;

    public Category categoryCreateDTOToCategory(ua.com.gup.rent.dto.category.RentCategoryCreateDTO rentCategoryCreateDTO) {
        Category category = new Category();
        fromCategoryCreateDTOToCategory(rentCategoryCreateDTO, category);
        category.setCode((int) sequenceService.getNextSequenceValue(CATEGORY_SEQUENCE_ID));
        return category;
    }

    public Category categoryUpdateDTOToCategory(ua.com.gup.rent.dto.category.RentRentCategoryUpdateDTO rentCategoryUpdateDTO) {
        Category category = new Category();
        fromCategoryCreateDTOToCategory(rentCategoryUpdateDTO, category);
        category.setCode(rentCategoryUpdateDTO.getCode());
        category.setId(rentCategoryUpdateDTO.getId());
        return category;
    }

    private void fromCategoryCreateDTOToCategory(ua.com.gup.rent.dto.category.RentCategoryCreateDTO source, Category target) {
        target.setActive(source.isActive());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setKey(source.getKey());
        target.setParent(source.getParent());
        target.setColor(source.getColor());
        target.setOrder(source.getOrder());
    }
}
