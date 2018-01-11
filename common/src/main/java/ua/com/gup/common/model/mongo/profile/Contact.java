package ua.com.gup.common.model.mongo.profile;

import ua.com.gup.common.model.enumeration.SocialNetwork;
import ua.com.gup.common.model.mongo.Phone;

import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Set;

public class Contact {

    @Size(min = 2, max = 70)
    private String position;
    @Size(min = 2, max = 70)
    private String companyName;
    @Size(max = 5000)
    private String aboutUs;

    @Size(min = 2, max = 70)
    private String skypeUserName;
    @Size(max = 256)
    private String linkToWebSite;

    @Size(max = 1)
    private Set<String> contactEmails;

    @Size(max = 5)
    private Set<Phone> contactPhones;

    private Map<SocialNetwork, String> socNetLink;

    public Contact() {
    }

    public Map<SocialNetwork, String> getSocNetLink() {
        return socNetLink;
    }

    public void setSocNetLink(Map<SocialNetwork, String> socNetLink) {
        this.socNetLink = socNetLink;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLinkToWebSite() {
        return linkToWebSite;
    }

    public void setLinkToWebSite(String linkToWebSite) {
        this.linkToWebSite = linkToWebSite;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<String> getContactEmails() {
        return contactEmails;
    }

    public void setContactEmails(Set<String> contactEmails) {
        this.contactEmails = contactEmails;
    }

    public Set<Phone> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(Set<Phone> contactPhones) {
        this.contactPhones = contactPhones;
    }

    public String getSkypeUserName() {
        return skypeUserName;
    }

    public void setSkypeUserName(String skypeUserName) {
        this.skypeUserName = skypeUserName;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }


    @Override
    public String toString() {
        return "Contact{" +
                ", position='" + position + '\'' +
                ", contactEmails=" + contactEmails +
                ", contactPhones=" + contactPhones +
                ", socNetLink=" + socNetLink +
                ", skypeUserName='" + skypeUserName + '\'' +
                ", linkToWebSite=" + linkToWebSite +
                ", aboutUs='" + aboutUs + '\'' +
                '}';
    }
}

