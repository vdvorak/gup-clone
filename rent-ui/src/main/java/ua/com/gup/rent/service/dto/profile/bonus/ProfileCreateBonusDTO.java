package ua.com.gup.rent.service.dto.profile.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;

import javax.validation.constraints.NotNull;

/**
 * @author Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProfileCreateBonusDTO extends CommonProfileBonusDTO {

    @NotNull
    private String name;

    //todo code generation by server
    private String code;

}
