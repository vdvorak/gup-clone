package ua.com.gup.common.dto.security.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.security.Role;

@Data
@ApiModel(description = "DTO for Role")
public class RoleEditDto {

    @ApiModelProperty(position = 10, example = "some discription")
    String title;


}
