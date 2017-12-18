package ua.com.gup.common.model.mongo.operation;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import ua.com.gup.common.GupLoggedUser;

import java.time.Instant;

@Data
public class CommonOperation {

    @Id
    private String id;
    private Instant operationDate;
    private OperationType operationType;
    private String objectType;
    @CreatedBy
    private GupLoggedUser operationUser;

}
