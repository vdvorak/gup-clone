package ua.com.itproekt.gup.util;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

/**
 * VK API sample implementation for Java 8.
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

    private static final String API_REQUEST = "https://api.vk.com/method/{METHOD_NAME}"
            + "?{PARAMETERS}"
            + "&access_token={ACCESS_TOKEN}"
            + "&v=" + API_VERSION;

//    public static VkAPI with(String appId, String accessToken) throws IOException {
//        return new VkAPI(appId, accessToken);
//    }

    private final String accessToken;

    public VkAPI(String appId, String accessToken) throws IOException { //private VkAPI(String appId, String accessToken) throws IOException {
        this.accessToken = accessToken;
        if (accessToken == null || accessToken.isEmpty()) {
            auth(appId);
            throw new Error("Need access token");
        }
    }


    public static void main(String[] args) {
        try {
            VkAPI vkAPI = new VkAPI("5612442", "386550907e4f00529704d89164ba227331bf67c135208f16cfe262b5cc3df2ffe31a9108ae52a6a37ddf5");
            System.err.println("Подключился !");
            System.out.println( "Dialogs: " + vkAPI.getDialogs() );
//            System.out.println( "Albums: " + vkAPI.getAlbums("381966870") );
//            System.out.println( "Albums: " + vkAPI.getAlbums("10758791") );
            System.out.println( "Albums: " + vkAPI.getAlbums("18791") );
            System.out.println( "History: " + vkAPI.getHistory("381966870", 1, 3, true) );
            System.out.println( "Profile: " + vkAPI.getProfile("381966870") );
        } catch (IOException e) {
            System.err.println("НЕподключился ?");
        }
    }


    private void auth(String appId) throws IOException {
        String reqUrl = AUTH_URL
                .replace("{APP_ID}", appId)
                .replace("{PERMISSIONS}", "photos,messages")
                .replace("{REDIRECT_URI}", "https://oauth.vk.com/blank.html")
                .replace("{DISPLAY}", "page")
                .replace("{API_VERSION}", API_VERSION);
        try {
            Desktop.getDesktop().browse(new URL(reqUrl).toURI());
        } catch (URISyntaxException ex) {
            throw new IOException(ex);
        }
    }

    public String getDialogs() throws IOException {
        return invokeApi("messages.getDialogs", null);
    }

    public String getProfile(String userId) throws IOException {
        return invokeApi("messages.getProfile", Params.create()
                .add("photo", "1"));
    }

    public String getHistory(String userId, int offset, int count, boolean rev) throws IOException {
        return invokeApi("messages.getHistory", Params.create()
                .add("user_id", userId)
                .add("offset", String.valueOf(offset))
                .add("count", String.valueOf(count))
                .add("rev", rev ? "1" : "0"));
    }

    public String getAlbums(String userId) throws IOException {
        return invokeApi("photos.getAlbums", Params.create()
                .add("owner_id", userId)
                .add("photo_sizes", "1")
                .add("thumb_src", "1"));
    }

    private String invokeApi(String method, Params params) throws IOException {
        final String parameters = (params == null) ? "" : params.build();
        String reqUrl = API_REQUEST
                .replace("{METHOD_NAME}", method)
                .replace("{ACCESS_TOKEN}", accessToken)
                .replace("{PARAMETERS}&", parameters);
        return invokeApi(reqUrl);
    }

    private static String invokeApi(String requestUrl) throws IOException {
        final StringBuilder result = new StringBuilder();
        final URL url = new URL(requestUrl);
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
}
