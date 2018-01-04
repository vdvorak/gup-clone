package ua.com.gup.common.mapper.profile.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.dto.operation.OperationDTO;
import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.service.OperationService;

import java.util.List;

@Component
public class AdminPrivateProfileMapper {

    @Autowired
    private OperationService operationService;

    public AdminPrivateProfileDTO convert(CommonProfile profile){
        AdminPrivateProfileDTO dto = new AdminPrivateProfileDTO(profile);
        List<OperationDTO> operations = operationService.findAllByOperationObjectIdDTO(profile.getId());
        dto.setOperationsHistory(operations);
        return dto;
    }
}
