package ua.com.gup.web.rest.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.service.dto.OfferCreateDTO;

@Component
public class OfferCreateValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return OfferCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title", "Title is required.");
    }
}
