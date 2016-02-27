package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.dao.nace.NaceRepository;

/**
 * Created by Fairy on 27.02.2016.
 */
@Controller
public class NaceTestController {
    @Autowired
    NaceRepository naceRepository;

    @RequestMapping("/nace")
    public String addOffer(Model model) {
        model.addAttribute("nace", naceRepository.findAll().get(0));
        return "nace";
    }
}
