package ua.com.gup.mongo.model.operation;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.mongo.operation.CommonOperation;
import ua.com.gup.common.model.object.ObjectType;

@Document(collection = ObjectType.SALE_OPERATION)
public class SaleOperation extends CommonOperation {
}
