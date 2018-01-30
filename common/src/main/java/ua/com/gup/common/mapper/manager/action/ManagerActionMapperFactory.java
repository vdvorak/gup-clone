package ua.com.gup.common.mapper.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerActionType;

@Component
public class ManagerActionMapperFactory {

    @Autowired
    private ManagerCallActionMapper managerCallActionMapper;

    public ManagerActionMapper getMapper(ManagerActionType type) {
        switch (type) {
            case TYPE_CALL:
                return managerCallActionMapper;
            default:
                throw new IllegalArgumentException("ManagerActionType[" + type + "] not supported");

        }


    }

}
