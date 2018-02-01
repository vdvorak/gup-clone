package ua.com.gup.common.dto.profile.manager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.security.Role;

import java.util.Set;

@Data
public class ManagerProfileFilter {
    @ApiModelProperty(position = 10, example = "username")
    private String username;
    @ApiModelProperty(position = 20, example = "email")
    private String email;
    @ApiModelProperty(position = 30, example = "id100000623")
    private String publicId;
    @ApiModelProperty(position = 40, example = "[ROLE_ADMIN | ROLE_MODERATOR | ROLE_MANAGER | ROLE_USER]")
    private Set<String> userRoles;
    @ApiModelProperty(position = 50, example = "0930000000")
    private String mainPhone;
    @ApiModelProperty(position = 60, example = "0930000000")
    private String additionalPhone;
    @ApiModelProperty(position = 60, example = "Иванов аван")
    private String managerUsername;



}
