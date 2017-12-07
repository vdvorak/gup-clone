package ua.com.gup.rent.validator.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryUpdateDTO;

import java.util.Optional;

@Component
public class RentOfferCategoryDTOValidator implements Validator {

    private final Logger log = LoggerFactory.getLogger(RentOfferCategoryDTOValidator.class);

    @Autowired
    private RentOfferCategoryService rentOfferCategoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RentOfferCategoryCreateDTO.class.equals(clazz) || RentOfferCategoryUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isUpdateDTO = RentOfferCategoryUpdateDTO.class.isInstance(target);
        RentOfferCategoryCreateDTO rentOfferCategoryCreateDTO = (RentOfferCategoryCreateDTO) target;

        if (isUpdateDTO) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            RentOfferCategoryUpdateDTO rentOfferCategoryUpdateDTO = (RentOfferCategoryUpdateDTO) target;
            if (rentOfferCategoryUpdateDTO.getCode() == 0) {
                errors.rejectValue("code", "code.cantBe0", null, "Code can't be null");
            }
            final Optional<RentOfferCategory> categoryOptional = rentOfferCategoryService.findOneByCode(rentOfferCategoryUpdateDTO.getCode());
            if (categoryOptional.isPresent() && !categoryOptional.get().getId().equals(rentOfferCategoryUpdateDTO.getId())) {
                errors.rejectValue("code", "code.inUse", null, "Code already used by " + categoryOptional.get().getId());
            }
        }

        if (rentOfferCategoryCreateDTO.getParent() != 0) {
            if (!rentOfferCategoryService.findOneByCode(rentOfferCategoryCreateDTO.getParent()).isPresent()) {
                errors.rejectValue("parent", "parent.notexist", null, "Parent <" + rentOfferCategoryCreateDTO.getParent() + "> doesn't exist");
            }
        }
        if (rentOfferCategoryCreateDTO.getTitle() == null || rentOfferCategoryCreateDTO.getTitle().size() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        }
    }
}
