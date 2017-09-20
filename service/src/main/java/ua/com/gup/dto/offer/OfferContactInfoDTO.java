package ua.com.gup.dto.offer;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class OfferContactInfoDTO implements Serializable{

    @ApiModelProperty(position = 0)
    private String contactName;

    @ApiModelProperty(position = 20, dataType="java.util.LinkedHashSet<String>", value = "[\"380501234567\",\"380507654321\"]")
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
