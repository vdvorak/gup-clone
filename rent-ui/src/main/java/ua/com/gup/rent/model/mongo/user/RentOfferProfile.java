package ua.com.gup.rent.model.mongo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.common.model.mongo.Phone;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.rent.RentOfferAddress;
import ua.com.gup.rent.model.rent.profiles.phone.RentOfferDBStorePhones;

import javax.validation.constraints.Size;
import java.util.Set;


@Document(collection = ObjectType.USER)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferProfile {

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
    private Set<CommonUserRole> userRoles;
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
    private CommonUserType userType;
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

}
