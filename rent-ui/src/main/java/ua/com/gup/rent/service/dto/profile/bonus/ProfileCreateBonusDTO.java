package ua.com.gup.rent.service.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Victor Dvorak
 **/
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString(callSuper = true)
public class ProfileCreateBonusDTO extends CommonProfileBonusDTO {

    @ApiModelProperty(value = "name", example = "BONUS_NAME")
    @NotNull
    private String name;
    @ApiModelProperty(value = "codeGen", example = "qw1wA-123dF")
    //@ApiModelProperty(hidden = true, readOnly = true)
    private String code;

    //for scenario manager
    @ApiModelProperty(value = "publicId", example = "id777")
    private String managerPublicId;
    //for scenario bonusAmount
    @ApiModelProperty(value = "##.##", example = "12.11")
    private BigDecimal bonusAmount;

}
