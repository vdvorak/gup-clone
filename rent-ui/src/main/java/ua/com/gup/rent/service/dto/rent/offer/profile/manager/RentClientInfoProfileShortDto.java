package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.dto.profile.manager.client.CommonClientInfoProfileDto;

@Data
public class RentClientInfoProfileShortDto extends CommonClientInfoProfileDto{
    private Integer offerQuantity;
    @ApiModelProperty(position = 60, example = "123")
    private Integer spendedMoney;
    @ApiModelProperty(position = 70, example = "123")
    private Integer rentOfferQuantity;
    @ApiModelProperty(position = 80, example = "123")
    private Integer bonusMoney;
    @ApiModelProperty(position = 85, example = "123")
    //Заявки на бронь
    private Integer bookingRequests;

//    public RentClientInfoProfileShortDto(RentManagerClientInfo info, RentOfferProfile manager) {
//        super(info, manager);
//        if (info != null) {
//            this.offerQuantity = info.getOfferQuantity();
//            this.spendedMoney = info.getSpendedMoney();
//            this.rentOfferQuantity = info.getRentOfferQuantity();
//            this.bonusMoney = info.getBonusMoney();
//            this.bookingRequests = info.getBookingRequests();
//        }
//    }
}
