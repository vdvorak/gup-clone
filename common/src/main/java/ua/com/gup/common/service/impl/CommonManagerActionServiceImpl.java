package ua.com.gup.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDTO;
import ua.com.gup.common.mapper.manager.action.ManagerActionMapper;
import ua.com.gup.common.mapper.manager.action.ManagerActionMapperFactory;
import ua.com.gup.common.model.filter.ManagerActionFilter;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerCallAction;
import ua.com.gup.common.model.mongo.profile.manager.event.status.CallStatus;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.common.repository.ManagerActionRepository;
import ua.com.gup.common.service.ManagerActionService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CommonManagerActionServiceImpl implements ManagerActionService {



    @Autowired
    private ManagerActionMapperFactory managerActionMapperFactory;

    @Autowired
    private ManagerActionRepository repository;

    @Autowired
    private CommonProfileRepository profileRepository;

    @Override
    public ManagerAction create(ManagerActionDTO dto) {
        ManagerActionMapper mapper = managerActionMapperFactory.getMapper(dto.getType());
        ManagerAction model = mapper.fromDto(dto.getType().createInstance(), dto);
        return repository.save(model);
    }

    @Override
    public ManagerAction save(ManagerActionDTO dto) {
        ManagerAction model = repository.findById(dto.getId());
        ManagerActionMapper mapper = managerActionMapperFactory.getMapper(dto.getType());
        model = mapper.fromDto(dto.getType().createInstance(), dto);
        return repository.save(model);
    }

    @Override
    public void remove(ManagerAction action) {
        repository.delete(action);
    }

    @Override
    public void remove(String id) {
        repository.delete(repository.findById(id));
    }

    @Override
    public Collection<ManagerAction> findByManagerPublicId(String managerPublicId) {
        return repository.findAllByManagerPublicId(managerPublicId);
    }

    @Override
    public ManagerAction changeStatus(ManagerCallAction action, CallStatus newStatus) {
        action.setCallStatus(newStatus);
        repository.save(action);
        return action;
    }

    @Override
    public boolean exists(String id) {
        return repository.exists(id);
    }

    @Override
    public ManagerActionDTO getById(String id) {
        ManagerAction action = repository.findById(id);
        if (action == null) {
            return null;
        }

        ManagerActionMapper mapper = managerActionMapperFactory.getMapper(action.getType());
        return mapper.toDto(action);
    }

    @Override
    public Page<ManagerActionDTO> findAll(ManagerActionFilter filter, Pageable pageable) {
        long count = repository.countFiltered(filter);
        List<ManagerAction> actions = Collections.EMPTY_LIST;
        if (count > 0) {
            actions = repository.findFiltered(filter, pageable);
        }

        List<ManagerActionDTO> list = actions.stream().map(action -> {
            return managerActionMapperFactory.getMapper(action.getType()).toDto(action);
        }).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, count);
    }
}
