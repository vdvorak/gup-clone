package ua.com.itproekt.gup.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class OAuthFilter implements Filter {

    private DefaultTokenServices tokenServices;

    @Value("${oauth.token.access.expiresInSeconds}")
    private int accessTokenExpiresInSeconds;

    public void init(FilterConfig config) throws ServletException {
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext());
        this.tokenServices = ctx.getBean(DefaultTokenServices.class);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws ServletException, IOException {
//        System.err.println("* servletRequest: " + servletRequest);
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
//            System.err.println("* httpServletRequest: " + httpServletRequest);
            FilteredRequest filteredRequest = new FilteredRequest(httpServletRequest);
//            System.err.println("* filteredRequest: " + filteredRequest);
//            System.err.println("** httpServletRequest.getCookies()" + Arrays.toString(httpServletRequest.getCookies()));

        if (httpServletRequest.getCookies() != null) {
            List<Cookie> cookies = Arrays.asList(httpServletRequest.getCookies());

            String authToken = cookies.stream()
                    .filter(c -> "authToken".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().orElse("");

            String refreshToken = cookies.stream()
                    .filter(c -> "refreshToken".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().orElse("");

            if (!authToken.isEmpty()) {
                String[] paramValues = new String[]{authToken};
                filteredRequest.addParameter("access_token", paramValues);
            } else if (!refreshToken.isEmpty()) {
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("client_id", "7b5a38705d7b3562655925406a652e32");
                parameters.put("client_secret", "655f523128212d6e70634446224c2a48");
//                parameters.put("grant_type", "password");
//                parameters.put("password", user.getPassword());
//                parameters.put("scope", "XXX");
//                parameters.put("username", user.getUserName());

                Set<String> scope = new HashSet();

                TokenRequest tokenRequest = new TokenRequest(parameters,
                        "7b5a38705d7b3562655925406a652e32",
                        scope,
                        "password");
                OAuth2AccessToken accessToken = tokenServices.refreshAccessToken(refreshToken, tokenRequest);
                System.err.println("*** accessToken" + accessToken);
                System.err.println("*** getTokenType" + accessToken.getTokenType());
                System.err.println("*** getExpiration" + accessToken.getExpiration());
                System.err.println("*** getExpiration" + accessToken.getValue());
                System.err.println("*** getRefreshToken" + accessToken.getRefreshToken());

                String[] paramValues = new String[]{accessToken.getValue()};

                Cookie cookie = new Cookie("authToken", accessToken.getValue());
                cookie.setMaxAge(accessTokenExpiresInSeconds);
                httpServletResponse.addCookie(cookie);
                filteredRequest.addParameter("access_token", paramValues);
            }
        }
//                for (Cookie cookie : httpServletRequest.getCookies()) {
////                    System.err.println("Cookie : " + cookie.getName() + ":" + cookie.getValue());
//
//                    if ("authToken".equals(cookie.getName())) {
//                        String[] paramValues = new String[]{cookie.getValue()};
//                        filteredRequest.addParameter("access_token", paramValues);
//                    } else if ("refreshToken".equals(cookie.getName())) {
//                        System.err.println("***** access token expired, refresh token = " + cookie.getValue());
//                    }
//                }


            chain.doFilter(filteredRequest, httpServletResponse);
//            System.err.println("chain.doFilter(filteredRequest, servletResponse)  filteredRequest: " + filteredRequest);
    }


    public void destroy() {
    }

}
