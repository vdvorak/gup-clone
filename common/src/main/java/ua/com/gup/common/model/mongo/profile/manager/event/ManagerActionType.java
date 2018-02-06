package ua.com.gup.common.model.mongo.profile.manager.event;

import ua.com.gup.common.model.mongo.profile.manager.event.status.CallStatus;
import ua.com.gup.common.model.mongo.profile.manager.event.status.MeetingStatus;

import java.util.Arrays;
import java.util.List;

public enum ManagerActionType {
    TYPE_CALL(Arrays.asList(CallStatus.values()), ManagerCallAction.class),
    TYPE_MEETING(Arrays.asList(MeetingStatus.values()), ManagerMeetingAction.class);

    private Class cls;
    private List statuses;

    ManagerActionType(List statuses, Class cls) {
        this.statuses = statuses;
        this.cls = cls;
    }

    public List getStatuses() {
        return statuses;
    }

    public ManagerAction createInstance() {
        try {
            return (ManagerAction) cls.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}
