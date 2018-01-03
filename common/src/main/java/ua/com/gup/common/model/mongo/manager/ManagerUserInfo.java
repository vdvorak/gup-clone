package ua.com.gup.common.model.mongo.manager;

import lombok.Data;

@Data
public class ManagerUserInfo {

    private String managerPublicId;
    private String managerFirstname;
    private String managerLastname;

    private ContactInfo contactInfo;

    public ManagerUserInfo(){
    }


}
