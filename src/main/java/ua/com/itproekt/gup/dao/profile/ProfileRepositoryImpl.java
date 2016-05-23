package ua.com.itproekt.gup.dao.profile;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of ProfileRepository.
 */
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
    public boolean profileExistsWithEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, Profile.class);
    }

    @Override
    public EntityPage<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions) {
        Query query = new Query();
        if (profileFilterOptions.getSearchField() != null) {
            String searchFieldRegex = "(?i:.*" + profileFilterOptions.getSearchField() + ".*)";
            query.addCriteria(Criteria.where("username").regex(searchFieldRegex));
        }

        if (profileFilterOptions.getUserRoles() != null) {
            query.addCriteria(Criteria.where("userRoles").all(profileFilterOptions.getUserRoles()));
        }

        if (profileFilterOptions.getContact() != null && profileFilterOptions.getContact().getType() != null) {
            query.addCriteria(Criteria.where("contact.type").is(profileFilterOptions.getContact().getType()));
        }

        query.fields().exclude("email");
        query.fields().exclude("password");
        query.fields().exclude("mainPhoneNumber");

        query.skip(profileFilterOptions.getSkip());
        query.limit(profileFilterOptions.getLimit());
        return new EntityPage<>(mongoTemplate.count(query, Profile.class),
                mongoTemplate.find(query, Profile.class));
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
    public Profile findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public Profile findByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
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
    public void addFriend(String profileId, String friendProfileId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(profileId)),
                new Update().push("friendList", friendProfileId), Profile.class);
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
}
