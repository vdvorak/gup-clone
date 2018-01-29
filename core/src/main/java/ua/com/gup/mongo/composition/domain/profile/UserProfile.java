package ua.com.gup.mongo.composition.domain.profile;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.com.gup.common.model.object.ObjectType;

@Document(collection = ObjectType.USER)
public class UserProfile extends Profile {

    @Field("saleManager")
    private String manager;

    @Field("saleManagerInfo")
    private SaleManagerUserInfo managerInfo;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public SaleManagerUserInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(SaleManagerUserInfo managerInfo) {
        this.managerInfo = managerInfo;
    }
}
