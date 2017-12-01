package ua.com.gup.rent.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.user.RentProfile;
import ua.com.gup.rent.repository.profile.RentProfileRepository;
import ua.com.gup.rent.security.RentLoggedUser;

import java.util.Collections;

//@Service
public abstract class RentUserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private RentProfileRepository profileRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        RentProfile rentProfile = profileRepository.loadByUsername(s);
//        return buildAuthenticationUser(rentProfile);
//    }
//
//    private RentLoggedUser buildAuthenticationUser(RentProfile profile) {
//        return new RentLoggedUser("username", "password",
//                true, true, true, true, Collections.EMPTY_LIST,"","","");
//    }


//
//    private List<GrantedAuthority> buildAuthenticationUserAuthorities(Set<UserRole> userRoles) {
//        return userRoles.stream()
//                .map(userRole -> new SimpleGrantedAuthority(userRole.toString()))
//                .collect(Collectors.toList());
//    }
}
