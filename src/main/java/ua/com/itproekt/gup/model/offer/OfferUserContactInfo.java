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

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getSkypeLogin() {
        return skypeLogin;
    }

    public void setSkypeLogin(String skypeLogin) {
        this.skypeLogin = skypeLogin;
    }
}
