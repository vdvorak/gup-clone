package ua.com.gup.dao.profile;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.model.profiles.ProfileContactList;
import ua.com.gup.model.profiles.ProfileFilterOptions;
import ua.com.gup.repository.dao.profile.ProfileRepository;
import ua.com.gup.repository.dao.profile.ProfileRepositoryImpl;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests using Fongo (fake mongo) library. Testing of ProfileRepository interface.
 *
 * @author Kobylyatskyy Alexander
 */
public class ProfileRepositoryTest {

    ProfileRepository profileRepository = new ProfileRepositoryImpl();
    MongoTemplate mongoTemplate;
    Mongo mongo;
    Fongo fongo = new Fongo("TestProfile");

    @Before
    public void setup() {

        // create fake mongo database
        mongo = fongo.getMongo();
        mongoTemplate = new MongoTemplate(mongo, "profileTestBase");

        fongo.dropDatabase("profileTestBase");

        profileRepository.setMongoTemplateInstanceForTests(mongoTemplate);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void findById_oneDocument_shouldFindProfileById() {

        //given
        Profile seedProfile = new Profile();
        mongoTemplate.insert(seedProfile);

        Profile profile = mongoTemplate.findAll(Profile.class, "users").get(0);
        String expectedId = profile.getId();

        //when
        Profile foundProfile = profileRepository.findById(expectedId);
        String actualId = foundProfile.getId();

        //then
        assertEquals(expectedId, actualId);
    }

    @Test
    public void findBySeoWord_shouldFindProfileBySeoWord() {

        //given
        Profile seedProfile = new Profile();
        seedProfile.setIdSeoWord("Gena");
        mongoTemplate.insert(seedProfile);

        Profile profile = mongoTemplate.findAll(Profile.class, "users").get(0);
        String expectedSeoWord = profile.getIdSeoWord();

        //when
        Profile foundProfile = profileRepository.findBySeoWord(expectedSeoWord);
        String actualSeoWord = foundProfile.getIdSeoWord();

        //then
        assertEquals(expectedSeoWord, actualSeoWord);
    }

    @Test
    public void findByEmail_shouldFindProfileByEmail() {

        //given
        Profile seedProfile = new Profile();
        seedProfile.setEmail("test@gmail.com");
        mongoTemplate.insert(seedProfile);

        Profile profile = mongoTemplate.findAll(Profile.class, "users").get(0);
        String expectedEmail = profile.getEmail();

        //when
        Profile foundProfile = profileRepository.findByEmail(expectedEmail);
        String actualEmail = foundProfile.getEmail();

        //then
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void findProfileAndUpdate_shouldUpdateProfile() {

        //given
        Profile seedProfile = new Profile();
        seedProfile.setUsername("Valera Ivanov");
        mongoTemplate.insert(seedProfile);

        Profile profile = mongoTemplate.findAll(Profile.class, "users").get(0);

        //when
        String newName = "Valera Petrov";
        profile.setUsername(newName);

        profileRepository.findProfileAndUpdate(profile);

        Profile profileAfterUpdate = mongoTemplate.findAll(Profile.class, "users").get(0);
        String actualName = profileAfterUpdate.getUsername();

        //then
        assertEquals(newName, actualName);
    }


    @Test
    public void deleteProfileById_shouldDeleteProfileById() {

        //given
        Profile seedProfile = new Profile();
        mongoTemplate.insert(seedProfile);

        Profile profile = mongoTemplate.findAll(Profile.class, "users").get(0);
        String expectedId = profile.getId();

        //when
        profileRepository.deleteProfileById(expectedId);

        List<Profile> profiles = mongoTemplate.findAll(Profile.class, "users");
        int actualSize = profiles.size();

        //then
        assertEquals(0, actualSize);
    }

    @Test
    public void profileExists_shouldShowIfProfileExistWithId() {

        //given
        Profile seedProfile = new Profile();
        mongoTemplate.insert(seedProfile);

        Profile profile = mongoTemplate.findAll(Profile.class, "users").get(0);
        String expectedId = profile.getId();

        //when
        boolean isProfileExist = profileRepository.profileExists(expectedId);

        //then
        assertTrue(isProfileExist);
    }

    @Test
    public void profileExists_shouldShowIfProfileExistWithEmail() {

        //given
        Profile seedProfile = new Profile();
        seedProfile.setEmail("test@gmail.com");
        mongoTemplate.insert(seedProfile);

        //when
        boolean isProfileExist = profileRepository.profileExistsWithEmail("test@gmail.com");

        //then
        assertTrue(isProfileExist);
    }

    @Test
    public void profileExists_shouldShowIfProfileExistWithSocWendor() {

        //given
        Profile seedProfile = new Profile();
        seedProfile.setSocWendor("gup.com.ua");
        mongoTemplate.insert(seedProfile);

        //when
        boolean isProfileExist = profileRepository.profileExistsWithSocWendor("gup.com.ua");

        //then
        assertTrue(isProfileExist);
    }


    @Test
    public void profileExists_shouldShowIfProfileExistWithprofileExistsWithUid() {

        //given
        Profile seedProfile = new Profile();
        seedProfile.setUid("123456");
        mongoTemplate.insert(seedProfile);

        //when
        boolean isProfileExist = profileRepository.profileExistsWithUid("123456");

        //then
        assertTrue(isProfileExist);
    }


    @Test
    public void profileExists_shouldShowIfProfileExistWithUidAndWendor() {

        //given
        Profile seedProfile = new Profile();
        seedProfile.setSocWendor("gup.com.ua");
        seedProfile.setUid("123456");
        mongoTemplate.insert(seedProfile);

        //when
        boolean isProfileExist = profileRepository.profileExistsWithUidAndWendor("123456", "gup.com.ua");

        //then
        assertTrue(isProfileExist);
    }


    @Test
    public void findAllProfilesForAdmin_shouldFindProfilesWithFilterOptionsSkip() {

        //given
        mongoTemplate.insert(new Profile());
        mongoTemplate.insert(new Profile());
        mongoTemplate.insert(new Profile());

        //when
        ProfileFilterOptions profileFilterOptions = new ProfileFilterOptions();
        profileFilterOptions.setSkip(1);

        List<Profile> profiles = profileRepository.findAllProfiles(profileFilterOptions);
        int resultSize = profiles.size();

        //then
        assertEquals(2, resultSize);
    }

    @Test
    public void findAllProfilesForAdmin_shouldFindProfilesWithFilterOptionsLimit() {

        //given
        mongoTemplate.insert(new Profile());
        mongoTemplate.insert(new Profile());
        mongoTemplate.insert(new Profile());

        //when
        ProfileFilterOptions profileFilterOptions = new ProfileFilterOptions();
        profileFilterOptions.setLimit(1);

        List<Profile> profiles = profileRepository.findAllProfiles(profileFilterOptions);
        int resultSize = profiles.size();

        //then
        assertEquals(1, resultSize);
    }

    @Test
    public void addContactToContactList_shouldAddContactToContactList() {

        //given
        mongoTemplate.insert(new Profile());

        Profile profile = mongoTemplate.findAll(Profile.class, "users").get(0);
        String userId = profile.getId();


        //when
        profileRepository.addContactToContactList(userId, "123");

        Profile updatedProfile = mongoTemplate.findAll(Profile.class, "users").get(0);
        Set<ProfileContactList> contactList = updatedProfile.getContactList();
        String contactResult = null;
        for (ProfileContactList contact : contactList) {
            contactResult = contact.toString();
        }

        //then
        assertEquals("123", contactResult);
    }


}

