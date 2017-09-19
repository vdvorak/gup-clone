package ua.com.gup.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class Validator3Util {

    public static boolean validate(Object object) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        for (ConstraintViolation<Object> cv : constraintViolations)
            System.err.println(String.format(
                    "Внимание, ошибка в поле [%s]: value [%s] >> %s",
                    cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));

        return (constraintViolations.size()==0) ? true : false; // return constraintViolations.size();
    }

}
