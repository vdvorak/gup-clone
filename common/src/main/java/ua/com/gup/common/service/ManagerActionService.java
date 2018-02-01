package ua.com.gup.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDto;
import ua.com.gup.common.model.filter.ManagerActionFilter;
import ua.com.gup.common.model.mongo.profile.manager.event.CallStatus;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerCallAction;

import java.util.Collection;

public interface ManagerActionService {

    ManagerAction create(ManagerActionDto action);

    ManagerAction save(ManagerActionDto action);

    void remove(ManagerAction action);

    void remove(String id);

    Collection<ManagerAction> findByManagerPublicId(String managerPublicId);

    ManagerAction changeStatus(ManagerCallAction action, CallStatus newStatus);

    boolean exists(String id);

    ManagerActionDto getById(String id);

    Page<ManagerActionDto> findAll(ManagerActionFilter filter, Pageable pageable);

}
