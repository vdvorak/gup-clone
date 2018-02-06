package ua.com.gup.common.dto.profile.bonus;

import lombok.Data;
import ua.com.gup.common.dto.CommonCreateDTO;

/**
 * @author Victor Dvorak
 **/
@Data
public class CommonBonusCreateDTO extends CommonCreateDTO {
    private String code;

    public CommonBonusCreateDTO(String id, String code) {
        super(id);
        this.code = code;
    }
}
