package ua.com.gup.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.gup.model.LoggedUser;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements GupUserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile profile = userService.findProfileByEmail(email);
        if (profile == null) {
            throw new UsernameNotFoundException("Email: [" + email + "]");
        }
        return buildUserForAuthentication(profile, buildUserAuthority(profile.getUserRoles()));
    }

    @Override
    public UserDetails loadUserByFacebookId(String facebookId) throws UsernameNotFoundException {
        Profile profile = userService.findProfileByFacebookId(facebookId);
        if (profile == null) {
            throw new UsernameNotFoundException("FacebookId: [" + facebookId + "]");
        }
        return buildUserForFacebookAuthentication(profile, buildUserAuthority(profile.getUserRoles()));
    }

    private UserDetails buildUserForFacebookAuthentication(Profile profile, List<GrantedAuthority> authorities) {
        return new LoggedUser(
                profile.getFacebookId(),
                profile.getSocWendor(),
                profile.getActive(),
                profile.getBan(),
                true,
                true,
                !profile.getBan(),
                authorities,
                profile.getId(),
                profile.getPublicId(),
                profile.getEmail());
    }

    private LoggedUser buildUserForAuthentication(Profile profile, List<GrantedAuthority> authorities) {
        return new LoggedUser(
                profile.getEmail(),
                profile.getPassword(),
                profile.getActive(),
                profile.getBan(),
                true,
                true,
                !profile.getBan(),
                authorities,
                profile.getId(),
                profile.getPublicId(),
                profile.getEmail());
    }
    private List<GrantedAuthority> buildUserAuthority(Set<String> userRoles) {
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole))
                .collect(Collectors.toList());
    }

}
