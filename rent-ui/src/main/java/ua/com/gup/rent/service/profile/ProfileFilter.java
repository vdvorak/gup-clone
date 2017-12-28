package ua.com.gup.rent.service.profile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonUserRole;

import java.util.Set;

@Data
public class ProfileFilter {
    @ApiModelProperty(position = 10, example = "username")
    private String username;
    @ApiModelProperty(position = 20, example = "email")
    private String email;
    @ApiModelProperty(position = 30, example = "id100000623")
    private String publicId;
    @ApiModelProperty(position = 40, example = "[ROLE_ADMIN | ROLE_MODERATOR | ROLE_MANAGER | ROLE_USER]")
    private Set<CommonUserRole> userRoles;
    @ApiModelProperty(position = 50, example = "0930000000")
    private String mainPhone;
    @ApiModelProperty(position = 60, example = "0930000000")
    private String additionalPhone;
    @ApiModelProperty(position = 60, example = "Иванов аван")
    private String managerUsername;



}
