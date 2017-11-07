package ua.com.gup.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ua.com.gup.util.CookieUtil;
import ua.com.gup.util.LogUtil;
import ua.com.gup.util.Oauth2Util;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DefaultTokenServices tokenServices;

    @Override
    public void init(FilterConfig config) throws ServletException {
        ApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        this.tokenServices = appCtx.getBean(DefaultTokenServices.class);
        logger.debug("-------------tokenService-------------------- {}", tokenServices);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        CustomParametersHttpServletRequest customParametersHttpServletRequest = new CustomParametersHttpServletRequest(request);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            authenticateByTokensFromCookies(customParametersHttpServletRequest, request, response, cookies);
        }

        chain.doFilter(customParametersHttpServletRequest, response);
    }

    private void authenticateByTokensFromCookies(CustomParametersHttpServletRequest customParametersHttpServletRequest, HttpServletRequest request, HttpServletResponse response, Cookie[] cookies) {
        String accessTokenValue = CookieUtil.getCookieValue(cookies, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME);
        String refreshTokenValue = CookieUtil.getCookieValue(cookies, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME);

        if (!accessTokenValue.isEmpty()) {
            if (isExistAccessToken(accessTokenValue)) {
                customParametersHttpServletRequest.addParameter(Oauth2Util.SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessTokenValue});
            } else {
                logger.error(" Incorrect access token [" + accessTokenValue + "]");
                CookieUtil.removeCookie(request, response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME);
                authenticateByRefreshToken(customParametersHttpServletRequest, request, response, refreshTokenValue);
            }
        } else if (!refreshTokenValue.isEmpty()) {
            authenticateByRefreshToken(customParametersHttpServletRequest, request, response, refreshTokenValue);
        }
    }

    private boolean isExistAccessToken(String accessTokenValue) {
        return (null != tokenServices.readAccessToken(accessTokenValue));
    }

    private void authenticateByRefreshToken(CustomParametersHttpServletRequest customParametersHttpServletRequest, HttpServletRequest request, HttpServletResponse response, String refreshTokenValue) {
        try {
            OAuth2AccessToken accessToken = tokenServices.refreshAccessToken(refreshTokenValue, Oauth2Util.getTokenRequest());
            customParametersHttpServletRequest.addParameter(Oauth2Util.SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessToken.getValue()});
            CookieUtil.addCookie(request, response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, accessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        } catch (Exception ex) {
            CookieUtil.removeCookie(request, response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME);
            logger.error(" Incorrect refresh token. " + LogUtil.getExceptionStackTrace(ex));
        }
    }

    @Override
    public void destroy() {
    }
}
