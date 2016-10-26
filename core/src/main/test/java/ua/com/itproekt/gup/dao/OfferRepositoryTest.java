package ua.com.itproekt.gup.dao;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.dao.offers.OfferRepositoryImpl;
import ua.com.itproekt.gup.model.offer.Offer;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class OfferRepositoryTest {

    OfferRepositoryImpl offerRepository = new OfferRepositoryImpl();
    MongoTemplate mongoTemplate;
    Mongo mongo;
    Fongo fongo = new Fongo("TestOffer");

    File oneOfferFile = new File("src/main/test/resources/", "OffersMongoSeeds.json");

    @Before
    public void setup() {

        // create fake mongo database
        mongo = fongo.getMongo();
        mongoTemplate = new MongoTemplate(mongo, "dbName");
        fongo.dropDatabase("dbName");

        offerRepository.setMongoTemplate(mongoTemplate);

//        System.err.println("Saving to the file");
        mongoTemplate.insert(oneOfferFile, "offer");

//        System.err.println("All Collections: " + mongoTemplate.getCollectionNames());
//        System.err.println("All documents in offer collection: " + mongoTemplate.findAll(Offer.class, "offer"));

    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void create_oneDocument_shouldCreateOneDocument() {

        //given
        mongoTemplate.insert(oneOfferFile, "offer");
        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findById(actualId);
        String expectedId = foundOffer.getId();

        //then
        assertEquals(expectedId, actualId);
    }


}