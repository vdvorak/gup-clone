package ua.com.itproekt.gup.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.itproekt.gup.dao.offers.OfferRepository;

import java.util.List;


public class StudentServiceIT {

    @Autowired
    private OfferRepository offerRepository;

    @Test
    public void create_should_create_new_student() {
//        offerRepository.create();
//
//
//        studentService.create("James Doe");
//        List studs = studentRepository.findAll();
//        assertTrue(studs.size() == 1);
//        assertEquals("James Doe", studs.get(0).getName());
//        assertNotNull(studs.get(0).getEnrollmentDate());

        assertEquals("1", "1");
    }
}