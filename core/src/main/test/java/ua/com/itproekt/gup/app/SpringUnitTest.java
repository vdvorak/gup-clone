//package ua.com.itproekt.gup.app;
//
//import org.apache.commons.io.FileUtils;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
//
//
//import java.io.File;
//import java.io.IOException;
//
//import static org.junit.Assert.assertEquals;
//
//
//@ActiveProfiles({ "test", "unit" })
////@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { IntegrationTestApplicationConfig.class })
//public abstract class SpringUnitTest {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
////    protected void importJSON(String collection, String file) {
//////        try {
//////            for (Object line : FileUtils.readLines(new File(file), "utf8")) {
//////                mongoTemplate.save(line, collection);
//////            }
//////        } catch (IOException e) {
//////            throw new RuntimeException("Could not import file: " + file, e);
//////        }
////
////        System.err.println("aaaa " + mongoTemplate.toString());
////    }
//
//
//    @Test
//    public void create_should_create_new_student() {
//
//        assertEquals("1", "1");
//    }
//
//}