package ua.com.gup.common.dto.profile;

import lombok.Data;
import ua.com.gup.common.dto.operation.OperationDTO;
import ua.com.gup.common.model.mongo.CommonProfile;

import java.util.List;

@Data
public class AdminPrivateProfileDTO extends PrivateProfileDTO {

    private Boolean ban;
    private List<OperationDTO> operationsHistory;


    public AdminPrivateProfileDTO(CommonProfile profile) {
        super(profile);
        this.ban = profile.getBan();
    }

}
