package ua.com.gup.common.model.mongo.profile.manager.event;

import lombok.Data;
import ua.com.gup.common.model.mongo.profile.manager.event.status.CallStatus;

import static ua.com.gup.common.model.mongo.profile.manager.event.ManagerActionType.TYPE_CALL;

@Data
public class ManagerCallAction extends ManagerAction {
    public CallStatus callStatus;

    public ManagerCallAction() {
        this.setType(TYPE_CALL);
    }
}
