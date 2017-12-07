package ua.com.gup.rent.model.mongo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.rent.model.enumeration.RentOfferUserRole;
import ua.com.gup.rent.model.enumeration.RentOfferUserType;
import ua.com.gup.rent.model.rent.RentOfferAddress;
import ua.com.gup.rent.model.rent.profiles.phone.RentOfferDBStorePhones;
import ua.com.gup.rent.model.rent.profiles.phone.RentOfferPhone;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import static ua.com.gup.rent.model.mongo.user.RentOfferProfile.COLLECTION_NAME;

@Document(collection = COLLECTION_NAME)
public class RentOfferProfile {

    public static final String COLLECTION_NAME = "users"; //rent.users

    @Id
    private String id;
    @Indexed
    private String publicId;
    @Indexed
    private String idSeoWord;
    private boolean active;


    @Indexed
    private String email;
    private String socWendor = "gup.com.ua";
    private String uid;

    private String passwordRestore;
    private String tokenKey;
    private RentOfferPhone mainPhone;
    @Indexed
    @Size(min = 2, max = 70)
    private String username;
    @Size(min = 2, max = 70)
    private String firstname;
    @Size(min = 2, max = 70)
    private String lastname;
    private String executive;
    private String contactPerson;
    private RentOfferAddress address;
    private String imgId;
    private String imgUrl;
    private Long birthDate;
  //  private Contact contact;
  //  private Set<RentOfferProfileContactList> contactList;
    private Set<String> socialList;
  //  private FinanceInfo financeInfo;
    private Set<String> favoriteOffers;
    @Size(max = 45)
    private String status;
    private Integer point;
  //  private Set<RentOfferProfileRating> profileRating;
    private Boolean confirmModerator;
    private Set<RentOfferUserRole> userRoles;
    private Long createdDate;
    private Long lastLoginDate;
    private boolean online;
    //private List<RentOfferOrderAddress> orderAddressList;
    //private List<OfferUserContactInfo> offerUserContactInfoList;
   // private BankCard bankCard;
    private Boolean ban = false;
    /*
     * Lawyer-Profile
     */
    private RentOfferUserType userType;
    private RentOfferAddress lawyerAddress;
    private String nameCompany;
    private String fullNameCompany;
    private String fullNameOvner;
    private String EGRPOU;
    private String identificationСode;
    private String IPN;
    private RentOfferAddress locationAddress;
    private String mainKindActivity;
    private Set<String> emploeyrList;
    private RentOfferDBStorePhones storePhones;

    //private RentOfferProfileStatistic profileStatistic;

    private String chatUID;

    public RentOfferProfile setLastLoginDateEqualsToCurrentDate() {
        this.lastLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public RentOfferProfile setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public String getImgId() {
        return imgId;
    }

    public RentOfferProfile setImgId(String imgId) {
        this.imgId = imgId;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public RentOfferProfile setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public RentOfferProfile setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public RentOfferProfile setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public Boolean getConfirmModerator() {
        return confirmModerator;
    }

    public RentOfferProfile setConfirmModerator(Boolean confirmModerator) {
        this.confirmModerator = confirmModerator;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public RentOfferProfile setPoint(Integer point) {
        this.point = point;
        return this;
    }

    public String getId() {
        return id;
    }

    public RentOfferProfile setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean isConfirmModerator() {
        return confirmModerator;
    }

    public String getIdSeoWord() {
        return idSeoWord;
    }

    public RentOfferProfile setIdSeoWord(String idSeoWord) {
        this.idSeoWord = idSeoWord;
        return this;
    }

    public RentOfferPhone getMainPhone() {
        if (mainPhone == null) {
            mainPhone = new RentOfferPhone();
        }
        return mainPhone;
    }

    public RentOfferProfile setMainPhone(RentOfferPhone mainPhone) {
        this.mainPhone = mainPhone;
        return this;
    }


    public String getUsername() {
        return username;
    }

    public RentOfferProfile setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public RentOfferProfile setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public RentOfferProfile setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getExecutive() {
        return executive;
    }

    public RentOfferProfile setExecutive(String executive) {
        this.executive = executive;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public RentOfferProfile setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public RentOfferAddress getAddress() {
        return address;
    }

    public RentOfferProfile setAddress(RentOfferAddress address) {
        this.address = address;
        return this;
    }

    public boolean getActive() {
        return active;
    }

    public RentOfferProfile setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public RentOfferProfile setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RentOfferProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPasswordRestore() {
        return passwordRestore;
    }

    public RentOfferProfile setPasswordRestore(String passwordRestore) {
        this.passwordRestore = passwordRestore;
        return this;
    }

    public Set<RentOfferUserRole> getUserRoles() {
        if (userRoles == null) {
            userRoles = new HashSet<>();
        }
        return userRoles;
    }

    public RentOfferProfile setUserRoles(Set<RentOfferUserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    /*public Contact getContact() {
        return contact;
    }

    public Profile setContact(Contact contact) {
        this.contact = contact;
        return this;
    }*/

    /*public Set<RentOfferProfileRating> getProfileRating() {
        return profileRating;
    }

    public Profile setProfileRating(Set<RentOfferProfileRating> profileRating) {
        this.profileRating = profileRating;
        return this;
    }*/

    /*public Set<RentOfferProfileContactList> getContactList() {
        return contactList;
    }

    public Profile setContactList(Set<RentOfferProfileContactList> contactList) {
        this.contactList = contactList;
        return this;
    }*/

    public Set<String> getSocialList() {
        return socialList;
    }

    public RentOfferProfile setSocialList(Set<String> socialList) {
        this.socialList = socialList;
        return this;
    }

    /*public FinanceInfo getFinanceInfo() {
        return financeInfo;
    }

    public Profile setFinanceInfo(FinanceInfo financeInfo) {
        this.financeInfo = financeInfo;
        return this;
    }*/

    public Long getCreatedDate() {
        return createdDate;
    }

    public RentOfferProfile setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    /*public List<OrderAddress> getOrderAddressList() {
        return orderAddressList;
    }

    public Profile setOrderAddressList(List<OrderAddress> orderAddressList) {
        this.orderAddressList = orderAddressList;
        return this;
    }*/

    /*public List<OfferUserContactInfo> getOfferUserContactInfoList() {
        return offerUserContactInfoList;
    }

    public Profile setOfferUserContactInfoList(List<OfferUserContactInfo> offerUserContactInfoList) {
        this.offerUserContactInfoList = offerUserContactInfoList;
        return this;
    }*/

    public Set<String> getFavoriteOffers() {
        return favoriteOffers;
    }

    public RentOfferProfile setFavoriteOffers(Set<String> favoriteOffers) {
        this.favoriteOffers = favoriteOffers;
        return this;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public RentOfferProfile setIsOnline(boolean isOnline) {
        this.online = isOnline;
        return this;
    }

    public String getSocWendor() {
        return socWendor;
    }

    public RentOfferProfile setSocWendor(String socWendor) {
        this.socWendor = socWendor;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RentOfferProfile setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public RentOfferProfile setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RentOfferProfile setStatus(String status) {
        this.status = status;
        return this;
    }


/*    public BankCard getBankCard() {
        return bankCard;
    }

    public Profile setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
        return this;
    }*/


    public Boolean getBan() {
        return ban;
    }

    public RentOfferProfile setBan(Boolean ban) {
        this.ban = ban;
        return this;
    }

    public Boolean isBan() {
        return ban;
    }

    /*
     * Lawyer-Profile
     */
    public RentOfferUserType getUserType() { //public Set<String> getUserType() {
        return userType;
    }

    public RentOfferProfile setUserType(RentOfferUserType userType) { //public Profile setUserType(Set<String> userType) {
        this.userType = userType;
        return this;
    }

    public RentOfferAddress getLawyerAddress() {
        return lawyerAddress;
    }

    public RentOfferProfile setLawyerAddress(RentOfferAddress lawyerAddress) {
        this.lawyerAddress = lawyerAddress;
        return this;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public RentOfferProfile setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
        return this;
    }

    public String getFullNameCompany() {
        return fullNameCompany;
    }

    public RentOfferProfile setFullNameCompany(String fullNameCompany) {
        this.fullNameCompany = fullNameCompany;
        return this;
    }

    public String getFullNameOvner() {
        return fullNameOvner;
    }

    public RentOfferProfile setFullNameOvner(String fullNameOvner) {
        this.fullNameOvner = fullNameOvner;
        return this;
    }

    public String getEGRPOU() {
        return EGRPOU;
    }

    public RentOfferProfile setEGRPOU(String EGRPOU) {
        this.EGRPOU = EGRPOU;
        return this;
    }

    public String getIdentificationСode() {
        return identificationСode;
    }

    public RentOfferProfile setIdentificationСode(String identificationСode) {
        this.identificationСode = identificationСode;
        return this;
    }

    public String getIPN() {
        return IPN;
    }

    public RentOfferProfile setIPN(String IPN) {
        this.IPN = IPN;
        return this;
    }

    public RentOfferAddress getLocationAddress() {
        return locationAddress;
    }

    public RentOfferProfile setLocationAddress(RentOfferAddress locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    public String getMainKindActivity() {
        return mainKindActivity;
    }

    public RentOfferProfile setMainKindActivity(String mainKindActivity) {
        this.mainKindActivity = mainKindActivity;
        return this;
    }

    public Set<String> getEmploeyrList() {
        return emploeyrList;
    }

    public RentOfferProfile setEmploeyrList(Set<String> emploeyrList) {
        this.emploeyrList = emploeyrList;
        return this;
    }

    public RentOfferDBStorePhones getStorePhones() {
        return storePhones;
    }

    public void setStorePhones(RentOfferDBStorePhones storePhones) {
        this.storePhones = storePhones;
    }

    /*public RentOfferProfileStatistic getProfileStatistic() {
        if (profileStatistic == null) {
            profileStatistic = new RentOfferProfileStatistic();
        }
        return profileStatistic;
    }

    public void setProfileStatistic(RentOfferProfileStatistic profileStatistic) {
        this.profileStatistic = profileStatistic;
    }*/

    public String getChatUID() {
        return chatUID;
    }

    public void setChatUID(String chatUID) {
        this.chatUID = chatUID;
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
                ", passwordRestore='" + passwordRestore + '\'' +
                ", tokenKey='" + tokenKey + '\'' +
                ", username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", executive='" + executive + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", address='" + address + '\'' +
                ", imgId='" + imgId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", birthDate=" + birthDate +
       //         ", contact=" + contact +
       //         ", contactList=" + contactList +
       //         ", financeInfo=" + financeInfo +
                ", favoriteOffers=" + favoriteOffers +
                ", status='" + status + '\'' +
                ", point=" + point +
       //         ", profileRating=" + profileRating +
                ", confirmModerator=" + confirmModerator +
                ", userRoles=" + userRoles +
                ", createdDate=" + createdDate +
                ", lastLoginDate=" + lastLoginDate +
                ", online=" + online +
      //          ", orderAddressList=" + orderAddressList +
      //          ", offerUserContactInfoList=" + offerUserContactInfoList +
      //          ", bankCard=" + bankCard +
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
