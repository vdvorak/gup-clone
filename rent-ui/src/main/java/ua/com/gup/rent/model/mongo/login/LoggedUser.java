package ua.com.gup.rent.model.mongo.login;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoggedUser extends User {
    private final String profileId;
    private final String publicId;
    private final String email;
    private final Boolean banned;

    /*public LoggedUser(DBObject dbObject) {
        super((String) dbObject.get("username"),
                (String) dbObject.get("password"),
                (Boolean) dbObject.get("enabled"),
                (Boolean) dbObject.get("accountNonExpired"),
                (Boolean) dbObject.get("credentialsNonExpired"),
                (Boolean) dbObject.get("accountNonLocked"),
                OAuth2AuthenticationReadConverter.getAuthorities((List<Map<String, String>>) dbObject.get("authorities")));

        this.profileId = (String) dbObject.get("profileId");
        this.publicId = (String) dbObject.get("publicId");
        this.email = (String) dbObject.get("email");
        this.banned = (Boolean) dbObject.get("ban");
    }*/


    public LoggedUser(String username, String password, boolean enabled, boolean banned,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                      String profileId, String publicId, String email) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.profileId = profileId;
        this.publicId = publicId;
        this.email = email;
        this.banned = banned;
    }

    public String getProfileId() {
        return profileId;
    }

    public Boolean isBanned() {
        return banned;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getEmail() {
        return email;
    }

}
