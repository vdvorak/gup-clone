package ua.com.itproekt.gup.server.api.rest.loginAndSignUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.login.FormLostPassword;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.dto.ProfileInfo;
import ua.com.itproekt.gup.service.login.UserDetailsServiceImpl;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.util.CookieUtil;
import ua.com.itproekt.gup.util.Oauth2Util;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Controller
@PropertySource("classpath:properties/lost_password.properties")
@RequestMapping("/api/oauth")
public class LostPasswordRestController {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    Environment env;

    private final String restorePasswordURL = "reset-password";

    /**
     * #1 Generate new client password
     *    Then generate a link to reset your password and send it to the client email
     *
     * @param email
     * @return http://gup.com.ua/restorePassword/57ac39364c8e87290e6bb02e/VtwIq8aE1U85GKYp3THCLE5nncyR2HStaxv6RoWn
     */
    @CrossOrigin
    @RequestMapping(value = "/send-lost-password-email", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendLostPasswordEmail(@RequestBody String email) {
        Profile profile = null;
        String secret = generateSecret();
        if( profilesService.profileExistsWithEmail(email) ){
            profile = profilesService.findProfileByEmail(email);
            String hashedPassword = passwordEncoder.encode(secret);
            profile.setPassword(hashedPassword);
            profilesService.editProfile(profile);
            // ToDo send to e-mail
            return new ResponseEntity<>(HttpStatus.OK); //return new ResponseEntity<>(generateURLRecovery(env.getProperty("lost_password.domain.url"), profile.getId(), secret), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * #2 According to information received from the client the link go to the site file (it automatically client authentication)
     *    Then the customer receives the right to edit your password
     *    http://gup.com.ua/restorePassword/{id}/{secret}
     * **************************************************
     * @param id
     * @param secret
     * @param response
     * @return
     */
//    @CrossOrigin
//    @RequestMapping(value = "/" + restorePasswordURL + "/{id}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ProfileInfo> resetPassword(@PathVariable String id, @RequestParam String secret, HttpServletResponse response) { //public ResponseEntity<String> resetPassword(@PathVariable String id, @RequestParam String secret, HttpServletResponse response) {
//        LoggedUser loggedUser;
//        Profile profile = null;
//        if( profilesService.profileExists(id) ){
//            profile = profilesService.findById(id);
//            try {
//                loggedUser = (LoggedUser) userDetailsService.loadUserByUsername(profile.getEmail());
//                if( passwordEncoder.matches(secret,loggedUser.getPassword()) ){
//                    authenticateByEmailAndPassword(loggedUser, response);
//                } else {
//                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//                }
//            } catch (UsernameNotFoundException ex) {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        ProfileInfo profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(profile.getEmail());
//        return new ResponseEntity<>(profileInfo, HttpStatus.OK); //return new ResponseEntity<>("redirect:/api/oauth/" + restorePasswordURL + "/" + id, HttpStatus.OK);
//    }

    /**
     * #3 Here the client can edit, change the password to a new Statement
     *    http://localhost:8083/api/oauth/reset-password
     *
     * @param secret
     * @return
     */
//    @CrossOrigin
//    @RequestMapping(value = "/" + restorePasswordURL, method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ProfileInfo> restorePassword(@RequestBody String secret) {
//        String id = SecurityOperations.getLoggedUserId();
//        Profile profile = null;
//        if( profilesService.profileExists(id) ){
//            profile = profilesService.findById(id);
//            String hashedPassword = passwordEncoder.encode(secret);
//            profile.setPassword(hashedPassword);
//            profilesService.editProfile(profile);
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        ProfileInfo profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(profile.getEmail());
//        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
//    }
    /**
     * #3 Here the client can edit, change the password to a new Statement
     *    http://localhost:8083/api/oauth/reset-password
     *
     * @param formLostPassword  { "id":"???","password":"???","newPassword":"???" }
     * @param response
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/" + restorePasswordURL, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileInfo> restorePassword(@RequestBody FormLostPassword formLostPassword, HttpServletResponse response) {
        LoggedUser loggedUser;
        Profile profile = null;
        if( profilesService.profileExists(formLostPassword.getId()) ){
            profile = profilesService.findById(formLostPassword.getId());
            try {
                loggedUser = (LoggedUser) userDetailsService.loadUserByUsername(profile.getEmail());
                if( passwordEncoder.matches(formLostPassword.getPassword(),loggedUser.getPassword()) ){
                    String hashedNewPassword = passwordEncoder.encode(formLostPassword.getNewPassword());
                    profile.setPassword(hashedNewPassword);
                    profilesService.editProfile(profile);
                    authenticateByEmailAndPassword(loggedUser, response);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch (UsernameNotFoundException ex) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ProfileInfo profileInfo = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(profile.getEmail());
        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }


    private void authenticateByEmailAndPassword(User user, HttpServletResponse response) {
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
        OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

        CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
        CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
    }

    /**
     * @param domain
     * @param id
     * @param secret
     * @return http://gup.com.ua/restorePassword/{id}/{secret}
     */
    private String generateURLRecovery(String domain, String id, String secret){
//        return "http://" + domain + "/api/oauth/" + restorePasswordURL + "/" + id + "?secret=" + secret;
        return "http://" + domain + "/" + env.getProperty("lost_password.restore_password_form.port.url") + "/" + id + "/" + secret;
    }

    private String generateSecret(){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i=0; i<40; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

}
