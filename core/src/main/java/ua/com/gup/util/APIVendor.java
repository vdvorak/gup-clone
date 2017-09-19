package ua.com.itproekt.gup.util;

import org.springframework.stereotype.Service;
import ua.com.gup.model.FacebookProfile;
import ua.com.gup.model.GooglePlusProfile;
import ua.com.gup.model.VkProfile;

import java.util.Map;
import java.util.TreeMap;

/**
 *^ @see https://developers.facebook.com/tools/explorer?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.3
 *
 * * * * * * * * * * * * * * * * * * * * * * *
 * email | UID | ACCESS_TOKEN | socWendor [gup.com.ua : graph.facebook.com : www.googleapis.com : vk.ru]
 */

@Service
public class APIVendor {

    private String id;
    private String name;
    private String username;
    private String link;
    private Map<String,String> image;
    private String email;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getLink() {
        return link;
    }

    public Map<String, String> getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public void init(String ACCESS_KEY, String UID){
        init(null, ACCESS_KEY, UID);
    }

    public void init(String urlWendor, String ACCESS_KEY, String UID){
        switch (Wendor.getWendor(urlWendor)){
            case FACEBOOK:
                initFacebook(ACCESS_KEY, UID);
                break;
            case GOOGLEPLUS:
                initGooglePlus(ACCESS_KEY, UID);
                break;
            case VK:
                initVK(ACCESS_KEY, UID);
                break;
            default:
                initGUP(ACCESS_KEY, UID);
                break;
        }
    }

    private void initFacebook(String ACCESS_TOKEN, String UID){
        FacebookAPI api = new FacebookAPI( ACCESS_TOKEN );
        FacebookProfile profile = api.get( UID );

        id = profile.getId();
        name = profile.getName();
        username = profile.getUsername();
        link = profile.getLink();
        image = profile.getImage();
        email = profile.getEmail();
    }

    private void initGooglePlus(String API_KEY, String UID){
        GooglePlusAPI api = new GooglePlusAPI( API_KEY );
        GooglePlusProfile profile = api.get( UID );

        id = profile.getId();
        name = profile.getDisplayName();
        username = profile.getDisplayName();
        link = profile.getURL();
        image = profile.getImage();
        email = profile.getEmail();
    }

    private void initVK(String ACCESS_TOKEN, String UID) {
        VkAPI api = new VkAPI(ACCESS_TOKEN);
//        VkProfile profile = api.getProfile(UID, "photo_id,verified,sex,bdate,city,country,home_town,has_photo,photo_50,photo_100,photo_200_orig,photo_200,photo_400_orig,photo_max,photo_max_orig,online,lists,domain,has_mobile,contacts,site,education,universities,schools,status,last_seen,followers_count,common_count,occupation,nickname,relatives,relation,personal,connections,exports,wall_comments,activities,interests,music,movies,tv,books,games,about,quotes,can_post,can_see_all_posts,can_see_audio,can_write_private_message,can_send_friend_request,is_favorite,is_hidden_from_feed,timezone,screen_name,maiden_name,crop_photo,is_friend,friend_status,career,military,blacklisted,blacklisted_by_me"); // TODO err: ',common_count'
        VkProfile profile = api.getProfile(UID, "photo_id,verified,sex,bdate,city,country,home_town,has_photo,photo_50,photo_100,photo_200_orig,photo_200,photo_400_orig,photo_max,photo_max_orig,online,lists,domain,has_mobile,contacts,site,education,universities,schools,status,last_seen,followers_count,occupation,nickname,relatives,relation,personal,connections,exports,wall_comments,activities,interests,music,movies,tv,books,games,about,quotes,can_post,can_see_all_posts,can_see_audio,can_write_private_message,can_send_friend_request,is_favorite,is_hidden_from_feed,timezone,screen_name,maiden_name,crop_photo,is_friend,friend_status,career,military,blacklisted,blacklisted_by_me");

        id = profile.getId();
        name = profile.getName();
        username = profile.getUsername();
        link = profile.getLink();
        image = profile.getImage();
        email = profile.getEmail();
    }

    private void initGUP(String ACCESS_TOKEN, String UID){
        id = "";
        name = "";
        username = "";
        link = "";
        image = new TreeMap<>();
        email = "";
    }

    public enum Wendor {
        GUP("gup.com.ua"),
        FACEBOOK("graph.facebook.com"),
        GOOGLEPLUS("www.googleapis.com"),
        VK("vk.ru");

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
            return GUP;
        }

        public String url() {
            return url;
        }
    }

}
