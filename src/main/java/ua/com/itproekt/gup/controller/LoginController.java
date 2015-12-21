package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;

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

	@RequestMapping("/login")
	public String getLoginForm(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		String message = "";
		if (error != null) {
			message = "Incorrect username or password!";
		} else if (logout != null) {
			message = "Logout successful!";
		}
		model.addAttribute("message", message);

		return "login";
	}

	@RequestMapping("/admin*")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	public String getAdminProfile() {
		return "adminPage";
	}

	@RequestMapping("/error403")
	public String getAccessDenied(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		} else {
			username = "guest";
		}

		model.addAttribute("username", username);
		return "error403";
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