package ua.com.gup.common.mapper.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerActionType;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerMeetingAction;

@Component
public class ManagerActionMapperFactory {

    @Autowired
    private ManagerCallActionMapper managerCallActionMapper;
    @Autowired
    private ManagerMeetingActionMapper managerMeetingActionMapper;

    public ManagerActionMapper getMapper(ManagerActionType type) {
        switch (type) {
            case TYPE_CALL:
                return managerCallActionMapper;
            case TYPE_MEETING:
                return managerMeetingActionMapper;
            default:
                throw new IllegalArgumentException("ManagerActionType[" + type + "] not supported");

        }


    }

}
