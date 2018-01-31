package ua.com.gup.rent.model.mongo.user;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;


@Data
public class RentManagerClientInfo extends ManagerClientInfo {


    private Integer offerQuantity;
    private Integer spendedMoney;
    private Integer rentOfferQuantity;
    private Integer bonusMoney;
    //Заявки на бронь
    private Integer bookingRequests;



}
