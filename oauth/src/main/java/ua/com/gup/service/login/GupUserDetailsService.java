package ua.com.gup.service.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Victor Dvorak
 **/
public interface GupUserDetailsService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    UserDetails loadUserByFacebookId(String s) throws UsernameNotFoundException;
}
