package ua.com.gup.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDTO;
import ua.com.gup.common.model.filter.ManagerActionFilter;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerCallAction;
import ua.com.gup.common.model.mongo.profile.manager.event.status.CallStatus;

import java.util.Collection;

public interface ManagerActionService {

    ManagerAction create(ManagerActionDTO action);

    ManagerAction save(ManagerActionDTO action);

    void remove(ManagerAction action);

    void remove(String id);

    Collection<ManagerAction> findByManagerPublicId(String managerPublicId);

    ManagerAction changeStatus(ManagerCallAction action, CallStatus newStatus);

    boolean exists(String id);

    ManagerActionDTO getById(String id);

    Page<ManagerActionDTO> findAll(ManagerActionFilter filter, Pageable pageable);

}
