package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

/**
 * Created by RAYANT on 25.11.2015.
 */

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    ProfilesService  profilesService;

    @RequestMapping("/isUserExist")
    @ResponseBody
    public boolean isUserExist(@RequestParam("email") String email){
        return profilesService.profileExists(email);
    }

    @RequestMapping("/registerAndLogin")
    public void registerAndLogin(HttpServletRequest request,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password){
        Profile profile = new Profile();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        profile.setPassword(hashedPassword);
        HashSet<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.ROLE_USER);
        profile.setUserRoles(userRoles);
        profilesService.createProfile(profile);
        try {
            request.login(email,password);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

}

