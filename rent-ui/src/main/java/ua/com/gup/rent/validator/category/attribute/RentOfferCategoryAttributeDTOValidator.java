package ua.com.gup.rent.validator.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.rent.model.mongo.category.attribute.RentOfferCategoryAttribute;
import ua.com.gup.rent.model.rent.category.RentOfferCategoriesSort;
import ua.com.gup.rent.service.category.attribute.RentOfferCategoryAttributeService;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeCreateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeUpdateDTO;

import java.util.Optional;


@Component
public class RentOfferCategoryAttributeDTOValidator implements Validator {

    private final Logger log = LoggerFactory.getLogger(RentOfferCategoryAttributeDTOValidator.class);

    @Autowired
    private RentOfferCategoryAttributeService rentOfferCategoryAttributeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RentOfferCategoryAttributeCreateDTO.class.equals(clazz) || RentOfferCategoryAttributeUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isUpdateDTO = RentOfferCategoryAttributeUpdateDTO.class.isInstance(target);
        RentOfferCategoryAttributeCreateDTO rentOfferCategoryAttributeCreateDTO = (RentOfferCategoryAttributeCreateDTO) target;
        if (isUpdateDTO) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            RentOfferCategoryAttributeUpdateDTO categoryUpdateDTO = (RentOfferCategoryAttributeUpdateDTO) target;
            if (categoryUpdateDTO.getCode() == 0) {
                errors.rejectValue("code", "code.cantBe0", null, "Code can't be null");
            }
            final Optional<RentOfferCategoryAttribute> categoryAttributeOptional = rentOfferCategoryAttributeService.findOneByCode(categoryUpdateDTO.getCode());
            if (categoryAttributeOptional.isPresent() && !categoryAttributeOptional.get().getId().equals(categoryUpdateDTO.getId())) {
                errors.rejectValue("code", "code.inUse", null, "Code already used by " + categoryAttributeOptional.get().getId());
            }
        }
        if (rentOfferCategoryAttributeCreateDTO.getTitle() == null || rentOfferCategoryAttributeCreateDTO.getTitle().size() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        }
        if (!StringUtils.isEmpty(rentOfferCategoryAttributeCreateDTO.getKey())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "key.required");
        }
        if(!rentOfferCategoryAttributeCreateDTO.getCategories().isEmpty()){
            setCategorySortInRentOfferDTOByDefault(rentOfferCategoryAttributeCreateDTO);
        }
    }

    private void setCategorySortInRentOfferDTOByDefault(RentOfferCategoryAttributeCreateDTO rentOfferCategoryAttributeCreateDTO ){
        Integer sort_category_att_index = new Integer(100);
        RentOfferCategoriesSort rentOfferCategoriesSort = new RentOfferCategoriesSort();
        for (Integer category_code: rentOfferCategoryAttributeCreateDTO.getCategories()){
            rentOfferCategoriesSort.setCode_category(category_code);
            rentOfferCategoriesSort.setOrder_category(sort_category_att_index++);
            rentOfferCategoryAttributeCreateDTO.getCategoriesSort().add(rentOfferCategoriesSort);
        }
    }
}