package ua.com.gup.rent.validator.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.rent.model.mongo.category.attribute.CategoryAttribute;
import ua.com.gup.rent.service.category.attribute.CategoryAttributeService;

import java.util.Optional;


@Component
public class CategoryAttributeDTOValidator implements Validator {

    private final Logger log = LoggerFactory.getLogger(CategoryAttributeDTOValidator.class);

    @Autowired
    private CategoryAttributeService categoryAttributeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO.class.equals(clazz) || ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isUpdateDTO = ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO.class.isInstance(target);
        ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO = (ua.com.gup.rent.dto.category.RentCategoryAttributeCreateDTO) target;
        if (isUpdateDTO) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO categoryUpdateDTO = (ua.com.gup.rent.dto.category.RentRentCategoryAttributeUpdateDTO) target;
            if (categoryUpdateDTO.getCode() == 0) {
                errors.rejectValue("code", "code.cantBe0", null, "Code can't be null");
            }
            final Optional<CategoryAttribute> categoryAttributeOptional = categoryAttributeService.findOneByCode(categoryUpdateDTO.getCode());
            if (categoryAttributeOptional.isPresent() && !categoryAttributeOptional.get().getId().equals(categoryUpdateDTO.getId())) {
                errors.rejectValue("code", "code.inUse", null, "Code already used by " + categoryAttributeOptional.get().getId());
            }
        }
        if (rentCategoryAttributeCreateDTO.getTitle() == null || rentCategoryAttributeCreateDTO.getTitle().size() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        }
        if (!StringUtils.isEmpty(rentCategoryAttributeCreateDTO.getKey())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "key.required");
        }
    }
}
