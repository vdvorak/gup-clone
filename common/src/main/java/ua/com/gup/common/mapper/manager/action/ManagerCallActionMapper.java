package ua.com.gup.common.mapper.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDto;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerCallAction;
import ua.com.gup.common.service.CommonProfileService;

@Component
public class ManagerCallActionMapper extends ManagerActionMapper<ManagerCallAction, ManagerActionDto> {

    @Autowired
    private CommonProfileService profileService;

    @Override
    protected ManagerActionDto createDto() {
        return new ManagerActionDto();
    }

    public ManagerActionDto toDto(ManagerCallAction action) {
        ManagerActionDto dto = super.toDto(action);
        dto.setCallStatus(action.getCallStatus());
        return dto;
    }

    public ManagerCallAction fromDto(ManagerCallAction model, ManagerActionDto dto) {
        model = super.fromDto(model, dto);
        model.setCallStatus(dto.getCallStatus());
        return model;
    }
}
