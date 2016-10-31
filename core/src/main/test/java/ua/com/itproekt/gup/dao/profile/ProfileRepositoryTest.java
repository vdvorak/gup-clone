package ua.com.itproekt.gup.dao.profile;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.model.profiles.Profile;

import static org.junit.Assert.assertEquals;

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


}

