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
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;

import static org.junit.Assert.assertEquals;

/**
 * JUnit tests using Fongo (fake mongo) library. Testing of OfferRepository interface.
 *
 * @author Kobylyatskyy Alexander
 */
public class OfferRepositoryTest {

    OfferRepository offerRepository = new OfferRepositoryImpl();
    MongoTemplate mongoTemplate;
    Mongo mongo;
    Fongo fongo = new Fongo("TestOffer");

    @Before
    public void setup() {

        // create fake mongo database
        mongo = fongo.getMongo();
        mongoTemplate = new MongoTemplate(mongo, "dbName");
        mongoTemplate.createCollection(Offer.class);

        fongo.dropDatabase("dbName");

        offerRepository.setMongoTemplateInstanceForTests(mongoTemplate);

//        System.err.println("All Collections: " + mongoTemplate.getCollectionNames());
//        System.err.println("All documents in offer collection: " + mongoTemplate.findAll(Offer.class, "offer"));
    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void findById_oneDocument_shouldFindDocument() {

        //given
        Offer seedOffer = new Offer();
        mongoTemplate.insert(seedOffer);

        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findById(actualId);
        String expectedId = foundOffer.getId();

        //then
        assertEquals(expectedId, actualId);
    }


    @Test
    public void findBySeoKey_oneDocument_shouldFindDocumentByItsSeoKEy() {

        //given
        Offer seedOffer = new Offer();
        seedOffer.setSeoKey("a1");
        mongoTemplate.insert(seedOffer);

        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findBySeoKey("a1");
        String expectedId = foundOffer.getId();

        //then
        assertEquals(expectedId, actualId);
    }


    @Test
    public void findAndUpdate_oneDocument_shouldUpdateFields() {

        //given
        Offer seedOffer = new Offer();
        seedOffer.setDescription("test description");
        mongoTemplate.insert(seedOffer);

        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findById(actualId);
        foundOffer.setDescription("updated description");
        offerRepository.findAndUpdate(foundOffer);

        Offer offerAfterUpdate = offerRepository.findById(actualId);
        String newDescription = offerAfterUpdate.getDescription();

        //then
        assertEquals("updated description", newDescription);
    }


    @Test
    public void delete_oneDocument_shouldDeleteOneDocumentFromCollection() {

        //given
        Offer seedOffer = new Offer();
        mongoTemplate.insert(seedOffer);

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
        Offer seedOffer = new Offer();
        mongoTemplate.insert(seedOffer);

        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String actualId = offer.getId();

        //when
        boolean isExist = offerRepository.offerExists(actualId);

        //then
        assertEquals(true, isExist);
    }


    @Test
    public void findOffersWihOptions_filterOption_shouldFindAmountOfOffersAsLimitValue() {

        //given
        for (int i = 0; i < 10; i++) {
            Offer seedOffer = new Offer();
            mongoTemplate.insert(seedOffer);
        }

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setLimit(3);

        //when
        int size = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities().size();

        //then
        assertEquals(3, size);
    }


    @Test
    public void findOffersWihOptions_filterOption_shouldFindAmountOfOffersAsSkipValue() {

        //given
        for (int i = 0; i < 10; i++) {
            Offer seedOffer = new Offer();
            mongoTemplate.insert(seedOffer);
        }

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setSkip(3);

        //when
        int size = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities().size();

        //then
        assertEquals(7, size);
    }


    //ToDo impl this
//    @Test
//    public void findOffersWithOptionsAndExcludes_filterOption_shouldSFindRelevantToFilterOptionsOffersAndExcludeOne() {
//
//        //given
//        mongoTemplate.insert(oneOfferFile, "offer");
//        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
//        String actualId = offer.getId();
//
//        //when
//        boolean isExist = offerRepository.offerExists(actualId);
//
//        //then
//        assertEquals(true, true);
//    }


    //ToDo impl this
//    @Test
//    public void deleteReservation_offerId_ShouldMarkedOneOfferAsDeleted() {
//
//        //given
//        mongoTemplate.insert(oneOfferFile, "offer");
//        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
//        String actualId = offer.getId();
//
//        //when
//        boolean isExist = offerRepository.offerExists(actualId);
//
//        //then
//        assertEquals(true, true);
//    }

    //ToDo impl this
//    @Test
//    public void rentOffer_ShouldCreateRentDocument() {
//
//        //given
//        mongoTemplate.insert(oneOfferFile, "offer");
//        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
//        String actualId = offer.getId();
//
//        //when
//        boolean isExist = offerRepository.offerExists(actualId);
//
//        //then
//        assertEquals(true, true);
//    }


    //FixMe
//    @Test
//    public void incViewsAtOne_ShouldIncreaseViewsValueOfTheOfferByOne() {
//
//        //given
//        mongoTemplate.insert(oneOfferFile, "offer");
//        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
//        String actualId = offer.getId();
//        Integer oldViewsCount = offer.getViews();
//
//        //when
//        offerRepository.incViewsAtOne(actualId);
//        Integer actualViewsCount = offer.getViews();
//
//        //then
//        assertEquals(++oldViewsCount, actualViewsCount);
//    }

    //ToDo impl this
//    @Test
//    public void deleteRent_ShouldDeleteRentDocument() {
//
//        //given
//        mongoTemplate.insert(oneOfferFile, "offer");
//        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
//        String actualId = offer.getId();
//
//        //when
//        boolean isExist = offerRepository.offerExists(actualId);
//
//        //then
//        assertEquals(true, true);
//    }

    //ToDo impl this
//    @Test
//    public void getMatchedNames_ShouldReturnOffersThatMatchedToInputName() {
//
//        //given
//        mongoTemplate.insert(oneOfferFile, "offer");
//        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
//        String actualId = offer.getId();
//
//        //when
//        boolean isExist = offerRepository.offerExists(actualId);
//
//        //then
//        assertEquals(true, true);
//    }


}