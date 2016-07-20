package ua.com.itproekt.gup.server.api;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class OauthAPI {

    @CrossOrigin
    @RequestMapping(value = "/loginTest", method = RequestMethod.POST)
    @ResponseBody
    protected HashMap<String, String> loginTest() {
        HashMap<String, String> model = new HashMap<>();
        String name = "LoginTest";
        model.put("oauth", name);

        return model;
    }

    @CrossOrigin
    @RequestMapping(value = "/logoutTest", method = RequestMethod.POST)
    @ResponseBody
    protected HashMap<String, String> logoutTest() {
        HashMap<String, String> model = new HashMap<>();
        String name = "LogoutTest";
        model.put("oauth", name);

        return model;
    }

}