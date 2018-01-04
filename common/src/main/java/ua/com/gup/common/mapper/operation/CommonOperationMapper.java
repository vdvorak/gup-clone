package ua.com.gup.common.mapper.operation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.dto.operation.OperationDTO;
import ua.com.gup.common.dto.operation.UserBanOperationDTO;
import ua.com.gup.common.model.mongo.BanInfo;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.operation.CommonOperation;
import ua.com.gup.common.service.CommonProfileService;

@Component
public class CommonOperationMapper {
    @Autowired
    private CommonProfileService profileService;

    public OperationDTO convert(CommonOperation operation) {
        OperationDTO operationDTO = null;
        switch (operation.getOperationType()) {
            case USER_BAN:
                operationDTO = new UserBanOperationDTO();
                CommonProfile profile1 = (CommonProfile) operation.getObjectBody();
                if (profile1 != null) {
                    BanInfo banInfo = profile1.getBanInfo();
                    if (banInfo != null) {
                        ((UserBanOperationDTO) operationDTO).setPrivateExplanation(banInfo.getPrivateExplanation());
                        ((UserBanOperationDTO) operationDTO).setPublicExplanation(banInfo.getPublicExplanation());
                    }
                }
                break;
            default:
                operationDTO = new OperationDTO();
                break;
        }

        operationDTO.setOperationType(operation.getOperationType());
        operationDTO.setOperationDatetime(operation.getOperationDate());
        operationDTO.setOperationUserId(operation.getOperationUser().getPublicId());
        operationDTO.setOperationUserName(profileService.findById(operation.getOperationUser().getId()).getUsername());

        return operationDTO;
    }
}
