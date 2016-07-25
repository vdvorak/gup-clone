//package ua.com.itproekt.gup.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ua.com.itproekt.gup.service.profile.ProfilesService;
//
///**
// * Created by RAYANT on 25.11.2015.
// */
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    ProfilesService  profilesService;
//
//    @RequestMapping("/isUserExist")
//    @ResponseBody
//    public boolean isUserExist(@RequestParam("email") String email){
//        return profilesService.profileExists(email);
//    }
//}
//
