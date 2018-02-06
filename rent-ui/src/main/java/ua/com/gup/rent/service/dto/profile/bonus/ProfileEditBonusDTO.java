package ua.com.gup.rent.service.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Victor Dvorak
 **/
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString(callSuper = true)
public class ProfileEditBonusDTO extends ProfileCreateBonusDTO {

    @ApiModelProperty
    @NotNull
    private String id;

}
