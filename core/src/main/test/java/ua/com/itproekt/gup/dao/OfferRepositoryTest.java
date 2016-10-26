package ua.com.itproekt.gup.dao;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.dao.offers.OfferRepository;
import ua.com.itproekt.gup.dao.offers.OfferRepositoryImpl;
import ua.com.itproekt.gup.model.offer.Offer;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class OfferRepositoryTest {

    OfferRepository offerRepository = new OfferRepositoryImpl();
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

        offerRepository.setMongoTemplateInstanceForTests(mongoTemplate);

//        System.err.println("Saving to the file");
//        mongoTemplate.insert(oneOfferFile, "offer");

//        System.err.println("All Collections: " + mongoTemplate.getCollectionNames());
//        System.err.println("All documents in offer collection: " + mongoTemplate.findAll(Offer.class, "offer"));
    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void findById_oneDocument_shouldFindDocument() {

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

    @Test
    public void findBySeoKey_oneDocument_shouldFindDocumentByItsSeo() {

        //given
        mongoTemplate.insert(oneOfferFile, "offer");
        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualSeoKey = offer.getSeoKey();
        String actualId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findBySeoKey(actualSeoKey);
        String expectedId = foundOffer.getId();

        //then
        assertEquals(expectedId, actualId);
    }


    //ToDo make this
//    @Test
//    public void findAndUpdate_oneDocument_shouldUpdateFields() {
//
//        //given
//        mongoTemplate.insert(oneOfferFile, "offer");
//        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
//        String actualSeoKey = offer.getSeoKey();
//        String actualId = offer.getId();
//
//        //when
//        Offer foundOffer = offerRepository.findBySeoKey(actualSeoKey);
//        String expectedId = foundOffer.getId();
//
//        //then
//        assertEquals(expectedId, actualId);
//    }


    @Test
    public void delete_oneDocument_shouldDeleteOneDocumentFromCollection() {

        //given
        mongoTemplate.insert(oneOfferFile, "offer");
        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualId = offer.getId();

        //when
        offerRepository.delete(actualId);
        int expectedSize = mongoTemplate.findAll(Offer.class, "offer").size();

        //then
        assertEquals(0, expectedSize);
    }

    @Test
    public void offerExists_oneDocument_shouldShowIfOrderExist() {

        //given
        mongoTemplate.insert(oneOfferFile, "offer");
        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualId = offer.getId();

        //when
        boolean isExist = offerRepository.offerExists(actualId);

        //then
        assertEquals(true, true);
    }


}