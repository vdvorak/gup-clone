package ua.com.gup.server.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.gup.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValidatorDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.dto.offer.OfferAddressDTO;
import ua.com.gup.dto.offer.OfferCreateDTO;
import ua.com.gup.dto.offer.OfferUpdateDTO;
import ua.com.gup.mongo.model.enumeration.CategoryAttributeType;
import ua.com.gup.service.category.CategoryService;
import ua.com.gup.service.category.attribute.CategoryAttributeService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OfferDTOValidator implements Validator {

    private final Logger log = LoggerFactory.getLogger(OfferDTOValidator.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryAttributeService categoryAttributeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return OfferCreateDTO.class.equals(clazz) || OfferUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
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
        if (offerCreateDTO.getDescription() != null && !(offerCreateDTO.getDescription().length() <= 5000)) {
            errors.rejectValue("description", "description.size", null, "Description should have size less then 5000");
        }
        String priceType = offerCreateDTO.getAttrs() == null ? null : offerCreateDTO.getAttrs().get("price");
        if (offerCreateDTO.getPrice() == null && "price".equals(priceType)) {
            errors.rejectValue("price", "price.required", null, "Price required for price type price.");
        }
        if (offerCreateDTO.getPrice() != null && "price".equals(priceType)) {
            if (offerCreateDTO.getPrice().getPriceWithVAT() == null) {
                errors.rejectValue("price", "price.withWat.required", null, "Price with WAT required.");
            }
            if (offerCreateDTO.getPrice().getAmount() == null) {
                errors.rejectValue("price", "price.amount.required", null, "Price amount required.");
            }
            if (offerCreateDTO.getPrice().getCurrency() == null) {
                errors.rejectValue("price", "price.currency.required", null, "Price currency required.");
            }
        }
        if (!isUpdateDTO && offerCreateDTO.getCategory() == null) {
            errors.rejectValue("categories", "categories.required", null, "Categories is required");
        }
        if (offerCreateDTO.getCategory() != null) {
            if (!categoryService.exists(offerCreateDTO.getCategory())) {
                errors.rejectValue("category", "category.notexist", null, "Category " + offerCreateDTO.getCategory() + " doesn't exist or has subcategory");
            } else {
                int numberOfAllAttrs = (offerCreateDTO.getAttrs() == null ? 0 : offerCreateDTO.getAttrs().size()) +
                        (offerCreateDTO.getAttrs() == null ? 0 : offerCreateDTO.getAttrs().size()) +
                        (offerCreateDTO.getAttrs() == null ? 0 : offerCreateDTO.getAttrs().size()) +
                        (offerCreateDTO.getAttrs() == null ? 0 : offerCreateDTO.getAttrs().size());
                if (!isUpdateDTO || (isUpdateDTO && numberOfAllAttrs > 0)) {
                    final LinkedHashSet<CategoryAttributeDTO> categoryAttributeDTOS = categoryAttributeService.findAllCategoryAttributeDTO().get(offerCreateDTO.getCategory());
                    final Map<String, CategoryAttributeDTO> categoryAttributeDTOMap = categoryAttributeDTOS.stream().collect(Collectors.toMap(CategoryAttributeDTO::getKey, Function.identity()));
                    // checking values of attrs
                    if (offerCreateDTO.getAttrs() != null) {
                        for (String key : offerCreateDTO.getAttrs().keySet()) {
                            final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                            if (categoryAttributeDTO == null) {
                                errors.rejectValue("attrs", "attrs." + key + ".unknown", null, "Unknown attribute");
                            } else {
                                CategoryAttributeValueDTO attributeValueDTO = new CategoryAttributeValueDTO();
                                attributeValueDTO.setKey(offerCreateDTO.getAttrs().get(key));
                                final Set<CategoryAttributeValueDTO> valuesToCheck = new HashSet<>(categoryAttributeDTO.getValues()); // TreeSet to HashSet
                                if (!valuesToCheck.contains(attributeValueDTO)) { //
                                    errors.rejectValue("attrs", "attrs." + key + ".value.unknown", null, "Unknown value <" + offerCreateDTO.getAttrs().get(key) + "> for attr <" + key + ">");
                                }
                            }
                        }
                    }
                    // checking values of multiAttrs
                    if (offerCreateDTO.getMultiAttrs() != null) {
                        for (String key : offerCreateDTO.getMultiAttrs().keySet()) {
                            final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                            if (categoryAttributeDTO == null) {
                                errors.rejectValue("multiAttrs", "multiAttrs." + key + ".unknown", null, "Unknown attribute");
                            } else {
                                final String[] values = offerCreateDTO.getMultiAttrs().get(key).split(",");
                                for (String value : values) {
                                    CategoryAttributeValueDTO attributeValueDTO = new CategoryAttributeValueDTO();
                                    attributeValueDTO.setKey(value);
                                    final Set<CategoryAttributeValueDTO> valuesToCheck = new HashSet<>(categoryAttributeDTO.getValues()); // TreeSet to HashSet
                                    if (!valuesToCheck.contains(attributeValueDTO)) { //
                                        errors.rejectValue("multiAttrs", "multiAttrs." + key + ".value.unknown", null, "Unknown value <" + value + "> for attr <" + key + ">");
                                    }
                                }
                            }
                        }
                    }
                    // checking values of numAttrs
                    if (offerCreateDTO.getNumAttrs() != null) {
                        for (String key : offerCreateDTO.getNumAttrs().keySet()) {
                            final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                            if (categoryAttributeDTO == null) {
                                errors.rejectValue("numAttrs", "numAttrs." + key + ".unknown", null, "Unknown attribute");
                            } else {
                                final CategoryAttributeValidatorDTO validator = categoryAttributeDTO.getValidator();
                                final BigDecimal value = offerCreateDTO.getNumAttrs().get(key);
                                if (validator.getMin() != null) {
                                    if (validator.getMin().compareTo(value) > 0) {
                                        errors.rejectValue("numAttrs", "numAttrs." + key + ".min", null, "The value = " + value + " is less then min value = " + validator.getMin());
                                    }
                                }
                                if (validator.getMax() != null) {
                                    if (validator.getMax().compareTo(value) < 0) {
                                        errors.rejectValue("numAttrs", "numAttrs." + key + ".max", null, "The value = " + value + " is more then max value = " + validator.getMax());
                                    }
                                }
                            }
                        }
                    }
                    // checking values of boolAttrs
                    if (offerCreateDTO.getBoolAttrs() != null) {
                        for (String key : offerCreateDTO.getBoolAttrs().keySet()) {
                            final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                            if (categoryAttributeDTO == null) {
                                errors.rejectValue("boolAttrs", "boolAttrs." + key + ".unknown", null, "Unknown attribute");
                            }
                        }
                    }

                    // checking required attributes
                    if (categoryAttributeDTOS != null) {
                        for (CategoryAttributeDTO categoryAttributeDTO : categoryAttributeDTOS) {
                            if (categoryAttributeDTO.getType() == CategoryAttributeType.SELECT) {
                                if (offerCreateDTO.getAttrs() == null || offerCreateDTO.getAttrs().get(categoryAttributeDTO.getKey()) == null && categoryAttributeDTO.getValidator().isRequired()) {
                                    errors.rejectValue("attrs", "attrs." + categoryAttributeDTO.getKey() + ".required", null, "Attribute is required");
                                }
                            } else if (categoryAttributeDTO.getType() == CategoryAttributeType.MULTI_SELECT) {
                                if (offerCreateDTO.getMultiAttrs() == null || offerCreateDTO.getMultiAttrs().get(categoryAttributeDTO.getKey()) == null && categoryAttributeDTO.getValidator().isRequired()) {
                                    errors.rejectValue("multiAttrs", "multiAttrs." + categoryAttributeDTO.getKey() + ".required", null, "Attribute is required");
                                }
                            } else if (categoryAttributeDTO.getType() == CategoryAttributeType.NUMBER) {
                                if (offerCreateDTO.getNumAttrs() == null || offerCreateDTO.getNumAttrs().get(categoryAttributeDTO.getKey()) == null && categoryAttributeDTO.getValidator().isRequired()) {
                                    errors.rejectValue("numAttrs", "numAttrs." + categoryAttributeDTO.getKey() + ".required", null, "Attribute is required");
                                }
                            } else if (categoryAttributeDTO.getType() == CategoryAttributeType.BOOLEAN) {
                                if (offerCreateDTO.getBoolAttrs() == null || offerCreateDTO.getBoolAttrs().get(categoryAttributeDTO.getKey()) == null && categoryAttributeDTO.getValidator().isRequired()) {
                                    errors.rejectValue("boolAttrs", "boolAttrs." + categoryAttributeDTO.getKey() + ".required", null, "Attribute is required");
                                }
                            }
                        }
                    }
                }
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
            if (!(-90d <= address.getLat().doubleValue() && address.getLat().doubleValue() <= 90 && -180d <= address.getLng().doubleValue() && address.getLng().doubleValue() <= 180)) {
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
}

