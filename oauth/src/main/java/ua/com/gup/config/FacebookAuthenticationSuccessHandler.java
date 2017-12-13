package ua.com.gup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.model.FacebookProfile;
import ua.com.gup.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class FacebookAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    @Qualifier("facebookRestTemplate")
    private OAuth2RestTemplate facebookRestTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    private RequestCache requestCache = new HttpSessionRequestCache();

    private UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com");
    private static final String FULL_PROFILE_FIELDS = "id,name,first_name,last_name,gender,locale,education,work,email,third_party_id,link,timezone,updated_time,verified,about,birthday,location,hometown,interested_in,religion,political,quotes,relationship_status,significant_other,website";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println(request.getPathInfo());
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());

        loadFacebookProfile();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request
                .getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);

            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private void loadFacebookProfile() {
        FacebookProfile facebookProfile = facebookRestTemplate.getForObject(builder.cloneBuilder().path("/me").queryParam("fields", FULL_PROFILE_FIELDS).toUriString(), FacebookProfile.class);
        HashMap facebookProfileImageSmall = facebookRestTemplate.getForObject(builder.cloneBuilder().path("/me/picture")
                .queryParam("redirect", false)
                .queryParam("type", "small")
                .toUriString(), HashMap.class);
        if (facebookProfileImageSmall != null && facebookProfileImageSmall.size() == 1) {
            HashMap data = (HashMap) facebookProfileImageSmall.get("data");
            String smallImageUrl = (String) data.get("url");
            if (!StringUtils.isEmpty(smallImageUrl)) {
                facebookProfile.setImageUrl(smallImageUrl);
            }
        }

        userService.facebookLogin(facebookProfile);
        authenticateUserManually(facebookProfile);
    }

    private void authenticateUserManually(FacebookProfile facebookProfile) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(facebookProfile.getEmail());
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));

    }
}

