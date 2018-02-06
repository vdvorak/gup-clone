package ua.com.gup.common.dto.profile.manager.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerActionType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ManagerActionDTO {
    @ApiModelProperty(position = 1, example = "Dmitriy")
    private String id;
    @ApiModelProperty(position = 2, example = "12-12-2012")
    @JsonFormat( pattern = "dd-MM-yyyy")
    private LocalDate date;
    @JsonFormat( pattern = "HH:mm")
    @ApiModelProperty(position = 3, example = "13:57")
    private LocalTime time;
    @ApiModelProperty(position = 4, example = "id100000228")
    private String clientId;
    @ApiModelProperty(position = 5, example = "Dmitriy")
    private String firstname;
    @ApiModelProperty(position = 6, example = "Dmitriy")
    private String lastname;
    @ApiModelProperty(position = 7, example = "qwe@qwe.com")
    private String email;
    @ApiModelProperty(position = 8, example = "0930000000")
    private String phone;
    @ApiModelProperty(position = 9, example = "some text")
    private String comment;
    @ApiModelProperty(position = 10, example = "id100000228")
    private String managerPublicId;
    @ApiModelProperty(position = 11, example = "Dmitriy")
    private String managerFirstname;
    @ApiModelProperty(position = 12, example = "Dmitriy")
    private String managerLastname;
    @ApiModelProperty(position = 13, example = "[TYPE_CALL]")
    @NotNull
    private ManagerActionType type;
    @ApiModelProperty(position = 14, example = "[NEW_CALL, FAIL_CALL, RE_CALL, PRESENTATION, ACTIVE_NEGOTIATIONS, CALL_BACK, REJECT]")
    private String status;
}
