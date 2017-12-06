package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeCreateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeUpdateDTO;
import ua.com.gup.rent.service.sequence.RentSequenceService;

@Component
public class RentCategoryAttributeMapper {

    private static final String CATEGORY_ATTRIBUTE_SEQUENCE_ID = "rent_category_attribute_sequence";

    @Autowired
    private RentSequenceService rentSequenceService;

    public RentCategoryAttribute categoryAttributeCreateDTOToCategoryAttribute(RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
           RentCategoryAttribute rentCategoryAttribute = new RentCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeCreateDTO, rentCategoryAttribute);
        rentCategoryAttribute.setCode((int) rentSequenceService.getNextSequenceValue(CATEGORY_ATTRIBUTE_SEQUENCE_ID));
        return rentCategoryAttribute;
    }

    public RentCategoryAttribute categoryAttributeUpdateDTOToCategoryAttribute(RentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        RentCategoryAttribute rentCategoryAttribute = new RentCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeUpdateDTO, rentCategoryAttribute);
        rentCategoryAttribute.setCode(rentCategoryAttributeUpdateDTO.getCode());
        rentCategoryAttribute.setId(rentCategoryAttributeUpdateDTO.getId());
        return rentCategoryAttribute;
    }

    private void fromCategoryCreateDTOToCategory(RentCategoryAttributeCreateDTO source, RentCategoryAttribute target) {
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