package ua.com.gup.common.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Victor Dvorak
 **/
@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public abstract class CommonProfileBonusDTO implements Serializable {

    @ApiModelProperty(value = "Boolean", example = "false|true")
    @NotNull
    protected Boolean active;

    @ApiModelProperty(value = "dd-MM-yyyy HH:mm:ss", example = "03-12-2018 10:15")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    protected LocalDateTime createDate;

    @ApiModelProperty(value = "dd-MM-yyyy HH:mm:ss", example = "03-12-2018 10:15")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull
    protected LocalDateTime startDate;

    @ApiModelProperty(value = "dd-MM-yyyy HH:mm:ss", example = "03-12-2018 10:15")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull
    protected LocalDateTime endDate;

    @ApiModelProperty(value = "Integer", example = "5")
    @NotNull
    protected Integer countUse;

    @ApiModelProperty(value = "String", example = "{BONUS_MONEY,REFERENCE_MANAGER}")
    @NotNull
    protected CommonBonusScenarios scenarios;

}