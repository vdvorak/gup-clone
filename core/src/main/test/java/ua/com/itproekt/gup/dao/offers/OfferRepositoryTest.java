package ua.com.itproekt.gup.dao.offers;


import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;

import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.*;

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
        String expectedId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findById(expectedId);
        String actualId = foundOffer.getId();

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
        String expectedId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findBySeoKey("a1");
        String actualId = foundOffer.getId();

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
        String expectedId = offer.getId();

        //when
        Offer foundOffer = offerRepository.findById(expectedId);
        foundOffer.setDescription("updated description");
        offerRepository.findAndUpdate(foundOffer);

        Offer offerAfterUpdate = offerRepository.findById(expectedId);
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
        String expectedId = offer.getId();

        //when
        offerRepository.delete(expectedId);
        int actualSize = mongoTemplate.findAll(Offer.class, "offer").size();

        //then
        assertEquals(0, actualSize);
    }

    @Test
    public void offerExists_oneDocument_shouldShowIfOrderExist() {

        //given
        Offer seedOffer = new Offer();
        mongoTemplate.insert(seedOffer);

        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        String expectedId = offer.getId();

        //when
        boolean isExist = offerRepository.offerExists(expectedId);

        //then
        assertTrue(isExist);
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

    @Test
    public void findOffersWihOptions_filterOption_ShouldShowReservedOffersIfTheCorrespondingValueIsSet() {

        //given
        Offer firstSeedOffer = new Offer(); // with reservation
        firstSeedOffer.setReservation(new Reservation());

        Offer secondSeedOffer = new Offer(); // without reservation
        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setShowReserved(true);

        //when
        int size = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities().size();
        Offer offer = mongoTemplate.findAll(Offer.class, "offer").get(0);

        //then
        assertEquals(1, size);
        assertNotNull(offer.getReservation());
    }


    @Test
    public void findOffersWihOptions_filterOption_ShouldShowOfferInFromToPriceGap() {

        //given
        Offer firstSeedOffer = new Offer().setPrice(200l);
        Offer secondSeedOffer = new Offer().setPrice(100l);
        Offer thirdSeedOffer = new Offer().setPrice(300l);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);


        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setFromPrice(101l);
        offerFilterOptions.setToPrice(299l);

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        int size = offers.size();
        Offer resultOffer = mongoTemplate.findAll(Offer.class, "offer").get(0);
        long actualPrice = resultOffer.getPrice();

        //then
        assertEquals(1, size);
        assertEquals(200l, actualPrice);
    }

    @Test
    public void findOffersWihOptions_filterOption_ShouldShowOfferInFromToPriceGapIncluded() {

        //given
        Offer firstSeedOffer = new Offer().setPrice(200l);
        Offer secondSeedOffer = new Offer().setPrice(100l);
        Offer thirdSeedOffer = new Offer().setPrice(300l);
        Offer fourthSeedOffer = new Offer().setPrice(400l);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);
        mongoTemplate.insert(fourthSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setFromPrice(100l);
        offerFilterOptions.setToPrice(300l);

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        int size = offers.size();
        Offer resultOffer = mongoTemplate.findAll(Offer.class, "offer").get(0);

        //then
        assertEquals(3, size);
    }

    @Test
    public void findOffersWihOptions_filterOption_ShouldReturnOffersInAscensiveOrderByTheirDateCreation() {

        //given
        Offer firstSeedOffer = new Offer().setCreatedDate(20000l);
        Offer secondSeedOffer = new Offer().setCreatedDate(40000l);
        Offer thirdSeedOffer = new Offer().setCreatedDate(30000l);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setCreatedDateSortDirection("ASC");

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        //then

        assertEquals((Long) 20000l, offers.get(0).getCreatedDate());
        assertEquals((Long) 30000l, offers.get(1).getCreatedDate());
        assertEquals((Long) 40000l, offers.get(2).getCreatedDate());
    }

    @Test
    public void findOffersWihOptions_filterOption_ShouldReturnOffersInDescendingOrderByTheirDateCreation() {

        //given
        Offer firstSeedOffer = new Offer().setCreatedDate(20000l);
        Offer secondSeedOffer = new Offer().setCreatedDate(40000l);
        Offer thirdSeedOffer = new Offer().setCreatedDate(30000l);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setCreatedDateSortDirection("DESC");

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        //then

        assertEquals((Long) 40000l, offers.get(0).getCreatedDate());
        assertEquals((Long) 30000l, offers.get(1).getCreatedDate());
        assertEquals((Long) 20000l, offers.get(2).getCreatedDate());
    }


    @Test
    public void findOffersWihOptions_filterOption_ShouldReturnOffersInAscensiveOrderByTheirPrice() {

        //given
        Offer firstSeedOffer = new Offer().setPrice(100l);
        Offer secondSeedOffer = new Offer().setPrice(400l);
        Offer thirdSeedOffer = new Offer().setPrice(200l);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setPriceSortDirection("ASC");

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        //then

        assertEquals((Long) 100l, offers.get(0).getPrice());
        assertEquals((Long) 200l, offers.get(1).getPrice());
        assertEquals((Long) 400l, offers.get(2).getPrice());
    }

    @Test
    public void findOffersWihOptions_filterOption_ShouldReturnOffersInDescendingOrderByTheirPrice() {

        //given
        Offer firstSeedOffer = new Offer().setPrice(100l);
        Offer secondSeedOffer = new Offer().setPrice(400l);
        Offer thirdSeedOffer = new Offer().setPrice(200l);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setPriceSortDirection("DESC");

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        //then

        assertEquals((Long) 400l, offers.get(0).getPrice());
        assertEquals((Long) 200l, offers.get(1).getPrice());
        assertEquals((Long) 100l, offers.get(2).getPrice());
    }


    @Test
    public void findOffersWihOptions_filterOption_ShouldReturnOffersWithRelevantCategories() {

        //given
        LinkedHashSet<String> categories1 = new LinkedHashSet<>();
        categories1.add("35");
        categories1.add("64");
        LinkedHashSet<String> categories2 = new LinkedHashSet<>();
        categories2.add("38");
        LinkedHashSet<String> categories3 = new LinkedHashSet<>();
        categories3.add("35");
        categories3.add("121");
        categories3.add("225");

        Offer firstSeedOffer = new Offer().setCategories(categories1);
        Offer secondSeedOffer = new Offer().setCategories(categories2);
        Offer thirdSeedOffer = new Offer().setCategories(categories3);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        LinkedHashSet<String> categoriesForFilter = new LinkedHashSet<>();
        categoriesForFilter.add("35");
        offerFilterOptions.setCategories(categoriesForFilter);

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        //then

        assertEquals(2, offers.size());
    }

    @Test
    public void findOffersWihOptions_filterOption_ShouldReturnOffersWithThirdLeveRelevantCategorie() {

        //given
        LinkedHashSet<String> categories1 = new LinkedHashSet<>();
        categories1.add("35");
        categories1.add("64");
        LinkedHashSet<String> categories2 = new LinkedHashSet<>();
        categories2.add("38");
        LinkedHashSet<String> categories3 = new LinkedHashSet<>();
        categories3.add("35");
        categories3.add("121");
        categories3.add("225");

        Offer firstSeedOffer = new Offer().setCategories(categories1);
        Offer secondSeedOffer = new Offer().setCategories(categories2);
        Offer thirdSeedOffer = new Offer().setCategories(categories3);

        mongoTemplate.insert(firstSeedOffer);
        mongoTemplate.insert(secondSeedOffer);
        mongoTemplate.insert(thirdSeedOffer);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        LinkedHashSet<String> categoriesForFilter = new LinkedHashSet<>();
        categoriesForFilter.add("225");
        offerFilterOptions.setCategories(categoriesForFilter);

        //when
        List<Offer> offers = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        //then

        assertEquals(1, offers.size());
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