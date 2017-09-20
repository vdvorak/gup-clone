package ua.com.gup.config.filter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ua.com.gup.util.CookieUtil;
import ua.com.gup.bank_api.util.LogUtil;
import ua.com.gup.util.Oauth2Util;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(OAuthFilter.class);

    private DefaultTokenServices tokenServices;

    @Override
    public void init(FilterConfig config) throws ServletException {
        ApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        this.tokenServices = appCtx.getBean(DefaultTokenServices.class);
    }

    @Override
    public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest       httpServletReq = (HttpServletRequest) servletReq;
        HttpServletResponse     httpServletResp = (HttpServletResponse) servletResp;
        CustomParametersRequest customParamsReq = new CustomParametersRequest(httpServletReq);

        Cookie[] cookies = httpServletReq.getCookies();
        if (cookies != null) {
            authenticateByTokensFromCookies(customParamsReq, httpServletResp, cookies);
        }

        chain.doFilter(customParamsReq, httpServletResp);
    }

    private void authenticateByTokensFromCookies(CustomParametersRequest customParamsReq, HttpServletResponse httpServletResp, Cookie[] cookies) {
        String accessTokenValue = CookieUtil.getCookieValue(cookies, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME);
        String refreshTokenValue = CookieUtil.getCookieValue(cookies, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME);

        if (!accessTokenValue.isEmpty()) {
            if (isExistAccessToken(accessTokenValue)) {
                customParamsReq.addParameter(Oauth2Util.SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessTokenValue});
            } else {
                LOG.error(" Incorrect access token [" + accessTokenValue + "]");
                CookieUtil.removeCookie(httpServletResp, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME);
                authenticateByRefreshToken(customParamsReq, httpServletResp, refreshTokenValue);
            }
        } else if (!refreshTokenValue.isEmpty()) {
            authenticateByRefreshToken(customParamsReq, httpServletResp, refreshTokenValue);
        }
    }

    public boolean isExistAccessToken(String accessTokenValue) {
        return (null != tokenServices.readAccessToken(accessTokenValue));
    }

    private void authenticateByRefreshToken(CustomParametersRequest request, HttpServletResponse response, String refreshTokenValue) {
        try {
            OAuth2AccessToken accessToken = tokenServices.refreshAccessToken(refreshTokenValue, Oauth2Util.getTokenRequest());
            request.addParameter(Oauth2Util.SPRING_ACCESS_TOKEN_PARAM_NAME, new String[]{accessToken.getValue()});
            CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, accessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        } catch (Exception ex) {
            CookieUtil.removeCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME);
            LOG.error(" Incorrect refresh token. " + LogUtil.getExceptionStackTrace(ex));
        }
    }

    @Override
    public void destroy() {}
}
