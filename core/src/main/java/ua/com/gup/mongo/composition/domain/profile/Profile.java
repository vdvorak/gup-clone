package ua.com.gup.mongo.composition.domain.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;
import ua.com.gup.common.model.mongo.manager.ManagerInfo;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.mongo.model.profiles.BankCard;
import ua.com.gup.mongo.model.profiles.ProfileRating;

import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = ObjectType.USER)
public class Profile extends CommonProfile {
    private Long birthDate;

    private Set<String> favoriteOffers;
    private Integer point;
    private Set<ProfileRating> profileRating;

    private BankCard bankCard;
    private Address lawyerAddress;
    private String nameCompany;
    private String fullNameCompany;
    private String fullNameOvner;
    private String EGRPOU;
    private String identificationСode;
    private String IPN;
    private Address locationAddress;
    private String mainKindActivity;
    private String facebookId;



    public Profile() {
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public Profile setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public Profile setPoint(Integer point) {
        this.point = point;
        return this;
    }


    public Set<ProfileRating> getProfileRating() {
        return profileRating;
    }

    public Profile setProfileRating(Set<ProfileRating> profileRating) {
        this.profileRating = profileRating;
        return this;
    }

    public Set<String> getFavoriteOffers() {
        return favoriteOffers;
    }

    public Profile setFavoriteOffers(Set<String> favoriteOffers) {
        this.favoriteOffers = favoriteOffers;
        return this;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public Profile setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
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


    public String getFacebookId() {
        return facebookId;
    }

    public Profile setFacebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;
    }


    @Override
    public ManagerClientInfo getManagerClientInfo() {
        return this.getSaleManagerClientInfo();
    }

    @Override
    public void setManagerClientInfo(ManagerClientInfo managerClientInfo) {
        this.saleManagerClientInfo = managerClientInfo;
    }

    @Override
    public ManagerInfo getManagerInfo() {
        return this.getSaleManagerInfo();
    }



    @Override
    public String toString() {

        return "Profile{" +
                ", birthDate=" + birthDate +
                ", favoriteOffers=" + favoriteOffers +
                ", point=" + point +
                ", profileRating=" + profileRating +
                ", bankCard=" + bankCard +
                ", lawyerAddress='" + lawyerAddress + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                ", fullNameCompany='" + fullNameCompany + '\'' +
                ", fullNameOvner='" + fullNameOvner + '\'' +
                ", EGRPOU='" + EGRPOU + '\'' +
                ", identificationСode='" + identificationСode + '\'' +
                ", IPN='" + IPN + '\'' +
                ", locationAddress='" + locationAddress + '\'' +
                ", mainKindActivity='" + mainKindActivity + '\'' +
                '}';
    }
}