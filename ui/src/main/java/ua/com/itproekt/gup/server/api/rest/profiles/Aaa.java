//package ua.com.itproekt.gup.server.api.rest.profiles;
//
//
//import ua.com.itproekt.gup.model.profiles.Profile;
//import ua.com.itproekt.gup.model.profiles.phone.DBStorePhones;
//import ua.com.itproekt.gup.model.profiles.phone.PhoneSynhronize;
//import ua.com.itproekt.gup.model.profiles.phone.ProfileStorePhones;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Aaa {
//
//    public static void main(String[] args) {
//        Aaa aaa = new Aaa();
//
//        ProfileStorePhones profileStorePhones = aaa.profileStorePhones("5922eb8e0f6650c6c07ec174");
//    }
//
//    private ProfileStorePhones profileStorePhones(String userId){
//        Profile profile = profilesService.findById(userId);
//        if (profile==null) return null;
//
//        DBStorePhones dbStorePhones = profile.getStorePhones();
//        if (dbStorePhones==null) return null;
//
//        List<PhoneSynhronize> oContactPhones = dbStorePhones.getContactPhones().stream()
//                .map(x -> (profilesService.findProfileByMainPhone(String.valueOf(x)) != null) ? (new PhoneSynhronize(x, true)) : (new PhoneSynhronize(x, false)))
//                .collect(Collectors.toList());
//
//        ProfileStorePhones profileStorePhones = new ProfileStorePhones();
//        profileStorePhones.setContactPhones(oContactPhones);
//
//        return profileStorePhones;
//    }
//}
