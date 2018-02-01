package ua.com.gup.rent.validator.rent.offer.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.gup.rent.service.dto.rent.offer.filter.RentOfferFilterDTO;

@Component
@Slf4j
public class RentOfferFilterDTOValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return RentOfferFilterDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isRentOfferFilter = RentOfferFilterDTO.class.isInstance(target);

        RentOfferFilterDTO rentOfferFilterDTO = (RentOfferFilterDTO) target;
        if (isRentOfferFilter) {

            if (rentOfferFilterDTO.getCount() != null && (rentOfferFilterDTO.getCount() > 100)) {

                errors.rejectValue("count", "count", new RentOfferFilterDTO[]{rentOfferFilterDTO}, "Description should have size less or equals then 100");

            }

        }
    }
}

