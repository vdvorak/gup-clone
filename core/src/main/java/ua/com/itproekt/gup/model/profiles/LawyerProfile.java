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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = LawyerProfile.COLLECTION_NAME)
public class LawyerProfile {
    public static final String COLLECTION_NAME = "lawyer_users";

    @Id
    private String id;

    @Indexed
    private String publicId;

    @Email(message = "заданный имэйл не может существовать")
    @Indexed
    private String email;

    @NotNull(message="Описание должно быть задано")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "заданный пароль не может существовать")
    private String password;

    @Size(min=14, message="Длина номера телефона должна быть больше четырнадцати")
    private String mainPhoneNumber;
    private Integer mainPhoneNumberViews;
    private Boolean mainPhoneNumberHide;
    private GeneralPhone generalPhone;

    private Address address;
    private String imgId;

    private FinanceInfo financeInfo;
    private Set<String> favoriteOffers;
    @Size(max = 45, message="Длина поля 'status' должна быть больше сорока пяти")
    private String status;

    private Integer point;
    private Set<ProfileRating> profileRating;

    private Boolean confirmModerator; // show is verified or not user profile
    private Set<UserRole> userRoles;

    private Long createdDate;
    private Long lastLoginDate;
    private Long lastTryLoginDate;
    private int countTryLoginDate;
    private boolean isUnlockAccount;
    private boolean online;
    private int notCompletedFields; //how many fields user filled

    private List<OrderAddress> orderAddressList;
    private List<OfferUserContactInfo> offerUserContactInfoList;

    private String publicHash;
    private String publicKey;
    private String privateKey;
    private BankCard bankCard;

    public boolean hasUserRole(String userRole) {
        return EnumUtils.isValidEnum(UserRole.class, userRole);
    }

    public LawyerProfile setLastTryLoginDateEqualsToCurrentDate() {
        if(isUnlockAccount && ((LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()-lastTryLoginDate)<60000)){ //TODO: account is unlock == TRUE & time interval less 1-minut
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

    public Address getAddress() {
        return address;
    }

    public LawyerProfile setAddress(Address address) {
        this.address = address;
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

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public LawyerProfile setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Set<ProfileRating> getProfileRating() {
        return profileRating;
    }

    public LawyerProfile setProfileRating(Set<ProfileRating> profileRating) {
        this.profileRating = profileRating;
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

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", publicId='" + publicId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mainPhoneNumber='" + mainPhoneNumber + '\'' +
                ", mainPhoneNumberViews=" + mainPhoneNumberViews +
                ", mainPhoneNumberHide=" + mainPhoneNumberHide +
                ", address='" + address + '\'' +
                ", imgId='" + imgId + '\'' +
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
                '}';
    }
}
