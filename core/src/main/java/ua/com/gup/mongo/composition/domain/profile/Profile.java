package ua.com.gup.mongo.composition.domain.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.config.annotation.Email;
import ua.com.gup.config.annotation.Password;
import ua.com.gup.mongo.model.offer.Address;
import ua.com.gup.mongo.model.offer.OfferUserContactInfo;
import ua.com.gup.mongo.model.profiles.*;
import ua.com.gup.mongo.model.profiles.order.OrderAddress;
import ua.com.gup.mongo.model.profiles.phone.DBStorePhones;
import ua.com.gup.mongo.model.profiles.phone.Phone;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ua.com.gup.common.model.ImageFileInfo;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Profile.COLLECTION_NAME)
public class Profile {
    public static final String COLLECTION_NAME = "users";

    @Id
    private String id;
    @Indexed
    private String publicId;
    @Indexed
    private String idSeoWord;
    private boolean active;


    @Indexed
    @Email
    private String email;
    private String socWendor = "gup.com.ua";
    private String uid;
    @Password
    private String password;
    private String passwordRestore;
    private String tokenKey;
    private Phone mainPhone;
    @Indexed
    @Size(min = 2, max = 70)
    private String username;
    @Size(min = 2, max = 70)
    private String firstname;
    @Size(min = 2, max = 70)
    private String lastname;
    private String executive;
    private String contactPerson;
    private Address address;    
    private String imgUrl;
    private Long birthDate;
    private Contact contact;
    private Set<ProfileContactList> contactList;
    private Set<String> socialList;
    private FinanceInfo financeInfo;
    private Set<String> favoriteOffers;
    @Size(max = 45)
    private String status;
    private Integer point;
    private Set<ProfileRating> profileRating;
    private Boolean confirmModerator;
    private Set<CommonUserRole> userRoles;
    private Long createdDate;
    private Long lastLoginDate;
    private boolean online;
    private List<OrderAddress> orderAddressList;
    private List<OfferUserContactInfo> offerUserContactInfoList;
    private BankCard bankCard;
    private Boolean ban = false;
    /*
     * Lawyer-Profile
     */
    private CommonUserType userType;
    private Address lawyerAddress;
    private String nameCompany;
    private String fullNameCompany;
    private String fullNameOvner;
    private String EGRPOU;
    private String identificationСode;
    private String IPN;
    private Address locationAddress;
    private String mainKindActivity;
    private Set<String> emploeyrList;
    private DBStorePhones storePhones;

    private ProfileStatistic profileStatistic;
    
    private String chatUID;
    private ImageFileInfo imageLarge;
    private ImageFileInfo imageSmall;

    public Profile setLastLoginDateEqualsToCurrentDate() {
        this.lastLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public Profile setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }

    public Profile setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Phone getMainPhone() {
        if (mainPhone == null) {
            mainPhone = new Phone();
        }
        return mainPhone;
    }

    public Profile setMainPhone(Phone mainPhone) {
        this.mainPhone = mainPhone;
        return this;
    }


    public String getUsername() {
        return username;
    }

    public Profile setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Profile setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public Profile setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getExecutive() {
        return executive;
    }

    public Profile setExecutive(String executive) {
        this.executive = executive;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Profile setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Profile setAddress(Address address) {
        this.address = address;
        return this;
    }

    public boolean getActive() {
        return active;
    }

    public Profile setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public Profile setPublicId(String publicId) {
        this.publicId = publicId;
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

    public String getPasswordRestore() {
        return passwordRestore;
    }

    public Profile setPasswordRestore(String passwordRestore) {
        this.passwordRestore = passwordRestore;
        return this;
    }

    public Set<CommonUserRole> getUserRoles() {
        if (userRoles == null) {
            userRoles = new HashSet<>();
        }
        return userRoles;
    }

    public Profile setUserRoles(Set<CommonUserRole> userRoles) {
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

    public Set<ProfileContactList> getContactList() {
        return contactList;
    }

    public Profile setContactList(Set<ProfileContactList> contactList) {
        this.contactList = contactList;
        return this;
    }

    public Set<String> getSocialList() {
        return socialList;
    }

    public Profile setSocialList(Set<String> socialList) {
        this.socialList = socialList;
        return this;
    }

    public FinanceInfo getFinanceInfo() {
        return financeInfo;
    }

    public Profile setFinanceInfo(FinanceInfo financeInfo) {
        this.financeInfo = financeInfo;
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

    public void setOnline(boolean online) {
        this.online = online;
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

    public String getStatus() {
        return status;
    }

    public Profile setStatus(String status) {
        this.status = status;
        return this;
    }


    public BankCard getBankCard() {
        return bankCard;
    }

    public Profile setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
        return this;
    }


    public Boolean getBan() {
        return ban;
    }

    public Profile setBan(Boolean ban) {
        this.ban = ban;
        return this;
    }

    public Boolean isBan() {
        return ban;
    }

    /*
     * Lawyer-Profile
     */
    public CommonUserType getUserType() { //public Set<String> getUserType() {
        return userType;
    }

    public Profile setUserType(CommonUserType userType) { //public Profile setUserType(Set<String> userType) {
        this.userType = userType;
        return this;
    }

    public Address getLawyerAddress() {
        return lawyerAddress;
    }

    public Profile setLawyerAddress(Address lawyerAddress) {
        this.lawyerAddress = lawyerAddress;
        return this;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public Profile setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
        return this;
    }

    public String getFullNameCompany() {
        return fullNameCompany;
    }

    public Profile setFullNameCompany(String fullNameCompany) {
        this.fullNameCompany = fullNameCompany;
        return this;
    }

    public String getFullNameOvner() {
        return fullNameOvner;
    }

    public Profile setFullNameOvner(String fullNameOvner) {
        this.fullNameOvner = fullNameOvner;
        return this;
    }

    public String getEGRPOU() {
        return EGRPOU;
    }

    public Profile setEGRPOU(String EGRPOU) {
        this.EGRPOU = EGRPOU;
        return this;
    }

    public String getIdentificationСode() {
        return identificationСode;
    }

    public Profile setIdentificationСode(String identificationСode) {
        this.identificationСode = identificationСode;
        return this;
    }

    public String getIPN() {
        return IPN;
    }

    public Profile setIPN(String IPN) {
        this.IPN = IPN;
        return this;
    }

    public Address getLocationAddress() {
        return locationAddress;
    }

    public Profile setLocationAddress(Address locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    public String getMainKindActivity() {
        return mainKindActivity;
    }

    public Profile setMainKindActivity(String mainKindActivity) {
        this.mainKindActivity = mainKindActivity;
        return this;
    }

    public Set<String> getEmploeyrList() {
        return emploeyrList;
    }

    public Profile setEmploeyrList(Set<String> emploeyrList) {
        this.emploeyrList = emploeyrList;
        return this;
    }

    public DBStorePhones getStorePhones() {
        return storePhones;
    }

    public void setStorePhones(DBStorePhones storePhones) {
        this.storePhones = storePhones;
    }

    public ProfileStatistic getProfileStatistic() {
        if (profileStatistic == null) {
            profileStatistic = new ProfileStatistic();
        }
        return profileStatistic;
    }

    public void setProfileStatistic(ProfileStatistic profileStatistic) {
        this.profileStatistic = profileStatistic;
    }

    public String getChatUID() {
        return chatUID;
    }

    public void setChatUID(String chatUID) {
        this.chatUID = chatUID;
    }

    public ImageFileInfo getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(ImageFileInfo imageLarge) {
        this.imageLarge = imageLarge;
    }

    public ImageFileInfo getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(ImageFileInfo imageSmall) {
        this.imageSmall = imageSmall;
    }
    
    public String imageLargeId() {
        return imageLarge == null ? null : imageLarge.getS3id();
    }

    public String imageSmallId() {
        return imageSmall == null ? null : imageSmall.getS3id();
    }

    @Override
    public String toString() {

        return "Profile{" +
                "id='" + id + '\'' +
                ", publicId='" + publicId + '\'' +
                ", active='" + active + '\'' +
                ", idSeoWord='" + idSeoWord + '\'' +
                ", email='" + email + '\'' +
                ", socWendor='" + socWendor + '\'' +
                ", uid='" + uid + '\'' +
                ", password='" + password + '\'' +
                ", passwordRestore='" + passwordRestore + '\'' +
                ", tokenKey='" + tokenKey + '\'' +
                ", username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", executive='" + executive + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", address='" + address + '\'' +                
                ", imgUrl='" + imgUrl + '\'' +
                ", birthDate=" + birthDate +
                ", contact=" + contact +
                ", contactList=" + contactList +
                ", financeInfo=" + financeInfo +
                ", favoriteOffers=" + favoriteOffers +
                ", status='" + status + '\'' +
                ", point=" + point +
                ", profileRating=" + profileRating +
                ", confirmModerator=" + confirmModerator +
                ", userRoles=" + userRoles +
                ", createdDate=" + createdDate +
                ", lastLoginDate=" + lastLoginDate +
                ", online=" + online +
                ", orderAddressList=" + orderAddressList +
                ", offerUserContactInfoList=" + offerUserContactInfoList +
                ", bankCard=" + bankCard +
                ", userType=" + userType +
                ", lawyerAddress='" + lawyerAddress + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                ", fullNameCompany='" + fullNameCompany + '\'' +
                ", fullNameOvner='" + fullNameOvner + '\'' +
                ", EGRPOU='" + EGRPOU + '\'' +
                ", identificationСode='" + identificationСode + '\'' +
                ", IPN='" + IPN + '\'' +
                ", locationAddress='" + locationAddress + '\'' +
                ", mainKindActivity='" + mainKindActivity + '\'' +
                ", emploeyrList=" + emploeyrList +
                ", storePhones=" + storePhones +
                '}';
    }
}