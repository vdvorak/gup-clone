package ua.com.gup.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ua.com.gup.repository.ComplaintOfferRepository;
import ua.com.gup.service.ComplaintOfferService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc-config.xml"})
public class ComplaintOfferServiceTest {

//    @Autowired
//    private ComplaintOfferRepository complaintRepository;

    @Autowired
    private ComplaintOfferService complaintService;

//    @Test
//    public void testRepository() throws Exception {
//        System.err.println();
//        System.err.println("--------------------------------------------------");
//        System.err.println( complaintRepository.findAll() );
//        System.err.println("--------------------------------------------------");
//        System.err.println();
//    }

    @Test
    public void testService() throws Exception {
        System.err.println();
        System.err.println("--------------------------------------------------");
        System.err.println( complaintService.findAll() );
        System.err.println("--------------------------------------------------");
        System.err.println();
    }

}
