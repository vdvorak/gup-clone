package ua.com.itproekt.gup.server.api.rest.loginAndSignUp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.login.FormLoggedUser;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.server.api.rest.profiles.dto.ProfileInfo;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.util.CookieUtil;
import ua.com.itproekt.gup.util.LogUtil;
import ua.com.itproekt.gup.util.Oauth2Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/oauth")
public class LoginRestController {
    private final static Logger LOG = Logger.getLogger(LoginRestController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    VerificationTokenService verificationTokenService;
    @Autowired
    ActivityFeedService activityFeedService;
    @Autowired
    private DefaultTokenServices tokenServices;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> login(@RequestBody FormLoggedUser formLoggedUser, HttpServletResponse response) {
        LoggedUser loggedUser;
        try {
            loggedUser = (LoggedUser) userDetailsService.loadUserByUsername(formLoggedUser.getEmail());
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!passwordEncoder.matches(formLoggedUser.getPassword(), loggedUser.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        authenticateByEmailAndPassword(loggedUser, response);

        ProfileInfo profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(formLoggedUser.getEmail());
        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        if (request.getCookies() == null) {
            Cookie cookieAuthToken = new Cookie("authToken", null);
            cookieAuthToken.setMaxAge(0);
            cookieAuthToken.setPath("/");
            response.addCookie(cookieAuthToken);

            Cookie cookieRefreshToken = new Cookie("refreshToken", null);
            cookieRefreshToken.setMaxAge(0);
            cookieRefreshToken.setPath("/");
            response.addCookie(cookieRefreshToken);
            return "redirect:/index";
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("authToken")) {
                tokenServices.revokeToken(cookie.getValue());
            }
        }

        Cookie cookieAuthToken = new Cookie("authToken", null);
        cookieAuthToken.setMaxAge(0);
        cookieAuthToken.setPath("/");
        response.addCookie(cookieAuthToken);

        Cookie cookieRefreshToken = new Cookie("refreshToken", null);
        cookieRefreshToken.setMaxAge(0);
        cookieRefreshToken.setPath("/");
        response.addCookie(cookieRefreshToken);

        return "redirect:/index";
    }


    private void authenticateByEmailAndPassword(User user, HttpServletResponse response) {
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

        CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
    }

    @CrossOrigin
    @RequestMapping(value = "/login/checkEmail", method = RequestMethod.POST)
    public String existEmailCheck(@RequestBody String email) {
        email = email.split("=")[0];

        try {
            email = URLDecoder.decode(email, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LOG.error(LogUtil.getExceptionStackTrace(ex));
        }

        return (profilesService.profileExistsWithEmail(email)) ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody Profile profile) {
        if (profilesService.profileExistsWithEmail(profile.getEmail()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        profilesService.createProfile(profile);
        verificationTokenService.sendEmailRegistrationToken(profile.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/register-autologin", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> registerAutoLogin(@RequestBody Profile profile, HttpServletResponse response) {

        // CHECK_EMAIL:
        String email = profile.getEmail().split("=")[0];
        try {
            email = URLDecoder.decode(email, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LOG.error(LogUtil.getExceptionStackTrace(ex));
        }
        if( profilesService.profileExistsWithEmail(email) ){

            // REGISTER:
            if (profilesService.profileExistsWithEmail(profile.getEmail())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            profilesService.createProfile(profile);
            verificationTokenService.sendEmailRegistrationToken(profile.getId());

            // LOGIN:
            LoggedUser loggedUser;
            try {
                loggedUser = (LoggedUser) userDetailsService.loadUserByUsername(profile.getEmail());
            } catch (UsernameNotFoundException ex) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (!passwordEncoder.matches(profile.getPassword(), loggedUser.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            authenticateByEmailAndPassword(loggedUser, response);
            ProfileInfo profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(profile.getEmail());
            return new ResponseEntity<>(profileInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}