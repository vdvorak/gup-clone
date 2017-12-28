package ua.com.gup.dto.profile;

import lombok.Data;
import ua.com.gup.dto.operation.OperationDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;

import java.util.List;

@Data
public class AdminPrivateProfileDTO extends PrivateProfileDTO {

    private Boolean ban;
    private List<OperationDTO> operationsHistory;


    public AdminPrivateProfileDTO(Profile profile) {
        super(profile);
        this.ban = profile.getBan();
    }

}
