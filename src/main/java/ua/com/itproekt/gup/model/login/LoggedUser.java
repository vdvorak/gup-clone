package ua.com.itproekt.gup.model.login;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoggedUser extends User {
    private final String profileId;

    public LoggedUser(String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                      String profileId) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }
}
