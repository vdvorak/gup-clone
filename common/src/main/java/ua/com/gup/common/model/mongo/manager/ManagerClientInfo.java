package ua.com.gup.common.model.mongo.manager;

import lombok.Data;

@Data
public  class ManagerClientInfo {

    private String manager;
    private String managerPublicId;
    private String managerFirstname;
    private String managerLastname;
    private String additionalInfo;
    private InterestingStatus interestingStatus;

    private ContactInfo contactInfo;

    //rent
    private Integer offerQuantity;
    private Integer spendedMoney;
    private Integer rentOfferQuantity;
    private Integer bonusMoney;
    //Заявки на бронь
    private Integer bookingRequests;


}
