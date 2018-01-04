package ua.com.gup.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.com.gup.common.dto.operation.OperationDTO;
import ua.com.gup.common.dto.operation.UserBanOperationDTO;
import ua.com.gup.common.mapper.operation.CommonOperationMapper;
import ua.com.gup.common.model.mongo.BanInfo;
import ua.com.gup.common.model.mongo.operation.CommonOperation;
import ua.com.gup.common.repository.OperationRepository;
import ua.com.gup.common.service.OperationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class OperationServiceImpl<T extends CommonOperation> implements OperationService<T> {


    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private CommonOperationMapper commonOperationMapper;

    @Override
    public void save(T t) {
        operationRepository.save(t);
    }

    @Override
    public List<T> findAllByOperationObjectId(String objectId) {
        return operationRepository.findAllByObjectIdIsOrderByOperationDateDesc(objectId);
    }

    @Override
    public List<OperationDTO> findAllByOperationObjectIdDTO(String objectId) {
        List<T> operations = findAllByOperationObjectId(objectId);
        if (!CollectionUtils.isEmpty(operations)) {
            List<OperationDTO> operationsHistory = new ArrayList<>(operations.size());
            for (CommonOperation operation : operations) {
                OperationDTO operationDto = commonOperationMapper.convert(operation);
                operationsHistory.add(operationDto);
            }
        return operationsHistory;
        }
        return Collections.EMPTY_LIST;
    }


}
