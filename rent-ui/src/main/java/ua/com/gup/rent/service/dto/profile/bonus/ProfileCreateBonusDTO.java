package ua.com.gup.rent.service.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 5, max = 50)
    private String name;
    //auto generation
    private String code;

    @ApiModelProperty(value = "publicId", example = "id777")
    private String managerPublicId;

    @ApiModelProperty(value = "Float", example = "12.11")
    private BigDecimal bonusAmount;

}
