package ua.com.gup.common.model.mongo.manager;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ManagerInfo {

    private Set<String> users;

    public Set<String> getUsers() {
        if(users == null){
            users = new HashSet<>();
        }
        return users;
    }
}
