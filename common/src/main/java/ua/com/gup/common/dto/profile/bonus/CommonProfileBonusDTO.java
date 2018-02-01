package ua.com.gup.common.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Victor Dvorak
 **/
@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CommonProfileBonusDTO implements Serializable {

    @ApiModelProperty
    @NotNull
    protected Boolean active;

    @ApiModelProperty
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    protected ZonedDateTime createDate;

    @ApiModelProperty
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @NotNull
    protected ZonedDateTime startDate;

    @ApiModelProperty
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @NotNull
    protected ZonedDateTime endDate;

    @ApiModelProperty
    @NotNull
    protected Integer countUse;

    @ApiModelProperty
    @NotNull
    protected CommonBonusScenarios scenarios;

}