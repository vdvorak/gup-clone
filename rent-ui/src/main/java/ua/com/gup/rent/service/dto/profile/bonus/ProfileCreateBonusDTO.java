package ua.com.gup.rent.service.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;

import javax.validation.constraints.NotNull;

/**
 * @author Victor Dvorak
 **/
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class ProfileCreateBonusDTO extends CommonProfileBonusDTO {

    @ApiModelProperty
    @NotNull
    private String name;

    @ApiModelProperty
    //todo code generation by server
    private String code;

}
