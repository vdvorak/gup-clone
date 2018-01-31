package ua.com.gup.repository.profile;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ua.com.gup.common.repository.filter.ProfileRepositoryFilter;
import ua.com.gup.config.mongo.MongoTemplateOperations;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.profiles.ProfileRating;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class ProfileRepositoryImpl implements ProfileRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Profile.class)) {
            mongoTemplate.createCollection(Profile.class);
        }
    }

    @Override
    public void createProfile(Profile profile) {
        mongoTemplate.insert(profile);
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
    public <T extends Profile> T findById(String id, Class<T> entityClass) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, entityClass);
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

    @Override
    public <T extends Profile> T findByPublicId(String id, Class<T> entityClass) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public Profile findBySeoWord(String seoWord) {
        Query query = new Query(Criteria.where("idSeoWord").is(seoWord));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    @Deprecated
    public Profile findProfileAndUpdate(Profile profile) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(profile);
    }

    @Override
    public int deleteProfileById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        WriteResult result = mongoTemplate.remove(query, Profile.class);
        return result.getN();
    }

    @Override
    public boolean profileExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        Query queryX = new Query(Criteria.where("email").regex(email, "i"));
        return mongoTemplate.exists(queryX, Profile.class);
    }

    @Override
    public boolean profileExistsWithFacebookId(String facebookId) {
        Query query = new Query(Criteria.where("facebookId").is(facebookId));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhoneNumber));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        Query query = new Query()
                .addCriteria(Criteria.where("uid").is(uid))
                .addCriteria(Criteria.where("socWendor").is(socWendor));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public Profile findProfileByUidAndWendor(String uid, String socWendor) {
        Query query = new Query()
                .addCriteria(Criteria.where("uid").is(uid))
                .addCriteria(Criteria.where("socWendor").is(socWendor));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public Profile findProfileByPhoneNumberAndWendor(String mainPhoneNumber, String socWendor) {
        Query query = new Query()
                .addCriteria(Criteria.where("mainPhoneNumber").is(mainPhoneNumber))
                .addCriteria(Criteria.where("socWendor").is(socWendor));
        return mongoTemplate.findOne(query, Profile.class);
    }


    @Override
    public Profile incMainPhoneViewsAtOne(String profileId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().inc("mainPhoneNumberViews", 1),
                Profile.class);

        Query query = new Query(Criteria.where("id").is(profileId));
        return mongoTemplate.findOne(query, Profile.class);
    }

    private Query buildQueryByFilter(ProfileFilter profileFilter) {
        Query query = new Query();
        if (!StringUtils.isEmpty(profileFilter.getUsername())) {
            String searchFieldRegex = "(?i:.*" + profileFilter.getUsername().trim() + ".*)";
            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        }

        if (!StringUtils.isEmpty(profileFilter.getEmail())) {
            query.addCriteria(Criteria.where("email").is(profileFilter.getEmail().trim()));
        }

        if (!StringUtils.isEmpty(profileFilter.getPublicId())) {
            query.addCriteria(Criteria.where("publicId").is(profileFilter.getPublicId().trim()));
        }

        if (profileFilter.getUserRoles() != null) {
            query.addCriteria(Criteria.where("userRoles").in(profileFilter.getUserRoles()));
        }

        if (!StringUtils.isEmpty(profileFilter.getMainPhone())) {
            query.addCriteria(Criteria.where("mainPhone.phoneNumber").is(profileFilter.getMainPhone()));
        }

        if (!StringUtils.isEmpty(profileFilter.getAdditionalPhone())) {
            query.addCriteria(Criteria.where("contact.contactPhones.phoneNumber").in(profileFilter.getAdditionalPhone()));
        }

        return query;
    }

    @Override
    public long countByFilter(ProfileFilter profileFilter) {
        Query query = buildQueryByFilter(profileFilter);
        return mongoTemplate.count(query, Profile.class);
    }

    @Override
    public List<Profile> findByFilterForAdmins(ProfileFilter profileFilter, Pageable pageable) {
        Query query = buildQueryByFilter(profileFilter);
        query.fields().exclude("password");
        query.with(pageable);
        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public List<Profile> findByRole(String role, Pageable pageable) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userRoles").is(new String[]{role}));
        query.fields().exclude("password");
        query.with(pageable);
        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public long countByRole(String role) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userRoles").is(new String[]{role}));
        return mongoTemplate.count(query, Profile.class);
    }

    @Override
    public Set<String> getMatchedNames(String term) {
        String searchFieldRegex = "(?i:.*" + term + ".*)";

        Query query = new Query()
                .addCriteria(Criteria.where("username").regex(searchFieldRegex));

        query.fields().include("username");
        query.skip(0);
        query.limit(10);
        return mongoTemplate.find(query, Profile.class).stream().map(Profile::getUsername).collect(Collectors.toSet());
    }


    public List<Profile> getMatchedNamesToFindWithId(String term) {
        String searchFieldRegex = "(?i:.*" + term + ".*)";

        Query query = new Query()
                .addCriteria(Criteria.where("username").regex(searchFieldRegex));

        query.fields().include("username");
        query.skip(0);
        query.limit(10);

        return mongoTemplate.find(query, Profile.class);
    }


    public List<Profile> getMatchedCompanies(String term) {
        String searchFieldRegex = "(?i:.*" + term + ".*)";

        Query query = new Query()
                .addCriteria(Criteria.where("contact.companyName").regex(searchFieldRegex));

        query.fields().include("contact.companyName");
        query.skip(0);
        query.limit(10);

        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public boolean profileExistsInUserSocialList(String userId, String profileId) {
        Query existsSocialInListQuery = new Query()
                .addCriteria(Criteria.where("id").is(userId))
                .addCriteria(Criteria.where("socialList").in(profileId));
        return mongoTemplate.exists(existsSocialInListQuery, Profile.class);
    }

    @Override
    public void addProfileToUserSocialList(String userId, String profileId) {
        Query query = new Query(Criteria.where("id").is(userId));
        Update update = new Update();
       // update.push("socialList", profileId);
        update.addToSet("socialList",profileId);
        mongoTemplate.updateFirst(query, update, Profile.class);
    }

    @Override
    public void deleteProfileFromUserSocialList(String userId, String profileId) {
        Query query = new Query(Criteria.where("id").is(userId));
        Update update = new Update();
        update.pull("socialList", profileId);
        mongoTemplate.updateFirst(query, update, Profile.class);
    }


    @Override
    public Profile findByEmail(String email) {
        Query queryX = new Query(Criteria.where("email").regex(email, "i"));
        return mongoTemplate.findOne(queryX, Profile.class);
    }

    @Override
    public Profile findProfileByMainPhone(String mainPhone) {
        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhone));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public boolean profileRatingExists(String profileId, String profileRatingId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(profileId))
                .addCriteria(Criteria.where("profileRating.profileRatingId").is(profileRatingId));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public void createProfileRating(String profileId, ProfileRating profileRating) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().push("profileRating", profileRating).inc("point", profileRating.getEarnPoints()), Profile.class);
    }

    @Override
    public int deleteProfileRating(String profileId, String profileRatingId) {
        WriteResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().pull("profileRating", Query.query(Criteria.where("profileRatingId").is(profileRatingId))), Profile.class);
        return result.getN();
    }

    @Override
    public Profile findProfileRating(String profileId, String profileRatingId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(profileId))
                .addCriteria(Criteria.where("profileRating.profileRatingId").is(profileRatingId));
        query.fields().slice("profileRating", 1);
        return mongoTemplate.findOne(query, Profile.class);
    }


    @Override
    public void incrementProfileStatistic(String profileId, String field) {
        Update update = new Update();
        update.inc("profileStatistic." + field, 1);
        mongoTemplate.findAndModify(new Query(Criteria.where("publicId").is(profileId)), update, new FindAndModifyOptions().returnNew(true), Profile.class);
    }

    @Override
    public void decrementProfileStatistic(String profileId, String field) {
        Update update = new Update();
        update.inc("profileStatistic." + field, -1);
        mongoTemplate.findAndModify(new Query(Criteria.where("publicId").is(profileId)), update, new FindAndModifyOptions().returnNew(true), Profile.class);
    }

    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        Query query = new Query(Criteria.where("publicId").is(profilePublicId));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public Profile findByFacebookId(String facebookId) {
        Query query = new Query(Criteria.where("facebookId").is(facebookId));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public boolean hasManager(String profilePublicId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(profilePublicId));
        query.addCriteria(Criteria.where("saleManagerClientInfo.manager").exists(true));
        return mongoTemplate.exists(query, Profile.class);
    }



    @Override
    public List<Profile> findUsersByManager(String managerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("saleManagerClientInfo.manager").is(managerId));
        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public Profile getManagerUser(String managerPublicId, String publicId) {
        String managerId = getIdByPulblicId(managerPublicId);
        Query query = new Query();
        query.addCriteria(Criteria.where("saleManagerClientInfo.manager").is(managerId));
        query.addCriteria(Criteria.where("publicId").is(publicId));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public Set<String> getManagerUserIds(String managerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(managerId));
        query.fields().include("users");
        Profile manager = mongoTemplate.findOne(query, Profile.class);
        if (manager == null) {
            return Collections.EMPTY_SET;
        }
        return manager.getManagerInfo().getUsers();

    }

    @Override
    public String getPulblicIdById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.fields().include("publicId");
        Profile profile = mongoTemplate.findOne(query, Profile.class);
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
        if (profiles != null) {
            return profiles.stream().map(Profile::getPublicId).collect(Collectors.toSet());
        }
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean hasRole(String profilePublicId, String role) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicId").is(profilePublicId));
        query.addCriteria(Criteria.where("userRoles").in(role));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public long countByFilter(ProfileRepositoryFilter filter) {
        Query query = buildQueryByFilter(filter);
        return mongoTemplate.count(query, Profile.class);
    }

    @Override
    public List<Profile> findByFilter(ProfileRepositoryFilter filter, Pageable pageable) {
        Query query = buildQueryByFilter(filter);
        query.with(pageable);
        return mongoTemplate.find(query, Profile.class);
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
                query.addCriteria(Criteria.where("saleManagerClientInfo.manager").is(managerId));
            }
        }

        if (!StringUtils.isEmpty(filter.getManagerUsername())) {
            List<Profile> managers = findLikeUsername(filter.getManagerUsername());
            if (managers!=null && !managers.isEmpty()) {
                List<String> managerIds = managers.stream().map(profile -> profile.getId()).collect(Collectors.toList());
                query.addCriteria(Criteria.where("saleManagerClientInfo.manager").in(managerIds));
            }
        }

        return query;
    }

    public List<Profile> findLikeUsername(String username){
        Query query = new Query();
        String searchFieldRegex = "(?i:.*" + username.trim() + ".*)";
        query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        return mongoTemplate.find(query, Profile.class);
    }

}
