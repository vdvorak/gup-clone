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

    @ApiModelProperty
    @NotNull
    protected Boolean active;

    @ApiModelProperty(value ="yyyy-MM-dd@HH:mm:ss" ,example = "2018-12-03T10:15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    protected LocalDateTime createDate;

    @ApiModelProperty(value ="yyyy-MM-dd@HH:mm:ss" ,example = "2018-12-03T10:15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm:ss")
    @NotNull
    protected LocalDateTime startDate;

    @ApiModelProperty(value ="yyyy-MM-dd@HH:mm:ss" ,example = "2018-12-03T10:15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    @NotNull
    protected LocalDateTime endDate;

    @ApiModelProperty
    @NotNull
    protected Integer countUse;

    @ApiModelProperty
    @NotNull
    protected CommonBonusScenarios scenarios;

}