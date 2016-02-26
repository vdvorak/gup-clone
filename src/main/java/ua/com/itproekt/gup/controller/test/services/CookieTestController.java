package ua.com.itproekt.gup.controller.test.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.util.CookieUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zver on 26.02.2016.
 */
@Controller
public class CookieTestController {

    @RequestMapping("/test/addAccessTokenCookie")
    public void addAccessTokenCookie(HttpServletResponse response) {
        CookieUtil.addCookie(response, "authToken", "90a71d9b-26f8-4151-a001-95dc8ba3b943", 777);
    }
//
}
