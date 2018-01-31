package ua.com.gup.common.repository;

import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.filter.ManagerActionFilter;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;

import java.util.List;

public interface ManagerActionRepository{

    ManagerAction save(ManagerAction  action);

    void delete(ManagerAction  action);

    ManagerAction findById(String id);

    List<ManagerAction> findAllByManagerPublicId(String managerPublicId);

    List<ManagerAction> findFiltered(ManagerActionFilter filter, Pageable pageable);

    long countFiltered(ManagerActionFilter filter);

    boolean exists(String id);
}
