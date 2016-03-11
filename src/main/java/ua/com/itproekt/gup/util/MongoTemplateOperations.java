package ua.com.itproekt.gup.util;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.apache.log4j.Logger;
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
import java.util.IllegalFormatFlagsException;
import java.util.Map;

@Component
public final class MongoTemplateOperations {
    private static final Logger LOG = Logger.getLogger(MongoTemplateOperations.class);
    private static final String isPrefix = "is";
    private static final String getPrefix = "get";

    private static MongoTemplate staticMongoTemplate;

    @Autowired
    public MongoTemplateOperations(MongoTemplate mongoTemplate){
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
                    && (method.getName().startsWith(getPrefix)
                    || method.getName().startsWith(isPrefix))) {
                try {
                    Object value = method.invoke(objWithNewValues);
                    if (method.getName().equals("getId")) {
                        query.addCriteria(Criteria.where("_id").is(value));
                    } else {
                        update.set(getFieldNameFromGetter(method.getName()), value);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOG.error(LogUtil.getExceptionStackTrace(e));
                }
            }
        }

        //FindAndModifyOptions().returnNew(true) = newly updated document
        //FindAndModifyOptions().returnNew(false) = old document (not updateInvestor yet)
        return (T)staticMongoTemplate.findAndModify(
                query,
                update,
                new FindAndModifyOptions().returnNew(true),
                objWithNewValues.getClass());
    }

    private static String getFieldNameFromGetter(String getterName) {
        int getterPrefixLength = -1;
        if (getterName.startsWith(getPrefix)) {
            getterPrefixLength = 3;
        } else if (getterName.startsWith(isPrefix)){
            getterPrefixLength = 2;
        } else {
            throw new IllegalArgumentException();
        }

        return Character.toLowerCase(getterName.charAt(getterPrefixLength)) + getterName.substring(getterPrefixLength + 1);
    }

    public static <T> Map explainQuery(String collectionName, Query query) {
        DBCollection collection = staticMongoTemplate.getCollection(collectionName);
        DBCursor cursor = collection.find(query.getQueryObject());
        return cursor.explain().toMap();
    }
}
