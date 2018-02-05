package ua.com.gup.rent.service.dto.rent;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonRefusalReason;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Api
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferModerationReportDTO implements Serializable {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    @NotNull
    private String id;

    @ApiModelProperty(position = 10, example = "5")
    private Integer category;

    @ApiModelProperty(position = 20, example = "['ADULT_CONTENT']")
    private List<CommonRefusalReason> refusalReasons;

    @ApiModelProperty(position = 30, example = "description")
    private String description;
}
