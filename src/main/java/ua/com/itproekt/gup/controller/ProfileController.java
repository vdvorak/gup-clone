package ua.com.itproekt.gup.controller;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.net.URI;
import java.util.HashSet;

/**
 * Created by Optical Illusion on 17.11.2015.
 */

@Controller
public class ProfileController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ActivityFeedService activityFeedService;


    //----------------------------------- show registration page  ------
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showPage(Model model) {
        return "registration";
    }


    //----------------------------------- create profile  ------

    @RequestMapping(value = "/registration/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createProfie(@RequestBody String body) {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(body);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jobj = (JSONObject) obj;

        String capcha = (String) jobj.get("g-recaptcha-response");

        boolean check = getRecapchaResponse(capcha);

        if (check) {
            Profile profile = new Profile();
            String password = (String) jobj.get("password");
            String email = (String) jobj.get("email");
            profile.setEmail(email);
            profile.setPassword(password);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(profile.getPassword());
            profile.setPassword(hashedPassword);
            HashSet<UserRole> userRoles = new HashSet<>();
            userRoles.add(UserRole.ROLE_ANONYMOUS);
            profile.setUserRoles(userRoles);
            profilesService.createProfile(profile);

            return true;
        }
        return false;
    }


    public boolean getRecapchaResponse(String capcha) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host = "www.google.com";

            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost(host)
                    .setPath("/recaptcha/api/siteverify")
                    .setParameter("secret", "6Lc6KxETAAAAAIlydKCdatNsHDUBtlRCbSd9b-yl")
                    .setParameter("response", capcha)
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse httpResponse = client.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                String response = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response);
                JSONObject jobj = (JSONObject) obj;
                boolean result = (boolean) jobj.get("success");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //----------------------------------- read profile for edit-profile page  ------
    @RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
    public String editProfilePageById(Model model, @PathVariable("id") String id) {
        Profile profile = new Profile();
        try {
            profile = profilesService.readById(id);
        } catch (Exception e) {
            System.out.println("Can't read profile by id");
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "edit-profile";
    }


    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editProfilePage(Model model) {
        Profile profile = new Profile();
        String userId;

        if (SecurityOperations.isUserLoggedIn()) {
            userId = SecurityOperations.getLoggedUserId();
        } else {

            return "login";
        }


        try {
            profile = profilesService.readById(userId);
        } catch (Exception e) {
            System.out.println("Can't read profile by id");
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "edit-profile";
    }

}
