package ua.com.gup.web.rest.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.domain.category.CategoryAttribute;
import ua.com.gup.domain.category.CategoryAttributeType;
import ua.com.gup.domain.category.CategoryAttributeValue;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.service.CategoryAttributeService;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.OfferAddressDTO;
import ua.com.gup.service.dto.OfferCreateDTO;
import ua.com.gup.service.dto.OfferUpdateDTO;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class OfferDTOValidator implements Validator {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryAttributeService categoryAttributeService;

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
        if (!isUpdateDTO && offerCreateDTO.getCategory() == null) {
            errors.rejectValue("categories", "categories.required", null, "Categories is required");
        }
        if (offerCreateDTO.getCategory() != null) {
            if (!categoryService.exists(offerCreateDTO.getCategory())) {
                errors.rejectValue("category", "category.notexist", null, "Category " + offerCreateDTO.getCategory() + " doesn't exist or has subcategory");
            } else {
//                final LinkedHashSet<CategoryAttributeDTO> categoryAttributeDTOS = categoryAttributeService.findAllCategoryAttributeDTO().get(offerCreateDTO.getCategory());
//                for (String key : offerCreateDTO.getAttrs().keySet()) {
//                    CategoryAttributeValue attributeValue = new CategoryAttributeValue();
//                    attributeValue.setKey(offerCreateDTO.getAttrs().get(key));
//                    boolean exist = false;
//                    for (CategoryAttributeDTO categoryAttributeDTO : categoryAttributeDTOS) {
//                        exist = exist || categoryAttributeDTO.getKey().equals(key) && categoryAttributeDTO.getValues().contains(attributeValue);
//                    }
//                    if (!exist) {
//                        errors.rejectValue("attr", "attr." + key + ".attributeValue", null, "attr." +
//                                key + ".attributeValue = " + offerCreateDTO.getAttrs().get(key) +
//                                " doesn't exist or not permitted for this category");
//                    }
//                }
//                for (String key : offerCreateDTO.getMultiAttrs().keySet()) {
//                    final String[] values = offerCreateDTO.getMultiAttrs().get(key).split(",");
//                    for (String v : values) {
//                        CategoryAttributeValue attributeValue = new CategoryAttributeValue();
//                        attributeValue.setKey(v);
//                        boolean exist = false;
//                        for (CategoryAttributeDTO categoryAttributeDTO : categoryAttributeDTOS) {
//                            exist = exist || categoryAttributeDTO.getKey().equals(key) && categoryAttributeDTO.getValues().contains(attributeValue);
//                        }
//                        if (!exist) {
//                            errors.rejectValue("multiAttr", "multiAttr." + key + ".attributeValue", null, "attr." +
//                                    key + ".attributeValue = " + offerCreateDTO.getMultiAttrs().get(key) +
//                                    " doesn't exist or not permitted for this category");
//                        }
//                    }
//                }
//                for (String key : offerCreateDTO.getNumAttrs().keySet()) {
//                    final BigDecimal value = offerCreateDTO.getNumAttrs().get(key);
//                    boolean exist = false;
//                    for (CategoryAttributeDTO categoryAttributeDTO : categoryAttributeDTOS) {
//                        if(categoryAttributeDTO.getKey().equals(key)) {
//                            if(categoryAttributeDTO.getValidator()!= null){
//                                if(categoryAttributeDTO.)
//                            }
//                            exist = exist || categoryAttributeDTO.getKey().equals(key) && categoryAttributeDTO.getValues().contains(attributeValue);
//                        }
//                    }
//                    if (!exist) {
//                        errors.rejectValue("attr", "attr." + key + ".attributeValue", null, "attr." +
//                                key + ".attributeValue = " + offerCreateDTO.getAttrs().get(key) +
//                                " doesn't exist or not permitted for this category");
//                    }
//                }
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
        if (offerCreateDTO.getNumAttrs() != null) {
            final Map<String, BigDecimal> numAttrs = offerCreateDTO.getNumAttrs();
            for (String key : numAttrs.keySet()) {
                try {
                    numAttrs.get(key).doubleValue();
                } catch (Exception e) {
                    errors.rejectValue("numAttrs", "numAttrs." + key + ".invalidFormat", null, "Invalid number format");
                }

            }
        }
    }

    private void checkAttribute(CategoryAttribute categoryAttribute, OfferCreateDTO offerCreateDTO, Errors errors) {
        if (categoryAttribute.getValidator().isRequired() && !categoryAttribute.getValidator().getExcept().contains(categoryAttribute)) {
            if (categoryAttribute.getType() == CategoryAttributeType.BOOLEAN) {
                if (offerCreateDTO.getBoolAttrs() == null
                        || !offerCreateDTO.getBoolAttrs().containsKey(categoryAttribute.getKey())) {
                    errors.rejectValue("boolAttrs", "boolAttrs.required", null, "boolAttrs." + categoryAttribute.getKey() + " is required");
                }
            } else if (categoryAttribute.getType() == CategoryAttributeType.MULTI_SELECT) {

            } else if (categoryAttribute.getType() == CategoryAttributeType.NUMBER) {

            } else if (categoryAttribute.getType() == CategoryAttributeType.SELECT) {
                if (offerCreateDTO.getAttrs() == null
                        || !offerCreateDTO.getAttrs().containsKey(categoryAttribute.getKey())) {
                    errors.rejectValue("attrs", "attrs.required", null, "attrs." + categoryAttribute.getKey() + " is required");
                } else {
                    final String value = offerCreateDTO.getAttrs().get(categoryAttribute.getKey());
                    boolean existForCategory = false;
                    for (CategoryAttributeValue attributeValue : categoryAttribute.getValues()) {
                        existForCategory = existForCategory ||
                                (value.equals(attributeValue.getKey()) && !attributeValue.getExceptCategory().contains(categoryAttribute.getCode()));
                    }
                    errors.rejectValue("attrs", "attrs.value", null, "value = " + value + "for attrs." + categoryAttribute.getKey() + " and category = " + categoryAttribute.getCode() + " doesn't exist");
                }
            }
        }
    }
}

