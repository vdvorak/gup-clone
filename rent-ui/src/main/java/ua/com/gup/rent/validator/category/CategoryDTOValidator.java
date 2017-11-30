package ua.com.gup.rent.validator.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.rent.model.mongo.category.Category;
import ua.com.gup.rent.service.category.CategoryService;

import java.util.Optional;

@Component
public class CategoryDTOValidator implements Validator {

    private final Logger log = LoggerFactory.getLogger(CategoryDTOValidator.class);

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ua.com.gup.rent.dto.category.RentCategoryCreateDTO.class.equals(clazz) || ua.com.gup.rent.dto.category.RentRentCategoryUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isUpdateDTO = ua.com.gup.rent.dto.category.RentRentCategoryUpdateDTO.class.isInstance(target);
        ua.com.gup.rent.dto.category.RentCategoryCreateDTO rentCategoryCreateDTO = (ua.com.gup.rent.dto.category.RentCategoryCreateDTO) target;

        if (isUpdateDTO) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ua.com.gup.rent.dto.category.RentRentCategoryUpdateDTO rentCategoryUpdateDTO = (ua.com.gup.rent.dto.category.RentRentCategoryUpdateDTO) target;
            if (rentCategoryUpdateDTO.getCode() == 0) {
                errors.rejectValue("code", "code.cantBe0", null, "Code can't be null");
            }
            final Optional<Category> categoryOptional = categoryService.findOneByCode(rentCategoryUpdateDTO.getCode());
            if (categoryOptional.isPresent() && !categoryOptional.get().getId().equals(rentCategoryUpdateDTO.getId())) {
                errors.rejectValue("code", "code.inUse", null, "Code already used by " + categoryOptional.get().getId());
            }
        }

        if (rentCategoryCreateDTO.getParent() != 0) {
            if (!categoryService.findOneByCode(rentCategoryCreateDTO.getParent()).isPresent()) {
                errors.rejectValue("parent", "parent.notexist", null, "Parent <" + rentCategoryCreateDTO.getParent() + "> doesn't exist");
            }
        }
        if (rentCategoryCreateDTO.getTitle() == null || rentCategoryCreateDTO.getTitle().size() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        }
    }
}
