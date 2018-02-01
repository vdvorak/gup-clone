package ua.com.gup.rent.repository.profile.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ua.com.gup.common.repository.filter.ProfileRepositoryFilter;
import ua.com.gup.rent.model.mongo.user.Profile;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RentOfferProfileRepositoryImpl extends RentOfferGenericRepositoryImpl<Profile, String> implements RentOfferProfileRepository {

    public RentOfferProfileRepositoryImpl() {
        super(Profile.class);
    }


    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Profile.class)) {
            mongoTemplate.createCollection(Profile.class);
        }
    }

    @Override
    public void updateProfile(Profile profile) {
        mongoTemplate.save(profile);
    }

    @Override
    public Profile findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public Profile findByPublicId(String id) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public boolean existsByRole(String role) {
        Query query = new Query(Criteria.where("userRoles").in(role));
        return mongoTemplate.exists(query,  Profile.class);
    }

    public void save(Profile profile) {
        mongoTemplate.save(profile);
    }


    @Override
    public boolean hasManager(String profilePublicId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(profilePublicId));
        query.addCriteria(Criteria.where("rentManagerClientInfo.manager").exists(true));
        return mongoTemplate.exists(query, Profile.class);
    }

    public List<Profile> findLikeUsername(String username){
        Query query = new Query();
        String searchFieldRegex = "(?i:.*" + username.trim() + ".*)";
        query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public String getIdByPulblicId(String publicId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(publicId));
        query.fields().include("_id");
        Profile profile = mongoTemplate.findOne(query, Profile.class);
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
        List<Profile> profiles = mongoTemplate.find(query, Profile.class);
        if(profiles != null){
            return profiles.stream().map(Profile::getPublicId).collect(Collectors.toSet());
        }
        return Collections.EMPTY_SET;
    }

    @Override
    public List<Profile> findByRole(String role, Pageable pageable) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userRoles").in(role));
        query.fields().exclude("password");
        query.with(pageable);
        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public long countByRole(String role) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userRoles").in(role));
        return mongoTemplate.count(query, Profile.class);
    }

    @Override
    public List<Profile> findByFilter(ProfileRepositoryFilter filter, Pageable pageable) {
        Query query = buildQueryByFilter(filter);
        query.with(pageable);
        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public boolean hasRole(String profilePublicId, String roleUser) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(profilePublicId));
        query.addCriteria(Criteria.where("userRoles").in(roleUser));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        Query query = new Query(Criteria.where("publicId").is(profilePublicId));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public long countByFilter(ProfileRepositoryFilter filter) {
        Query query = buildQueryByFilter(filter);
        return mongoTemplate.count(query, Profile.class);
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
                query.addCriteria(Criteria.where("rentManagerClientInfo.manager").is(managerId));
            }
        }

        if (!StringUtils.isEmpty(filter.getManagerUsername())) {
            List<Profile> managers = findLikeUsername(filter.getManagerUsername());
            if (managers!=null && !managers.isEmpty()) {
                List<String> managerIds = managers.stream().map(profile -> profile.getId()).collect(Collectors.toList());
                query.addCriteria(Criteria.where("rentManagerClientInfo.manager").in(managerIds));
            }
        }

        return query;
    }
}
