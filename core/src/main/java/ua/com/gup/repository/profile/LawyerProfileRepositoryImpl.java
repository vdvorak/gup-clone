//package ua.com.gup.repository.dao.profile;
//
//import com.mongodb.WriteResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Repository;
//import ua.com.gup.model.profiles.LawyerProfile;
//import ua.com.gup.model.profiles.ProfileFilterOptions;
//import ua.com.gup.model.profiles.ProfileRating;
//import ua.com.gup.model.profiles.UserRole;
//import ua.com.gup.config.mongo.MongoTemplateOperations;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//
//@Repository
//public class LawyerProfileRepositoryImpl implements LawyerProfileRepository {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @PostConstruct
//    void init() {
//        if (!mongoTemplate.collectionExists(LawyerProfile.class)) {
//            mongoTemplate.createCollection(LawyerProfile.class);
//        }
//    }
//
//    @Override
//    public LawyerProfile findById(String id) {
//        Query query = new Query(Criteria.where("id").is(id));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile findByPublicId(String id) {
//        Query query = new Query(Criteria.where("publicId").is(id));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile findBySeoWord(String seoWord) {
//        Query query = new Query(Criteria.where("idSeoWord").is(seoWord));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public void createProfile(LawyerProfileRepository lawyerProfile) {
//        //TODO:
//    }
//
//    @Override
//    public LawyerProfile findProfileAndUpdate(LawyerProfile lawyerProfile) {
//        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(lawyerProfile);
//    }
//
//    @Override
//    public int deleteProfileById(String id) {
//        Query query = new Query(Criteria.where("_id").is(id));
//        WriteResult result = mongoTemplate.remove(query, LawyerProfile.class);
//        return result.getN();
//    }
//
//    @Override
//    public boolean profileExists(String id) {
//        Query query = new Query(Criteria.where("_id").is(id));
//        return mongoTemplate.exists(query, LawyerProfile.class);
//    }
//
//    @Override
//    public boolean profilePublicExists(String id) {
//        Query query = new Query(Criteria.where("publicId").is(id));
//        return mongoTemplate.exists(query, LawyerProfile.class);
//    }
//
//    @Override
//    public boolean profileExistsWithEmail(String email) {
//        Query query = new Query(Criteria.where("email").is(email));
//        return mongoTemplate.exists(query, LawyerProfile.class);
//    }
//
//    @Override
//    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
//        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhoneNumber));
//        return mongoTemplate.exists(query, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile findProfileByUidAndWendor(String uid, String socWendor) {
//        Query query = new Query()
//                .addCriteria(Criteria.where("uid").is(uid))
//                .addCriteria(Criteria.where("socWendor").is(socWendor));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile findProfileByPhoneNumberAndWendor(String mainPhoneNumber, String socWendor) {
//        Query query = new Query()
//                .addCriteria(Criteria.where("mainPhoneNumber").is(mainPhoneNumber))
//                .addCriteria(Criteria.where("socWendor").is(socWendor));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public List<LawyerProfile> findAllProfiles(ProfileFilterOptions profileFilterOptions) {
//        Query query = new Query();
//        if (profileFilterOptions.getSearchField() != null) {
//            String searchFieldRegex = "(?i:.*" + profileFilterOptions.getSearchField() + ".*)";
//            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
//        }
//
//        if (profileFilterOptions.getContact() != null && profileFilterOptions.getContact().getType() != null) {
//            query.addCriteria(Criteria.where("contact.type").is(profileFilterOptions.getContact().getType()));
//        }
//
//        query.fields().exclude("email");
//        query.fields().exclude("password");
//        query.fields().exclude("mainPhoneNumber");
//
//        query.skip(profileFilterOptions.getSkip());
//        query.limit(profileFilterOptions.getLimit());
//        return mongoTemplate.find(query, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile incMainPhoneViewsAtOne(String profileId) {
//        mongoTemplate.updateFirst(
//                Query.query(Criteria.where("id").is(profileId)),
//                new Update().inc("mainPhoneNumberViews", 1),
//                LawyerProfile.class);
//
//        Query query = new Query(Criteria.where("id").is(profileId));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public List<LawyerProfile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions) {
//        Query query = new Query();
//        if (profileFilterOptions.getSearchField() != null) {
//            String searchFieldRegex = "(?i:.*" + profileFilterOptions.getSearchField() + ".*)";
//            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
//        }
//
//        if (profileFilterOptions.getUserRoles() != null) {
//            query.addCriteria(Criteria.where("userRoles").in(profileFilterOptions.getUserRoles()));
//        }
//
//        if (profileFilterOptions.getContact() != null && profileFilterOptions.getContact().getType() != null) {
//            query.addCriteria(Criteria.where("contact.type").is(profileFilterOptions.getContact().getType()));
//        }
//
//        query.fields().exclude("password");
//
//        query.skip(profileFilterOptions.getSkip());
//        query.limit(profileFilterOptions.getLimit());
//        return mongoTemplate.find(query, LawyerProfile.class);
//    }
//
//
//    @Override
//    public Set<String> getMatchedNames(String term) {
//        String searchFieldRegex = "(?i:.*" + term + ".*)";
//
//        Query query = new Query()
//                .addCriteria(Criteria.where("username").regex(searchFieldRegex));
//
//        query.fields().include("username");
//        query.skip(0);
//        query.limit(10);
//        return null; //TODO:
//    }
//
//
//    public List<LawyerProfile> getMatchedNamesToFindWithId(String term) {
//        String searchFieldRegex = "(?i:.*" + term + ".*)";
//
//        Query query = new Query()
//                .addCriteria(Criteria.where("username").regex(searchFieldRegex));
//
//        query.fields().include("username");
//        query.skip(0);
//        query.limit(10);
//
//        return mongoTemplate.find(query, LawyerProfile.class);
//    }
//
//
//    public List<LawyerProfile> getMatchedCompanies(String term) {
//        String searchFieldRegex = "(?i:.*" + term + ".*)";
//
//        Query query = new Query()
//                .addCriteria(Criteria.where("contact.companyName").regex(searchFieldRegex));
//
//        query.fields().include("contact.companyName");
//        query.skip(0);
//        query.limit(10);
//
//        return mongoTemplate.find(query, LawyerProfile.class);
//    }
//
//
//    @Override
//    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
//        Query addContactQuery = new Query(Criteria.where("id").is(profileOwnerContactListId));
//
//        Query existsContactInListQuery = new Query()
//                .addCriteria(Criteria.where("id").is(profileOwnerContactListId))
//                .addCriteria(Criteria.where("contactList").in(contactId));
//
//        Update update = new Update();
//        if (mongoTemplate.exists(existsContactInListQuery, LawyerProfile.class)) {
//            update.pull("contactList", contactId);
//        } else {
//            update.push("contactList", contactId);
//        }
//
//        mongoTemplate.updateFirst(addContactQuery, update, LawyerProfile.class);
//    }
//
//    @Override
//    public void addSocialToSocialList(String userId, String profileId) {
//        Query addContactQuery = new Query(Criteria.where("id").is(userId));
//        Query existsSocialInListQuery = new Query()
//                .addCriteria(Criteria.where("id").is(userId))
//                .addCriteria(Criteria.where("socialList").in(profileId));
//
//        Update update = new Update();
//        if (mongoTemplate.exists(existsSocialInListQuery, LawyerProfile.class)) {
//            update.pull("socialList", profileId);
//        } else {
//            update.push("socialList", profileId);
//        }
//
//        mongoTemplate.updateFirst(addContactQuery, update, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile findByUsername(String username) {
//        Query query = new Query(Criteria.where("username").is(username));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile findByEmail(String email) {
//
//        Query query = new Query( Criteria.where("email").regex(email.toString(), "i"));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public LawyerProfile findProfileByMainPhone(String mainPhone) {
//        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhone));
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public boolean profileRatingExists(String profileId, String profileRatingId) {
//        Query query = new Query()
//                .addCriteria(Criteria.where("id").is(profileId))
//                .addCriteria(Criteria.where("profileRating.profileRatingId").is(profileRatingId));
//        return mongoTemplate.exists(query, LawyerProfile.class);
//    }
//
//    @Override
//    public void createProfileRating(String profileId, ProfileRating profileRating) {
//        mongoTemplate.updateFirst(
//                Query.query(Criteria.where("id").is(profileId)),
//                new Update().push("profileRating", profileRating).inc("point", profileRating.getEarnPoints()), LawyerProfile.class);
//    }
//
//    @Override
//    public int deleteProfileRating(String profileId, String profileRatingId) {
//        WriteResult result = mongoTemplate.updateFirst(
//                Query.query(Criteria.where("id").is(profileId)),
//                new Update().pull("profileRating", Query.query(Criteria.where("profileRatingId").is(profileRatingId))), LawyerProfile.class);
//        return result.getN();
//    }
//
//    @Override
//    public LawyerProfile findProfileRating(String profileId, String profileRatingId) {
//        Query query = new Query()
//                .addCriteria(Criteria.where("id").is(profileId))
//                .addCriteria(Criteria.where("profileRating.profileRatingId").is(profileRatingId));
//        query.fields().slice("profileRating", 1);
//        return mongoTemplate.findOne(query, LawyerProfile.class);
//    }
//
//    @Override
//    public void addUserRole(String profileId, UserRole userRole) {
//        mongoTemplate.updateFirst(
//                Query.query(Criteria.where("id").is(profileId)),
//                new Update().push("userRoles", userRole), LawyerProfile.class);
//    }
//
//    @Override
//    public void deleteUserRole(String profileId, UserRole userRole) {
//        mongoTemplate.updateFirst(
//                Query.query(Criteria.where("id").is(profileId)),
//                new Update().pull("userRoles", userRole), LawyerProfile.class);
//    }
//
//}
