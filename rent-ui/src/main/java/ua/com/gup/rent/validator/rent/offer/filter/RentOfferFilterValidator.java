package ua.com.gup.rent.validator.rent.offer.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.gup.rent.filter.RentOfferFilter;

@Component
@Slf4j
public class RentOfferFilterValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return RentOfferFilter.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean isRentOfferFilter = RentOfferFilter.class.isInstance(target);

        RentOfferFilter rentOfferFilter = (RentOfferFilter) target;
        if (isRentOfferFilter) {

            if (rentOfferFilter.getCount() != null && (rentOfferFilter.getCount() > 100)) {

                errors.rejectValue("count", "count", new RentOfferFilter[]{rentOfferFilter}, "Description should have size less or equals then 100");

            }

        }
    }
}

