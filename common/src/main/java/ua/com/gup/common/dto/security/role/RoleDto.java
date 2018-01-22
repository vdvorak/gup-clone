package ua.com.gup.common.dto.security.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.security.Role;

@Data
@ApiModel(description = "DTO for Role")
public class RoleDto {

    @ApiModelProperty(position = 1, example = "ROLE_ADMIN")
    String name;
    @ApiModelProperty(position = 10, example = "some discription")
    String title;

    public RoleDto() {
    }

    public RoleDto(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public RoleDto(Role role) {
        this.name = role.getName();
        this.title = role.getTitle();
    }
}
