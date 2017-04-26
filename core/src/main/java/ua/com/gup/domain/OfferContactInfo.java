package ua.com.gup.domain;


import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

public class OfferContactInfo {

    @Size(min = 2, max = 70)
    private String contactName;

    private String email;

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
