package ua.com.itproekt.gup.filter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.security.sasl.SaslException;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OAuthFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(OAuthFilter.class);

    private final int ACCESS_TOKEN_EXPIRES_IN_SECONDS = (int) TimeUnit.MINUTES.toSeconds(10);

    private DefaultTokenServices tokenServices;

    public void init(FilterConfig config) throws ServletException {
        ApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        this.tokenServices = appCtx.getBean(DefaultTokenServices.class);
    }

    public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain)
            throws ServletException, IOException{

        HttpServletRequest httpServletReq = (HttpServletRequest) servletReq;
        HttpServletResponse httpServletResp = (HttpServletResponse) servletResp;
        FilteredRequest filteredReq = new FilteredRequest(httpServletReq);

        Cookie[] cookies = httpServletReq.getCookies();
        if (cookies != null) {
            String authToken = Arrays.stream(cookies)
                    .filter(c -> "authToken".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().orElse("");

            if (!authToken.isEmpty()) {
                filteredReq.addParameter("access_token", new String[]{authToken});
            } else {
                String refreshToken = Arrays.stream(cookies)
                        .filter(c -> "refreshToken".equals(c.getName()))
                        .map(Cookie::getValue)
                        .findAny().orElse("");

                if (!refreshToken.isEmpty()) {
                    Set<String> scope = new HashSet<>();
                    HashMap<String, String> parameters = new HashMap<>();
//                parameters.put("client_id", "7b5a38705d7b3562655925406a652e32");
//                parameters.put("client_secret", "655f523128212d6e70634446224c2a48");

                    TokenRequest tokenRequest = new TokenRequest(
                            parameters, "7b5a38705d7b3562655925406a652e32", scope, "password");

                    OAuth2AccessToken accessToken = null;
                    try {
                        accessToken = tokenServices.refreshAccessToken(refreshToken, tokenRequest);
                    } catch (AuthenticationException ex) {
                        StringWriter stack = new StringWriter();
                        ex.printStackTrace(new PrintWriter(stack));
                        LOG.error(stack.toString());

                        Cookie cookie = new Cookie("refreshToken", null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        httpServletResp.addCookie(cookie);

//                        httpServletResp.setStatus(HttpServletResponse.SC_OK);
                        throw ex;
                    }

                    if (accessToken != null) {
                        Cookie cookie = new Cookie("authToken", accessToken.getValue());
                        cookie.setMaxAge(ACCESS_TOKEN_EXPIRES_IN_SECONDS);
                        cookie.setPath("/");
                        httpServletResp.addCookie(cookie);

                        filteredReq.addParameter("access_token", new String[]{accessToken.getValue()});
                    }
                }
            }
        }

        try {
            chain.doFilter(filteredReq, httpServletResp);
        } catch (ServletException | IOException ex) {
            StringWriter stack = new StringWriter();
            ex.printStackTrace(new PrintWriter(stack));
            LOG.error(stack.toString());

            throw ex;
        }

    }

    public void destroy() {}
}
