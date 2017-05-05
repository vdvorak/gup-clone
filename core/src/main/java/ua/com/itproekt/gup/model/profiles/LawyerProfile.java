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
public class LawyerProfile {
    public static final String COLLECTION_NAME = "lawyer_users";

    @Id
    private String id;

    @Indexed
    private String publicId;

    @Indexed
    private String idSeoWord;

    private boolean active;

    @Email(message = "заданный имэйл не может существовать")
    @Indexed
    private String email;

    @NotNull(message="Описание должно быть задано")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "заданный пароль не может существовать")
    private String password;

    @NotNull(message="Описание должно быть задано")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="заданный пароль не может существовать")
    @Size(min=8, message="Длина фамилии должна быть больше восьми")
    private String passwordRestore;
    private String tokenKey;

    @Size(min=14, message="Длина номера телефона должна быть больше четырнадцати")
    private String mainPhoneNumber;
    private Integer mainPhoneNumberViews;
    private Boolean mainPhoneNumberHide;
    private GeneralPhone generalPhone;

    private String executive;
    private String contactPerson;
    private Address lawyerAddress;
    private String imgId;
    private Contact contact;

    private Set<ProfileContactList> contactList;
    private Set<String> socialList;
    private FinanceInfo financeInfo;
    private Set<String> favoriteOffers;
    @Size(max = 45, message="Длина поля 'status' должна быть больше сорока пяти")
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

    /*
     * Requisites:
     */
    private String nameCompany;
    private String fullNameCompany;
    private String fullNameOvner;
    private String EGRPOU;
    private String identificationСode;
    private String IPN;
    private Address locationAddress;
    private String mainKindActivity;

    private Set<String> emploeyrList;

    public boolean hasUserRole(String userRole) {
        return EnumUtils.isValidEnum(UserRole.class, userRole);
    }

    public LawyerProfile setLastTryLoginDateEqualsToCurrentDate() {
        if(isUnlockAccount && ((LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()-lastTryLoginDate)<60000)){
            //TODO: time interval less five seconds
            if (5000<(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()-lastTryLoginDate)){
                countTryLoginDate++;
            }
            //TODO: it was more 5-tryis that block account
            if(4<countTryLoginDate){
                isUnlockAccount = false;
            }
            lastTryLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        } else if (lastTryLoginDate==null){ //TODO: then account was after lock (account is unlock == FALSE|TRUE) - time interval more 1-minut
            lastTryLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
            isUnlockAccount = true;
            countTryLoginDate = 1;
        } else if (60000<(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()-lastTryLoginDate)){
            isUnlockAccount = true;
            countTryLoginDate = 1;
        }

        return this;
    }

    public LawyerProfile setLastLoginDateEqualsToCurrentDate() {
        this.lastLoginDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public LawyerProfile setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    //*********************************************************************


    public String getImgId() {
        return imgId;
    }

    public LawyerProfile setImgId(String imgId) {
        this.imgId = imgId;
        return this;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public LawyerProfile setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public Long getLastTryLoginDate() {
        return lastTryLoginDate;
    }

    public LawyerProfile setLastTryLoginDate(Long lastTryLoginDate) {
        this.lastTryLoginDate = lastTryLoginDate;
        return this;
    }

    public int getCountTryLoginDate() {
        return countTryLoginDate;
    }

    public LawyerProfile setCountTryLoginDate(int lastTryLoginDate) {
        this.countTryLoginDate = countTryLoginDate;
        return this;
    }

    public boolean getIsUnlockAccount() {
        return isUnlockAccount;
    }

    public LawyerProfile setIsUnlockAccount(boolean isUnlockAccount) {
        this.isUnlockAccount = isUnlockAccount;
        return this;
    }

    public Boolean getConfirmModerator() {
        return confirmModerator;
    }

    public LawyerProfile setConfirmModerator(Boolean confirmModerator) {
        this.confirmModerator = confirmModerator;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public LawyerProfile setPoint(Integer point) {
        this.point = point;
        return this;
    }

    public String getId() {
        return id;
    }

    public LawyerProfile setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean isConfirmModerator() {
        return confirmModerator;
    }

    public String getIdSeoWord() {
        return idSeoWord;
    }

    public LawyerProfile setIdSeoWord(String idSeoWord) {
        this.idSeoWord = idSeoWord;
        return this;
    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public LawyerProfile setMainPhoneNumber(String mainPhoneNumber) {
        this.mainPhoneNumber = mainPhoneNumber;
        return this;
    }

    public Integer getMainPhoneNumberViews() {
        return mainPhoneNumberViews;
    }

    public LawyerProfile setMainPhoneNumberViews(Integer mainPhoneNumberViews) {
        this.mainPhoneNumberViews = mainPhoneNumberViews;
        return this;
    }

    public Boolean isMainPhoneNumberHide() {
        return mainPhoneNumberHide;
    }

    public LawyerProfile setMainPhoneNumberHide(Boolean mainPhoneNumberHide) {
        this.mainPhoneNumberHide = mainPhoneNumberHide;
        return this;
    }

    public GeneralPhone getGeneralPhone() {
        return generalPhone;
    }

    public LawyerProfile setGeneralPhone(GeneralPhone generalPhone) {
        this.generalPhone = generalPhone;
        return this;
    }

    public String getExecutive() {
        return executive;
    }

    public LawyerProfile setExecutive(String executive) {
        this.executive = executive;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public LawyerProfile setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public Address getLawyerAddress() {
        return lawyerAddress;
    }

    public LawyerProfile setLawyerAddress(Address lawyerAddress) {
        this.lawyerAddress = lawyerAddress;
        return this;
    }

    public boolean getActive() {
        return active;
    }

    public LawyerProfile setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public LawyerProfile setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LawyerProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LawyerProfile setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPasswordRestore() {
        return passwordRestore;
    }

    public LawyerProfile setPasswordRestore(String passwordRestore) {
        this.passwordRestore = passwordRestore;
        return this;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public LawyerProfile setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public LawyerProfile setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Set<ProfileRating> getProfileRating() {
        return profileRating;
    }

    public LawyerProfile setProfileRating(Set<ProfileRating> profileRating) {
        this.profileRating = profileRating;
        return this;
    }

    public Set<ProfileContactList> getContactList() {
        return contactList;
    }

    public LawyerProfile setContactList(Set<ProfileContactList> contactList) {
        this.contactList = contactList;
        return this;
    }

    public Set<String> getSocialList() {
        return socialList;
    }

    public LawyerProfile setSocialList(Set<String> socialList) {
        this.socialList = socialList;
        return this;
    }

    public FinanceInfo getFinanceInfo() {
        return financeInfo;
    }

    public LawyerProfile setFinanceInfo(FinanceInfo financeInfo) {
        this.financeInfo = financeInfo;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public LawyerProfile setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public List<OrderAddress> getOrderAddressList() {
        return orderAddressList;
    }

    public LawyerProfile setOrderAddressList(List<OrderAddress> orderAddressList) {
        this.orderAddressList = orderAddressList;
        return this;
    }

    public List<OfferUserContactInfo> getOfferUserContactInfoList() {
        return offerUserContactInfoList;
    }

    public LawyerProfile setOfferUserContactInfoList(List<OfferUserContactInfo> offerUserContactInfoList) {
        this.offerUserContactInfoList = offerUserContactInfoList;
        return this;
    }

    public Set<String> getFavoriteOffers() {
        return favoriteOffers;
    }

    public LawyerProfile setFavoriteOffers(Set<String> favoriteOffers) {
        this.favoriteOffers = favoriteOffers;
        return this;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public LawyerProfile setIsOnline(boolean isOnline) {
        this.online = isOnline;
        return this;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public LawyerProfile setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public LawyerProfile setStatus(String status) {
        this.status = status;
        return this;
    }


    public int getNotCompletedFields() {
        return notCompletedFields;
    }

    public LawyerProfile setNotCompletedFields(int notCompletedFields) {
        this.notCompletedFields = notCompletedFields;
        return this;
    }

    public String getPublicHash() {
        return publicHash;
    }

    public LawyerProfile setPublicHash(String publicHash) {
        this.publicHash = publicHash;
        return this;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public LawyerProfile setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public LawyerProfile setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public LawyerProfile setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
        return this;
    }


    public String getNameCompany() {
        return nameCompany;
    }

    public LawyerProfile setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
        return this;
    }

    public String getFullNameCompany() {
        return fullNameCompany;
    }

    public LawyerProfile setFullNameCompany(String fullNameCompany) {
        this.fullNameCompany = fullNameCompany;
        return this;
    }

    public String getFullNameOvner() {
        return fullNameOvner;
    }

    public LawyerProfile setFullNameOvner(String fullNameOvner) {
        this.fullNameOvner = fullNameOvner;
        return this;
    }

    public String getEGRPOU() {
        return EGRPOU;
    }

    public LawyerProfile setEGRPOU(String EGRPOU) {
        this.EGRPOU = EGRPOU;
        return this;
    }

    public String getIdentificationСode() {
        return identificationСode;
    }

    public LawyerProfile setIdentificationСode(String identificationСode) {
        this.identificationСode = identificationСode;
        return this;
    }

    public String getIPN() {
        return IPN;
    }

    public LawyerProfile setIPN(String IPN) {
        this.IPN = IPN;
        return this;
    }

    public Address getLocationAddress() {
        return locationAddress;
    }

    public LawyerProfile setLocationAddress(Address locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    public String getMainKindActivity() {
        return mainKindActivity;
    }

    public LawyerProfile setMainKindActivity(String mainKindActivity) {
        this.mainKindActivity = mainKindActivity;
        return this;
    }

    public Set<String> getEmploeyrList() {
        return emploeyrList;
    }

    public LawyerProfile setEmploeyrList(Set<String> emploeyrList) {
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
                ", password='" + password + '\'' +
                ", passwordRestore='" + passwordRestore + '\'' +
                ", tokenKey='" + tokenKey + '\'' +
                ", mainPhoneNumber='" + mainPhoneNumber + '\'' +
                ", mainPhoneNumberViews=" + mainPhoneNumberViews +
                ", mainPhoneNumberHide=" + mainPhoneNumberHide +
                ", executive='" + executive + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", lawyerAddress='" + lawyerAddress + '\'' +
                ", imgId='" + imgId + '\'' +
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
                ", nameCompany=" + nameCompany +
                ", fullNameCompany=" + fullNameCompany +
                ", fullNameOvner=" + fullNameOvner +
                ", EGRPOU=" + EGRPOU +
                ", identificationСode=" + identificationСode +
                ", IPN=" + IPN +
                ", locationAddress=" + locationAddress +
                ", mainKindActivity=" + mainKindActivity +
                ", emploeyrList=" + emploeyrList +
                '}';
    }
}
