package ua.com.gup.common.dto.operation;

import lombok.Data;
import ua.com.gup.common.model.mongo.operation.OperationType;

import java.time.Instant;

@Data
public class OperationDTO {
    private OperationType operationType;
    private String operationUserId;
    private String operationUserName;
    private Instant operationDatetime;
}
