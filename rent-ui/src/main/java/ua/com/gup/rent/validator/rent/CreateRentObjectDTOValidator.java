package ua.com.gup.rent.validator.rent;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.gup.rent.service.dto.rent.RentCreateDTO;

@Component
public class CreateRentObjectDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RentCreateDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
