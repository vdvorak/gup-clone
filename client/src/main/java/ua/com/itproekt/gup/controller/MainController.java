//package ua.com.itproekt.gup.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ua.com.itproekt.gup.bank_api.BankSession;
//import ua.com.itproekt.gup.util.SecurityOperations;
//
//
//@Controller
//public class MainController {
//
//    @Autowired
//    BankSession bankSession;
//
//    @RequestMapping(value = {"/", "/index", "/404", "/403", "/500", "/favourites", "/bulletinDetails", "/bulletinAdd", "/editProfile", "/profile", "/login", "/register", "/searchResults"})
//    public String index() {
//        return "index";
//    }
//
//    //ToDo где-то есть уже дубль, удалить
//    @RequestMapping(value = "/check-balance", method = RequestMethod.POST)
//    @ResponseBody
//    public Integer checkBalance() {
//        if (SecurityOperations.isUserLoggedIn()) {
//            String userId = SecurityOperations.getLoggedUserId();
//            return bankSession.getUserBalance(userId);
//        }
//        return 0;
//    }
//}
