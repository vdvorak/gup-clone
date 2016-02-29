package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SocialNetwork;

import java.util.*;


@Controller
public class ProfileTestController {
    /**
     * The Profile service.
     */
    @Autowired
    ProfilesService profileService;

    /**
     * The Nace service.
     */
    @Autowired
    NaceService naceService;

    /**
     *
     * Add simple user.
     */
    @RequestMapping("/addProfile")
    public String addUser(Model model) {
        Profile profile = new Profile();
        profile.setConfirmModerator(true);
        profile.setIdWord("ImChiefHere");
        profile.setUsername("Main Admin");
        profile.setEmail("admin@abc.com");
        profile.setImgId("EnterPictureIdHere");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin");
        profile.setPassword(hashedPassword);
        HashSet<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.ROLE_ADMIN);
        profile.setUserRoles(userRoles);

        Contact contact = new Contact();
        contact.setType(UserType.LEGAL_ENTITY);
        contact.setPosition("Admin Jr. Adm");
        HashSet<String> contactEmails = new HashSet<>();
        contactEmails.add("info@abc");
        contactEmails.add("office@abc");
        contact.setContactEmails(contactEmails);
        HashSet<String> contactPhones = new HashSet<>();
        contactPhones.add("+38(044)-111-11-11");
        contactPhones.add("+38(067)-111-11-11");
        contact.setContactPhones(contactPhones);
//        HashSet<String> linkToWebSite = new HashSet<>();
//        linkToWebSite.add("http://www.president.gov.ua/");
//        linkToWebSite.add("https://www.whitehouse.gov/");
        String linkToWebSite = "https://www.whitehouse.gov/";
        contact.setLinkToWebSite(linkToWebSite);
        contact.setAboutUs("In the morning, the President will depart Chicago en route Washington, DC. ");
        contact.setNace(Nace.E);
        HashMap<SocialNetwork, String> socNetLink = new HashMap<>();
        socNetLink.put(SocialNetwork.SKYPE, "SkypeAdm");
        socNetLink.put(SocialNetwork.FACEBOOK, "https://www.facebook.com/zuck");
        contact.setSocNetLink(socNetLink);

        ProfileRating profileRating = new ProfileRating();
        Set<ProfileRating> profileRatingSet = new HashSet<>();
        profileRatingSet.add(profileRating.setEarnPoints(1));
        profileRatingSet.add(profileRating.setLongDescription("We've selected this list of the 10 best volunteer activities."));
        profileRatingSet.add(profileRating.setShortDescription("Best volunteer"));
        profileRatingSet.add(profileRating.setIdAttachedFile("561f9faf1b7bfd660799a59b"));

        profile.setProfileRating(profileRatingSet);
        profile.setContact(contact);
        profileService.createProfile(profile);
        model.addAttribute("message", "Profile with the email " + profile.getEmail() + " is created.");
        return "index";
    }

//    @RequestMapping("/addNace")
//    public String addNace(Model model) {
//        DepartmentOrNace nace1 = new DepartmentOrNace();
//        nace1.setId("1");
//        nace1.setName("11");
//        nace1.setTranscript("111");
//
//        DepartmentOrNace nace2 = new DepartmentOrNace();
//        nace2.setId("2");
//        nace2.setName("22");
//        nace2.setTranscript("222");
//
//        DepartmentOrNace nace3 = new DepartmentOrNace();
//        nace3.setId("3");
//        nace3.setName("33");
//        nace3.setTranscript("333");
//
//        List<DepartmentOrNace> ch2 = new ArrayList<>();
//        ch2.add(nace2);
//
//        nace1.setChildren(ch2);
//
//        List<DepartmentOrNace> ch3 = new ArrayList<>();
//        ch3.add(nace3);
//
//        nace2.setChildren(ch3);
//
//        naceService.addNace(nace1);
//        return "index";
//    }

}
