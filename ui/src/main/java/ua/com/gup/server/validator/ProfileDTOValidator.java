package ua.com.gup.server.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.dto.profile.CreateProfileDTO;
import ua.com.gup.dto.profile.EditProfileDTO;

@Component
public class ProfileDTOValidator implements Validator {

    private final Logger log = LoggerFactory.getLogger(ProfileDTOValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateProfileDTO.class.equals(clazz) || EditProfileDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        boolean isUpdateDTO = EditProfileDTO.class.isInstance(target);
        CreateProfileDTO createProfileDTO = (CreateProfileDTO) target;

        if (isUpdateDTO) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
        }
       /* if (!isUpdateDTO) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        }*/

        if (createProfileDTO.getContact() != null) {

            if (createProfileDTO.getContact().getContactPhones() == null || createProfileDTO.getContact().getContactPhones().size() == 0) {
                errors.rejectValue("contact.contactPhones", "contact.contactPhones.size", null, "At least one phone number should be");
            } else {
                for (String phoneNo : createProfileDTO.getContact().getContactPhones()) {
                    if (phoneNo == null || !phoneNo.matches("^380[0-9]{9}$")) {
                        errors.rejectValue("contactInfo.contactPhones", "contactInfo.contactPhones.format", null, "Phone number format is ^380[0-9]{9}$");
                    }
                }
            }
        }


    }
}

