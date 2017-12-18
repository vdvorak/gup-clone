package ua.com.gup.server.component.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.command.SessionCommandExecutor;
import ua.com.gup.common.model.mongo.operation.CommonOperation;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.service.OperationService;
import ua.com.gup.mongo.model.operation.SaleOperation;
import ua.com.gup.server.component.SpringApplicationContextProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Instant;


@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class SaleCommandExecutor extends SessionCommandExecutor {

    private static final long serialVersionUID = 1L;


    @Autowired
    private transient OperationService operationService;

    @Override
    final protected void journal(Journalable journalable, OperationType operationType) {
        CommonOperation operation = new SaleOperation();
        operation.setObjectId(journalable.getObjectId());
        operation.setObjectType(journalable.getObjectType());
        operation.setOperationType(operationType);
        operation.setOperationDate(Instant.now());
        operationService.save(operation);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        // restore operationService field on deserialization
        SpringApplicationContextProvider.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
    }


}
