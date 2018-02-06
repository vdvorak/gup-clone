package ua.com.gup.common.model.mongo.profile.manager.event;

import lombok.Data;
import ua.com.gup.common.model.mongo.profile.manager.event.status.MeetingStatus;

import static ua.com.gup.common.model.mongo.profile.manager.event.ManagerActionType.TYPE_MEETING;

@Data
public class ManagerMeetingAction extends ManagerAction {
    public MeetingStatus meetingStatus;

    public ManagerMeetingAction() {
        this.setType(TYPE_MEETING);
    }
}
