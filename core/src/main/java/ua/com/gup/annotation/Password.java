package ua.com.gup.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for password validation
 *
 * @author Ivashkov Alexey
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern.List({
        @Pattern(regexp = "((?=.*\\d)(?=.*[A-Z]).{2,})", message = "{profile.password.atLeastOneNumberAndLetter}")
        , @Pattern(regexp = "([^\\s]+$)", message = "{profile.password.mayNotContainSpaces}")
        , @Pattern(regexp = "(^[^а-яА-ЯёЁ]+$)", message = "{profile.password.mayNotContainCyrillicSymbols}")
})
@Size(min = 6, message = "{profile.password.minLength}")
public @interface Password {

    String message() default "Password is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

