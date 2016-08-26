package ua.com.itproekt.gup.model.profiles;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;
import ua.com.itproekt.gup.util.OfferUserContactInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
public class Profile {
    @Id
    private String id;
    @Indexed
    private String idSeoWord;

    @Indexed
    private String email;
    private String socWendor = "gup.com.ua";
    private String uid;
    private String password;
    private String tokenKey;
    private String mainPhoneNumber;

    @Indexed
    private String username;
    private String imgId;
    private Long birthDate;
    private Contact contact;
    private Set<String> contactList;
    private UserProfile userProfile;
    private Set<String> favoriteOffers;

    private Integer point;
    private Set<ProfileRating> profileRating;

    private Boolean confirmModerator;
    private Set<UserRole> userRoles;

    private Long createdDate;
    private Long lastLoginDate;
    private boolean online;

    private List<OrderAddress> orderAddressList;
    private List<OfferUserContactInfo> offerUserContactInfoList;

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

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public Profile setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public Boolean getConfirmModerator() {
        return confirmModerator;
    }

    public Profile setConfirmModerator(Boolean confirmModerator) {
        this.confirmModerator = confirmModerator;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public Profile setPoint(Integer point) {
        this.point = point;
        return this;
    }

    public String getId() {
        return id;
    }

    public Profile setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean isConfirmModerator() {
        return confirmModerator;
    }

    public String getIdSeoWord() {
        return idSeoWord;
    }

    public Profile setIdSeoWord(String idSeoWord) {
        this.idSeoWord = idSeoWord;
        return this;
    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public Profile setMainPhoneNumber(String mainPhoneNumber) {
        this.mainPhoneNumber = mainPhoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Profile setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Profile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Profile setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public Profile setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public Profile setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Set<ProfileRating> getProfileRating() {
        return profileRating;
    }

    public Profile setProfileRating(Set<ProfileRating> profileRating) {
        this.profileRating = profileRating;
        return this;
    }

    public Set<String> getContactList() {
        return contactList;
    }

    public Profile setContactList(Set<String> contactList) {
        this.contactList = contactList;
        return this;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public Profile setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Profile setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public List<OrderAddress> getOrderAddressList() {
        return orderAddressList;
    }

    public Profile setOrderAddressList(List<OrderAddress> orderAddressList) {
        this.orderAddressList = orderAddressList;
        return this;
    }

    public List<OfferUserContactInfo> getOfferUserContactInfoList() {
        return offerUserContactInfoList;
    }

    public Profile setOfferUserContactInfoList(List<OfferUserContactInfo> offerUserContactInfoList) {
        this.offerUserContactInfoList = offerUserContactInfoList;
        return this;
    }

    public Set<String> getFavoriteOffers() {
        return favoriteOffers;
    }

    public Profile setFavoriteOffers(Set<String> favoriteOffers) {
        this.favoriteOffers = favoriteOffers;
        return this;
    }

    public boolean isOnline() {
        return online;
    }

    public Profile setIsOnline(boolean isOnline) {
        this.online = isOnline;
        return this;
    }

    public String getSocWendor() {
        return socWendor;
    }

    public Profile setSocWendor(String socWendor) {
        this.socWendor = socWendor;
        return this;
    }


    public String getUid() {
        return uid;
    }

    public Profile setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public Profile setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
        return this;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", idSeoWord='" + idSeoWord + '\'' +
                ", email='" + email + '\'' +
                ", socWendor='" + socWendor + '\'' +
                ", uid='" + uid + '\'' +
                ", password='" + password + '\'' +
                ", tokenKey='" + tokenKey + '\'' +
                ", mainPhoneNumber='" + mainPhoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", imgId='" + imgId + '\'' +
                ", birthDate=" + birthDate +
                ", contact=" + contact +
                ", contactList=" + contactList +
                ", userProfile=" + userProfile +
                ", favoriteOffers=" + favoriteOffers +
                ", point=" + point +
                ", profileRating=" + profileRating +
                ", confirmModerator=" + confirmModerator +
                ", userRoles=" + userRoles +
                ", createdDate=" + createdDate +
                ", lastLoginDate=" + lastLoginDate +
                ", online=" + online +
                ", orderAddressList=" + orderAddressList +
                ", offerUserContactInfoList=" + offerUserContactInfoList +
                '}';
    }
}
