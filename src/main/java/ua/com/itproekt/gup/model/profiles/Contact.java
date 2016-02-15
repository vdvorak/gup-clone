package ua.com.itproekt.gup.model.profiles;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.itproekt.gup.util.SocialNetwork;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Contact is a part of Profile entity.
 */
public class Contact {
    private UserType type;
    private String companyDirector;
    private Set<String> contactEmails;
    private Set<String> contactPhones;
    private Map<SocialNetwork, String> socNetLink;
    private String linkToWebSite;
    private String skypeUserName;
    private String aboutUs;
    private Nace nace;
    private String pic;
    private int balance;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Long lastUpdate;
    private boolean member;
    private List<String> naceId;

    public String getLinkToWebSite() {
        return linkToWebSite;
    }

    public void setLinkToWebSite(String linkToWebSite) {
        this.linkToWebSite = linkToWebSite;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Instantiates a new created date of Contact.
     */
    public Contact() {
        this.lastUpdate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public UserType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * Gets company director.
     *
     * @return the company director
     */
    public String getCompanyDirector() {
        return companyDirector;
    }

    /**
     * Sets company director.
     *
     * @param companyDirector the company director
     */
    public void setCompanyDirector(String companyDirector) {
        this.companyDirector = companyDirector;
    }

    /**
     * Gets contact emails.
     *
     * @return the contact emails
     */
    public Set<String> getContactEmails() {
        return contactEmails;
    }

    /**
     * Sets contact emails.
     *
     * @param contactEmails the contact emails
     */
    public void setContactEmails(Set<String> contactEmails) {
        this.contactEmails = contactEmails;
    }

    /**
     * Gets contact phones.
     *
     * @return the contact phones
     */
    public Set<String> getContactPhones() {
        return contactPhones;
    }

    /**
     * Sets contact phones.
     *
     * @param contactPhones the contact phones
     */
    public void setContactPhones(Set<String> contactPhones) {
        this.contactPhones = contactPhones;
    }

    /**
     * Gets soc net link.
     *
     * @return the soc net link
     */
    public Map<SocialNetwork, String> getSocNetLink() {
        return socNetLink;
    }

    /**
     * Sets soc net link.
     *
     * @param socNetLink the soc net link
     */
    public void setSocNetLink(Map<SocialNetwork, String> socNetLink) {
        this.socNetLink = socNetLink;
    }

    /**
     * Gets skype user name.
     *
     * @return the skype user name
     */
    public String getSkypeUserName() {
        return skypeUserName;
    }

    /**
     * Sets skype user name.
     *
     * @param skypeUserName the skype user name
     */
    public void setSkypeUserName(String skypeUserName) {
        this.skypeUserName = skypeUserName;
    }

    /**
     * Gets about us.
     *
     * @return the about us
     */
    public String getAboutUs() {
        return aboutUs;
    }

    /**
     * Sets about us.
     *
     * @param aboutUs the about us
     */
    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    /**
     * Gets nace.
     *
     * @return the nace
     */
    public Nace getNace() {
        return nace;
    }

    /**
     * Sets nace.
     *
     * @param nace the nace
     */
    public void setNace(Nace nace) {
        this.nace = nace;
    }

    /**
     * Gets pic.
     *
     * @return the pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * Sets pic.
     *
     * @param pic the pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Long getCreatedDate() {
        return lastUpdate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Long createdDate) {
        this.lastUpdate = createdDate;
    }

    /**
     * Is member boolean.
     *
     * @return the boolean
     */
    public boolean isMember() {
        return member;
    }

    /**
     * Sets member.
     *
     * @param member the member
     */
    public void setMember(boolean member) {
        this.member = member;
    }

    /**
     * Gets nace id.
     *
     * @return the nace id
     */
    public List<String> getNaceId() {
        return naceId;
    }

    /**
     * Sets nace id.
     *
     * @param naceId the nace id
     */
    public void setNaceId(List<String> naceId) {
        this.naceId = naceId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "type=" + type +
                ", companyDirector='" + companyDirector + '\'' +
                ", contactEmails=" + contactEmails +
                ", contactPhones=" + contactPhones +
                ", socNetLink=" + socNetLink +
                ", skypeUserName='" + skypeUserName + '\'' +
                ", linkToWebSite=" + linkToWebSite +
                ", aboutUs='" + aboutUs + '\'' +
                ", nace=" + nace +
                ", pic='" + pic + '\'' +
                ", balance=" + balance +
                ", lastUpdate=" + lastUpdate +
                ", member=" + member +
                ", naceId=" + naceId +
                '}';
    }
}

