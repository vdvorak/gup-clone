package ua.com.itproekt.gup.server.api.rest.profile;


import com.google.gson.Gson;
import ua.com.itproekt.gup.server.api.model.profiles.DBStorePhones;
import ua.com.itproekt.gup.server.api.model.profiles.PhoneSynhronize;
import ua.com.itproekt.gup.server.api.model.profiles.ProfileStorePhones;
import ua.com.itproekt.gup.server.api.model.profiles.StorePhones;

import java.util.*;
import java.util.stream.Collectors;

public class StorePhones2Test {

    public static void main(String[] args) {
        /////////////////////////////////////////////////////////////////
        String      userId = "58efaf104c8e83648c2f1e1e";

        List<Long> mainPhones = new ArrayList<>();
        mainPhones.add(380994444444l);

        String profileStorePhones = "{\"contactPhones\":[{\"numberPhone\":380994444444,\"isFound\":false},{\"numberPhone\":380934311043,\"isFound\":false},{\"numberPhone\":380970072837,\"isFound\":false},{\"numberPhone\":380939325476,\"isFound\":false}]}";

        Gson gson = new Gson();
        ProfileStorePhones oProfileStorePhones = gson.fromJson(profileStorePhones, ProfileStorePhones.class);
        System.out.println( oProfileStorePhones );

        DBStorePhones oDBStorePhones = test1(userId, mainPhones, oProfileStorePhones);
        System.out.println( oDBStorePhones );
        //DBStorePhones{idUser='58efaf104c8e83648c2f1e1e', mainPhones=[380994444444], contactPhones=[380994444444, 380934311043, 380970072837, 380939325476]}

        /////////////////////////////////////////////////////////////////
        Long   searchPhone = 380994444444l;

        oProfileStorePhones = test2(oDBStorePhones, searchPhone);
        System.out.println( oProfileStorePhones );
        //{"contactPhones":[{"numberPhone":380994444444,"isFound":true},{"numberPhone":380994444444,"isFound":false},{"numberPhone":380994444444,"isFound":false},{"numberPhone":380994444444,"isFound":false}]}

        /////////////////////////////////////////////////////////////////
        oProfileStorePhones = test3(userId, searchPhone);
        System.out.println( oProfileStorePhones );
        //{"contactPhones":[{"numberPhone":380994444444,"isFound":true},{"numberPhone":380994444444,"isFound":false},{"numberPhone":380994444444,"isFound":false},{"numberPhone":380994444444,"isFound":false}]}
    }


    public static DBStorePhones test1(String userId, List<Long> mainPhones, ProfileStorePhones profileStorePhones){
        List<Long> contactPhones = profileStorePhones.getContactPhones().stream()
                .map(x -> x.getNumberPhone())
                .collect(Collectors.toList());

        return new DBStorePhones(userId,mainPhones,contactPhones);
    }

    public static ProfileStorePhones test2(DBStorePhones dbStorePhones, Long searchPhone){
        List<PhoneSynhronize> contactPhones = dbStorePhones.getContactPhones().stream()
                .map(x -> x.equals(searchPhone) ? (new PhoneSynhronize(searchPhone, true)) : (new PhoneSynhronize(searchPhone, false)))
                .collect(Collectors.toList());

        ProfileStorePhones profileStorePhones = new ProfileStorePhones();
        profileStorePhones.setContactPhones(contactPhones);

        return profileStorePhones;
    }

    public static ProfileStorePhones test3(String userId, Long searchPhone){
        ///////////////////////////
        List<Long> mainPhones = new ArrayList<>();
        mainPhones.add(380994444444l);

        List<Long> contactPhones = new ArrayList<>();
        contactPhones.add(380994444444l);
        contactPhones.add(380934311043l);
        contactPhones.add(380970072837l);
        contactPhones.add(380939325476l);

        DBStorePhones dbStorePhones = new DBStorePhones();
        dbStorePhones.setIdUser(userId);
        dbStorePhones.setMainPhones(mainPhones);
        dbStorePhones.setContactPhones(contactPhones);
        ///////////////////////////

        List<PhoneSynhronize> oContactPhones = dbStorePhones.getContactPhones().stream()
                .map(x -> x.equals(searchPhone) ? (new PhoneSynhronize(searchPhone, true)) : (new PhoneSynhronize(searchPhone, false)))
                .collect(Collectors.toList());

        ProfileStorePhones profileStorePhones = new ProfileStorePhones();
        profileStorePhones.setContactPhones(oContactPhones);

        return profileStorePhones;
    }
}
