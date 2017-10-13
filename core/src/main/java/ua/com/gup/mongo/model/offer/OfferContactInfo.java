package ua.com.gup.mongo.model.offer;


import javax.validation.constraints.Size;
import java.util.LinkedHashSet;

public class OfferContactInfo {

    @Size(min = 2, max = 70)
    private String contactName;

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
