package ua.com.gup.common.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.filter.ManagerActionFilter;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.common.repository.ManagerActionRepository;

import java.util.Collection;
import java.util.List;

@Repository
public class ManagerActionRepositoryImpl implements ManagerActionRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommonProfileRepository profileRepository;

    @Override
    public ManagerAction save(ManagerAction action) {
        mongoTemplate.save(action);
        return action;
    }

    @Override
    public void delete(ManagerAction action) {
        mongoTemplate.remove(action);
    }

    @Override
    public ManagerAction findById(String id) {
        return mongoTemplate.findById(id, ManagerAction.class);
    }

    @Override
    public List<ManagerAction> findAllByManagerPublicId(String managerPublicId) {
        String publicId = profileRepository.getIdByPulblicId(managerPublicId);
        Criteria criteria = Criteria.where("managerPublicId").is(publicId);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, ManagerAction.class);
    }

    @Override
    public List<ManagerAction> findFiltered(ManagerActionFilter filter, Pageable pageable) {
        Query query = buildQuery(filter);
        query.with(pageable);
        return mongoTemplate.find(query, ManagerAction.class);
    }

    public long countFiltered(ManagerActionFilter filter) {
        Query query = buildQuery(filter);
        return mongoTemplate.count(query, ManagerAction.class);
    }

    @Override
    public boolean exists(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, ManagerAction.class);
    }

    private Query buildQuery(ManagerActionFilter filter) {
        Query query = new Query();

        if (filter.getDateStart() != null || filter.getDateEnd() != null) {
            Criteria date = Criteria.where("date");
            if(filter.getDateStart() != null){
                date = date.gte(filter.getDateStart());
            }
            if(filter.getDateEnd() != null){
                date = date.lte(filter.getDateEnd());
            }
            query.addCriteria(date);
        }

        if (filter.getTimeStart() != null || filter.getTimeEnd() != null) {
            Criteria date = Criteria.where("time");
            if(filter.getTimeStart() != null){
                date = date.gte(filter.getTimeStart());
            }
            if(filter.getTimeEnd() != null){
                date = date.lte(filter.getTimeEnd());
            }
            query.addCriteria(date);
        }

        if (filter.getCallStatus() != null) {
            query.addCriteria(Criteria.where("callStatus").is(filter.getCallStatus()));
        }
        if (!StringUtils.isEmpty(filter.getClientId())) {
            query.addCriteria(Criteria.where("clientId").is(filter.getClientId()));
        }
        if (!StringUtils.isEmpty(filter.getPhone())) {
            query.addCriteria(Criteria.where("phone").is(filter.getPhone()));
        }
        if (!StringUtils.isEmpty(filter.getEmail())) {
            query.addCriteria(Criteria.where("callStatus").is(filter.getEmail()));
        }
        if (!StringUtils.isEmpty(filter.getManagerPublicId())) {
            query.addCriteria(Criteria.where("managerPublicId").is(filter.getManagerPublicId()));
        }
        if (!StringUtils.isEmpty(filter.getUsername())) {
            String username = filter.getUsername().trim();
            Criteria first = Criteria.where("firstname").regex(username, "i");
            Criteria last = Criteria.where("lastname").regex(username, "i");
            Criteria criteria = Criteria.where("").orOperator(first, last);
            query.addCriteria(criteria);
        }

        return query;
    }
}
