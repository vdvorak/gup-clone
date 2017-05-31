package ua.com.itproekt.gup.dao.profile;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.ProfileRating;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;
import java.util.*;
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
    public Profile findBySeoWord(String seoWord) {
        Query query = new Query(Criteria.where("idSeoWord").is(seoWord));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
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
    public boolean profilePublicExists(String id) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhoneNumber));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithSocWendor(String socWendor) {
        Query query = new Query(Criteria.where("socWendor").is(socWendor));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public boolean profileExistsWithUid(String uid) {
        Query query = new Query(Criteria.where("uid").is(uid));
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
    public List<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions) {
        Query query = new Query();
        if (profileFilterOptions.getSearchField() != null) {
            String searchFieldRegex = "(?i:.*" + profileFilterOptions.getSearchField() + ".*)";
            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        }

        if (profileFilterOptions.getContact() != null && profileFilterOptions.getContact().getType() != null) {
            query.addCriteria(Criteria.where("contact.type").is(profileFilterOptions.getContact().getType()));
        }

        query.fields().exclude("email");
        query.fields().exclude("password");
        query.fields().exclude("mainPhoneNumber");

        query.skip(profileFilterOptions.getSkip());
        query.limit(profileFilterOptions.getLimit());
        return mongoTemplate.find(query, Profile.class);
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

    @Override
    public List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions) {
        Query query = new Query();
        if (profileFilterOptions.getSearchField() != null) {
            String searchFieldRegex = "(?i:.*" + profileFilterOptions.getSearchField() + ".*)";
            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        }

        if (profileFilterOptions.getId() != null) {
            query.addCriteria(Criteria.where("id").in(profileFilterOptions.getId()));
        }

        if (profileFilterOptions.getUserRoles() != null) {
            query.addCriteria(Criteria.where("userRoles").in(profileFilterOptions.getUserRoles()));
        }

        if (profileFilterOptions.getContact() != null && profileFilterOptions.getContact().getType() != null) {
            query.addCriteria(Criteria.where("contact.type").is(profileFilterOptions.getContact().getType()));
        }

        query.fields().exclude("password");

        query.skip(profileFilterOptions.getSkip());
        query.limit(profileFilterOptions.getLimit());
        return mongoTemplate.find(query, Profile.class);
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


    @Override
    public Set<String> getAdminIdAll() {
        Query query = new Query(Criteria.where("userRoles").is("ROLE_ADMIN"));

        return mongoTemplate.find(query, Profile.class).stream().map(Profile::getId).collect(Collectors.toSet());
    }

    @Override
    public String getAdminId() {
        Query            query = new Query(Criteria.where("userRoles").is("ROLE_ADMIN"));
        Set<String> IdAdminAll = mongoTemplate.find(query, Profile.class).stream().map(Profile::getId).collect(Collectors.toSet());

        return getRandomObj(IdAdminAll);
    }

    private String getRandomObj(Collection from) {
        Random rnd = new Random();
        int i = rnd.nextInt(from.size());
        return (String)from.toArray()[i];
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
    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
        Query addContactQuery = new Query(Criteria.where("id").is(profileOwnerContactListId));

        Query existsContactInListQuery = new Query()
                .addCriteria(Criteria.where("id").is(profileOwnerContactListId))
                .addCriteria(Criteria.where("contactList").in(contactId));

        Update update = new Update();
        if (mongoTemplate.exists(existsContactInListQuery, Profile.class)) {
            update.pull("contactList", contactId);
        } else {
            update.push("contactList", contactId);
        }

        mongoTemplate.updateFirst(addContactQuery, update, Profile.class);
    }

    @Override
    public void addSocialToSocialList(String userId, String profileId) {
        Query addContactQuery = new Query(Criteria.where("id").is(userId));
        Query existsSocialInListQuery = new Query()
                .addCriteria(Criteria.where("id").is(userId))
                .addCriteria(Criteria.where("socialList").in(profileId));

        Update update = new Update();
        if (mongoTemplate.exists(existsSocialInListQuery, Profile.class)) {
            update.pull("socialList", profileId);
        } else {
            update.push("socialList", profileId);
        }

        mongoTemplate.updateFirst(addContactQuery, update, Profile.class);
    }

    @Override
    public Profile findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public Profile findByEmail(String email) {
////        Query query = new Query(Criteria.where("email").is(email));
//        Query query = new Query( Criteria.where("email").regex(email.toString(), "i")); //TODO // db.getCollection('users').find({ email: { $regex: "ololosh@mail.ru", $options: '-i' }})
//        return mongoTemplate.findOne(query, Profile.class);
        ////////////////////////////////////////////////////////////////
        Query queryX = new Query( Criteria.where("email").regex(email.toString(), "i"));
        List<Profile> profiles = mongoTemplate.find(queryX, Profile.class);
        Optional<Profile> profile = profiles.stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst();
        try {
            return profile.get();
        } catch (Exception e){
            return null;
        }
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
    public void addUserRole(String profileId, UserRole userRole) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().push("userRoles", userRole), Profile.class);
    }

    @Override
    public void deleteUserRole(String profileId, UserRole userRole) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().pull("userRoles", userRole), Profile.class);
    }


    //ToDo delete this bulshit in the future
    @Override
    public void setMongoTemplateInstanceForTests(MongoTemplate mongoTemplateInstanceForTests) {
        this.mongoTemplate = mongoTemplateInstanceForTests;
        MongoTemplateOperations.setStaticMongoTemplate(mongoTemplateInstanceForTests);
    }
}
