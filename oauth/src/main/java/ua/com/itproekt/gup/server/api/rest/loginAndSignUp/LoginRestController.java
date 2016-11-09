package ua.com.itproekt.gup.server.api.rest.loginAndSignUp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ua.com.itproekt.gup.model.login.FormChangePassword;
import ua.com.itproekt.gup.model.login.FormLoggedUser;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.verification.VerificationTokenType;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;
import ua.com.itproekt.gup.dto.ProfileInfo;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.emailnotification.EmailServiceTokenModel;
import ua.com.itproekt.gup.service.emailnotification.MailSenderService;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.login.UserDetailsServiceImpl;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/oauth")
public class LoginRestController {
    private final static Logger LOG = Logger.getLogger(LoginRestController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    ActivityFeedService activityFeedService;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    APIVendor profileVendor;

    @Autowired
    StorageService storageService;

    @Autowired
    private MailSenderService mailSenderService;


    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> register(@RequestBody Profile profile, HttpServletResponse response) {
        ResponseEntity<ProfileInfo> resp = null;

        // CHECK:
        if( !profilesService.profileExistsWithEmail(profile.getEmail()) ){

            // REGISTER:
            if( profile.getSocWendor()==null )
                profile.setSocWendor("GUP");
            profilesService.createProfile(profile);
            verificationTokenService.sendEmailRegistrationToken(profile.getId());

            // LOGIN:
            LoggedUser loggedUser = null;
            try {
                loggedUser = (LoggedUser) userDetailsService.loadUserByUsername(profile.getEmail());
                if (!passwordEncoder.matches(profile.getPassword(), loggedUser.getPassword())) {
                    resp = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                authenticateByEmailAndPassword(loggedUser, response);
                ProfileInfo profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(profile.getEmail());
                resp = new ResponseEntity<>(profileInfo, HttpStatus.OK);
            } catch (UsernameNotFoundException ex) {
                resp = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            resp = new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return resp;
    }




    //ToDo hide this controller under @PreAuthorize annotation
    /**
     * Controller for registration new users under admin panel.
     * @param profile   the Profile object with email, password and role.
     * @return          status 2oo (Ok) if user was created; return status 409 if user with current email already exist.
     */
    @CrossOrigin
    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> registerForAdminPanel(@RequestBody Profile profile) {


        // CHECK:
        if( !profilesService.profileExistsWithEmail(profile.getEmail()) ){

            // REGISTER:
            if( profile.getSocWendor()==null )
                profile.setSocWendor("GUP");
            profilesService.createProfileWithRoles(profile);
//            verificationTokenService.sendEmailRegistrationToken(profile.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/soc-register", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> vendorRegister(@RequestBody Profile profile, HttpServletResponse response) {
        ResponseEntity<ProfileInfo> resp = null;

        // CHECK:
        if( !profilesService.profileExistsWithUidAndWendor(profile.getUid(), profile.getSocWendor()) ){

            // REGISTER:
            profilesService.facebookRegister(profile);

            // EDIT:
            // TODO try {
                profileVendor.init(profile.getSocWendor(), profile.getTokenKey(), profile.getUid());
                /* Edit Photo Profile */
                // TODO String imgId = storageService.saveCachedImageProfile( getCachedImageProfile("profile", getImageProfile(profileVendor.getImage().get("url")), "profile_" + profile.getId()) );
                // TODO profile.setImgId(imgId);
                profile.setImgUrl(profileVendor.getImage().get("url"));
                /* Edit Profile */
            profile.setUsername(profileVendor.getName()); //TODO: fix-change 'nickname' on 'name'
                profilesService.editProfile(profile);
            // TODO } catch (NullPointerException e) {
            // TODO     LOG.error(LogUtil.getExceptionStackTrace(e));
            // TODO     resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            // TODO } catch (IOException e) {
            // TODO     LOG.error(LogUtil.getExceptionStackTrace(e));
            // TODO     resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            // TODO }

            // LOGIN:
            LoggedUser loggedUser = null;
            try {
                loggedUser = (LoggedUser) userDetailsService.loadUserByUidAndVendor(profile.getUid(), profile.getSocWendor());
            } catch (UsernameNotFoundException ex) {
                resp = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            authenticateByUidAndToken(loggedUser, profile.getSocWendor(), response); //TODO: fix collizion
            ProfileInfo profileInfo = profilesService.findPrivateProfileByUidAndUpdateLastLoginDate(profile.getUid(), profile.getSocWendor());

            resp = new ResponseEntity<>(profileInfo, HttpStatus.OK);
        } else {
            resp = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return resp;
    }

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
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public String changePassword(@RequestBody FormChangePassword formChangePassword, HttpServletRequest request, HttpServletResponse response) {
        Profile profile = profilesService.findWholeProfileById(SecurityOperations.getLoggedUser().getProfileId());

        /* Edit Profile | change password */
        if( passwordEncoder.matches(formChangePassword.getPassword(),profile.getPassword()) ){
            profile.setPassword(passwordEncoder.encode(formChangePassword.getNewPassword()));
            profilesService.editProfile(profile);
            mailSenderService.sendLostPasswordEmail(new EmailServiceTokenModel(profile.getEmail(), "", VerificationTokenType.LOST_PASSWORD, formChangePassword.getNewPassword()));

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

            return "redirect:/login";
        }
        return String.valueOf(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @RequestMapping(value = "/soc-login", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo>vendorLogin(@RequestBody Profile profile, HttpServletResponse response) {
        if( !profilesService.profileExistsWithUidAndWendor(profile.getUid(), profile.getSocWendor()) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        LoggedUser loggedUser;
        try {
            /* Edit Profile */
//            profileVendor.init(profile.getSocWendor(), profile.getTokenKey(), profile.getUid());
//            Profile profileEdit = profilesService.findPrivateProfileByUidAndUpdateLastLoginDate(profile.getUid(), profile.getSocWendor()).getProfile();
////            profileEdit.setUsername(profileVendor.getUsername());
////            profileEdit.setImgUrl(profileVendor.getImage().get("url"));
//            profilesService.editProfile(profileEdit);

            loggedUser = (LoggedUser) userDetailsService.loadUserByUidAndVendor(profile.getUid(), profile.getSocWendor());
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authenticateByUidAndToken(loggedUser, profile.getSocWendor(), response); //TODO: fix collizion

        ProfileInfo profileInfo = profilesService.findPrivateProfileByUidAndUpdateLastLoginDate(profile.getUid(), profile.getSocWendor());
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


    private void authenticateByEmailAndPassword(User user, HttpServletResponse response) {
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

        CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
    }

    private void authenticateByUidAndToken(User user, String socWendor, HttpServletResponse response) {
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, socWendor, user.getAuthorities()); // "password":socWendor
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

        CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
    }

    private InputStream getImageProfile(String url) throws IOException {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        ResponseEntity<byte[]> profileImg = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        return new ByteArrayInputStream(profileImg.getBody());
    }

    private FileUploadWrapper getCachedImageProfile(String serviceName, InputStream img, String filename) throws IOException {
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();
        fileUploadWrapper
                .setServiceName(serviceName.toUpperCase())
                .setInputStream(img)
                .setContentType("")
                .setFilename(filename);
        return fileUploadWrapper;
    }

}
