package ua.com.itproekt.gup.dao.order;


import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;


@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Order.class)) {
            mongoTemplate.createCollection(Order.class);
        }
    }

    @Override
    public void create(Order order) {
        mongoTemplate.insert(order);
    }

    @Override
    public Order findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Order.class);
    }

    @Override
    public Order findAndUpdate(Order order) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(order);
    }

    @Override
    public int delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, Order.class);
        return result.getN();
    }
}
