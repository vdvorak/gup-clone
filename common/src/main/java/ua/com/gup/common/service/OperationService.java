package ua.com.gup.common.service;

import ua.com.gup.common.dto.operation.OperationDTO;
import ua.com.gup.common.model.mongo.operation.CommonOperation;

import java.io.Serializable;
import java.util.List;

public interface OperationService<T extends CommonOperation> extends Serializable {

    void save(T t);

    List<T> findAllByOperationObjectId(String objectId);

    List<OperationDTO> findAllByOperationObjectIdDTO(String objectId);
}
