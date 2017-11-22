package ua.com.gup.mongo.model.offer;


import javax.validation.constraints.Size;
import java.util.Set;

public class OfferContactInfo {

    @Size(min = 2, max = 70)
    private String contactName;

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
