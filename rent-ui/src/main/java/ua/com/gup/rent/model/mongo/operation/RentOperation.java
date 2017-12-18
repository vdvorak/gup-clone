package ua.com.gup.rent.model.mongo.operation;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.mongo.operation.CommonOperation;
import ua.com.gup.common.model.object.ObjectType;

@Document(collection = ObjectType.RENT_OPERATION)
public class RentOperation extends CommonOperation {
}
