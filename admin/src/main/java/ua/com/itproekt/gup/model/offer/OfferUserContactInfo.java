package ua.com.itproekt.gup.model.offer;


import javax.validation.constraints.Size;
import java.util.Set;

public class OfferUserContactInfo {
    @Size(min = 2, max = 50)
    private String contactName;
    private String email;
    private Set<String> phoneNumbers;
    @Size(min = 5, max = 32)
    private String skypeLogin;

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

    public String getSkypeLogin() {
        return skypeLogin;
    }

    public OfferUserContactInfo setSkypeLogin(String skypeLogin) {
        this.skypeLogin = skypeLogin;
        return this;
    }
}
