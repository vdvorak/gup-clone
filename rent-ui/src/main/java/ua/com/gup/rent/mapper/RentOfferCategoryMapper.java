package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentCategoryUpdateDTO;
import ua.com.gup.rent.service.sequence.RentSequenceService;

@Component
public class RentOfferCategoryMapper {

    private static final String CATEGORY_SEQUENCE_ID = "rent_category_sequence";

    @Autowired
    private RentSequenceService rentSequenceService;

    public RentOfferCategory categoryCreateDTOToCategory(ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO rentCategoryCreateDTO) {
        RentOfferCategory rentOfferCategory = new RentOfferCategory();
        fromCategoryCreateDTOToCategory(rentCategoryCreateDTO, rentOfferCategory);
        rentOfferCategory.setCode((int) rentSequenceService.getNextSequenceValue(CATEGORY_SEQUENCE_ID));
        return rentOfferCategory;
    }

    public RentOfferCategory categoryUpdateDTOToCategory(RentCategoryUpdateDTO rentCategoryUpdateDTO) {
        RentOfferCategory rentOfferCategory = new RentOfferCategory();
        fromCategoryCreateDTOToCategory(rentCategoryUpdateDTO, rentOfferCategory);
        rentOfferCategory.setCode(rentCategoryUpdateDTO.getCode());
        rentOfferCategory.setId(rentCategoryUpdateDTO.getId());
        return rentOfferCategory;
    }

    private void fromCategoryCreateDTOToCategory(RentCategoryCreateDTO source, RentOfferCategory target) {
        target.setActive(source.isActive());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setKey(source.getKey());
        target.setParent(source.getParent());
        target.setColor(source.getColor());
        target.setOrder(source.getOrder());
    }
}