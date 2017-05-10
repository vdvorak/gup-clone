package ua.com.gup.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedHashSet;

public class OfferContactInfoDTO {

    @ApiModelProperty(position = 0)
    private String contactName;

    @ApiModelProperty(position = 20, example = "380501234567")
    private LinkedHashSet<String> phoneNumbers;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public LinkedHashSet<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(LinkedHashSet<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
