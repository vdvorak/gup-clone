package ua.com.itproekt.gup.model.profiles;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Document(collection = "users")
public class Profile {
    @Id
    private String id;
    @Indexed
    private String idSeoWord;

    @Indexed
    private String email;
    private String password;
    private String mainPhoneNumber;

    @Indexed
    private String username;
    private String imgId;
    private Long birthDate;
    private Contact contact;
    private Set<String> contactList;
    private UserProfile userProfile;

    private Set<PriOfficeSetting> priofficeSets;

    private Integer point;
    private Integer unreadMessages;
    private Set<ProfileRating> profileRating;

    private Boolean confirmModerator;
    private Set<UserRole> userRoles;

    private Long createdDate;
    private Long lastLoginDate;


    public boolean hasUserRole(String userRole) {
        return EnumUtils.isValidEnum(UserRole.class, userRole);
    }

    public Profile setLastLoginDateEqualsToCurrentDate() {
        this.lastLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public Profile setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    //*********************************************************************

    public Set<PriOfficeSetting> getPriofficeSets() {
        return priofficeSets;
    }

    public Profile setPriofficeSets(Set<PriOfficeSetting> priofficeSets) {
        this.priofficeSets = priofficeSets;
        return this;
    }

    public String getImgId() {
        return imgId;
    }

    public Profile setImgId(String imgId) {
        this.imgId = imgId;
        return this;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public Profile setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Profile setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public Boolean getConfirmModerator() {
        return confirmModerator;
    }

    public Profile setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Profile setId(String id) {
        this.id = id;
        return this;
    }

    public Profile setConfirmModerator(Boolean confirmModerator) {
        this.confirmModerator = confirmModerator;
        return this;
    }

    public Profile setIdSeoWord(String idSeoWord) {
        this.idSeoWord = idSeoWord;
        return this;
    }

    public Profile setMainPhoneNumber(String mainPhoneNumber) {
        this.mainPhoneNumber = mainPhoneNumber;
        return this;
    }

    public Profile setUsername(String username) {
        this.username = username;
        return this;
    }

    public Profile setEmail(String email) {
        this.email = email;
        return this;
    }

    public Profile setPassword(String password) {
        this.password = password;
        return this;
    }

    public Profile setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Profile setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Profile setProfileRating(Set<ProfileRating> profileRating) {
        this.profileRating = profileRating;
        return this;
    }

    public Profile setContactList(Set<String> contactList) {
        this.contactList = contactList;
        return this;
    }

    public Profile setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public Profile setPoint(Integer point) {
        this.point = point;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public String getId() {
        return id;
    }

    public Boolean isConfirmModerator() {
        return confirmModerator;
    }

    public String getIdSeoWord() {
        return idSeoWord;
    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public Contact getContact() {
         return contact;
    }

    public Set<ProfileRating> getProfileRating() {
        return profileRating;
    }

    public Set<String> getContactList() {
        return contactList;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Integer getUnreadMessages() {
        return unreadMessages;
    }

    public Profile setUnreadMessages(Integer unreadMessages) {
        this.unreadMessages = unreadMessages;
        return this;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", confirmModerator=" + confirmModerator +
                ", idSeoWord='" + idSeoWord + '\'' +
                ", mainPhoneNumber='" + mainPhoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                ", userProfile=" + userProfile +
                ", contact=" + contact +
                ", profileRating=" + profileRating +
                ", contactList=" + contactList +
                ", createdDate=" + createdDate +
                ", unreadMessages=" + unreadMessages +
                '}';
    }
}
