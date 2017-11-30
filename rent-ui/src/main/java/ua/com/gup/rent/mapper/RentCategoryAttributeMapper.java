package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.category.attribute.CategoryAttribute;
import ua.com.gup.rent.service.sequence.SequenceService;

@Component
public class RentCategoryAttributeMapper {

    private static final String CATEGORY_ATTRIBUTE_SEQUENCE_ID = "category_attribute_sequence";

    @Autowired
    private SequenceService sequenceService;

    public CategoryAttribute categoryAttributeCreateDTOToCategoryAttribute(ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeCreateDTO, categoryAttribute);
        categoryAttribute.setCode((int) sequenceService.getNextSequenceValue(CATEGORY_ATTRIBUTE_SEQUENCE_ID));
        return categoryAttribute;
    }

    public CategoryAttribute categoryAttributeUpdateDTOToCategoryAttribute(ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeUpdateDTO, categoryAttribute);
        categoryAttribute.setCode(rentCategoryAttributeUpdateDTO.getCode());
        categoryAttribute.setId(rentCategoryAttributeUpdateDTO.getId());
        return categoryAttribute;
    }

    private void fromCategoryCreateDTOToCategory(ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO source, CategoryAttribute target) {
        target.setActive(source.isActive());
        target.setKey(source.getKey());
        target.setTitle(source.getTitle());
        target.setUnit(source.getUnit());
        target.setCategories(source.getCategories());
        target.setCategoriesSort(source.getRentCategoriesSort());
        target.setType(source.getType());
        target.setValidator(source.getValidator());
        target.setValues(source.getValues());
        target.setPrivateAttr(source.isPrivateAttr());
    }
}
