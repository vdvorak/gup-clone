package ua.com.itproekt.gup.rental.algorithm.calculator;

import com.google.gson.Gson;

import java.util.*;

public class Aaa {

    public static void main(String[] args) {
        Gson gson = new Gson();

        Admin admin = new Admin("5901b9f18218d6960747599d");
        System.out.println( gson.toJson(admin) );

        Admins admins = new Admins( new String[]{"58ee044d4c8e83648c2f1aec","58edf17a4c8e83648c2f1aa3","58ed61ab4c8ebd9459277498","58ede8da4c8ebd94592774c3","5901b9f18218d6960747599d","58ede40c4c8ebd94592774be","58f64c754c8e83648c2f1f34","5922e12c06545131b96bcdbf","5922eb8e0f6650c6c07ec174","58ed606e4c8ebd9459277495","5912f7a6d25402527cb7c342","58edf1314c8ebd94592774ca"} );
        System.out.println( gson.toJson(admins) );
    }

    static class Admin {

        public Admin(){}

        public Admin(String id){
            this.id = id;
        }

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    static class Admins {

        public Admins(){}

        public Admins(String[] ids){
            this.ids = new HashSet<>();
            for (String id: ids)
                this.ids.add(new Admin(id));
        }

        private Set<Admin> ids;

        public Set<Admin> getIds() {
            return ids;
        }

        public void setIds(Set<Admin> ids) {
            this.ids = ids;
        }
    }

}
