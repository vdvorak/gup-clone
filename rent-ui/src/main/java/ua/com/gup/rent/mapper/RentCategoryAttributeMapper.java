package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentCategoryAttributeMapper {

    private static final String CATEGORY_ATTRIBUTE_SEQUENCE_ID = "rent_category_attribute_sequence";

    @Autowired
    private ua.com.gup.rent.service.sequence.RentSequenceService rentSequenceService;

    public ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute categoryAttributeCreateDTOToCategoryAttribute(ua.com.gup.rent.service.dto.category.RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
        ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute rentCategoryAttribute = new ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeCreateDTO, rentCategoryAttribute);
        rentCategoryAttribute.setCode((int) rentSequenceService.getNextSequenceValue(CATEGORY_ATTRIBUTE_SEQUENCE_ID));
        return rentCategoryAttribute;
    }

    public ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute categoryAttributeUpdateDTOToCategoryAttribute(ua.com.gup.rent.service.dto.category.RentRentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute rentCategoryAttribute = new ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeUpdateDTO, rentCategoryAttribute);
        rentCategoryAttribute.setCode(rentCategoryAttributeUpdateDTO.getCode());
        rentCategoryAttribute.setId(rentCategoryAttributeUpdateDTO.getId());
        return rentCategoryAttribute;
    }

    private void fromCategoryCreateDTOToCategory(ua.com.gup.rent.service.dto.category.RentCategoryAttributeCreateDTO source, ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute target) {
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
