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

    private final int ACCESS_TOKEN_EXPIRES_IN_SECONDS = (int) TimeUnit.MINUTES.toSeconds(10);
    private final String ACCESS_TOKEN_COOKIE_NAME = "authToken";
    private final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";
    private final String SPRING_ACCESS_TOKEN_PARAM_NAME = "access_token";

    private DefaultTokenServices tokenServices;

    @Override
    public void init(FilterConfig config) throws ServletException {
        ApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        this.tokenServices = appCtx.getBean(DefaultTokenServices.class);
    }

    @Override
    public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletReq = (HttpServletRequest) servletReq;
        HttpServletResponse httpServletResp = (HttpServletResponse) servletResp;
        FilteredRequest filteredReq = new FilteredRequest(httpServletReq);

        Cookie[] cookies = httpServletReq.getCookies();
        if (cookies != null) {
            String accessTokenValue = CookieUtil.getCookieValue(cookies, ACCESS_TOKEN_COOKIE_NAME);
            if (!accessTokenValue.isEmpty()) {
                if (null == tokenServices.readAccessToken(accessTokenValue)) {
                    LOG.error(" **** Access token not valid **** ");
                }

                filteredReq.addParameter(SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessTokenValue});
            } else {
                String refreshToken = CookieUtil.getCookieValue(cookies, REFRESH_TOKEN_COOKIE_NAME);
                if (!refreshToken.isEmpty()) {
                    Set<String> scope = new HashSet<>();
                    HashMap<String, String> requestParameters = new HashMap<>();
                    String clientId = "7b5a38705d7b3562655925406a652e32";
                    String grantType = "password";

                    TokenRequest tokenRequest = new TokenRequest(requestParameters, clientId, scope, grantType);

                    try {
                        OAuth2AccessToken accessToken = tokenServices.refreshAccessToken(refreshToken, tokenRequest);
                        filteredReq.addParameter(SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessToken.getValue()});
                        CookieUtil.addCookie(httpServletResp, ACCESS_TOKEN_COOKIE_NAME, accessToken.getValue(), ACCESS_TOKEN_EXPIRES_IN_SECONDS);
                    } catch (AuthenticationException ex) {
                        CookieUtil.addCookie(httpServletResp, REFRESH_TOKEN_COOKIE_NAME, null, 0);
                        LOG.error("Incorrect request token. " + LogUtil.getExceptionStackTrace(ex));
                        throw ex;
                    }
                }
            }
        }

        chain.doFilter(filteredReq, httpServletResp);
    }

    @Override
    public void destroy() {

    }
}
