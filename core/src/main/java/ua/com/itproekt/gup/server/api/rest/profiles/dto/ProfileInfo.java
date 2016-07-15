package ua.com.itproekt.gup.server.api.rest.profiles.dto;

import ua.com.itproekt.gup.model.profiles.*;

import java.util.Set;

public class ProfileInfo {

    private String id;

    private String idSeoWord;
    private String email;
    private String password;
    private String mainPhoneNumber;
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

    private boolean isOnline;

    public static ProfileInfo toModel(Profile profile) {

        return new ProfileInfo()
                .setId(profile.getId())
                .setIdSeoWord(profile.getIdSeoWord())
                .setEmail(profile.getEmail())
                .setPassword(profile.getPassword())
                .setMainPhoneNumber(profile.getMainPhoneNumber())
                .setUsername(profile.getUsername())
                .setImgId(profile.getImgId())
                .setBirthDate(profile.getBirthDate())
                .setContact(profile.getContact())
                .setContactList(profile.getContactList())
                .setUserProfile(profile.getUserProfile())
                .setPriofficeSets(profile.getPriofficeSets())
                .setPoint(profile.getPoint())
                .setUnreadMessages(profile.getUnreadMessages())
                .setProfileRating(profile.getProfileRating())
                .setConfirmModerator(profile.getConfirmModerator())
                .setUserRoles(profile.getUserRoles())
                .setCreatedDate(profile.getCreatedDate())
                .setLastLoginDate(profile.getLastLoginDate());
    }

    public String getId() {
        return id;
    }

    public ProfileInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getIdSeoWord() {
        return idSeoWord;
    }

    public ProfileInfo setIdSeoWord(String idSeoWord) {
        this.idSeoWord = idSeoWord;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ProfileInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public ProfileInfo setMainPhoneNumber(String mainPhoneNumber) {
        this.mainPhoneNumber = mainPhoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProfileInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getImgId() {
        return imgId;
    }

    public ProfileInfo setImgId(String imgId) {
        this.imgId = imgId;
        return this;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public ProfileInfo setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public ProfileInfo setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Set<String> getContactList() {
        return contactList;
    }

    public ProfileInfo setContactList(Set<String> contactList) {
        this.contactList = contactList;
        return this;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public ProfileInfo setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public Set<PriOfficeSetting> getPriofficeSets() {
        return priofficeSets;
    }

    public ProfileInfo setPriofficeSets(Set<PriOfficeSetting> priofficeSets) {
        this.priofficeSets = priofficeSets;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public ProfileInfo setPoint(Integer point) {
        this.point = point;
        return this;
    }

    public Integer getUnreadMessages() {
        return unreadMessages;
    }

    public ProfileInfo setUnreadMessages(Integer unreadMessages) {
        this.unreadMessages = unreadMessages;
        return this;
    }

    public Set<ProfileRating> getProfileRating() {
        return profileRating;
    }

    public ProfileInfo setProfileRating(Set<ProfileRating> profileRating) {
        this.profileRating = profileRating;
        return this;
    }

    public Boolean getConfirmModerator() {
        return confirmModerator;
    }

    public ProfileInfo setConfirmModerator(Boolean confirmModerator) {
        this.confirmModerator = confirmModerator;
        return this;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public ProfileInfo setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public ProfileInfo setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public ProfileInfo setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    private boolean isUserOnline(String userEmail) {

        return false;
    }


    @Override
    public String toString() {
        return "ProfileInfo{" +
                "id='" + id + '\'' +
                ", idSeoWord='" + idSeoWord + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mainPhoneNumber='" + mainPhoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", imgId='" + imgId + '\'' +
                ", birthDate=" + birthDate +
                ", contact=" + contact +
                ", contactList=" + contactList +
                ", userProfile=" + userProfile +
                ", priofficeSets=" + priofficeSets +
                ", point=" + point +
                ", unreadMessages=" + unreadMessages +
                ", profileRating=" + profileRating +
                ", confirmModerator=" + confirmModerator +
                ", userRoles=" + userRoles +
                ", createdDate=" + createdDate +
                ", lastLoginDate=" + lastLoginDate +
                ", isOnline=" + isOnline +
                '}';
    }
}
