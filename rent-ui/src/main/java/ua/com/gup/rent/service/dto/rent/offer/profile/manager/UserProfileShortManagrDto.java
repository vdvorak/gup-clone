package ua.com.gup.rent.service.dto.rent.offer.profile.manager;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.rent.model.mongo.user.RentOfferManagerProfile;
import ua.com.gup.rent.model.mongo.user.RentOfferUserProfile;

@Data
public class UserProfileShortManagrDto {
    @ApiModelProperty(position = 10, example = "id000001")
    private String publicId;
    @ApiModelProperty(position = 20, example = "Dmitriy")
    private String firstname;
    @ApiModelProperty(position = 30, example = "Dmitriy")
    private String lastname;
    @ApiModelProperty(position = 40, example = "0930000000")
    private String phone;
    @ApiModelProperty(position = 50, example = "123")
    private Integer offerQuantity;
    @ApiModelProperty(position = 60, example = "123")
    private Integer spendedMoney;
    @ApiModelProperty(position = 70, example = "123")
    private Integer rentOfferQuantity;
    @ApiModelProperty(position = 80, example = "v")
    private Integer bonusMoney;
    @ApiModelProperty(position = 90, example = "id000002")
    private String managerPublicId;
    @ApiModelProperty(position = 100, example = "Dmitriy")
    private String managerFirstname;
    @ApiModelProperty(position = 110, example = "Dmitriy")
    private String managerLastname;


    public UserProfileShortManagrDto(RentOfferUserProfile profile, RentOfferManagerProfile manager) {
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.publicId = profile.getPublicId();
        this.phone = profile.getMainPhone() != null ? profile.getMainPhone().getPhoneNumber() : null;
        this.offerQuantity = 0;
        this.spendedMoney = 0;
        this.rentOfferQuantity = 0;
        this.bonusMoney = 0;
        if (manager != null) {
            this.managerFirstname = manager.getFirstname();
            this.managerLastname = manager.getLastname();
            this.managerPublicId = manager.getPublicId();
        }
    }
}
