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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ProfilesService profileService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Profile profile = profileService.findWholeProfileByEmail(email);
		if (profile == null) {
			throw new UsernameNotFoundException("Email: [" + email + "]");
		}
		return buildUserForAuthentication(profile, buildUserAuthority(profile.getUserRoles()));
	}

	private LoggedUser buildUserForAuthentication(Profile profile, List<GrantedAuthority> authorities) {
		return new LoggedUser(profile.getEmail(), profile.getPassword(),
				true, true, true, true, authorities,
				profile.getId());
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		return userRoles.stream()
				.map(userRole -> new SimpleGrantedAuthority(userRole.toString()))
				.collect(Collectors.toList());
	}

}
