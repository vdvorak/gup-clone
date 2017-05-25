package ua.com.gup.web.rest.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.domain.Category;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.category.CategoryCreateDTO;
import ua.com.gup.service.dto.category.CategoryUpdateDTO;

import java.util.Optional;

@Component
public class CategoryDTOValidator implements Validator {

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryCreateDTO.class.equals(clazz) || CategoryUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isUpdateDTO = CategoryUpdateDTO.class.isInstance(target);
        CategoryCreateDTO categoryCreateDTO = (CategoryCreateDTO) target;

        if (isUpdateDTO) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            CategoryUpdateDTO categoryUpdateDTO = (CategoryUpdateDTO) target;
            if (categoryUpdateDTO.getCode() == 0) {
                errors.rejectValue("code", "code.cantBe0", null, "Code can't be null");
            }
            final Optional<Category> categoryOptional = categoryService.findOneByCode(categoryUpdateDTO.getCode());
            if (categoryOptional.isPresent() && !categoryOptional.get().getId().equals(categoryUpdateDTO.getId())) {
                errors.rejectValue("code", "code.inUse", null, "Code already used by " + categoryOptional.get().getId());
            }
        }

        if (categoryCreateDTO.getParent() != 0) {
            if (!categoryService.exists(categoryCreateDTO.getParent())) {
                errors.rejectValue("parent", "parent.notexist", null, "Parent <" + categoryCreateDTO.getParent() + "> doesn't exist");
            }
        }
        if (categoryCreateDTO.getTitle() == null || categoryCreateDTO.getTitle().size() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        }

        if (StringUtils.isEmpty(categoryCreateDTO.getKey())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "title.required");
        }
    }
}
