package ua.com.gup.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.CategoryAttribute;
import ua.com.gup.service.SequenceService;
import ua.com.gup.dto.category.CategoryAttributeCreateDTO;
import ua.com.gup.dto.category.CategoryAttributeUpdateDTO;

@Component
public class CategoryAttributeMapper {

    private static final String CATEGORY_ATTRIBUTE_SEQUENCE_ID = "category_attribute_sequence";

    @Autowired
    private SequenceService sequenceService;

    public CategoryAttribute categoryAttributeCreateDTOToCategoryAttribute(CategoryAttributeCreateDTO categoryAttributeCreateDTO) {
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        fromCategoryCreateDTOToCategory(categoryAttributeCreateDTO, categoryAttribute);
        categoryAttribute.setCode((int) sequenceService.getNextSequenceValue(CATEGORY_ATTRIBUTE_SEQUENCE_ID));
        return categoryAttribute;
    }

    public CategoryAttribute categoryAttributeUpdateDTOToCategoryAttribute(CategoryAttributeUpdateDTO categoryAttributeUpdateDTO) {
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        fromCategoryCreateDTOToCategory(categoryAttributeUpdateDTO, categoryAttribute);
        categoryAttribute.setCode(categoryAttributeUpdateDTO.getCode());
        categoryAttribute.setId(categoryAttributeUpdateDTO.getId());
        return categoryAttribute;
    }

    private void fromCategoryCreateDTOToCategory(CategoryAttributeCreateDTO source, CategoryAttribute target) {
        target.setActive(source.isActive());
        target.setKey(source.getKey());
        target.setTitle(source.getTitle());
        target.setUnit(source.getUnit());
        target.setCategories(source.getCategories());
        target.setType(source.getType());
        target.setValidator(source.getValidator());
        target.setValues(source.getValues());
        target.setPrivateAttr(source.isPrivateAttr());
    }
}
