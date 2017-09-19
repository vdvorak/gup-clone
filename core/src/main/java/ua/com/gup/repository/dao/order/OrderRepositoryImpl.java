package ua.com.gup.repository.dao.order;


import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.model.order.Order;
import ua.com.gup.model.order.filter.OrderFilterOptions;
import ua.com.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;
import java.util.List;


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


    /**
     *
     * @param orderFilterOptions
     * @return
     */
    @Override
    public List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions) {
        Query query = new Query();

        if (orderFilterOptions.getOfferId() != null) {
            query.addCriteria(Criteria.where("offerId").is(orderFilterOptions.getOfferId()));
        }

        if (orderFilterOptions.getCreatedDateSortDirection() != null) {
            query.with(new Sort(Sort.Direction.fromString(orderFilterOptions.getCreatedDateSortDirection()), "startDate"));
        }


        query.skip(orderFilterOptions.getSkip());
        query.limit(orderFilterOptions.getLimit());


        if (orderFilterOptions.getBuyerId() != null && orderFilterOptions.getSellerId() != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("buyerId").is(orderFilterOptions.getBuyerId()),
                    Criteria.where("sellerId").is(orderFilterOptions.getSellerId())));
            return mongoTemplate.find(query, Order.class);
        }

        if (orderFilterOptions.getBuyerId() != null) {
            query.addCriteria(Criteria.where("buyerId").is(orderFilterOptions.getBuyerId()));
        }

        if (orderFilterOptions.getSellerId() != null) {
            query.addCriteria(Criteria.where("sellerId").is(orderFilterOptions.getSellerId()));
        }

        return mongoTemplate.find(query, Order.class);
    }
}
