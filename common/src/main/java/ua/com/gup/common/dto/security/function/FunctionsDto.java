package ua.com.gup.common.dto.security.function;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FunctionsDto {

    @ApiModelProperty(position = 20, example = "[READ_MANAGER_PROFILE, UPDATE_CATEGORY_ATTRIBUTE, CREATE_COMPLAINT]")
    private List<String> functions;
}
