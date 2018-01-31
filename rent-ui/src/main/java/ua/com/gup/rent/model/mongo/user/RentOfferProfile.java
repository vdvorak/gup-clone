package ua.com.gup.rent.model.mongo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;
import ua.com.gup.common.model.mongo.manager.ManagerInfo;
import ua.com.gup.common.model.object.ObjectType;

import java.util.Set;


@Document(collection = ObjectType.USER)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferProfile extends CommonProfile<RentManagerClientInfo> {
    private Set<String> favoriteOffers;
    private Integer point;
    private Address lawyerAddress;
    private String nameCompany;
    private String fullNameCompany;
    private String fullNameOvner;
    private String EGRPOU;
    private String identificationСode;
    private String IPN;
    private Address locationAddress;
    private String mainKindActivity;

    @Field("rentManagerClientInfo")
    private RentManagerClientInfo managerClientInfo;
    @Field("rentManagerInfo")
    private ManagerInfo managerInfo;

    public ManagerInfo getManagerInfo() {
        if(managerInfo == null){
            managerInfo = new ManagerInfo();
        }
        return managerInfo;
    }

    public RentManagerClientInfo getManagerClientInfo() {
        if(managerClientInfo == null){
            managerClientInfo = new RentManagerClientInfo();
        }
        return managerClientInfo;
    }
}
