package ua.com.itproekt.gup.model.profiles;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Document(collection = "users")
public class Profile {

    @Id
    private String id;
    private boolean confirmModerator;
    private String idWord;
    private int point;
    private String mainPhoneNumber;
    private String username;
    private String email;
    private String password;
    private Set<UserRole> userRoles;
    private UserProfile userProfile;
    private Contact contact;
    private Set<ProfileRating> profileRating;
    private Set<String> friendList;
    private Long createdDate;
    private AtomicInteger unreadMessages;
    public boolean hasUserRole(String userRole) {
        return EnumUtils.isValidEnum(UserRole.class, userRole);
    }

    //*********************************************************************

    public Profile setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public Profile setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Profile setId(String id) {
        this.id = id;
        return this;
    }

    public Profile setConfirmModerator(boolean confirmModerator) {
        this.confirmModerator = confirmModerator;
        return this;
    }

    public Profile setIdWord(String idWord) {
        this.idWord = idWord;
        return this;
    }

    public Profile setPoint(int point) {
        this.point = point;
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

    public Profile setFriendList(Set<String> friendList) {
        this.friendList = friendList;
        return this;
    }

    public Profile setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public String getId() {
        return id;
    }

    public boolean isConfirmModerator() {
        return confirmModerator;
    }

    public String getIdWord() {
        return idWord;
    }

    public int getPoint() {
        return point;
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

    public Set<String> getFriendList() {
        return friendList;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public AtomicInteger getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(AtomicInteger unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", confirmModerator=" + confirmModerator +
                ", idWord='" + idWord + '\'' +
                ", point=" + point +
                ", mainPhoneNumber='" + mainPhoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                ", userProfile=" + userProfile +
                ", contact=" + contact +
                ", profileRating=" + profileRating +
                ", friendList=" + friendList +
                ", createdDate=" + createdDate +
                ", unreadMessages=" + unreadMessages +
                '}';
    }
}
