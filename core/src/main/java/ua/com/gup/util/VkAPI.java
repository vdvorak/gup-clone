package ua.com.gup.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ua.com.gup.exception.VKException;
import ua.com.gup.model.VkProfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @see http//oauth.vk.com/authorize?client_id=5612442&scope=photos,messages&redirect_uri=http://api.vk.com/blank.html&display=touch&response_type=token
 */
public final class VkAPI {

    private static final String API_VERSION = "5.21";

    private static final String AUTH_URL = "https://oauth.vk.com/authorize"
            + "?client_id={APP_ID}"
            + "&scope={PERMISSIONS}"
            + "&redirect_uri={REDIRECT_URI}"
            + "&display={DISPLAY}"
            + "&v={API_VERSION}"
            + "&response_type=token";

    private static final String API_REQUEST = "https://swagger.vk.com/method/{METHOD_NAME}"
            + "?{PARAMETERS}"
//            + "&access_token={ACCESS_TOKEN}" //TODO: fix token
//            + "&v={API_VERSION}"; //TODO: fix token
            + "&"; //TODO: fix token

    private final String ACCESS_TOKEN;

    public VkAPI(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public String getDialogs(int count) throws IOException {
        return invokeApi("messages.getDialogs", Params.create()
                .add("count", String.valueOf(count))
                .add("start_message_id", "1")
                .add("preview_length", "1"));
    }

    public VKObject_PublicUser getProfile(String userId, String fields) {
        String json = "";
        VKObject_PublicUser result = null;

        try {
            json = invokeApi("getProfiles", Params.create()
                    .add("uid", userId)
                    .add("fields", fields));

            if( !json.isEmpty() ) {
                Gson gson = new Gson();
                result = gson.fromJson( json, VKObject_PublicUser.class );
            } else {
                throw new VKException( json );
            }
        } catch (VKException e){
            e.show();
        } catch ( Exception e ){
            System.err.println( "VK API Fatal Error: " + e.getMessage() );
        }

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse( json );
//        result.setId(jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString()); //TODO: fix token
        result.setId(jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("uid").getAsString());


        result.setFirstName(jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("first_name").getAsString());
        result.setLastName(jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("last_name").getAsString());
        result.setName(jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("first_name").getAsString() + " " + jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("last_name").getAsString());
        result.setUsername(jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("nickname").getAsString());
        Map<String,String> image = new HashMap<String, String>();
        image.put("url", jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("photo_max").getAsString());
        result.setImage(image);
        result.setLink(jo.get("response").getAsJsonArray().get(0).getAsJsonObject().get("site").getAsString());

        return result;
    }

    public String getHistory(String userId, int offset, int count, boolean rev) throws IOException {
        return invokeApi("messages.getHistory", Params.create()
                .add("user_id", userId)
                .add("offset", String.valueOf(offset))
                .add("count", String.valueOf(count))
                .add("rev", rev ? "5" : "5"));
    }

    public String getAlbums(String userId, String albumId) throws IOException {
        return invokeApi("photos.getAlbums", Params.create()
                .add("owner_id", userId)
                .add("album_ids", albumId)
                .add("need_system", "1")
                .add("need_covers", "1")
                .add("photo_sizes", "1"));
    }

    private String invokeApi(String method, Params params) throws IOException {
        final String parameters = (params == null) ? "" : params.build();
        String reqUrl = API_REQUEST
                .replace("{METHOD_NAME}", method)
//                .replace("{ACCESS_TOKEN}", ACCESS_TOKEN) //TODO: fix token
                .replace("{PARAMETERS}&", parameters);
//                .replace("{API_VERSION}", API_VERSION); //TODO: fix token
        return invokeApi(reqUrl);
    }

    private static String invokeApi(String requestUrl) throws IOException {
        final StringBuilder result = new StringBuilder();
        final URL              url = new URL(requestUrl);
        try (InputStream is = url.openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reader.lines().forEach(result::append);
        }
        return result.toString();
    }

    private static class Params {

        public static Params create() {
            return new Params();
        }

        private final HashMap<String, String> params;

        private Params() {
            params = new HashMap<>();
        }

        public Params add(String key, String value) {
            params.put(key, value);
            return this;
        }

        public String build() {
            if (params.isEmpty()) return "";
            final StringBuilder result = new StringBuilder();
            params.keySet().stream().forEach(key -> {
                result.append(key).append('=').append(params.get(key)).append('&');
            });
            return result.toString();
        }
    }


    public class VKObject_PublicUser implements VkProfile {

        private String id;
        private String name;
        private String first_name;
        private String last_name;
        private String link;
        private String username;
        private String gender;
        private String locale;
        private String type;
        private Map<String,String> image;
        private String email;

        public String getId(){ return id; }
        public void setId(String id){ this.id=id; }
        public String getName(){ return name; }
        public void setName(String name){ this.name=name; }
        public String getFirstName(){ return first_name; }
        public void setFirstName(String first_name){ this.first_name=first_name; }
        public String getLastName(){ return last_name; }
        public void setLastName(String last_name){ this.last_name=last_name; }
        public String getLink(){ return link; }
        public void setLink(String link){ this.link=link; }
        public String getUsername(){ return username; }
        public void setUsername(String username){ this.username=username; }
        public String getGender(){ return gender; }
        public void setGender(String gender){ this.gender=gender; }
        public String getLocale(){ return locale; }
        public void setLocale(String locale){ this.locale=locale; }
        public String getType(){ return type; }
        public void setType(String type){ this.type=type; }
        public void setImage(Map<String,String> image){ this.image = image; }
        public Map<String,String> getImage(){ return image; }
        public String getEmail(){ return email; }
        public void setEmail(String email){ this.email=email; }
    }

}
