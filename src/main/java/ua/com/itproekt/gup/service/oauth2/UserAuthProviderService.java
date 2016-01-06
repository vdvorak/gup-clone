package ua.com.itproekt.gup.service.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.itproekt.gup.model.login.LoggedUser;

import java.util.Collection;


@Component
public class UserAuthProviderService implements AuthenticationProvider {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    //    private ProfilesService profilesService;
    //    private UserAuthConfigService authConfigService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private Authentication signInUser(LoggedUser loggedUser, Collection<GrantedAuthority> roles) {
        System.err.println("3 Authentication signInUser profile" + " : " + loggedUser);
        System.err.println("3 Authentication signInUser roles" + " : " + roles);

        UserDetails springSecurityUser = new User(loggedUser.getUsername(), loggedUser.getPassword(), roles);
        System.err.println("3 Authentication signInUser springSecurityUser" + " : " + springSecurityUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(springSecurityUser, loggedUser.getPassword(), roles);
        System.err.println("3 Authentication signInUser authentication" + " : " + authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        System.err.println("1 Authentication authenticate " + " : " + a);

        String email = a.getName();
        String password = a.getCredentials().toString();
        System.err.println("password = a.getCredentials().toString() : " + password);
        LoggedUser loggedUser = (LoggedUser)userDetailsService.loadUserByUsername(email);
        System.err.println("2 Authentication authenticate Profile profile " + " : " + loggedUser);

        if (null != loggedUser) {
            if (passwordEncoder.matches(password, loggedUser.getPassword())) {
                System.err.println("1 Authentication authenticate if (passwordEncoder. " + " : " + loggedUser);
                Collection<GrantedAuthority> roleAuthority = loggedUser.getAuthorities();
                return signInUser(loggedUser, roleAuthority);
            }

            throw new AuthenticationException("Password for '" + email + "' not correct.") {};
        }

            throw new AuthenticationException("Could not find user with name '" + email + "'") {};
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }


}

