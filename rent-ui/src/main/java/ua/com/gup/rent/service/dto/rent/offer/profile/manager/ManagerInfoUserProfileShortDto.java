package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.rent.model.mongo.user.RentManagerUserInfo;
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
    @ApiModelProperty(position = 120, example = "Some additional text info")
    public String additionalInfo;
    @ApiModelProperty(position = 130, example = "100")
    public Integer userRating; // 100 percent

    private ManagerContactInfoDto contactInfo;

    public ManagerInfoUserProfileShortDto(RentManagerUserInfo info, RentOfferManagerProfile manager) {
        if(info != null) {
            this.offerQuantity = info.getOfferQuantity();
            this.spendedMoney = info.getSpendedMoney();
            this.rentOfferQuantity = info.getRentOfferQuantity();
            this.bonusMoney = info.getBonusMoney();
            this.bookingRequests = info.getBookingRequests();
            this.contactInfo = new ManagerContactInfoDto(info.getContactInfo());
        }
        if (manager != null) {
            this.managerPublicId = manager.getPublicId();
            this.managerFirstname = manager.getFirstname();
            this.managerLastname = manager.getLastname();
        }

    }
}
