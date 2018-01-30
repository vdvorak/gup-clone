package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.mongo.manager.InterestingStatus;
import ua.com.gup.rent.model.mongo.user.ExtendManagerUserInfo;
import ua.com.gup.rent.model.mongo.user.ManagerProfile;

@Data
public class ManagerInfoUserProfileShortDTO {
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
    private String additionalInfo;
    @ApiModelProperty(position = 130, example = "INTERESTED")
    private InterestingStatus interestingStatus;

    private ManagerContactInfoDTO contactInfo;

    public ManagerInfoUserProfileShortDTO(ExtendManagerUserInfo info, ManagerProfile manager) {
        if(info != null) {
            this.offerQuantity = info.getOfferQuantity();
            this.spendedMoney = info.getSpendedMoney();
            this.rentOfferQuantity = info.getRentOfferQuantity();
            this.bonusMoney = info.getBonusMoney();
            this.bookingRequests = info.getBookingRequests();
            this.additionalInfo = info.getAdditionalInfo();
            this.interestingStatus = info.getInterestingStatus();
            this.contactInfo = new ManagerContactInfoDTO(info.getContactInfo());
        }
        if (manager != null) {
            this.managerPublicId = manager.getPublicId();
            this.managerFirstname = manager.getFirstname();
            this.managerLastname = manager.getLastname();
        }

    }
}
