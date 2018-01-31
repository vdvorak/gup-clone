package ua.com.gup.rent.model.mongo.user;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.ManagerUserInfo;

@Data
public class ExtendManagerUserInfo extends ManagerUserInfo {
    private Integer offerQuantity;
    private Integer spendedMoney;
    private Integer rentOfferQuantity;
    private Integer bonusMoney;
    //Заявки на бронь
    private Integer bookingRequests;
}
