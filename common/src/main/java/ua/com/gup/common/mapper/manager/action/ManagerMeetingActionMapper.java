package ua.com.gup.common.mapper.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDTO;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerCallAction;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerMeetingAction;
import ua.com.gup.common.model.mongo.profile.manager.event.status.MeetingStatus;
import ua.com.gup.common.service.CommonProfileService;

@Component
public class ManagerMeetingActionMapper extends ManagerActionMapper<ManagerMeetingAction, ManagerActionDTO> {

    @Autowired
    private CommonProfileService profileService;

    @Override
    protected ManagerActionDTO createDto() {
        return new ManagerActionDTO();
    }

    public ManagerActionDTO toDto(ManagerMeetingAction action) {
        ManagerActionDTO dto = super.toDto(action);
        dto.setStatus(action.getMeetingStatus().name());
        return dto;
    }

    public ManagerMeetingAction fromDto(ManagerMeetingAction model, ManagerActionDTO dto) {
        model = super.fromDto(model, dto);
        model.setMeetingStatus(MeetingStatus.valueOf(dto.getStatus()));
        return model;
    }
}
