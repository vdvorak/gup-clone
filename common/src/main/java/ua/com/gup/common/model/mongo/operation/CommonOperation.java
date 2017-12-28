package ua.com.gup.common.model.mongo.operation;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import ua.com.gup.common.GupLoggedUser;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CommonOperation {

    @Id
    private String id;
    private Instant operationDate;
    private OperationType operationType;
    private String objectId;
    private String objectType;
    private Object objectBody;
    @CreatedBy
    private GupLoggedUser operationUser;

}
