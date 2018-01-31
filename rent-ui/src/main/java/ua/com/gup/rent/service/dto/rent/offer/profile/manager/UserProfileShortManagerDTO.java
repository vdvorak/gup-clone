package ua.com.gup.rent.service.dto.rent.offer.profile.manager;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.mongo.Phone;
import ua.com.gup.rent.model.mongo.user.ExtendManagerUserInfo;
import ua.com.gup.rent.model.mongo.user.ManagerProfile;
import ua.com.gup.rent.model.mongo.user.UserProfile;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserProfileShortManagerDTO {
    @ApiModelProperty(position = 10, example = "id000001")
    private String publicId;
    @ApiModelProperty(position = 20, example = "Dmitriy")
    private String firstname;
    @ApiModelProperty(position = 30, example = "Dmitriy")
    private String lastname;
    @ApiModelProperty(position = 40, example = "0930000000")
    private Phone mainPhone;
    @ApiModelProperty(position = 50)
    private ManagerInfoUserProfileShortDTO managerInfo;
    @ApiModelProperty(position = 111, example = "true|false")
    private Boolean active;
    @ApiModelProperty(position = 112, example = "true|false")
    private Boolean ban;
    @ApiModelProperty(position = 113, example = "1323215646")
    private Long createdDate;
    @ApiModelProperty(position = 114, example = "qwe@qwe.com")
    private String email;
    @ApiModelProperty(position = 115, example = "13456798")
    private Long lastLogin;
    @ApiModelProperty(position = 116, example = "http://images.s3.dev.gup.ua/650ad70d-9b84-478e-9280-0f1d4de0cb44")
    private String imageUrlSmall;
    @ApiModelProperty(position = 117, example = "http://images.s3.dev.gup.ua/650ad70d-9b84-478e-9280-0f1d4de0cb44")
    private String imageUrlLarge;
    @ApiModelProperty(position = 118, example = "[ROLE_USER,ROLE_MANAGER]")
    private Set<String> userRoles;


    public UserProfileShortManagerDTO(UserProfile profile, ManagerProfile manager) {
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.publicId = profile.getPublicId();
        this.mainPhone = profile.getMainPhone();
        this.userRoles = profile.getUserRoles().stream().map(cur -> cur.toString()).collect(Collectors.toSet());
        this.publicId = profile.getPublicId();
        this.imageUrlSmall = profile.getImageLarge() != null ? profile.getImageLarge().getUrl() : null;
        this.imageUrlLarge = profile.getImageLarge() != null ? profile.getImageSmall().getUrl() : null;
        this.createdDate = profile.getCreatedDate();
        this.lastLogin = profile.getLastLoginDate();
        this.active = profile.getActive();
        this.ban = profile.getBan();
        this.email = profile.getEmail();
        ExtendManagerUserInfo managerInfo = profile.getManagerInfo();
        if(managerInfo != null) {
            this.managerInfo = new ManagerInfoUserProfileShortDTO(managerInfo, manager);
        }
    }
}
