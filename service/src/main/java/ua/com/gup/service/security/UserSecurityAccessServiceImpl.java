package ua.com.gup.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityAccessServiceImpl implements UserSecurityAccessService{

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public boolean isOwner(String ownerId, String objectId, String entity, String propertyName){    
        
        Criteria criteria = Criteria.where("_id").is(objectId);        
        criteria = criteria.andOperator(Criteria.where(propertyName).is(ownerId));
        return mongoTemplate.exists(new Query(criteria), entity);
    }       
  
}
