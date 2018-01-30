package ua.com.gup.rent.repository.profile.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.rent.model.mongo.user.RentOfferManagerProfile;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.mongo.user.RentOfferUserProfile;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;
import ua.com.gup.rent.repository.profile.ProfileRepositoryFilter;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RentOfferProfileRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferProfile, String> implements RentOfferProfileRepository {

    public RentOfferProfileRepositoryImpl() {
        super(RentOfferProfile.class);
    }


    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferProfile.class)) {
            mongoTemplate.createCollection(RentOfferProfile.class);
        }
    }


    @Override
    public void updateProfile(RentOfferProfile profile) {
        mongoTemplate.save(profile);
    }

    @Override
    public RentOfferProfile findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public <T extends RentOfferProfile> T findById(String id, Class<T> entityClass) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public RentOfferProfile findByPublicId(String id) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }


    @Override
    public boolean existsByRole(String role) {
        Query query = new Query(Criteria.where("userRoles").in(role));
        return mongoTemplate.exists(query,  RentOfferProfile.class);
    }

    @Override
    public <T extends RentOfferProfile> T findByPublicId(String id, Class<T> entityClass) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.findOne(query, entityClass);
    }


    public void save(RentOfferProfile profile) {
        mongoTemplate.save(profile);
    }


    @Override
    public boolean hasManager(String profilePublicId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(profilePublicId));
        query.addCriteria(Criteria.where("rentManager").exists(true));
        return mongoTemplate.exists(query, RentOfferUserProfile.class);
    }

    public List<RentOfferProfile> findLikeUsername(String username){
        Query query = new Query();
        String searchFieldRegex = "(?i:.*" + username.trim() + ".*)";
        query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        return mongoTemplate.find(query, RentOfferProfile.class);
    }

    @Override
    public List<RentOfferUserProfile> findUsersByManager(String managerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("rentManager").is(managerId));
        return mongoTemplate.find(query, RentOfferUserProfile.class);
    }

    @Override
    public RentOfferUserProfile getManagerUser(String managerPublicId, String publicId) {
        String managerId = getIdByPulblicId(managerPublicId);
        Query query = new Query();
        query.addCriteria(Criteria.where("rentManager").is(managerId));
        query.addCriteria(Criteria.where("publicId").is(publicId));
        return mongoTemplate.findOne(query, RentOfferUserProfile.class);
    }

    @Override
    public Set<String> getManagerUserIds(String managerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(managerId));
        query.fields().include("users");
        RentOfferManagerProfile manager = mongoTemplate.findOne(query, RentOfferManagerProfile .class);
        if(manager == null){
            return Collections.EMPTY_SET;
        }
        return manager.getUsers();

    }

    @Override
    public String getPulblicIdById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.fields().include("publicId");
        RentOfferProfile profile = mongoTemplate.findOne(query, RentOfferProfile.class);
        if (profile != null) {
            return profile.getPublicId();
        }
        return null;
    }

    @Override
    public String getIdByPulblicId(String publicId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(publicId));
        query.fields().include("_id");
        RentOfferProfile profile = mongoTemplate.findOne(query, RentOfferProfile.class);
        if (profile != null) {
            return profile.getId();
        }
        return null;
    }

    @Override
    public Set<String> getPulblicIdsByIds(Set<String> usersPublicId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(usersPublicId));
        query.fields().include("publicId");
        List<RentOfferProfile> profiles = mongoTemplate.find(query, RentOfferProfile.class);
        if(profiles != null){
            return profiles.stream().map(RentOfferProfile::getPublicId).collect(Collectors.toSet());
        }
        return Collections.EMPTY_SET;
    }

    @Override
    public List<RentOfferProfile> findByRole(String role, Pageable pageable) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userRoles").in(role));
        query.fields().exclude("password");
        query.with(pageable);
        return mongoTemplate.find(query, RentOfferProfile.class);
    }

    @Override
    public long countByRole(String role) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userRoles").in(role));
        return mongoTemplate.count(query, RentOfferProfile.class);
    }

//    @Override
//    public List<RentOfferProfile> findByFilter(ProfileRepositoryFilter filter, Pageable pageable) {
//        Query query = buildQueryByFilter(filter);
//        query.with(pageable);
//        return mongoTemplate.find(query, RentOfferProfile.class);
//    }

    @Override
    public <T extends RentOfferProfile> List<T> findByFilter(ProfileRepositoryFilter filter, Pageable pageable, Class<T> entityClass) {
        Query query = buildQueryByFilter(filter);
        query.with(pageable);
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public boolean hasRole(String profilePublicId, String roleUser) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(profilePublicId));
        query.addCriteria(Criteria.where("userRoles").in(roleUser));
        return mongoTemplate.exists(query, RentOfferProfile.class);
    }

    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        Query query = new Query(Criteria.where("publicId").is(profilePublicId));
        return mongoTemplate.exists(query, RentOfferProfile.class);
    }

    @Override
    public long countByFilter(ProfileRepositoryFilter filter) {
        Query query = buildQueryByFilter(filter);
        return mongoTemplate.count(query, RentOfferProfile.class);
    }

    private Query buildQueryByFilter(ProfileRepositoryFilter filter) {
        Query query = new Query();
        if (filter.getRole() != null) {
            query.addCriteria(Criteria.where("userRoles").is(new String[]{filter.getRole()}));
        }
        if (!StringUtils.isEmpty(filter.getUsername())) {
            String searchFieldRegex = "(?i:.*" + filter.getUsername().trim() + ".*)";
            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        }

        if (!StringUtils.isEmpty(filter.getEmail())) {
            query.addCriteria(Criteria.where("email").is(filter.getEmail().trim()));
        }

        if (!StringUtils.isEmpty(filter.getPublicId())) {
            query.addCriteria(Criteria.where("publicId").is(filter.getPublicId().trim()));
        }

        if (filter.getUserRoles() != null) {
            query.addCriteria(Criteria.where("userRoles").in(filter.getUserRoles()));
        }

        if (!StringUtils.isEmpty(filter.getMainPhone())) {
            query.addCriteria(Criteria.where("mainPhone.phoneNumber").is(filter.getMainPhone()));
        }

        if (!StringUtils.isEmpty(filter.getAdditionalPhone())) {
            query.addCriteria(Criteria.where("contact.contactPhones.phoneNumber").in(filter.getAdditionalPhone()));
        }

        if (!StringUtils.isEmpty(filter.getManagerPublicId())) {
            String managerId = getIdByPulblicId(filter.getManagerPublicId());
            if (managerId != null) {
                query.addCriteria(Criteria.where("rentManager").is(managerId));
            }
        }

        if (!StringUtils.isEmpty(filter.getManagerUsername())) {
            List<RentOfferProfile> managers = findLikeUsername(filter.getManagerUsername());
            if (managers!=null && !managers.isEmpty()) {
                List<String> managerIds = managers.stream().map(profile -> profile.getId()).collect(Collectors.toList());
                query.addCriteria(Criteria.where("rentManager").in(managerIds));
            }
        }

        return query;
    }
}
