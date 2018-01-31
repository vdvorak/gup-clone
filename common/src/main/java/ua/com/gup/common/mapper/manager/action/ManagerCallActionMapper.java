package ua.com.gup.common.mapper.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDTO;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerCallAction;
import ua.com.gup.common.service.CommonProfileService;

@Component
public class ManagerCallActionMapper extends ManagerActionMapper<ManagerCallAction, ManagerActionDTO> {

    @Autowired
    private CommonProfileService profileService;

    @Override
    protected ManagerActionDTO createDto() {
        return new ManagerActionDTO();
    }

    public ManagerActionDTO toDto(ManagerCallAction action) {
        ManagerActionDTO dto = super.toDto(action);
        dto.setCallStatus(action.getCallStatus());
        return dto;
    }

    public ManagerCallAction fromDto(ManagerCallAction model, ManagerActionDTO dto) {
        model = super.fromDto(model, dto);
        model.setCallStatus(dto.getCallStatus());
        return model;
    }
}
