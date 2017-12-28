package ua.com.gup.rent.component.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.command.SessionCommandExecutor;
import ua.com.gup.common.model.mongo.operation.CommonOperation;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.service.OperationService;
import ua.com.gup.rent.component.SpringApplicationContextProvider;
import ua.com.gup.rent.model.mongo.operation.RentOperation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Instant;


@Component
public class RentCommandExecutor extends SessionCommandExecutor {

    private static final long serialVersionUID = 1L;


    @Autowired
    private transient OperationService operationService;

    @Override
    final protected void journal(Journalable journalable, OperationType operationType) {
        CommonOperation operation = new RentOperation();
        operation.setObjectId(journalable.getObjectId());
        operation.setObjectType(journalable.getObjectType());
        operation.setOperationType(operationType);
        operation.setOperationDate(Instant.now());
        operation.setObjectBody(journalable.getObject());
        operationService.save(operation);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        // restore operationService field on deserialization
        SpringApplicationContextProvider.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
    }


}
