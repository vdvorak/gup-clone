package ua.com.itproekt.gup.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class OAuthFilter implements Filter {
    private final int ACCESS_TOKEN_EXPIRES_IN_SECONDS = 600 - 3;

    private DefaultTokenServices tokenServices;

    public void init(FilterConfig config) throws ServletException {
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext());

        this.tokenServices = ctx.getBean(DefaultTokenServices.class);
    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        FilteredRequest filteredRequest = new FilteredRequest(httpServletRequest);

        if (httpServletRequest.getCookies() != null) {
            String authToken = Arrays.stream(httpServletRequest.getCookies())
                    .filter(c -> "authToken".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().orElse("");

            String refreshToken = Arrays.stream(httpServletRequest.getCookies())
                    .filter(c -> "refreshToken".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().orElse("");

            if (!authToken.isEmpty()) {
                String[] paramValues = new String[]{authToken};
                filteredRequest.addParameter("access_token", paramValues);
            } else if (!refreshToken.isEmpty()) {
                HashMap<String, String> parameters = new HashMap<>();
//                parameters.put("client_id", "7b5a38705d7b3562655925406a652e32");
//                parameters.put("client_secret", "655f523128212d6e70634446224c2a48");

                Set<String> scope = new HashSet<>();

                TokenRequest tokenRequest = new TokenRequest(parameters,
                        "7b5a38705d7b3562655925406a652e32",
                        scope,
                        "password");

                OAuth2AccessToken accessToken = null;
                try {
                    accessToken = tokenServices.refreshAccessToken(refreshToken, tokenRequest);
                } catch (Exception ex) {
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

// **** **** **** **** **** *** **** **** *** **** **** *** **** **** *** *** **** **** *** *** **** **** ***
                    System.err.println("***" + ex.getClass());
                    ex.printStackTrace();
// **** **** **** **** **** *** **** **** *** **** **** *** **** **** *** *** **** **** *** *** **** **** ***
                }

                Cookie cookie = new Cookie("authToken", accessToken.getValue());
                cookie.setMaxAge(ACCESS_TOKEN_EXPIRES_IN_SECONDS);
                httpServletResponse.addCookie(cookie);

                String[] paramValues = new String[]{accessToken.getValue()};
                filteredRequest.addParameter("access_token", paramValues);
            }
        }

        chain.doFilter(filteredRequest, httpServletResponse);
    }

    public void destroy() {}
}
