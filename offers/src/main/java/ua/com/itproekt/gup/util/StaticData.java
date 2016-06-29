//package ua.com.itproekt.gup.util;
//
//import ua.com.itproekt.gup.model.profiles.Profile;
//import ua.com.itproekt.gup.model.profiles.UserRole;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public final class StaticData {
//    private StaticData(){}
//    public static final Profile SUPPORT_DIALOGUE_ADMIN = new Profile();
//    public static final String SUPPORT_DIALOGUES_ADMIN_ID = "1";
//    public static final String SUPPORT_EMAIL = "inform@e-otg.com";
//    public static final String SUPPORT_EMAIL_PASSWORD = "Kt801Ma5cYuI";
//    public static final String SUPPORT_ADMIN_USERNAME = "Специалист службы поддержки";
//    public static final String SUPPORT_ADMIN_PASSWORD = "admin";
//
//    static {
//        SUPPORT_DIALOGUE_ADMIN.setId(SUPPORT_DIALOGUES_ADMIN_ID);
//        SUPPORT_DIALOGUE_ADMIN.setUsername(SUPPORT_ADMIN_USERNAME);
//        SUPPORT_DIALOGUE_ADMIN.setPassword(SUPPORT_ADMIN_PASSWORD);
//        SUPPORT_DIALOGUE_ADMIN.setEmail(SUPPORT_EMAIL);
//        Set<UserRole> roles = new HashSet<>();
//        roles.add(UserRole.ROLE_ADMIN);
//        roles.add(UserRole.ROLE_SUPPORT);
//        SUPPORT_DIALOGUE_ADMIN.setUserRoles(roles);
//    }
//}
