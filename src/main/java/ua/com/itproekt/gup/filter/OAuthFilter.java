package ua.com.itproekt.gup.filter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ua.com.itproekt.gup.util.CookieUtil;
import ua.com.itproekt.gup.util.LogUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OAuthFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(OAuthFilter.class);

    private final String ACCESS_TOKEN_COOKIE_NAME = "authToken";
    private final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";
    private final String SPRING_ACCESS_TOKEN_PARAM_NAME = "access_token";
    private final int ACCESS_TOKEN_EXPIRES_IN_SECONDS = (int) TimeUnit.MINUTES.toSeconds(10);

    private DefaultTokenServices tokenServices;

    @Override
    public void init(FilterConfig config) throws ServletException {
        ApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        this.tokenServices = appCtx.getBean(DefaultTokenServices.class);
    }

    @Override
    public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain)
            throws IOException, ServletException {

        try {

        HttpServletRequest httpServletReq = (HttpServletRequest) servletReq;
        HttpServletResponse httpServletResp = (HttpServletResponse) servletResp;
        FilteredRequest filteredReq = new FilteredRequest(httpServletReq);

        Cookie[] cookies = httpServletReq.getCookies();
        if (cookies != null) {
            System.err.println("if (cookies != nu");

            String accessTokenValue = CookieUtil.getCookieValue(cookies, ACCESS_TOKEN_COOKIE_NAME);
            String refreshTokenValue = CookieUtil.getCookieValue(cookies, REFRESH_TOKEN_COOKIE_NAME);

            if (!accessTokenValue.isEmpty()) {
                System.err.println(" if (!accessTokenValue.isEmpty(");
                if (isExistAccessToken(accessTokenValue)) {
                    System.err.println(" *** isExistAccessToken.isEmpty():" + accessTokenValue);
                    filteredReq.addParameter(SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessTokenValue});
                    System.err.println(" *** accessTokenValue.isEmpty()");
                } else {
                    System.err.println(" *** accessTokenValue.else()");
                    LOG.error(" **** Access token [" + accessTokenValue + "] not valid **** ");
                    CookieUtil.addCookie(httpServletResp, ACCESS_TOKEN_COOKIE_NAME, null, 0);
                    authenticateByRefreshToken(filteredReq, httpServletResp, refreshTokenValue);
                }
            } else if (!refreshTokenValue.isEmpty()){
                authenticateByRefreshToken(filteredReq, httpServletResp, refreshTokenValue);
            }
        }

        chain.doFilter(filteredReq, httpServletResp);
        } catch (Exception ex) {
            System.err.println(LogUtil.getExceptionStackTrace(ex));
        }

    }

    private boolean isExistAccessToken(String accessTokenValue) {
        System.err.println(" *** tokenServices.readAccessToken(accessTokenValue):" + tokenServices.readAccessToken(accessTokenValue));
        System.err.println(null != tokenServices.readAccessToken(accessTokenValue));

        return (null != tokenServices.readAccessToken(accessTokenValue));
    }

    private void authenticateByRefreshToken(FilteredRequest request, HttpServletResponse response, String refreshTokenValue) {
        System.err.println("authenticateByRefreshToken");

        try {
            OAuth2AccessToken accessToken = tokenServices.refreshAccessToken(refreshTokenValue, getTokenRequest());
            request.addParameter(SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessToken.getValue()});
            CookieUtil.addCookie(response, ACCESS_TOKEN_COOKIE_NAME, accessToken.getValue(), ACCESS_TOKEN_EXPIRES_IN_SECONDS);

            System.err.println("try accessToken:" + accessToken.getValue() );

        } catch (AuthenticationException ex) {
            System.err.println("authenticateByRefreshToken" );

            CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, null, 0);
            LOG.error("Incorrect request token. " + LogUtil.getExceptionStackTrace(ex));
            throw ex;
        }
    }

    private TokenRequest getTokenRequest() {
        Set<String> scope = new HashSet<>();
        HashMap<String, String> requestParameters = new HashMap<>();
        String clientId = "7b5a38705d7b3562655925406a652e32";
        String grantType = "password";

        return new TokenRequest(requestParameters, clientId, scope, grantType);
    }

    @Override
    public void destroy() {

    }
}
