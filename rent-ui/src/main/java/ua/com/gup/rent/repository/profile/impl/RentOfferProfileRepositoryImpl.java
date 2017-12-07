package ua.com.gup.rent.repository.profile.impl;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.config.RentOfferMongoTemplateOperations;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.profiles.RentOfferProfileFilterOptions;
import ua.com.gup.rent.model.rent.profiles.RentOfferProfileRating;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;

import javax.annotation.PostConstruct;
import java.util.*;
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
    public RentOfferProfile loadByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public void createProfile(RentOfferProfile profile) {
        mongoTemplate.insert(profile);
    }

    @Override
    public RentOfferProfile findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findByPublicId(String id) {
        Query query = new Query(Criteria.where("publicId").is(id));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findBySeoWord(String seoWord) {
        Query query = new Query(Criteria.where("idSeoWord").is(seoWord));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findProfileAndUpdate(RentOfferProfile profile) {
        return RentOfferMongoTemplateOperations.updateFieldsAndReturnUpdatedObj(profile);
    }

    @Override
    public int deleteProfileById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        WriteResult result = mongoTemplate.remove(query, RentOfferProfile.class);
        return result.getN();
    }

    @Override
    public boolean profileExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, RentOfferProfile.class);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        Query queryX = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(queryX, RentOfferProfile.class);
    }

    @Override
    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhoneNumber));
        return mongoTemplate.exists(query, RentOfferProfile.class);
    }

    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        Query query = new Query()
                .addCriteria(Criteria.where("uid").is(uid))
                .addCriteria(Criteria.where("socWendor").is(socWendor));
        return mongoTemplate.exists(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findProfileByUidAndWendor(String uid, String socWendor) {
        Query query = new Query()
                .addCriteria(Criteria.where("uid").is(uid))
                .addCriteria(Criteria.where("socWendor").is(socWendor));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findProfileByPhoneNumberAndWendor(String mainPhoneNumber, String socWendor) {
        Query query = new Query()
                .addCriteria(Criteria.where("mainPhoneNumber").is(mainPhoneNumber))
                .addCriteria(Criteria.where("socWendor").is(socWendor));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public List<RentOfferProfile> findAllProfiles(RentOfferProfileFilterOptions profileFilterOptions) {
        Query query = new Query();
        if (profileFilterOptions.getSearchField() != null) {
            String searchFieldRegex = "(?i:.*" + profileFilterOptions.getSearchField() + ".*)";
            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        }

       /* if (profileFilterOptions.getContact() != null && profileFilterOptions.getUserType() != null) {
            query.addCriteria(Criteria.where("contact.type").is(profileFilterOptions.getUserType()));
        }*/

        query.fields().exclude("email");
        query.fields().exclude("password");
        query.fields().exclude("mainPhoneNumber");

        query.skip(profileFilterOptions.getSkip());
        query.limit(profileFilterOptions.getLimit());
        return mongoTemplate.find(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile incMainPhoneViewsAtOne(String profileId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().inc("mainPhoneNumberViews", 1),
                RentOfferProfile.class);

        Query query = new Query(Criteria.where("id").is(profileId));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public List<RentOfferProfile> findAllProfilesForAdmin(RentOfferProfileFilterOptions profileFilterOptions) {
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

       /* if (profileFilterOptions.getContact() != null && profileFilterOptions.getUserType() != null) {
            query.addCriteria(Criteria.where("contact.type").is(profileFilterOptions.getUserType()));
        }*/

        query.fields().exclude("password");

        query.skip(profileFilterOptions.getSkip());
        query.limit(profileFilterOptions.getLimit());
        return mongoTemplate.find(query, RentOfferProfile.class);
    }


    @Override
    public Set<String> getMatchedNames(String term) {
        String searchFieldRegex = "(?i:.*" + term + ".*)";

        Query query = new Query()
                .addCriteria(Criteria.where("username").regex(searchFieldRegex));

        query.fields().include("username");
        query.skip(0);
        query.limit(10);
        return mongoTemplate.find(query, RentOfferProfile.class).stream().map(RentOfferProfile::getUsername).collect(Collectors.toSet());
    }


    @Override
    public Set<String> getAdminIdAll() {
        Query query = new Query(Criteria.where("userRoles").is("ROLE_ADMIN"));

        return mongoTemplate.find(query, RentOfferProfile.class).stream().map(RentOfferProfile::getId).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAdminIdAllByOnline() {
        Query query = new Query()
                .addCriteria(Criteria.where("userRoles").is("ROLE_ADMIN"))
                .addCriteria(Criteria.where("online").is(true));

        return mongoTemplate.find(query, RentOfferProfile.class).stream().map(RentOfferProfile::getId).collect(Collectors.toSet());
    }

    @Override
    public String getAdminId() {
        Query query = new Query(Criteria.where("userRoles").is("ROLE_ADMIN"));
        Set<String> IdAdminAll = mongoTemplate.find(query, RentOfferProfile.class).stream().map(RentOfferProfile::getId).collect(Collectors.toSet());

        return getRandomObj(IdAdminAll);
    }

    @Override
    public String getAdminIdByOnline() {
        Query query = new Query()
                .addCriteria(Criteria.where("userRoles").is("ROLE_ADMIN"))
                .addCriteria(Criteria.where("online").is(true));

        Set<String> IdAdminAll = mongoTemplate.find(query, RentOfferProfile.class).stream().map(RentOfferProfile::getId).collect(Collectors.toSet());

        return getRandomObj(IdAdminAll);
    }

    private String getRandomObj(Collection from) {
        Random rnd = new Random();
        int i = rnd.nextInt(from.size());
        return (String) from.toArray()[i];
    }


    public List<RentOfferProfile> getMatchedNamesToFindWithId(String term) {
        String searchFieldRegex = "(?i:.*" + term + ".*)";

        Query query = new Query()
                .addCriteria(Criteria.where("username").regex(searchFieldRegex));

        query.fields().include("username");
        query.skip(0);
        query.limit(10);

        return mongoTemplate.find(query, RentOfferProfile.class);
    }


    public List<RentOfferProfile> getMatchedCompanies(String term) {
        String searchFieldRegex = "(?i:.*" + term + ".*)";

        Query query = new Query()
                .addCriteria(Criteria.where("contact.companyName").regex(searchFieldRegex));

        query.fields().include("contact.companyName");
        query.skip(0);
        query.limit(10);

        return mongoTemplate.find(query, RentOfferProfile.class);
    }

    @Override
    public boolean profileExistsInUserSocialList(String userId, String profileId) {
        Query existsSocialInListQuery = new Query()
                .addCriteria(Criteria.where("id").is(userId))
                .addCriteria(Criteria.where("socialList").in(profileId));
        return mongoTemplate.exists(existsSocialInListQuery, RentOfferProfile.class);
    }

    @Override
    public void addProfileToUserSocialList(String userId, String profileId) {
        Query query = new Query(Criteria.where("id").is(userId));
        Update update = new Update();
        update.push("socialList", profileId);
        mongoTemplate.updateFirst(query, update, RentOfferProfile.class);
    }

    @Override
    public void deleteProfileFromUserSocialList(String userId, String profileId) {
        Query query = new Query(Criteria.where("id").is(userId));
        Update update = new Update();
        update.pull("socialList", profileId);
        mongoTemplate.updateFirst(query, update, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public RentOfferProfile findByEmail(String email) {
////        Query query = new Query(Criteria.where("email").is(email));
//        Query query = new Query( Criteria.where("email").regex(email.toString(), "i")); //TODO // db.getCollection('users').find({ email: { $regex: "ololosh@mail.ru", $options: '-i' }})
//        return mongoTemplate.findOne(query, Profile.class);
        ////////////////////////////////////////////////////////////////
        Query queryX = new Query(Criteria.where("email").regex(email.toString(), "i"));
        List<RentOfferProfile> profiles = mongoTemplate.find(queryX, RentOfferProfile.class);
        Optional<RentOfferProfile> profile = profiles.stream()
                .filter(p -> p.getEmail().toUpperCase().equals(email.toUpperCase()))
                .findFirst();
        try {
            return profile.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RentOfferProfile findProfileByMainPhone(String mainPhone) {
        Query query = new Query(Criteria.where("mainPhoneNumber").is(mainPhone));
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }

    @Override
    public boolean profileRatingExists(String profileId, String profileRatingId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(profileId))
                .addCriteria(Criteria.where("profileRating.profileRatingId").is(profileRatingId));
        return mongoTemplate.exists(query, RentOfferProfile.class);
    }

    @Override
    public void createProfileRating(String profileId, RentOfferProfileRating profileRating) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().push("profileRating", profileRating).inc("point", profileRating.getEarnPoints()), RentOfferProfile.class);
    }

    @Override
    public int deleteProfileRating(String profileId, String profileRatingId) {
        WriteResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().pull("profileRating", Query.query(Criteria.where("profileRatingId").is(profileRatingId))), RentOfferProfile.class);
        return result.getN();
    }

    @Override
    public RentOfferProfile findProfileRating(String profileId, String profileRatingId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(profileId))
                .addCriteria(Criteria.where("profileRating.profileRatingId").is(profileRatingId));
        query.fields().slice("profileRating", 1);
        return mongoTemplate.findOne(query, RentOfferProfile.class);
    }


    @Override
    public void incrementProfileStatistic(String profileId, String field) {
        Update update = new Update();
        update.inc("profileStatistic." + field, 1);
        mongoTemplate.findAndModify(new Query(Criteria.where("id").is(profileId)), update, RentOfferProfile.class);
    }

    @Override
    public void decrementProfileStatistic(String profileId, String field) {
        Update update = new Update();
        update.inc("profileStatistic." + field, -1);
        mongoTemplate.findAndModify(new Query(Criteria.where("id").is(profileId)), update, RentOfferProfile.class);
    }

    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        Query query = new Query(Criteria.where("publicId").is(profilePublicId));
        return mongoTemplate.exists(query, RentOfferProfile.class);
    }
    public void  save(RentOfferProfile profile){
        mongoTemplate.save(profile);
    }
}
