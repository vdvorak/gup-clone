package ua.com.gup.common.dto.profile.manager.event;

import lombok.Data;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerActionType;

import java.util.List;

@Data
public class ManagerActionTypeDTO {
    private ManagerActionType key;
    private List statuses;

    public ManagerActionTypeDTO() {
    }

    public ManagerActionTypeDTO(ManagerActionType type) {
        this.key = type;
        this.statuses = type.getStatuses();
    }
}
