package ua.com.gup.common.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
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

    @ApiModelProperty(value = "dd-MM-yyyy HH:mm:ss", example = "03-12-2018 10:15")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    protected LocalDateTime createDate;

    @ApiModelProperty(value = "dd-MM-yyyy HH:mm:ss", example = "03-12-2018 10:15")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    protected LocalDateTime startDate;

    @ApiModelProperty(value = "dd-MM-yyyy HH:mm:ss", example = "03-12-2018 10:15")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull
    protected LocalDateTime endDate;

    @ApiModelProperty
    @NotNull
    protected Integer countUse;

    @ApiModelProperty
    @NotNull
    protected CommonBonusScenarios scenarios;

}