package ua.com.itproekt.gup.server.api.rest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.gup.repository.ComplaintOfferRepository;
import ua.com.gup.service.ComplaintOfferService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc-config.xml"})
@WebAppConfiguration
public class ComplaintOfferServiceTest {

    @Autowired
    private ComplaintOfferRepository complaintOfferRepository;

    @Autowired
    private ComplaintOfferService complaintOfferService;


    @Test
    public void testRepository() throws Exception {
        System.out.println( complaintOfferRepository.findAll() );
    }

    @Test
    public void testService() throws Exception {
        System.out.println( complaintOfferService.findAll() );
    }

}
