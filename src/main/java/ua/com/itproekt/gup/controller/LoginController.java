package ua.com.itproekt.gup.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Controller
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class);

	private final int ACCESS_TOKEN_EXPIRES_IN_SECONDS = 600 - 3;
	private final int REFRESH_TOKEN_EXPIRES_IN_SECONDS = 2592000 - 3;

	@Autowired
	ProfilesService profileService;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	UserDetailsService userDetailsService;

	@RequestMapping("/loginForm")
	 public String getLoginForm() {
		return "loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestParam String email, @RequestParam String password,
					  HttpServletResponse response) {

		Map<String, String> requestParameters = new HashMap<>();
		String clientId = "7b5a38705d7b3562655925406a652e32";
		Set<String> scope = new HashSet<>();
		OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId, null, true,
				scope, null, null, null, null);

		LoggedUser loggedUser = (LoggedUser)userDetailsService.loadUserByUsername(email);
		if (!passwordEncoder.matches(password, loggedUser.getPassword())) {
			throw new BadCredentialsException("The input password doesn't match");
		}

		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(loggedUser,
				loggedUser.getPassword(), loggedUser.getAuthorities());

		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);

		OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

		Cookie cookieAuthToken = new Cookie("authToken", oAuth2AccessToken.getValue());
		cookieAuthToken.setMaxAge(ACCESS_TOKEN_EXPIRES_IN_SECONDS);
		cookieAuthToken.setPath("/");
		response.addCookie(cookieAuthToken);

		Cookie cookieRefreshToken = new Cookie("refreshToken", oAuth2AccessToken.getRefreshToken().getValue());
		cookieRefreshToken.setMaxAge(REFRESH_TOKEN_EXPIRES_IN_SECONDS);
		cookieRefreshToken.setPath("/");
		response.addCookie(cookieRefreshToken);

		logger.debug("Login: profile {email : " + email + "}");
	}

//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
//
//			Authenticate the user
//		Authentication authentication = authenticationManager.authenticate(authRequest);
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		securityContext.setAuthentication(authentication);
//
//			Create a new session and add the security context.
//		HttpSession session = request.getSession(true);
//		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);


	@RequestMapping("/admin*")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	public String getAdminProfile() {
		return "adminPage";
	}

/*--------------------------------------- Check -----------------------------------------------------------------*/
	@RequestMapping(value = "/login/checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public String existEmailCheck(@RequestBody String email) {

		email = email.split("=")[0];

		try {
			email = URLDecoder.decode(email, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.err.println(email);
		System.err.println(profileService.profileExistsWithEmail(email));

		if (profileService.profileExistsWithEmail(email)) {
			return "true";
		} else {
			return "false";
		}
	}

}