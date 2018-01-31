package ua.com.gup.common.model.mongo.manager;

import lombok.Data;

@Data
public abstract class ManagerClientInfo {

    private String manager;
    private String managerPublicId;
    private String managerFirstname;
    private String managerLastname;
    private String additionalInfo;
    private InterestingStatus interestingStatus;

    private ContactInfo contactInfo;


}
