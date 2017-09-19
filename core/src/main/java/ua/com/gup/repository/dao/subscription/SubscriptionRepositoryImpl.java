//package ua.com.gup.repository.dao.subscription;
//
//import com.mongodb.WriteResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//import ua.com.gup.model.subscription.Subscription;
//import ua.com.gup.model.subscription.filter.SubscriptionFilterOptions;
//import ua.com.gup.util.MongoTemplateOperations;
//
//import java.util.List;
//
//
///**
// * Implementation of the repository for work with subscriptions.
// *
// * @author Kobylyatskyy Alexander
// */
//@Repository
//public class SubscriptionRepositoryImpl implements SubscriptionRepository {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//
//    @Override
//    public void create(Subscription subscription) {
//        mongoTemplate.insert(subscription);
//    }
//
//    @Override
//    public Subscription find(String subscriptionId) {
//        Query query = new Query(Criteria.where("id").is(subscriptionId));
//        return mongoTemplate.findOne(query, Subscription.class);
//    }
//
//    @Override
//    public Subscription findAndUpdate(Subscription subscription) {
//        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(subscription);
//    }
//
//    @Override
//    public int delete(String subscriptionId) {
//        Query query = new Query(Criteria.where("id").is(subscriptionId));
//        WriteResult result = mongoTemplate.remove(query, Subscription.class);
//        return result.getN();
//    }
//
//    @Override
//    public List<Subscription> findWithFilterOption(SubscriptionFilterOptions subscriptionFilterOptions) {
//        Query query = new Query();
//
//        if (subscriptionFilterOptions.getUserId() != null) {
//            query.addCriteria(Criteria.where("userId").is(subscriptionFilterOptions.getUserId()));
//        }
//
//        if (subscriptionFilterOptions.getOfferFilterOptionsCheckSum() != null){
//            query.addCriteria(Criteria.where("offerFilterOptionsCheckSum").is(subscriptionFilterOptions.getOfferFilterOptionsCheckSum()));
//        }
//
//        query.skip(subscriptionFilterOptions.getSkip());
//        query.limit(subscriptionFilterOptions.getLimit());
//
//        return mongoTemplate.find(query, Subscription.class);
//    }
//
//    @Override
//    public List<Subscription> findAll() {
//        return mongoTemplate.findAll(Subscription.class);
//    }
//}
