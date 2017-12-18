package ua.com.gup.common.service;

import ua.com.gup.common.model.mongo.operation.CommonOperation;

import java.io.Serializable;

public interface OperationService<T extends CommonOperation> extends Serializable {

    void save(T t);
}
