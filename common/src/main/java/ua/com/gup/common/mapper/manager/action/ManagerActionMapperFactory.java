package ua.com.gup.common.mapper.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDto;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerActionType;
import ua.com.gup.common.service.CommonProfileService;

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
