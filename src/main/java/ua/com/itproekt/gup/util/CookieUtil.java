package ua.com.itproekt.gup.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by Zver on 26.02.2016.
 */
public final class CookieUtil {
    public static String getCookieValue(final Cookie[] cookies, final String cookieName) {
        return  Arrays.stream(cookies)
                .filter(c -> cookieName.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny().orElse("");
    }

    public static void addCookie(HttpServletResponse resp, String cookieName, String cookieValue, int secondExpires) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(secondExpires);
        cookie.setPath("/");
        resp.addCookie(cookie);
    }
}
