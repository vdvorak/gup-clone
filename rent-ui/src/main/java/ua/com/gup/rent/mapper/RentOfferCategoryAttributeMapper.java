package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeCreateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeUpdateDTO;
import ua.com.gup.rent.service.sequence.RentSequenceService;

@Component
public class RentOfferCategoryAttributeMapper {

    private static final String CATEGORY_ATTRIBUTE_SEQUENCE_ID = "rent_category_attribute_sequence";

    @Autowired
    private RentSequenceService rentSequenceService;

    public RentOfferCategoryAttribute categoryAttributeCreateDTOToCategoryAttribute(RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO) {
           RentOfferCategoryAttribute rentOfferCategoryAttribute = new RentOfferCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeCreateDTO, rentOfferCategoryAttribute);
        rentOfferCategoryAttribute.setCode((int) rentSequenceService.getNextSequenceValue(CATEGORY_ATTRIBUTE_SEQUENCE_ID));
        return rentOfferCategoryAttribute;
    }

    public RentOfferCategoryAttribute categoryAttributeUpdateDTOToCategoryAttribute(RentCategoryAttributeUpdateDTO rentCategoryAttributeUpdateDTO) {
        RentOfferCategoryAttribute rentOfferCategoryAttribute = new RentOfferCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentCategoryAttributeUpdateDTO, rentOfferCategoryAttribute);
        rentOfferCategoryAttribute.setCode(rentCategoryAttributeUpdateDTO.getCode());
        rentOfferCategoryAttribute.setId(rentCategoryAttributeUpdateDTO.getId());
        return rentOfferCategoryAttribute;
    }

    private void fromCategoryCreateDTOToCategory(RentCategoryAttributeCreateDTO source, RentOfferCategoryAttribute target) {
        target.setActive(source.isActive());
        target.setKey(source.getKey());
        target.setTitle(source.getTitle());
        target.setUnit(source.getUnit());
        target.setCategories(source.getCategories());
        target.setCategoriesSort(source.getRentOfferCategoriesSort());
        target.setType(source.getType());
        target.setValidator(source.getValidator());
        target.setValues(source.getValues());
        target.setPrivateAttr(source.isPrivateAttr());
    }
}