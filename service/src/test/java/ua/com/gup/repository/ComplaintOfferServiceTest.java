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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc-config.xml"})
public class ComplaintOfferServiceTest {

    @Autowired
    private ComplaintOfferRepository complaintRepository;

    @Test
    public void testRepository() throws Exception {
        System.err.println();
        System.err.println("--------------------------------------------------");
        System.err.println( complaintRepository.findAll() );
        System.err.println("--------------------------------------------------");
        System.err.println();
    }

}
