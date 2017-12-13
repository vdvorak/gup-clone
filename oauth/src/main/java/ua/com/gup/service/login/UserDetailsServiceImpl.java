package ua.com.gup.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.login.LoggedUser;
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

//    @Override
//    public UserDetails loadUserByUidAndVendor(String uid, String vendor) throws UsernameNotFoundException {
//        Profile profile = userService.findProfileByUidAndWendor(uid, vendor);
//        if (profile == null) {
//            throw new UsernameNotFoundException("UID / VENDOR: [" + uid + " / " + vendor + "]");
//        }
//        return buildVendorUserForAuthentication(profile, buildUserAuthority(profile.getUserRoles()));
//    }
//
//    @Override
//    public UserDetails loadUserByPhoneAndVendor(String phoneNumber, String vendor) throws UsernameNotFoundException {
//        Profile profile = userService.findProfileByPhoneNumberAndWendor(phoneNumber, vendor);
//        if (profile == null) {
//            throw new UsernameNotFoundException("PHONE_NUMBER / VENDOR: [" + phoneNumber + " / " + vendor + "]");
//        }
//        return buildPhoneUserForAuthentication(profile, buildUserAuthority(profile.getUserRoles()));
//    }

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

    private LoggedUser buildVendorUserForAuthentication(Profile profile, List<GrantedAuthority> authorities) {
        return new LoggedUser(profile.getUid(), profile.getSocWendor(),
                true, profile.getBan(), true, true, true, authorities,
                profile.getId(), profile.getPublicId(), profile.getEmail());
    }

    private LoggedUser buildPhoneUserForAuthentication(Profile profile, List<GrantedAuthority> authorities) {
        return new LoggedUser(profile.getMainPhone().getPhoneNumber(), profile.getSocWendor(),
                true, profile.getBan(), true, true, true, authorities,
                profile.getId(), profile.getPublicId(), profile.getEmail());
    }

    private List<GrantedAuthority> buildUserAuthority(Set<CommonUserRole> userRoles) {
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.toString()))
                .collect(Collectors.toList());
    }

}
