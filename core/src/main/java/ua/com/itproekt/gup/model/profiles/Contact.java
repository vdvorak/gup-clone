package ua.com.itproekt.gup.model.profiles;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.itproekt.gup.util.SocialNetwork;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Contact {
    private boolean member;
    private List<String> naceId;

    private UserType type;
    private String position;
    private String companyName;
    @Size(max = 3500)
    private String aboutUs;

    private String skypeUserName;
    private String linkToWebSite;
    private Set<String> contactEmails;
    private Set<String> contactPhones;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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

    public Set<String> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(Set<String> contactPhones) {
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

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public List<String> getNaceId() {
        return naceId;
    }

    public void setNaceId(List<String> naceId) {
        this.naceId = naceId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "type=" + type +
                ", position='" + position + '\'' +
                ", contactEmails=" + contactEmails +
                ", contactPhones=" + contactPhones +
                ", socNetLink=" + socNetLink +
                ", skypeUserName='" + skypeUserName + '\'' +
                ", linkToWebSite=" + linkToWebSite +
                ", aboutUs='" + aboutUs + '\'' +
                ", member=" + member +
                ", naceId=" + naceId +
                '}';
    }
}

