package ua.com.itproekt.gup.model.profiles;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.offer.Address;
import ua.com.itproekt.gup.model.offer.GeneralPhone;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;
import ua.com.itproekt.gup.util.OfferUserContactInfo;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


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

    @Email
    @Indexed
    private String email;
    private String socWendor = "gup.com.ua";
    private String uid;
    private String password;
    private String passwordRestore;
    private String tokenKey;
    private String mainPhoneNumber;
    private Integer mainPhoneNumberViews;
    private Boolean mainPhoneNumberHide;
    private GeneralPhone generalPhone;

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
    private String imgId;
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
    private Set<UserRole> userRoles;

    private Long createdDate;
    private Long lastLoginDate;
    private Long lastTryLoginDate;
    private int countTryLoginDate;
    private boolean isUnlockAccount;
    private boolean online;
    private int notCompletedFields;

    private List<OrderAddress> orderAddressList;
    private List<OfferUserContactInfo> offerUserContactInfoList;

    private String publicHash;
    private String publicKey;
    private String privateKey;
    private BankCard bankCard;


    private Boolean ban = false;

    /* ************************************************************************************************ */
    /*
     * Lawyer-Profile
     */
    private Set<String> userType;
    private Address lawyerAddress;
    private String nameCompany;
    private String fullNameCompany;
    private String fullNameOvner;
    private String EGRPOU;
    private String identificationСode;
    private String IPN;
    private Address locationAddress;
    private String mainKindActivity;
    private Set<String> emploeyrList; //private Set<ProfileContactList> emploeyrList;



    public boolean hasUserRole(String userRole) {
        return EnumUtils.isValidEnum(UserRole.class, userRole);
    }

    public Profile setLastTryLoginDateEqualsToCurrentDate() {
        final int LIMIT_ATTEMPTS = 5,
                BLOCK_TIME_INTERVAL = 60000;

        //TODO: account is unlock == TRUE & time interval less one-minut
        if(isUnlockAccount && ((LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()-lastTryLoginDate)<BLOCK_TIME_INTERVAL)){
            //TODO if the time interval is less than five seconds
            if ((LIMIT_ATTEMPTS*1000)<(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()-lastTryLoginDate)){
                countTryLoginDate++;
            }
            //TODO: it was more than a five attemps - then block account
            if(LIMIT_ATTEMPTS<countTryLoginDate){
                isUnlockAccount = false;
            }
            lastTryLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        } else if (lastTryLoginDate==null){ //TODO: then account was after lock (account is unlock == FALSE|TRUE) - time interval more one-minut
            lastTryLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
            isUnlockAccount = true;
            countTryLoginDate = 1;
        } else if (BLOCK_TIME_INTERVAL<(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()-lastTryLoginDate)){
            lastTryLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
            isUnlockAccount = true;
            countTryLoginDate = 1;
        }

        return this;
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

    public Long getLastTryLoginDate() {
        return lastTryLoginDate;
    }

    public Profile setLastTryLoginDate(Long lastTryLoginDate) {
        this.lastTryLoginDate = lastTryLoginDate;
        return this;
    }

    public int getCountTryLoginDate() {
        return countTryLoginDate;
    }

    public Profile setCountTryLoginDate(int lastTryLoginDate) {
        this.countTryLoginDate = countTryLoginDate;
        return this;
    }

    public boolean getIsUnlockAccount() {
        return isUnlockAccount;
    }

    public Profile setIsUnlockAccount(boolean isUnlockAccount) {
        this.isUnlockAccount = isUnlockAccount;
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

    public Integer getMainPhoneNumberViews() {
        return mainPhoneNumberViews;
    }

    public Profile setMainPhoneNumberViews(Integer mainPhoneNumberViews) {
        this.mainPhoneNumberViews = mainPhoneNumberViews;
        return this;
    }

    public Boolean isMainPhoneNumberHide() {
        return mainPhoneNumberHide;
    }

    public Profile setMainPhoneNumberHide(Boolean mainPhoneNumberHide) {
        this.mainPhoneNumberHide = mainPhoneNumberHide;
        return this;
    }

    public GeneralPhone getGeneralPhone() {
        return generalPhone;
    }

    public Profile setGeneralPhone(GeneralPhone generalPhone) {
        this.generalPhone = generalPhone;
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


    public int getNotCompletedFields() {
        return notCompletedFields;
    }

    public Profile setNotCompletedFields(int notCompletedFields) {
        this.notCompletedFields = notCompletedFields;
        return this;
    }

    public String getPublicHash() {
        return publicHash;
    }

    public Profile setPublicHash(String publicHash) {
        this.publicHash = publicHash;
        return this;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public Profile setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public Profile setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
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

    /* ************************************************************************************************ */
    /*
     * Lawyer-Profile
     */
    public Set<String> getUserType() {
        return userType;
    }

    public Profile setUserType(Set<String> userType) {
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
                ", mainPhoneNumber='" + mainPhoneNumber + '\'' +
                ", mainPhoneNumberViews=" + mainPhoneNumberViews +
                ", mainPhoneNumberHide=" + mainPhoneNumberHide +
                ", username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", executive='" + executive + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", address='" + address + '\'' +
                ", imgId='" + imgId + '\'' +
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
                ", lastTryLoginDate=" + lastTryLoginDate +
                ", countTryLoginDate=" + countTryLoginDate +
                ", isUnlockAccount=" + isUnlockAccount +
                ", online=" + online +
                ", notCompletedFields=" + notCompletedFields +
                ", orderAddressList=" + orderAddressList +
                ", offerUserContactInfoList=" + offerUserContactInfoList +
                ", publicKey=" + publicKey +
                ", privateKey=" + privateKey +
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
                '}';
    }
}
