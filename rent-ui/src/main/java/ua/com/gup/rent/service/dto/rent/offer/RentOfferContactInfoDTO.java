package ua.com.gup.rent.service.dto.rent.offer;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;

public class RentOfferContactInfoDTO implements Serializable{

    @ApiModelProperty(position = 0)
    private String contactName;

    @ApiModelProperty(position = 20, dataType="java.util.LinkedHashSet<String>", value = "['380501234567','380507654321']")
    private Set<String> phoneNumbers;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
