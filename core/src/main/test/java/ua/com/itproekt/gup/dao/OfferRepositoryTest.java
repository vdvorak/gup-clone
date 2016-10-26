package ua.com.itproekt.gup.dao;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.dao.offers.OfferRepositoryImpl;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;

public class OfferRepositoryTest {

    OfferRepositoryImpl offerRepository = new OfferRepositoryImpl();
    MongoTemplate mongoTemplate;
    Mongo mongo;
    Fongo fongo = new Fongo("Test");

    @Before
    public void setup() {

        mongo = fongo.getMongo();
        mongoTemplate = new MongoTemplate(mongo, "dbName");
        fongo.dropDatabase("dbName");

        offerRepository.setMongoTemplate(mongoTemplate);
    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void test_in_memory_mongodb() {


        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setSkip(0);
        offerFilterOptions.setLimit(10);

        Offer offer = new Offer();
        offer.setActive(true);

        offerRepository.create(offer);

        System.err.println("test" + offerRepository.findOffersWihOptions(offerFilterOptions));

        System.err.println("test2" + mongoTemplate.toString());
    }


}