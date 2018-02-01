package ua.com.gup.common.dto.profile.manager.client;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.manager.InterestingStatus;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;

@Data
public abstract class CommonClientInfoProfileDto {

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
    private ClientContactInfoDto contactInfo;
}
