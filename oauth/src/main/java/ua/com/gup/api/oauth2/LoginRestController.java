package ua.com.gup.api.oauth2;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.config.oauth2.TokenStoreService;
import ua.com.gup.dto.ProfileInfo;
import ua.com.gup.exception.VerificationTokenExpiredException;
import ua.com.gup.exception.VerificationTokenNotFoundException;
import ua.com.gup.model.login.FormChangePassword;
import ua.com.gup.model.login.FormLoggedUser;
import ua.com.gup.model.login.LoggedUser;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.model.profiles.UserType;
import ua.com.gup.model.profiles.verification.VerificationToken;
import ua.com.gup.model.profiles.verification.VerificationTokenType;
import ua.com.gup.service.emailnotification.EmailService;
import ua.com.gup.service.emailnotification.EmailServiceTokenModel;
import ua.com.gup.service.event.OnForgetPasswordEvent;
import ua.com.gup.service.event.OnInitialRegistrationByEmailEvent;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.service.login.UserDetailsServiceImpl;
import ua.com.gup.service.profile.LockRemoteIPService;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.service.profile.VerificationTokenService;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.util.APIVendor;
import ua.com.gup.util.CookieUtil;
import ua.com.gup.util.Oauth2Util;
import ua.com.gup.util.SecurityOperations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/oauth")
public class LoginRestController {
    private final static Logger LOG = Logger.getLogger(LoginRestController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ProfilesService profilesService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private APIVendor profileVendor;

    @Autowired
    private StorageService storageService;

    @Autowired
    private EmailService emailService;


    @Autowired
    private LockRemoteIPService lockRemoteIPService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private TokenStoreService tokenStoreService;


    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> register(@RequestBody @Validated Profile profile) {

        // email exists check:
        if (!profilesService.profileExistsWithEmail(profile.getEmail())) {
            // REGISTER:
            if (profile.getSocWendor() == null) {
                profile.setSocWendor("GUP");
            }
            profile.setUserType(UserType.LEGAL_ENTITY);
            profile.setActive(false);
            profilesService.createProfile(profile);
            eventPublisher.publishEvent(new OnInitialRegistrationByEmailEvent(profile));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @RequestMapping(value = "/registerConfirm", method = RequestMethod.GET)
    public ResponseEntity<ProfileInfo> registerConfirm(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new VerificationTokenNotFoundException();
        }
        if (verificationToken.getExpiryDate().before(new Date())) {
            throw new VerificationTokenExpiredException();
        }
        Profile profile = profilesService.findById(verificationToken.getUserId());
        if (!Boolean.TRUE.equals(profile.getActive())) {
            profile.setActive(Boolean.TRUE);
            profilesService.editProfile(profile);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //ToDo hide this controller under @PreAuthorize annotation

    /**
     * Controller for registration new users under admin panel.
     *
     * @param profile the Profile object with email, password and role.
     * @return status '200' (Ok) if user was created; return status '409' (Conflict) if user with current email already exist.
     */
    @CrossOrigin
    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> registerForAdminPanel(@RequestBody Profile profile) {
        // CHECK:
        if (!profilesService.profileExistsWithEmail(profile.getEmail())) {

            // REGISTER:
            if (profile.getSocWendor() == null) {
                profile.setSocWendor("GUP");
            }
            profile.setUserType(UserType.LEGAL_ENTITY);
            profile.setActive(false);
            profilesService.createProfileWithRoles(profile);
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
        if (!profilesService.profileExistsWithUidAndWendor(profile.getUid(), profile.getSocWendor())) {
            // REGISTER:
            profilesService.facebookRegister(profile);
            // EDIT:
            profileVendor.init(profile.getSocWendor(), profile.getTokenKey(), profile.getUid());
            /* Edit Photo Profile */
            profile.setImgUrl(profileVendor.getImage().get("url"));
            /* Edit Profile */
            profile.setEmail(profileVendor.getEmail());
            profile.setUsername(profileVendor.getUsername());
            String[] fullname = profileVendor.getName().split(" ");
            profile.setFirstname(fullname[0]);
            profile.setLastname(fullname[1]);
            profile.setActive(true);
            profilesService.editProfile(profile);
            // LOGIN:
            LoggedUser loggedUser = null;
            try {
                loggedUser = (LoggedUser) userDetailsService.loadUserByUidAndVendor(profile.getUid(), profile.getSocWendor());
            } catch (UsernameNotFoundException ex) {
                resp = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            authenticateByUidAndToken(loggedUser, profile.getSocWendor(), response); //TODO: fix collizion
            ProfileInfo profileInfo = profilesService.findPrivateProfileByUidAndUpdateLastLoginDate(profile.getUid(), profile.getSocWendor());
            Profile getProfile = profileInfo.getProfile();
            //getProfile.setRefreshToken(authenticateByUidAndToken(loggedUser, getProfile.getSocWendor(), response));
            profileInfo.setProfile(getProfile);
            resp = new ResponseEntity<>(profileInfo, HttpStatus.OK);
        } else {
            resp = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return resp;
    }


    @CrossOrigin
    @RequestMapping(value = "/register-by-phone", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> registerByPhone(@RequestBody Profile profile, HttpServletResponse response) {
        ResponseEntity<ProfileInfo> resp = null;
        // CHECK:
        if (!profilesService.profileExistsWithMainPhoneNumber(profile.getMainPhoneNumber())) {
            // REGISTER:
            if (profile.getSocWendor() == null) {
                profile.setSocWendor("GUP");
            }
            profile.setUserType(UserType.LEGAL_ENTITY);
            profile.setActive(false);
            profilesService.createProfile(profile);
            // LOGIN:
            LoggedUser loggedUser = null;
            try {
                loggedUser = (LoggedUser) userDetailsService.loadUserByPhoneNumberdAndVendor(profile.getMainPhoneNumber(), profile.getSocWendor());
                if (!passwordEncoder.matches(profile.getPassword(), loggedUser.getPassword())) {
                    resp = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                //////////////////////////////////////////////////////////////////////////////////////////
                authenticateByPhoneAndPassword(loggedUser, profile.getMainPhoneNumber(), response);
                ProfileInfo profileInfo = profilesService.findPrivateProfileByPhoneNumberdAndUpdateLastLoginDate(profile.getMainPhoneNumber(), profile.getSocWendor());

                resp = new ResponseEntity<>(profileInfo, HttpStatus.OK);
            } catch (UsernameNotFoundException ex) {
                resp = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            resp = new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return resp;
    }


    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody FormLoggedUser formLoggedUser,
                                HttpServletResponse response) {
        ProfileInfo profileInfo = null;
        synchronized (profilesService) {
            LoggedUser loggedUser;
            try {

                //tempory delete block user for login
                /*LockRemoteIP lockRemoteIP = lockRemoteIPService.findLockRemoteIPByIp(request.getRemoteAddr());
                if (lockRemoteIP == null) {
                    lockRemoteIP = new LockRemoteIP();
                    lockRemoteIP.setIp(request.getRemoteAddr());
                    lockRemoteIPService.createLockRemoteIP(lockRemoteIP);
                }
                if (!lockRemoteIPService.findLockRemoteIPByIpAndUpdateLastTryLoginDate(request.getRemoteAddr())) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }*/

                loggedUser = (LoggedUser) userDetailsService.loadUserByUsername(formLoggedUser.getEmail());
            } catch (UsernameNotFoundException ex) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (!loggedUser.isEnabled()) {
                return new ResponseEntity<>("User is not active yet", HttpStatus.FORBIDDEN);
            }

            if (!passwordEncoder.matches(formLoggedUser.getPassword(), loggedUser.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(formLoggedUser.getEmail());
            if (profileInfo.getProfile().isBan())
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            if (!SecurityUtils.isAuthenticated()) {
                LOG.debug("----------- user isAuthenticated  :=  " + SecurityUtils.isAuthenticated());
                authenticateByEmailAndPassword(loggedUser, response);
            }
        }

        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/soc-login", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> vendorLogin(@RequestBody Profile profile,
                                                   HttpServletResponse response) {
        if (!profilesService.profileExistsWithUidAndWendor(profile.getUid(), profile.getSocWendor()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        LoggedUser loggedUser;
        try {
            /*LockRemoteIP lockRemoteIP = lockRemoteIPService.findLockRemoteIPByIp(request.getRemoteAddr());
            if (lockRemoteIP == null) {
                lockRemoteIP = new LockRemoteIP();
                lockRemoteIP.setIp(request.getRemoteAddr());
                lockRemoteIPService.createLockRemoteIP(lockRemoteIP);
            }
            if (!lockRemoteIPService.findLockRemoteIPByIpAndUpdateLastTryLoginDate(request.getRemoteAddr())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }*/

            loggedUser = (LoggedUser) userDetailsService.loadUserByUidAndVendor(profile.getUid(), profile.getSocWendor());
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProfileInfo profileInfo = profilesService.findPrivateProfileByUidAndUpdateLastLoginDate(profile.getUid(), profile.getSocWendor());
        if (profileInfo.getProfile().isBan())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        //Profile getProfile = profileInfo.getProfile();
        //getProfile.setRefreshToken(authenticateByUidAndToken(loggedUser, getProfile.getSocWendor(), response));
        //profileInfo.setProfile(getProfile);,
        authenticateByUidAndToken(loggedUser, profileInfo.getProfile().getSocWendor(), response);
        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/phone-login", method = RequestMethod.POST)
    public ResponseEntity<ProfileInfo> phoneLogin(@RequestBody Profile profile, HttpServletResponse response) {
        if (profilesService.findProfileByPhoneNumberAndWendor(profile.getMainPhoneNumber(), profile.getSocWendor()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        LoggedUser loggedUser;
        try {
            /* Edit Profile */
            loggedUser = (LoggedUser) userDetailsService.loadUserByPhoneNumberdAndVendor(profile.getMainPhoneNumber(), profile.getSocWendor());
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProfileInfo profileInfo = profilesService.findPrivateProfileByPhoneNumberdAndUpdateLastLoginDate(profile.getMainPhoneNumber(), profile.getSocWendor());
        if (profileInfo.getProfile().isBan())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        authenticateByPhoneAndPassword(loggedUser, profile.getMainPhoneNumber(), response);

        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<String> success = new ResponseEntity<>(HttpStatus.OK);
        if (request.getCookies() == null) {
            Cookie cookieAuthToken = new Cookie("authToken", null);
            cookieAuthToken.setMaxAge(0);
            cookieAuthToken.setPath("/");
            response.addCookie(cookieAuthToken);

            Cookie cookieRefreshToken = new Cookie("refreshToken", null);
            cookieRefreshToken.setMaxAge(0);
            cookieRefreshToken.setPath("/");
            response.addCookie(cookieRefreshToken);
            return success;
        }


        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("authToken")) {
                LOG.info("authToken remove : " + cookie.getValue());
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

        return success;
    }

    @CrossOrigin
    @RequestMapping(value = "/login/checkEmail", method = RequestMethod.POST)
    public ResponseEntity existEmailCheck(@RequestBody String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            return new ResponseEntity<>("email is invalid", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity entity = new ResponseEntity(profilesService.profileExistsWithEmail(email) ? Boolean.TRUE : Boolean.FALSE, HttpStatus.OK);
        return (entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity resetPasswordByToken(@RequestParam("token") String token,
                                               @RequestBody @Validated FormChangePassword fcp) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new VerificationTokenNotFoundException();
        }
        if (verificationToken.getExpiryDate().before(new Date())) {
            throw new VerificationTokenExpiredException();
        }

        Profile profile = profilesService.findById(verificationToken.getUserId());
        profile.setPassword(passwordEncoder.encode(fcp.getNewPassword()));
        profilesService.editProfile(profile);

        Collection<OAuth2AccessToken> tokensByClientId = tokenStoreService.findAccessTokensByUserName(profile.getId());
        for (OAuth2AccessToken oAuth2AccessToken : tokensByClientId) {
            tokenStoreService.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
            tokenStoreService.removeAccessToken(oAuth2AccessToken);
        }

        verificationTokenService.deleteToken(verificationToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin
    @RequestMapping(value = "/restore-password", method = RequestMethod.POST)
    public ResponseEntity restorePassword(@RequestParam("email") String email) {
        if (StringUtils.isEmpty(email)) {
            return new ResponseEntity<>("email is empty", HttpStatus.BAD_REQUEST);
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            return new ResponseEntity<>("email is invalid", HttpStatus.BAD_REQUEST);
        }
        Profile profile = profilesService.findProfileByEmail(email);
        if (profile != null) {
            eventPublisher.publishEvent(new OnForgetPasswordEvent(profile));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("User with email: %s not found", email), HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<String> changePassword(@RequestBody @Validated FormChangePassword formChangePassword,
                                                 HttpServletRequest request, HttpServletResponse response) {
        Profile profile = profilesService.findWholeProfileById(SecurityOperations.getLoggedUser().getProfileId());
        /* Edit Profile | change password */
        if (passwordEncoder.matches(formChangePassword.getPassword(), profile.getPassword())) {
            profile.setPassword(passwordEncoder.encode(formChangePassword.getNewPassword()));
            profilesService.editProfile(profile);
            emailService.sendLostPasswordEmail(new EmailServiceTokenModel(profile.getEmail(), "",
                    VerificationTokenType.LOST_PASSWORD, formChangePassword.getNewPassword()));

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

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    private void authenticateByEmailAndPassword(User user, HttpServletResponse response) {

        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

        CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);

    }


    private String authenticateByUidAndToken(User user, String socWendor, HttpServletResponse response) {
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, socWendor, user.getAuthorities()); // "password":socWendor
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

        CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);

        return oAuth2AccessToken.getRefreshToken().getValue();
    }

    private void authenticateByPhoneAndPassword(User user, String phone, HttpServletResponse response) {
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, phone, user.getAuthorities());
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

        CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
    }


}
