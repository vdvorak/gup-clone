package ua.com.gup.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.gup.common.model.mongo.operation.CommonOperation;
import ua.com.gup.common.repository.OperationRepository;
import ua.com.gup.common.service.OperationService;

import java.io.IOException;
import java.io.ObjectInputStream;

public abstract class OperationServiceImpl<T extends CommonOperation> implements OperationService<T> {



    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void save(T t) {
        operationRepository.save(t);
    }


}
