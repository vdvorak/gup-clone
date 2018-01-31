package ua.com.gup.rent.validator.profile.bonus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;

/**
 * @author Victor Dvorak
 **/
@Slf4j
@Component
public class ProfileBonusDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileCreateBonusDTO.class.equals(clazz) || ProfileEditBonusDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isRentOfferEditBonusDTO = ProfileEditBonusDTO.class.isInstance(target);
        ProfileCreateBonusDTO profileCreateBonusDTO = (ProfileCreateBonusDTO) target;
    }
}
