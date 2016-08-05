package ua.com.itproekt.gup.util;


import javax.validation.constraints.Size;
import java.util.Set;

public class OfferUserContactInfo {
    @Size(min = 2, max = 50)
    private String contactName;
    private String email;
    private Set<String> phoneNumbers;


    public String getEmail() {
        return email;
    }

    public OfferUserContactInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    public OfferUserContactInfo setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public OfferUserContactInfo setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        return this;
    }

    @Override
    public String toString() {
        return "OfferUserContactInfo{" +
                "contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
