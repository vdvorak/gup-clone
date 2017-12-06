package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.category.RentCategory;
import ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentCategoryUpdateDTO;
import ua.com.gup.rent.service.sequence.RentSequenceService;

@Component
public class RentCategoryMapper {

    private static final String CATEGORY_SEQUENCE_ID = "rent_category_sequence";

    @Autowired
    private RentSequenceService rentSequenceService;

    public RentCategory categoryCreateDTOToCategory(ua.com.gup.rent.service.dto.category.RentCategoryCreateDTO rentCategoryCreateDTO) {
        RentCategory rentCategory = new RentCategory();
        fromCategoryCreateDTOToCategory(rentCategoryCreateDTO, rentCategory);
        rentCategory.setCode((int) rentSequenceService.getNextSequenceValue(CATEGORY_SEQUENCE_ID));
        return rentCategory;
    }

    public RentCategory categoryUpdateDTOToCategory(RentCategoryUpdateDTO rentCategoryUpdateDTO) {
        RentCategory rentCategory = new RentCategory();
        fromCategoryCreateDTOToCategory(rentCategoryUpdateDTO, rentCategory);
        rentCategory.setCode(rentCategoryUpdateDTO.getCode());
        rentCategory.setId(rentCategoryUpdateDTO.getId());
        return rentCategory;
    }

    private void fromCategoryCreateDTOToCategory(RentCategoryCreateDTO source, RentCategory target) {
        target.setActive(source.isActive());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setKey(source.getKey());
        target.setParent(source.getParent());
        target.setColor(source.getColor());
        target.setOrder(source.getOrder());
    }
}