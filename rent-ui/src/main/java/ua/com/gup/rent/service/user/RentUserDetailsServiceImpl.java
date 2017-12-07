package ua.com.gup.rent.service.user;


import org.springframework.security.core.userdetails.UserDetailsService;

//@Service
public abstract class RentUserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private RentOfferProfileRepository profileRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        RentOfferProfile rentProfile = profileRepository.loadByUsername(s);
//        return buildAuthenticationUser(rentProfile);
//    }
//
//    private RentLoggedUser buildAuthenticationUser(RentOfferProfile profile) {
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
