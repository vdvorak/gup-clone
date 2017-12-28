package ua.com.gup.rent.model.mongo.user;

import java.util.HashSet;
import java.util.Set;

public class RentOfferManagerProfile extends RentOfferProfile {

    private Set<String> users;

    public Set<String> getUsers() {
        if(users == null){
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}
