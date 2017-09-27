package ua.com.gup.config.mongo;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

@Component
public final class MongoTemplateOperations {

    private static MongoTemplate staticMongoTemplate;

    @Autowired
    public MongoTemplateOperations(MongoTemplate mongoTemplate) {
        this.staticMongoTemplate = mongoTemplate;
    }

    /**
     * @param objWithNewValues
     * @param <T>
     * @return updated object
     * Updates only those fields that != null
     */
    public static <T> T updateFieldsAndReturnUpdatedObj(T objWithNewValues) {

        Query query = new Query();
        Update update = new Update();

        for (Method method : objWithNewValues.getClass().getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers())
                    && (method.getName().startsWith("get")
                    || method.getName().startsWith("is"))) {
                Object value = null;
                try {
                    value = method.invoke(objWithNewValues);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    if (method.getName().equals("getId")) {
                        query.addCriteria(Criteria.where("_id").is(value));
                    } else if (method.getName().startsWith("get")) {
                        String fieldName = method.getName().substring(3, 4).toLowerCase();
                        fieldName += method.getName().substring(4);
                        update.set(fieldName, value);
                    } else if (method.getName().startsWith("is")) {
                        String fieldName = method.getName().substring(2, 3).toLowerCase();
                        fieldName += method.getName().substring(3);
                        update.set(fieldName, value);
                    }
                }
            }
        }

        return (T) staticMongoTemplate.findAndModify(
                query,
                update,
                new FindAndModifyOptions().returnNew(true),
                objWithNewValues.getClass());
    }

    public static <T> Map explainQuery(String collectionName, Query query) {
        DBCollection collection = staticMongoTemplate.getCollection(collectionName);
        DBCursor cursor = collection.find(query.getQueryObject());
        return cursor.explain().toMap();
    }


}
