package ua.com.gup.model.profiles.admin;

import java.util.HashSet;
import java.util.Set;

public class Admins {
    private Set<Admin> ids;

    public Admins(Set<String> ids){
        this.ids = new HashSet<>();
        for (String id: ids)
            this.ids.add(new Admin(id));
    }

    public Set<Admin> getIds() {
        return ids;
    }
    public void setIds(Set<Admin> ids) {
        this.ids = ids;
    }
}
