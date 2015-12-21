package ua.com.itproekt.gup.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ProfilesService profileService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Profile profile = profileService.findProfileByEmail(email);
		List<GrantedAuthority> authorities = buildUserAuthority(profile.getUserRoles());
		return buildUserForAuthentication(profile, authorities);
	}

	private LoggedUser buildUserForAuthentication(Profile profile, List<GrantedAuthority> authorities) {
		return new LoggedUser(profile.getEmail(), profile.getPassword(),
				true, true, true, true, authorities,
				profile.getId());
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.toString()));
		}

		return new ArrayList<>(setAuths);
	}

}
