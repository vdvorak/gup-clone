package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * The type Login controller.
 */
@Controller
public class LoginController {
	/**
	 * The Profile service.
	 */
	@Autowired
	ProfilesService profileService;

	/**
	 * The Nace service.
	 */
	@Autowired
	NaceService naceService;

	/**
	 *
	 * Add simple user.
	 */
	@RequestMapping("/addProfile")
	public String addUser(Model model) {
		Profile profile = new Profile();
		profile.setConfirmModerator(true);
		profile.setIdWord("ImChiefHere");
		profile.setUsername("Main Admin");
		profile.setEmail("admin@abc.com");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("admin");
		profile.setPassword(hashedPassword);
		HashSet<UserRole> userRoles = new HashSet<>();
		userRoles.add(UserRole.ROLE_ADMIN);
		profile.setUserRoles(userRoles);

		Contact contact = new Contact();
		contact.setType(UserType.LEGAL_ENTITY);
		contact.setCompanyDirector("Admin Jr. Adm");
		HashSet<String> contactEmails = new HashSet<>();
		contactEmails.add("info@abc");
		contactEmails.add("office@abc");
		contact.setContactEmails(contactEmails);
		HashSet<String> contactPhones = new HashSet<>();
		contactPhones.add("+38(044)-111-11-11");
		contactPhones.add("+38(067)-111-11-11");
		contact.setContactPhones(contactPhones);
		HashSet<String> linkToWebSite = new HashSet<>();
		linkToWebSite.add("http://www.president.gov.ua/");
		linkToWebSite.add("https://www.whitehouse.gov/");
		contact.setLinkToWebSite(linkToWebSite);
		contact.setAboutUs("In the morning, the President will depart Chicago en route Washington, DC. ");
		contact.setNace(Nace.E);
		contact.setPic("EnterPictureIdHere");
		contact.setBalance(988);
		HashMap<SocialNetworkList, String> socNetLink = new HashMap<>();
		socNetLink.put(SocialNetworkList.SKYPE, "SkypeAdm");
		socNetLink.put(SocialNetworkList.FACEBOOK, "https://www.facebook.com/zuck");
		contact.setSocNetLink(socNetLink);

		ProfileRating profileRating = new ProfileRating();
		Set<ProfileRating> profileRatingSet = new HashSet();
		profileRatingSet.add(profileRating.setEarnPoints(1));
		profileRatingSet.add(profileRating.setLongDescription("We've selected this list of the 10 best volunteer activities."));
		profileRatingSet.add(profileRating.setShortDescription("Best volunteer"));
		profileRatingSet.add(profileRating.setIdAttachedFile("561f9faf1b7bfd660799a59b"));

		profile.setProfileRating(profileRatingSet);
		profile.setContact(contact);
		profileService.createProfile(profile);
		model.addAttribute("message", "Profile with the email " + profile.getEmail() + " is created.");
		return "index";
	}

	@RequestMapping("/addNace")
	public String addNace(Model model) {
		DepartmentOrNace nace1 = new DepartmentOrNace();
		nace1.setId("1");
		nace1.setName("11");
		nace1.setTranscript("111");

		DepartmentOrNace nace2 = new DepartmentOrNace();
		nace2.setId("2");
		nace2.setName("22");
		nace2.setTranscript("222");

		DepartmentOrNace nace3 = new DepartmentOrNace();
		nace3.setId("3");
		nace3.setName("33");
		nace3.setTranscript("333");

		List<DepartmentOrNace> ch2 = new ArrayList<>();
		ch2.add(nace2);

		nace1.setChildren(ch2);

		List<DepartmentOrNace> ch3 = new ArrayList<>();
		ch3.add(nace3);

		nace2.setChildren(ch3);

		naceService.addNace(nace1);
		return "index";
	}


	@Qualifier("userAuthenticationManager")
	@Autowired
	AuthenticationManager authenticationManager;

	private final int ACCESS_TOKEN_EXPIRES_IN_SECONDS = 600 - 3;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	UserDetailsService userDetailsService;

	@RequestMapping("/loginForm")
	 public String getLoginForm(Model model) {
		return "loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response,
						@RequestParam String email, @RequestParam String password) {
//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
//		Authentication authentication = authenticationManager.authenticate(authRequest);
//
		Map<String, String> requestParameters = new HashMap<>();
		String clientId = "7b5a38705d7b3562655925406a652e32";
		Set<String> scope = new HashSet<>();
		OAuth2Request oAuth2Request = new OAuth2Request(requestParameters,
				clientId, null, true, scope,
				null, null, null, null);

		LoggedUser loggedUser = (LoggedUser)userDetailsService.loadUserByUsername(email);
		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(loggedUser,
				loggedUser.getPassword(), loggedUser.getAuthorities());

		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
//
		System.err.println("****" + tokenServices.createAccessToken(oAuth2Authentication));
		OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);
		oAuth2AccessToken.getRefreshToken();
//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
//
//		// Authenticate the user
//		Authentication authentication = authenticationManager.authenticate(authRequest);
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		securityContext.setAuthentication(authentication);
//
//			// Create a new session and add the security context.
//		HttpSession session = request.getSession(true);
//		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	}


//	@RequestMapping("/doLogin")
//	public String doFirstLogin(
//			@RequestParam(value = "email") String email,
//			@RequestParam(value = "passwords") String passwords, Model model) {
//
//
//		String message = "";
//		model.addAttribute("message", "test");
//
//		return "login";
//	}
//







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