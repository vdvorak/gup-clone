package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.dao.tender.doer.DoerRepository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerClient;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fairy on 14.01.2016.
 */
@Controller
public class DoerTestController {
    @Autowired
    DoerRepository doerRepository;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ProfileRepository profileRepository;

    @RequestMapping("/addDoer/{numberOfDoers}")
    public String addDoer(@PathVariable("numberOfDoers") int numberOfDoers, Model model) {
        ProfileFilterOptions pf = new ProfileFilterOptions();
        pf.setLimit(20);
        pf.setSkip(0);
        try {
            List<Profile> users = profileRepository.findAll();
            System.out.println("user OK " + users.size());
            List<String> ids = users.stream().map(Profile::getId).collect(Collectors.toList());
            System.out.println("ids Ok " + ids.size());
            for (int i = 0; i < numberOfDoers; i++) {
                Doer doer = new Doer();
                if (!ids.isEmpty()) {
                    doer.setAuthorId(randId(ids));
                    doer.setAuthorContacts(profilesService.findByIdWholeProfile(doer.getAuthorId()).getContact());
                    DoerClient doerClient1 = new DoerClient();
                    doerClient1.setId(randId(ids));
                    doerClient1.setClientConfirm(true);
                    doerClient1.setDoerConfirm(true);
                    DoerClient doerClient2 = new DoerClient();
                    doerClient2.setId(randId(ids));
                    doerClient2.setClientConfirm(true);
                    DoerClient doerClient3 = new DoerClient();
                    doerClient3.setId(randId(ids));
                    doerClient3.setDoerConfirm(true);
                    ArrayList<DoerClient> clients = new ArrayList<>();
                    clients.add(doerClient1);
                    clients.add(doerClient2);
                    clients.add(doerClient3);
                    doer.setClients(clients);
                } else {
                    doer.setAuthorId("24438TEST");
                }
                doer.setBody("DOER DESCRIPTION DOER DESCRIPTION DOER DESCRIPTION DOER DESCRIPTION DOER DESCRIPTION" + i);
                doer.setTitle("Doer NAME" + i);
                List<String> naceIds = new ArrayList<>();
                naceIds.add("10.10");
                naceIds.add("20.20");
                naceIds.add("30.30");
                doer.setNaceIds(naceIds);
                doerRepository.createDoer(doer);
            }
        } catch (Exception e){
            System.out.println("PROBLEM!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }

        model.addAttribute("message", numberOfDoers + " test doers is created.");
        return "index";
    }

    private String randId (List<String> from){
        if(from.isEmpty()) return null;
        return from.get((int) (Math.random() * 100) % from.size());
    }
}