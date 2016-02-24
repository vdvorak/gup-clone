package ua.com.itproekt.gup.api.rest.loginAndSignUp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@RestController
public class LoginRestController {
	private Logger logger = Logger.getLogger(LoginRestController.class);

	private final int ACCESS_TOKEN_EXPIRES_IN_SECONDS = (int)TimeUnit.MINUTES.toSeconds(10);
	private final int REFRESH_TOKEN_EXPIRES_IN_SECONDS = (int)TimeUnit.DAYS.toSeconds(30);

	@Autowired
	ProfilesService profileService;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	UserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestParam String email, @RequestParam String password,
									  HttpServletResponse response) {

		Map<String, String> requestParameters = new HashMap<>();
		String clientId = "7b5a38705d7b3562655925406a652e32";
		Set<String> scope = new HashSet<>();
		OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId, null, true,
				scope, null, null, null, null);

		LoggedUser loggedUser = null;
		try {
			loggedUser = (LoggedUser)userDetailsService.loadUserByUsername(email);
		} catch (UsernameNotFoundException ex) {
			StringWriter stack = new StringWriter();
			ex.printStackTrace(new PrintWriter(stack));
			logger.debug(stack.toString());

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (!passwordEncoder.matches(password, loggedUser.getPassword())) {
			logger.debug("Password doesn't match: email [" + email + "]");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(loggedUser,
				loggedUser.getPassword(), loggedUser.getAuthorities());
		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
		OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

		System.err.println("oAuth2AccessToken.getExpiration()" + oAuth2AccessToken.getExpiration());


		Cookie cookieAuthToken = new Cookie("authToken", oAuth2AccessToken.getValue());
		cookieAuthToken.setMaxAge(ACCESS_TOKEN_EXPIRES_IN_SECONDS);
		cookieAuthToken.setPath("/");
		response.addCookie(cookieAuthToken);

		Cookie cookieRefreshToken = new Cookie("refreshToken", oAuth2AccessToken.getRefreshToken().getValue());
		cookieRefreshToken.setMaxAge(REFRESH_TOKEN_EXPIRES_IN_SECONDS);
		cookieRefreshToken.setPath("/");
		response.addCookie(cookieRefreshToken);

		logger.debug("Login: profile {email : " + email + "}");

		return new ResponseEntity<>(HttpStatus.OK);
	}

//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
//			Authenticate the user
//		Authentication authentication = authenticationManager.authenticate(authRequest);
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		securityContext.setAuthentication(authentication);
//
//			Create a new session and add the security context.
//		HttpSession session = request.getSession(true);
//		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);


/*--------------------------------------- Check -----------------------------------------------------------------*/
	@RequestMapping(value = "/login/checkEmail", method = RequestMethod.POST)
	public String existEmailCheck(@RequestBody String email) {

		email = email.split("=")[0];

		try {
			email = URLDecoder.decode(email, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			StringWriter stack = new StringWriter();
			ex.printStackTrace(new PrintWriter(stack));

			logger.error(stack.toString());
		}

//		System.err.println(email);
//		System.err.println(profileService.profileExistsWithEmail(email));

		if (profileService.profileExistsWithEmail(email)) {
			return "true";
		} else {
			return "false";
		}
	}

}