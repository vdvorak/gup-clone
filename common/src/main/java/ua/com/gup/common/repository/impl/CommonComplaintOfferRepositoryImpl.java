package ua.com.gup.common.repository.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.complaint.CommonComplaint;
import ua.com.gup.common.model.complaint.ComplaintFilter;
import ua.com.gup.common.model.complaint.ComplaintOfferStatus;
import ua.com.gup.common.model.filter.ManagerActionFilter;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.repository.CommonComplaintOfferRepository;

import java.util.List;
import java.util.stream.Collectors;


public abstract class CommonComplaintOfferRepositoryImpl<T  extends CommonComplaint>
        implements CommonComplaintOfferRepository<T> {

    private final Logger log = LoggerFactory.getLogger(CommonComplaintOfferRepositoryImpl.class);

    private final Class<? extends T> clazz;

    @Autowired
    private MongoTemplate mongoTemplate;

    protected CommonComplaintOfferRepositoryImpl(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T complaintOffer) {
        mongoTemplate.insert(complaintOffer);
    }

    @Override
    public void update(T complaintOffer) {
        mongoTemplate.save(complaintOffer);
    }

    @Override
    public T findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, clazz);
    }

    @Override
    public boolean exists(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, clazz);
    }

    @Override
    public List<T> findAll(){
        return mongoTemplate.findAll(clazz).stream().collect(Collectors.toList());
    }

    @Override
    public List<T> findAllByOfferId(String offerId) {
        Query query = new Query(Criteria.where("offerId").is(offerId));
        return mongoTemplate.find(query, clazz).stream().collect(Collectors.toList());
    }

    @Override
    public List<T> findAllByInitiatorId(String initiatorId) {
        Query query = new Query(Criteria.where("initiatorId").is(initiatorId));
        return mongoTemplate.find(query, clazz).stream().collect(Collectors.toList());
    }

    @Override
    public List<T> findAllByStatus(ComplaintOfferStatus status) {
        Query query = new Query(Criteria.where("status").is(status));
        return mongoTemplate.find(query, clazz).stream().collect(Collectors.toList());
    }

    @Override
    public long countFilter(ComplaintFilter filter) {
        Query query = buildQuery(filter);
        return mongoTemplate.count(query, clazz);
    }

    @Override
    public List<T> findFilter(ComplaintFilter filter, Pageable pageable) {
        Query query = buildQuery(filter);
        query.with(pageable);
        return (List) mongoTemplate.find(query, clazz);
    }

    private Query buildQuery(ComplaintFilter filter) {
        Query query = new Query();

        if (!filter.getStatuses().isEmpty()) {
            query.addCriteria(Criteria.where("status").in(filter.getStatuses()));
        }

        if (!filter.getTypes().isEmpty()) {
            query.addCriteria(Criteria.where("types").in(filter.getTypes()));
        }

        if (!StringUtils.isEmpty(filter.getOfferId())) {
            query.addCriteria(Criteria.where("offerId").is(filter.getOfferId()));
        }

        if (!StringUtils.isEmpty(filter.getUsername())) {
            String username = filter.getUsername().trim();
            Criteria first = Criteria.where("initiator.firstName").regex(username, "i");
            Criteria last = Criteria.where("initiator.lastName").regex(username, "i");
            Criteria criteria = Criteria.where("").orOperator(first, last);
            query.addCriteria(criteria);
        }

        if (!StringUtils.isEmpty(filter.getAuthorId())) {
            query.addCriteria(Criteria.where("initiator.id").is(filter.getAuthorId()));
        }

        return query;
    }

}
