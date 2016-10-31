package ua.com.itproekt.gup.dao.profile;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;

/**
 * JUnit tests using Fongo (fake mongo) library. Testing of ProfileRepository interface.
 *
 * @author Kobylyatskyy Alexander
 */
public class ProfileRepositoryTest {


    ProfileRepository profileRepository =  new ProfileRepositoryImpl();
    MongoTemplate mongoTemplate;
    Mongo mongo;
    Fongo fongo = new Fongo("TestProfile");

    @Before
    public void setup() {

        // create fake mongo database
        mongo = fongo.getMongo();
        mongoTemplate = new MongoTemplate(mongo, "dbName");
        mongoTemplate.createCollection(Offer.class);

        fongo.dropDatabase("dbName");

        profileRepository.setMongoTemplateInstanceForTests(mongoTemplate);
    }

    @After
    public void tearDown() throws Exception {
    }




}

