package ua.com.gup.rent.service.dto.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.dto.bonus.CommonBonusDTO;

import javax.validation.constraints.NotNull;

/**
 * @author Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCreateBonusDTO extends CommonBonusDTO {

    @NotNull
    private String name;

    //todo code generation by server
    private String code;

}
