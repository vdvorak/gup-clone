package ua.com.gup.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedHashSet;

public class OfferContactInfoDTO {

    @ApiModelProperty(position = 0)
    private String contactName;

    @ApiModelProperty(position = 10)
    private String email;

    @ApiModelProperty(position = 20)
    private LinkedHashSet<String> phoneNumbers;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedHashSet<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(LinkedHashSet<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
