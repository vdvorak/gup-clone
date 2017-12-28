package ua.com.gup.service.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Victor Dvorak
 **/
public interface GupUserDetailsService extends UserDetailsService {
//    default UserDetails loadUserByUidAndVendor(String uid, String vendor) throws UsernameNotFoundException {
//        return null;
//    }
//
//    default UserDetails loadUserByPhoneAndVendor(String phoneNumber, String vendor) throws UsernameNotFoundException {
//        return null;
//    }

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    UserDetails loadUserByFacebookId(String s) throws UsernameNotFoundException;
}
