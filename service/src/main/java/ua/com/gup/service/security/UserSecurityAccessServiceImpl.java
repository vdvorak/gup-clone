package ua.com.gup.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ua.com.gup.mongo.composition.domain.profile.ManagerProfile;
import ua.com.gup.repository.offer.OfferRepository;
import ua.com.gup.repository.profile.ProfileRepository;

import java.util.Set;

@Service
public class UserSecurityAccessServiceImpl implements UserSecurityAccessService{

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public boolean isOwner(String ownerId, String objectId, String entity, String propertyName){    
        
        Criteria criteria = Criteria.where("_id").is(objectId);        
        criteria = criteria.andOperator(Criteria.where(propertyName).is(ownerId));
        return mongoTemplate.exists(new Query(criteria), entity);
    }

    @Override
    public Boolean isManagerOffer(String managerId, String offerId) {
        Set<String> userIds = profileRepository.getManagerUserIds(managerId);
        return offerRepository.existsByIdAndAuthorId(offerId, userIds);
    }

}
