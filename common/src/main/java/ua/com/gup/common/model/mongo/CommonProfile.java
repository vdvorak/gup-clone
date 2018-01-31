package ua.com.gup.common.model.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.com.gup.common.annotation.Email;
import ua.com.gup.common.annotation.Password;
import ua.com.gup.common.model.ImageFileInfo;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;
import ua.com.gup.common.model.mongo.manager.ManagerInfo;
import ua.com.gup.common.model.mongo.offer.OfferUserContactInfo;
import ua.com.gup.common.model.mongo.profile.Contact;
import ua.com.gup.common.model.mongo.profile.FinanceInfo;
import ua.com.gup.common.model.mongo.profile.ProfileContactList;
import ua.com.gup.common.model.mongo.profile.ProfileStatistic;
import ua.com.gup.common.model.mongo.profile.order.OrderAddress;
import ua.com.gup.common.model.mongo.profile.phone.DBStorePhones;
import ua.com.gup.common.model.security.Role;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public abstract class CommonProfile<T extends ManagerClientInfo> {

    @Id
    private String id;
    @Indexed
    private  String publicId;
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
    private Phone mainPhone;
    @Indexed
    @Size(min = 2, max = 70)
    private String username;
    @Size(min = 2, max = 70)
    private String firstname;
    @Size(min = 2, max = 70)
    private String lastname;
    private Long createdDate;
    private Long lastLoginDate;
    private Boolean ban = false;
    private BanInfo banInfo;
    @Setter
    private ImageFileInfo imageLarge;
    @Setter
    private ImageFileInfo imageSmall;

    private CommonUserType userType;
    private Contact contact;
    @Size(max = 45)
    private String status;
    private Set<ProfileContactList> contactList;
    private Address address;
    private Set<String> socialList;
    private String imgUrl;
    private ProfileStatistic profileStatistic;
    private boolean online;
    private String chatUID;
    private Set<String> userRoles;
    private Boolean confirmModerator;
    private DBStorePhones storePhones;
    private Set<String> emploeyrList;
    private FinanceInfo financeInfo;
    private List<OrderAddress> orderAddressList;
    private List<OfferUserContactInfo> offerUserContactInfoList;

    public CommonProfile setId(String id) {
        this.id = id;
        return this;
    }

    public CommonProfile setIdSeoWord(String idSeoWord) {
        this.idSeoWord = idSeoWord;
        return this;
    }

    public CommonProfile setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public boolean getActive() {
        return active;
    }

    public CommonProfile setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Phone getMainPhone() {
        if (mainPhone == null) {
            mainPhone = new Phone();
        }
        return mainPhone;
    }

    public String imageLargeUrl() {
        return imageLarge == null ? null : imageLarge.getUrl();
    }

    public String imageSmallUrl() {
        return imageSmall == null ? null : imageSmall.getUrl();
    }

    /*
     * Lawyer-Profile
     */
    public CommonUserType getUserType() { //public Set<String> getUserType() {
        return userType;
    }

    public CommonProfile setUserType(CommonUserType userType) { //public Profile setUserType(Set<String> userType) {
        this.userType = userType;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public CommonProfile setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CommonProfile setStatus(String status) {
        this.status = status;
        return this;
    }

    public Set<ProfileContactList> getContactList() {
        return contactList;
    }

    public CommonProfile setContactList(Set<ProfileContactList> contactList) {
        this.contactList = contactList;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public CommonProfile setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Set<String> getSocialList() {
        return socialList;
    }

    public CommonProfile setSocialList(Set<String> socialList) {
        this.socialList = socialList;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public CommonProfile setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
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

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public CommonProfile setIsOnline(boolean isOnline) {
        this.online = isOnline;
        return this;
    }

    public String getChatUID() {
        return chatUID;
    }

    public void setChatUID(String chatUID) {
        this.chatUID = chatUID;
    }

    public Set<String> getUserRoles() {
        if (userRoles == null) {
            userRoles = new HashSet<>();
        }
        return userRoles;
    }

    public CommonProfile setUserRoles(Set<String> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Boolean isConfirmModerator() {
        return confirmModerator;
    }
    public Boolean getConfirmModerator() {
        return confirmModerator;
    }

    public CommonProfile setConfirmModerator(Boolean confirmModerator) {
        this.confirmModerator = confirmModerator;
        return this;
    }

    public DBStorePhones getStorePhones() {
        return storePhones;
    }

    public void setStorePhones(DBStorePhones storePhones) {
        this.storePhones = storePhones;
    }

    public Set<String> getEmploeyrList() {
        return emploeyrList;
    }

    public CommonProfile setEmploeyrList(Set<String> emploeyrList) {
        this.emploeyrList = emploeyrList;
        return this;
    }

    public CommonProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public CommonProfile setMainPhone(Phone mainPhone) {
        this.mainPhone = mainPhone;
        return this;
    }

    public CommonProfile setUsername(String username) {
        this.username = username;
        return this;
    }


    public CommonProfile setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }


    public CommonProfile setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CommonProfile setPassword(String password) {
        this.password = password;
        return this;
    }


    public CommonProfile setPasswordRestore(String passwordRestore) {
        this.passwordRestore = passwordRestore;
        return this;
    }

    public CommonProfile setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public CommonProfile setSocWendor(String socWendor) {
        this.socWendor = socWendor;
        return this;
    }


    public CommonProfile setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public CommonProfile setBan(Boolean ban) {
        this.ban = ban;
        return this;
    }

    public Boolean isBan() {
        return ban;
    }


    public CommonProfile setLastLoginDateEqualsToCurrentDate() {
        this.lastLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public CommonProfile setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }


    public CommonProfile setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public FinanceInfo getFinanceInfo() {
        return financeInfo;
    }

    public CommonProfile setFinanceInfo(FinanceInfo financeInfo) {
        this.financeInfo = financeInfo;
        return this;
    }


    public List<OrderAddress> getOrderAddressList() {
        return orderAddressList;
    }

    public CommonProfile setOrderAddressList(List<OrderAddress> orderAddressList) {
        this.orderAddressList = orderAddressList;
        return this;
    }

    public List<OfferUserContactInfo> getOfferUserContactInfoList() {
        return offerUserContactInfoList;
    }

    public CommonProfile setOfferUserContactInfoList(List<OfferUserContactInfo> offerUserContactInfoList) {
        this.offerUserContactInfoList = offerUserContactInfoList;
        return this;
    }

    public BanInfo getBanInfo() {
        return banInfo;
    }

    public void setBanInfo(BanInfo banInfo) {
        this.banInfo = banInfo;
    }


    public abstract T getManagerClientInfo();

    public abstract void setManagerClientInfo(T managerClientInfo);

    public abstract ManagerInfo getManagerInfo();

}
