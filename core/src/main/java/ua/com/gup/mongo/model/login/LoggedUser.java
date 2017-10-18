package ua.com.gup.mongo.model.login;


import com.mongodb.DBObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ua.com.gup.config.mongo.converter.OAuth2AuthenticationReadConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LoggedUser extends User {
    private final String profileId;
    private final Boolean banned;

    public LoggedUser(DBObject dbObject) {
        super((String) dbObject.get("username"),
                (String) dbObject.get("password"),
                (Boolean) dbObject.get("enabled"),
                (Boolean) dbObject.get("accountNonExpired"),
                (Boolean) dbObject.get("credentialsNonExpired"),
                (Boolean) dbObject.get("accountNonLocked"),
                OAuth2AuthenticationReadConverter.getAuthorities((List<Map<String, String>>) dbObject.get("authorities")));

        this.profileId = (String) dbObject.get("profileId");
        this.banned = (Boolean) dbObject.get("ban");
    }


    public LoggedUser(String username, String password, boolean enabled, boolean banned,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                      String profileId) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.profileId = profileId;
        this.banned = banned;
    }

    public String getProfileId() {
        return profileId;
    }

    public Boolean isBanned() {
        return banned;
    }
}
