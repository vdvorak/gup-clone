package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.tender.*;
import ua.com.itproekt.gup.service.tender.TenderService;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Комп2 on 02.11.2015.
 */
@Controller
public class TenderTestController {
    @Autowired
    TenderService tenderService;

    @RequestMapping("/addTender")
    public String addTender(Model model) {
        Tender tender = new Tender();
        tender.setBeginLocalDateTime(LocalDateTime.now());
        LocalDateTime end = LocalDateTime.now().plusDays(20);
        tender.setEndLocalDateTime(end);

        Member member = new Member();
        member.setId("5613d146fa12fded9736f671");
        member.setName("roro");
        tender.setMembers(new ArrayList<>());
        tender.getMembers().add(member);

        Propose propose = new Propose();
        propose.setAuthorId("15");
        propose.setBody("this is propose body");
        propose.setTimeLocalDateTime(LocalDateTime.now());
        tender.setProposes(new ArrayList<>());
        tender.getProposes().add(propose);

        tender.setBody("This is tender body");

        tender.setAuthorId("12");
        tender.setNaceIds("1");
        tender.setVisited(123L);
        tender.setTitle("this is title");
        tender.setType(TenderType.OPEN);

        tenderService.createTender(tender);
        return "index";
    }
}
