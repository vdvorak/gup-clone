package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentCategoryMapper {

    private static final String CATEGORY_SEQUENCE_ID = "rent_category_sequence";

    @Autowired
    private ua.com.gup.rent.service.sequence.RentSequenceService rentSequenceService;

    public ua.com.gup.rent.model.mongo.category.RentCategory categoryCreateDTOToCategory(ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO rentCategoryCreateDTO) {
        ua.com.gup.rent.model.mongo.category.RentCategory rentCategory = new ua.com.gup.rent.model.mongo.category.RentCategory();
        fromCategoryCreateDTOToCategory(rentCategoryCreateDTO, rentCategory);
        rentCategory.setCode((int) rentSequenceService.getNextSequenceValue(CATEGORY_SEQUENCE_ID));
        return rentCategory;
    }

    public ua.com.gup.rent.model.mongo.category.RentCategory categoryUpdateDTOToCategory(ua.com.gup.rent.service.dto.category.RentRentCategoryUpdateDTO rentCategoryUpdateDTO) {
        ua.com.gup.rent.model.mongo.category.RentCategory rentCategory = new ua.com.gup.rent.model.mongo.category.RentCategory();
        fromCategoryCreateDTOToCategory(rentCategoryUpdateDTO, rentCategory);
        rentCategory.setCode(rentCategoryUpdateDTO.getCode());
        rentCategory.setId(rentCategoryUpdateDTO.getId());
        return rentCategory;
    }

    private void fromCategoryCreateDTOToCategory(ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO source, ua.com.gup.rent.model.mongo.category.RentCategory target) {
        target.setActive(source.isActive());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setKey(source.getKey());
        target.setParent(source.getParent());
        target.setColor(source.getColor());
        target.setOrder(source.getOrder());
    }
}
