package ua.com.itproekt.gup.util;

import ua.com.itproekt.gup.model.FacebookProfile;
import ua.com.itproekt.gup.model.GooglePlusProfile;

/**
 ** {@link http://shanechism.com/code/2011/10/introduction-to-util-tutorial-java}
 * {@link https://www.mkyong.com/java/java-enum-example/} {@link https://habrahabr.ru/post/128390/}
 * * * * * * * * * * * * * * * * * * * * * * *
 * email | UID | ACCESS_TOKEN | API_KEY | socWendor [gup.com.ua : graph.facebook.com : www.googleapis.com : vkontakte.ru]
 */
public class API {

    public void getProfile(String ACCESS_KEY, String UID){
        getProfile(null, ACCESS_KEY, UID);
    }

    public void getProfile(String urlWendor, String ACCESS_KEY, String UID){
        switch (Wendor.getWendor(urlWendor)){
            case FACEBOOK:
                System.out.println( "\n============== Facebook Graph API Results ==============" );
                getFacebookProfile(ACCESS_KEY, UID);
                break;
            case GOOGLEPLUS:
                System.out.println( "\n============== Google+ API Results ==============" );
                getGooglePlusProfile(ACCESS_KEY, UID);
                break;
            case VKONTAKTE:
                System.out.println( "\n============== VKontakte Results ==============" );
                break;
            default:
                System.out.println( "\n============== GUP Results ==============" );
                break;
        }
    }

    private void getFacebookProfile(String ACCESS_TOKEN, String UID){
        FacebookAPI         api = new FacebookAPI( ACCESS_TOKEN );
        FacebookProfile profile = api.get( UID );

        System.out.println( "        UID: " + profile.getId() );
        System.out.println( "  Full Name: " + profile.getName() );
        System.out.println( "   UserName: " + profile.getUsername());
        System.out.println( "Profile URL: " + profile.getLink() );
        System.out.println( "Picture URL: " + profile.getImage().get("url") );
    }

    private void getGooglePlusProfile(String API_KEY, String UID){
        GooglePlusAPI         api = new GooglePlusAPI( API_KEY );
        GooglePlusProfile profile = api.get( UID );

        System.out.println( "        UID: " + profile.getId() );
        System.out.println( "  Full Name: " + profile.getDisplayName() );
        System.out.println( "   UserName: " + profile.getDisplayName() );
        System.out.println( "Profile URL: " + profile.getURL() );
        System.out.println( "Picture URL: " + profile.getImage().get("url") );
    }

    public enum Wendor {
        GUP("gup.com.ua"),
        FACEBOOK("graph.facebook.com"),
        GOOGLEPLUS("www.googleapis.com"),
        VKONTAKTE("vkontakte.ru");

        private String url;

        private Wendor(String url) {
            this.url = url;
        }

        static public Wendor getWendor(String url) {
            for (Wendor wendor: Wendor.values()) {
                if (wendor.url().equals(url)) {
                    return wendor;
                }
            }
//            throw new RuntimeException("unknown wendor");
            return GUP;
        }

        public String url() {
            return url;
        }
    }

}
