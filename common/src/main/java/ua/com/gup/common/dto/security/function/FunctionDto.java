package ua.com.gup.common.dto.security.function;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.security.Function;

import java.util.List;

@Data
public class FunctionDto {

    @ApiModelProperty(position = 20, example = "READ_MANAGER_PROFILE]")
    String name;
    @ApiModelProperty(position = 20, example = "Доступ к профилю менеджера")
    String title;
    @ApiModelProperty(position = 20, example = "[\"GET /api/managers/{managerPublicId}\"]")
    private List<String> paths;

    public FunctionDto() {
    }

    public FunctionDto(Function function) {
        this.name = function.getName();
        this.title= function.getTitle();
        this.paths= function.getPaths();
    }

    public FunctionDto(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
