package ua.com.gup.rent.model.mongo.user;

import org.springframework.data.mongodb.core.mapping.Field;

public class RentOfferUserProfile extends RentOfferProfile {

    private String manager;

    @Field("rentManagerInfo")
    private RentManagerUserInfo managerInfo;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public RentManagerUserInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(RentManagerUserInfo managerInfo) {
        this.managerInfo = managerInfo;
    }
}
