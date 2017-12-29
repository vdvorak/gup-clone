package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.rent.model.mongo.user.RentOfferManagerProfile;

@Data
public class ManagerInfoUserProfileShortDto {
    private Integer offerQuantity;
    @ApiModelProperty(position = 60, example = "123")
    private Integer spendedMoney;
    @ApiModelProperty(position = 70, example = "123")
    private Integer rentOfferQuantity;
    @ApiModelProperty(position = 80, example = "v")
    private Integer bonusMoney;
    @ApiModelProperty(position = 85, example = "123")
    //Заявки на бронь
    private Integer bookingRequests;
    @ApiModelProperty(position = 90, example = "id000002")
    private String managerPublicId;
    @ApiModelProperty(position = 100, example = "Dmitriy")
    private String managerFirstname;
    @ApiModelProperty(position = 110, example = "Dmitriy")
    private String managerLastname;

    public ManagerInfoUserProfileShortDto(Integer offerQuantity,
                                          Integer spendedMoney,
                                          Integer rentOfferQuantity,
                                          Integer bonusMoney,
                                          Integer bookingRequests,
                                          RentOfferManagerProfile manager) {
        this.offerQuantity = offerQuantity;
        this.spendedMoney = spendedMoney;
        this.rentOfferQuantity = rentOfferQuantity;
        this.bonusMoney = bonusMoney;
        this.bookingRequests = bookingRequests;
        if (manager != null) {
            this.managerPublicId = manager.getPublicId();
            this.managerFirstname = manager.getFirstname();
            this.managerLastname = manager.getLastname();
        }
    }
}
