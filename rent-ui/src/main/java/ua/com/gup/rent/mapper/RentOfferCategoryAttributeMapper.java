package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeCreateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeUpdateDTO;
import ua.com.gup.rent.service.sequence.RentSequenceService;

@Component
public class RentOfferCategoryAttributeMapper {

    private static final String CATEGORY_ATTRIBUTE_SEQUENCE_ID = "rent_category_attribute_sequence";

    @Autowired
    private RentSequenceService rentSequenceService;

    public RentOfferCategoryAttribute categoryAttributeCreateDTOToCategoryAttribute(RentOfferCategoryAttributeCreateDTO rentOfferCategoryAttributeCreateDTO) {
           RentOfferCategoryAttribute rentOfferCategoryAttribute = new RentOfferCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentOfferCategoryAttributeCreateDTO, rentOfferCategoryAttribute);
        rentOfferCategoryAttribute.setCode((int) rentSequenceService.getNextSequenceValue(CATEGORY_ATTRIBUTE_SEQUENCE_ID));
        return rentOfferCategoryAttribute;
    }

    public RentOfferCategoryAttribute categoryAttributeUpdateDTOToCategoryAttribute(RentOfferCategoryAttributeUpdateDTO rentOfferCategoryAttributeUpdateDTO) {
        RentOfferCategoryAttribute rentOfferCategoryAttribute = new RentOfferCategoryAttribute();
        fromCategoryCreateDTOToCategory(rentOfferCategoryAttributeUpdateDTO, rentOfferCategoryAttribute);
        rentOfferCategoryAttribute.setCode(rentOfferCategoryAttributeUpdateDTO.getCode());
        rentOfferCategoryAttribute.setId(rentOfferCategoryAttributeUpdateDTO.getId());
        return rentOfferCategoryAttribute;
    }

    private void fromCategoryCreateDTOToCategory(RentOfferCategoryAttributeCreateDTO source, RentOfferCategoryAttribute target) {
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