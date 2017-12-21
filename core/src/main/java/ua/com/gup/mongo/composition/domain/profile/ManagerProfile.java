package ua.com.gup.mongo.composition.domain.profile;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.object.ObjectType;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Document(collection = ObjectType.USER)
public class ManagerProfile extends Profile {

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
