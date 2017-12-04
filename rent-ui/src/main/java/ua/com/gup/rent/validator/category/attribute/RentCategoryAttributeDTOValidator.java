package ua.com.gup.rent.validator.category.attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeUpdateDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentCategoryAttributeCreateDTO;

import java.util.Optional;


@Component
public class RentCategoryAttributeDTOValidator implements Validator {

    private final Logger log = LoggerFactory.getLogger(RentCategoryAttributeDTOValidator.class);

    @Autowired
    private ua.com.gup.rent.service.category.attribute.RentCategoryAttributeServiceRent rentCategoryAttributeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RentCategoryAttributeCreateDTO.class.equals(clazz) || RentCategoryAttributeUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isUpdateDTO = RentCategoryAttributeUpdateDTO.class.isInstance(target);
        RentCategoryAttributeCreateDTO rentCategoryAttributeCreateDTO = (RentCategoryAttributeCreateDTO) target;
        if (isUpdateDTO) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            RentCategoryAttributeUpdateDTO categoryUpdateDTO = (RentCategoryAttributeUpdateDTO) target;
            if (categoryUpdateDTO.getCode() == 0) {
                errors.rejectValue("code", "code.cantBe0", null, "Code can't be null");
            }
            final Optional<ua.com.gup.rent.model.mongo.category.attribute.RentCategoryAttribute> categoryAttributeOptional = rentCategoryAttributeService.findOneByCode(categoryUpdateDTO.getCode());
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
