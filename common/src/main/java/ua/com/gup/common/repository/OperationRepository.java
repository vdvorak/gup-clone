package ua.com.gup.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.common.model.mongo.operation.CommonOperation;

public interface OperationRepository<T extends CommonOperation> extends MongoRepository<T, String> {

}
