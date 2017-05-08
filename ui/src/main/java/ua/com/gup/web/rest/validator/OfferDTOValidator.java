package ua.com.gup.web.rest.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.domain.OfferCategory;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.OfferAddressDTO;
import ua.com.gup.service.dto.OfferCreateDTO;
import ua.com.gup.service.dto.OfferUpdateDTO;

@Component
public class OfferDTOValidator implements Validator {

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return OfferCreateDTO.class.equals(clazz) || OfferUpdateDTO.class.equals(clazz) || OfferFilter.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (OfferFilter.class.isInstance(target)) {
            return;
        }
        boolean isUpdateDTO = OfferUpdateDTO.class.isInstance(target);
        OfferCreateDTO offerCreateDTO = (OfferCreateDTO) target;
        if (isUpdateDTO) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
        }
        if (!isUpdateDTO) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        }
        if (offerCreateDTO.getTitle() != null && !(2 <= offerCreateDTO.getTitle().length() && offerCreateDTO.getTitle().length() <= 70)) {
            errors.rejectValue("title", "title.size", null, "Title should have size in range [2;70]");
        }
        if (!isUpdateDTO && offerCreateDTO.getCategories() == null) {
            errors.rejectValue("categories", "categories.required", null, "Categories is required");
        }
        if (offerCreateDTO.getCategories() != null && offerCreateDTO.getCategories().size() < 2) {
            errors.rejectValue("categories", "categories.size", null, "Categories should be more then 1");
        }
        if (offerCreateDTO.getCategories() != null) {
            int parent = 0;
            for (OfferCategory offerCategory : offerCreateDTO.getCategories()) {
                if (!categoryService.exists(offerCategory.getCode())) {
                    errors.rejectValue("categories", "categories.notexist", null, "Category " + offerCategory.getCode() + " doesn't exist");
                }
                if (parent != categoryService.getParentCode(offerCategory.getCode())) {
                    errors.rejectValue("categories", "categories.wrong_hierarchy", null, "Wrong hierarchy. Category <" + offerCategory.getCode() + "> should be subcategory of " + categoryService.getParentCode(offerCategory.getCode()));
                }
                parent = offerCategory.getCode();
            }
        }
        if (!isUpdateDTO && offerCreateDTO.getContactInfo() == null) {
            errors.rejectValue("contactInfo", "contactInfo.required", null, "ContactInfo is required");
        }
        if (offerCreateDTO.getContactInfo() != null) {
            if (StringUtils.isEmpty(offerCreateDTO.getContactInfo().getContactName())) {
                errors.rejectValue("contactInfo.contactName", "contactInfo.contactName.required", null, "Contact name required");
            }
            if (offerCreateDTO.getContactInfo().getPhoneNumbers() == null || offerCreateDTO.getContactInfo().getPhoneNumbers().size() == 0) {
                errors.rejectValue("contactInfo.phoneNumbers", "contactInfo.phoneNumbers.size", null, "At least one phone number should be");
            } else {
                for (String phoneNo : offerCreateDTO.getContactInfo().getPhoneNumbers()) {
                    if (phoneNo == null || !phoneNo.matches("^[0-9]{12,15}$")) {
                        errors.rejectValue("contactInfo.phoneNumbers", "contactInfo.phoneNumbers.format", null, "Phone number format is ^[0-9]{12,15}$");
                    }
                }
            }
        }
        if (!isUpdateDTO && offerCreateDTO.getAddress() == null) {
            errors.rejectValue("address", "address.required", null, "address required");
        }
        if (offerCreateDTO.getAddress() != null) {
            final OfferAddressDTO address = offerCreateDTO.getAddress();
            if (address.getLat() == null || address.getLng() == null) {
                errors.rejectValue("address.coordinates", "address.coordinates.required", null, "Coordinates required");
            }
            if (!(-90d <= address.getLat() && address.getLat() <= 90 && -180d <= address.getLng() && address.getLng() <= 180)) {
                errors.rejectValue("address.coordinates", "address.format", null, "Lat in [-90;90] Lng in [-180;180]");
            }
            if (address.getCountry() == null) {
                errors.rejectValue("address.country", "address.country.required", null, "Country required");
            }
            if (address.getDistrict() == null) {
                errors.rejectValue("address.district", "address.district.required", null, "District required");
            }
            if (address.getCity() == null) {
                errors.rejectValue("address.city", "address.city.required", null, "City required");
            }
        }
    }
}

