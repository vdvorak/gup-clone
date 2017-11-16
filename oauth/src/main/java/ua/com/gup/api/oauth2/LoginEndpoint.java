//package ua.com.gup.api.oauth2;
//
//import org.apache.commons.validator.routines.EmailValidator;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import ua.com.gup.config.oauth2.GUPTokenStore;
//import ua.com.gup.dto.profile.ProfileDTO;
//import ua.com.gup.dto.profile.RegisterProfileDTO;
//import ua.com.gup.dto.profile.SocialLoginDTO;
//import ua.com.gup.event.OnForgetPasswordEvent;
//import ua.com.gup.event.OnInitialRegistrationByEmailEvent;
//import ua.com.gup.exception.VerificationTokenExpiredException;
//import ua.com.gup.exception.VerificationTokenNotFoundException;
//import ua.com.gup.mongo.composition.domain.profile.Profile;
//import ua.com.gup.mongo.composition.domain.verification.VerificationToken;
//import ua.com.gup.mongo.model.enumeration.UserRole;
//import ua.com.gup.mongo.model.enumeration.UserType;
//import ua.com.gup.mongo.model.enumeration.VerificationTokenType;
//import ua.com.gup.mongo.model.login.FormChangePassword;
//import ua.com.gup.mongo.model.login.LoggedUser;
//import ua.com.gup.service.emailnotification.EmailService;
//import ua.com.gup.service.emailnotification.EmailServiceTokenModel;
//import ua.com.gup.service.login.GupUserDetailsService;
//import ua.com.gup.service.profile.ProfilesService;
//import ua.com.gup.service.profile.VerificationTokenService;
//import ua.com.gup.util.APIVendor;
//import ua.com.gup.util.CookieUtil;
//import ua.com.gup.util.Oauth2Util;
//import ua.com.gup.util.SecurityOperations;
//import ua.com.gup.util.security.SecurityUtils;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Collection;
//import java.util.Date;
//
//@RestController
//@RequestMapping("/api")
//public class LoginEndpoint {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Autowired
//    private GupUserDetailsService userDetailsService;
//
//    @Autowired
//    private ProfilesService profilesService;
//
//    @Autowired
//    private VerificationTokenService verificationTokenService;
//
////    @Autowired
////    private DefaultTokenServices tokenServices;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private ApplicationEventPublisher eventPublisher;
//
////    @Autowired
////    private GUPTokenStore tokenStoreService;
//
//
//    @CrossOrigin
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<ProfileDTO> register(@RequestBody @Validated RegisterProfileDTO registerProfileDTO) {
//        // email exists check:
//        if (!profilesService.profileExistsWithEmail(registerProfileDTO.getEmail())) {
//            // REGISTER:
//            Profile profile = new Profile();
//            profile.setPassword(registerProfileDTO.getPassword());
//            profile.setEmail(registerProfileDTO.getEmail());
//            profile.setSocWendor("GUP");
//            profile.setUserType(UserType.LEGAL_ENTITY);
//            profile.setActive(false);
//            profile.getUserRoles().add(UserRole.ROLE_USER);
//            profilesService.createProfile(profile);
//            eventPublisher.publishEvent(new OnInitialRegistrationByEmailEvent(profile));
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.CONFLICT);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/register/confirm", method = RequestMethod.GET)
//    public ResponseEntity<ProfileDTO> registerConfirm(@RequestParam("token") String token) {
//        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
//        if (verificationToken == null) {
//            throw new VerificationTokenNotFoundException();
//        }
//        if (verificationToken.getExpiryDate().before(new Date())) {
//            throw new VerificationTokenExpiredException();
//        }
//        Profile profile = profilesService.findById(verificationToken.getUserId());
//        if (!Boolean.TRUE.equals(profile.getActive())) {
//            profile.setActive(Boolean.TRUE);
//            profilesService.editProfile(profile);
//        }
//        verificationTokenService.deleteToken(verificationToken);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/soc-register", method = RequestMethod.POST)
//    public ResponseEntity<ProfileDTO> vendorRegister(@RequestBody Profile profile,HttpServletRequest request ,HttpServletResponse response) {
//        // CHECK:
//        if (!profilesService.profileExistsWithUidAndWendor(profile.getUid(), profile.getSocWendor())) {
//            // REGISTER:
//            profilesService.facebookRegister(profile);
//            // EDIT:
//            APIVendor profileVendor = new APIVendor();
//            profileVendor.init(profile.getSocWendor(), profile.getTokenKey(), profile.getUid());
//            /* Edit Photo Profile */
//            profile.setImgUrl(profileVendor.getImage().get("url"));
//            /* Edit Profile */
//            profile.setEmail(profileVendor.getEmail());
//            profile.setUsername(profileVendor.getUsername());
//            String[] fullname = profileVendor.getName().split(" ");
//            profile.setFirstname(fullname[0]);
//            profile.setLastname(fullname[1]);
//            profile.setActive(true);
//            profilesService.editProfile(profile);
//            // LOGIN:
//            LoggedUser loggedUser = null;
//            try {
//                loggedUser = (LoggedUser) userDetailsService.loadUserByUidAndVendor(profile.getUid(), profile.getSocWendor());
//            } catch (UsernameNotFoundException ex) {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//            authenticateByUidAndToken(loggedUser, profile.getSocWendor(),request ,response); //TODO: fix collizion
//            ProfileDTO profileInfo = profilesService.findPrivateProfileDTOByUid(profile.getUid(), profile.getSocWendor());
//            return new ResponseEntity<>(profileInfo, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.CONFLICT);
//    }
//
//
//    @CrossOrigin
//    @RequestMapping(value = "/register-by-phone", method = RequestMethod.POST)
//    public ResponseEntity<ProfileDTO> registerByPhone(@RequestBody Profile profile,HttpServletRequest request, HttpServletResponse response) {
//        // CHECK:
//        if (!profilesService.profileExistsWithMainPhoneNumber(profile.getMainPhone().getPhoneNumber())) {
//            // REGISTER:
//            if (profile.getSocWendor() == null) {
//                profile.setSocWendor("GUP");
//            }
//            profile.setUserType(UserType.LEGAL_ENTITY);
//            profile.setActive(false);
//            profilesService.createProfile(profile);
//            // LOGIN:
//            try {
//                LoggedUser loggedUser = (LoggedUser) userDetailsService.loadUserByPhoneAndVendor(profile.getMainPhone().getPhoneNumber(), profile.getSocWendor());
//                if (!passwordEncoder.matches(profile.getPassword(), loggedUser.getPassword())) {
//                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//                }
//                authenticateByPhoneAndPassword(loggedUser, profile.getMainPhone().getPhoneNumber(),request, response);
//                ProfileDTO profileInfo = profilesService.findPrivateProfileDTOByPhoneNumberd(profile.getMainPhone().getPhoneNumber(), profile.getSocWendor());
//                return new ResponseEntity<>(profileInfo, HttpStatus.OK);
//            } catch (UsernameNotFoundException ex) {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.CONFLICT);
//    }
//
//
//    @CrossOrigin
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity login(@RequestBody RegisterProfileDTO registerProfileDTO,HttpServletRequest request,HttpServletResponse response ) throws Exception {
//
//        ProfileDTO profileInfo = null;
//        if (!SecurityUtils.isAuthenticated()) {
//            LoggedUser loggedUser = null;
//            try {
//                loggedUser = (LoggedUser) userDetailsService.loadUserByUsername(registerProfileDTO.getEmail());
//                if (!passwordEncoder.matches(registerProfileDTO.getPassword(), loggedUser.getPassword())) {
//                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//                }
//                if (!passwordEncoder.matches(registerProfileDTO.getPassword(), loggedUser.getPassword())) {
//                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//                }
//            } catch (UsernameNotFoundException ex) {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//            if (!loggedUser.isEnabled()) {
//                log.debug("User is not active yet");
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//            }
//            if (loggedUser.isBanned())
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//
//            profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(registerProfileDTO.getEmail());
//            authenticateByEmailAndPassword(loggedUser, request,response);
//        } else {
//            profileInfo = profilesService.getLoggedUser(request);
//        }
//        //  }
//
//        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/soc-login", method = RequestMethod.POST)
//    public ResponseEntity<ProfileDTO> vendorLogin(@RequestBody SocialLoginDTO socialLoginDTO,HttpServletRequest request, HttpServletResponse response) {
//        if (!profilesService.profileExistsWithUidAndWendor(socialLoginDTO.getUid(), socialLoginDTO.getSocWendor()))
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        LoggedUser loggedUser;
//        try {
//            loggedUser = (LoggedUser) userDetailsService.loadUserByUidAndVendor(socialLoginDTO.getUid(), socialLoginDTO.getSocWendor());
//        } catch (UsernameNotFoundException ex) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        if (loggedUser.isBanned())
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        ProfileDTO profileInfo = profilesService.findPrivateProfileDTOByUid(socialLoginDTO.getUid(), socialLoginDTO.getSocWendor());
//        authenticateByUidAndToken(loggedUser, socialLoginDTO.getSocWendor(),request ,response);
//        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/phone-login", method = RequestMethod.POST)
//    public ResponseEntity<ProfileDTO> phoneLogin(@RequestBody Profile profile,HttpServletRequest request, HttpServletResponse response) {
//        if (profilesService.findProfileByPhoneNumberAndWendor(profile.getMainPhone().getPhoneNumber(), profile.getSocWendor()) == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        LoggedUser loggedUser;
//        try {
//            /* Edit Profile */
//            loggedUser = (LoggedUser) userDetailsService.loadUserByPhoneAndVendor(profile.getMainPhone().getPhoneNumber(), profile.getSocWendor());
//        } catch (UsernameNotFoundException ex) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        if (loggedUser.isBanned())
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        ProfileDTO profileInfo = profilesService.findPrivateProfileDTOByPhoneNumberd(profile.getMainPhone().getPhoneNumber(), profile.getSocWendor());
//
//        authenticateByPhoneAndPassword(loggedUser, profile.getMainPhone().getPhoneNumber(),request ,response);
//
//        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
//        ResponseEntity<String> success = new ResponseEntity<>(HttpStatus.OK);
//        if (request.getCookies() == null) {
//            Cookie cookieAuthToken = new Cookie("authToken", null);
//            cookieAuthToken.setMaxAge(0);
//            cookieAuthToken.setPath("/");
//            response.addCookie(cookieAuthToken);
//
//            Cookie cookieRefreshToken = new Cookie("refreshToken", null);
//            cookieRefreshToken.setMaxAge(0);
//            cookieRefreshToken.setPath("/");
//            response.addCookie(cookieRefreshToken);
//            return success;
//        }
//
//
//        for (Cookie cookie : request.getCookies()) {
//            if (cookie.getName().equals("authToken")) {
//                log.info("authToken remove : " + cookie.getValue());
////                tokenServices.revokeToken(cookie.getValue());
//            }
//        }
//
//        Cookie cookieAuthToken = new Cookie("authToken", null);
//        cookieAuthToken.setMaxAge(0);
//        cookieAuthToken.setPath("/");
//        response.addCookie(cookieAuthToken);
//
//        Cookie cookieRefreshToken = new Cookie("refreshToken", null);
//        cookieRefreshToken.setMaxAge(0);
//        cookieRefreshToken.setPath("/");
//        response.addCookie(cookieRefreshToken);
//
//        return success;
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/login/checkEmail", method = RequestMethod.POST)
//    public ResponseEntity existEmailCheck(@RequestBody String email) {
//        if (!EmailValidator.getInstance().isValid(email)) {
//            return new ResponseEntity<>("invalid", HttpStatus.BAD_REQUEST);
//        }
//        ResponseEntity entity = new ResponseEntity(profilesService.profileExistsWithEmail(email) ? Boolean.TRUE : Boolean.FALSE, HttpStatus.OK);
//        return (entity);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
//    public ResponseEntity resetPasswordByToken(@RequestParam("token") String token,
//                                               @RequestBody @Validated FormChangePassword fcp) {
//        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
//        if (verificationToken == null) {
//            throw new VerificationTokenNotFoundException();
//        }
//        if (verificationToken.getExpiryDate().before(new Date())) {
//            throw new VerificationTokenExpiredException();
//        }
//
//        Profile profile = profilesService.findById(verificationToken.getUserId());
//        profile.setPassword(passwordEncoder.encode(fcp.getNewPassword()));
//        profilesService.editProfile(profile);
//
////        Collection<OAuth2AccessToken> tokensByClientId = ((GUPTokenStore) tokenStoreService).findAccessTokensByUserName(profile.getEmail());
////        for (OAuth2AccessToken oAuth2AccessToken : tokensByClientId) {
////            tokenStoreService.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
////            tokenStoreService.removeAccessToken(oAuth2AccessToken);
////        }
//
//        verificationTokenService.deleteToken(verificationToken);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    @CrossOrigin
//    @RequestMapping(value = "/password/restore", method = RequestMethod.POST)
//    public ResponseEntity restorePassword(@RequestParam("email") String email) {
//        if (StringUtils.isEmpty(email)) {
//            return new ResponseEntity<>("email is empty", HttpStatus.BAD_REQUEST);
//        }
//        if (!EmailValidator.getInstance().isValid(email)) {
//            return new ResponseEntity<>("email is invalid", HttpStatus.BAD_REQUEST);
//        }
//        Profile profile = profilesService.findProfileByEmail(email);
//        if (profile != null) {
//            eventPublisher.publishEvent(new OnForgetPasswordEvent(profile));
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(String.format("User with email: %s not found", email), HttpStatus.NOT_FOUND);
//    }
//
//    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
//    public ResponseEntity<String> changePassword(@RequestBody @Validated FormChangePassword formChangePassword,
//                                                 HttpServletRequest request, HttpServletResponse response) {
//        Profile profile = profilesService.findWholeProfileById(SecurityOperations.getLoggedUser().getProfileId());
//        /* Edit Profile | change password */
//        if (passwordEncoder.matches(formChangePassword.getPassword(), profile.getPassword())) {
//            profile.setPassword(passwordEncoder.encode(formChangePassword.getNewPassword()));
//            profilesService.editProfile(profile);
//            emailService.sendLostPasswordEmail(new EmailServiceTokenModel(profile.getEmail(), "",
//                    VerificationTokenType.LOST_PASSWORD, formChangePassword.getNewPassword()));
//
//            for (Cookie cookie : request.getCookies()) {
//                if (cookie.getName().equals("authToken")) {
////                    tokenServices.revokeToken(cookie.getValue());
//                }
//            }
//
//            Cookie cookieAuthToken = new Cookie("authToken", null);
//            cookieAuthToken.setMaxAge(0);
//            cookieAuthToken.setPath("/");
//            response.addCookie(cookieAuthToken);
//
//            Cookie cookieRefreshToken = new Cookie("refreshToken", null);
//            cookieRefreshToken.setMaxAge(0);
//            cookieRefreshToken.setPath("/");
//            response.addCookie(cookieRefreshToken);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }
//
//
//    private void authenticateByEmailAndPassword(User user, HttpServletRequest request, HttpServletResponse response) {
//
//        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
//
////        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);
////
////        CookieUtil.addCookie(request, response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
////        CookieUtil.addCookie(request, response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
//
//    }
//
//
//    private String authenticateByUidAndToken(User user, String socWendor, HttpServletRequest request, HttpServletResponse response) {
//        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, socWendor, user.getAuthorities()); // "password":socWendor
//        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
//
////        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);
////
////        CookieUtil.addCookie(request, response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
////        CookieUtil.addCookie(request, response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
//
//        return "";//oAuth2AccessToken.getRefreshToken().getValue();
//    }
//
//    private void authenticateByPhoneAndPassword(User user, String phone, HttpServletRequest request, HttpServletResponse response) {
//        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, phone, user.getAuthorities());
//        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
//
////        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);
////
////        CookieUtil.addCookie(request, response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
////        CookieUtil.addCookie(request, response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
//    }
//}