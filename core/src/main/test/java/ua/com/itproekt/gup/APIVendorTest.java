package ua.com.itproekt.gup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;
import ua.com.itproekt.gup.util.APIVendor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 ** @see https://developers.facebook.com/tools/explorer?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.3
 ** @see http://oauth.vk.com/authorize?client_id=5612442&scope=photos,messages&redirect_uri=http://api.vk.com/blank.html&display=touch&response_type=token
 * email | UID | ACCESS_TOKEN | API_KEY | socWendor [gup.com.ua : graph.facebook.com : www.googleapis.com : vk.ru]
 */
public class APIVendorTest {

    private APIVendor profileVendor;

    @Before
    public void setUp() {
        profileVendor = new APIVendor();
    }

    @After
    public void tearDown(){
        profileVendor = null;
    }

    /**
     * test get Vendor & edit Profile
     */
    @Test
	public void testEditProfileVendor(){
        final String FACEBOOK_WENDOR = "graph.facebook.com";
        final String FACEBOOK_ACCESS_TOKEN = "EAACEdEose0cBAKLwGCeLSaaLWQCmfjNsoe1KAiVXuUbTklg8ammMEBrpnr1ooIRfsoX09AhE9O5EPN2ZASykJcPHgCYlGu9GX66ZCBDBR32ChVIPgpXbfjqPWOq6mY44VevA7JQ6F4ICkxfwLwZAL4bZAexu0gNodVKNoaAKRgZDZD";
        final String FACEBOOK_UID = "1077154112339703";   // "1106460174"
        final String GOOGLEPLUS_WENDOR = "www.googleapis.com";
        final String GOOGLEPLUS_API_KEY = "AIzaSyAMjpEzLQJDYZdrgBGp-zNQ27xjw_6xEDA";
        final String GOOGLEPLUS_UID = "107235630368984173445"; // "117976156812233500456"
        final String VK_WENDOR = "vk.ru";
        final String VK_ACCESS_TOKEN = "36d4d2f7afb05e980d8e6adaea2ff5572df65356453573e54d7cf9224abe161897d4a16b562d0bc6c5ebb";
        final String VK_UID = "381966870"; // "123456" "321456" "369852" "741258" "185014513"

        try {
            profileVendor(FACEBOOK_WENDOR, FACEBOOK_ACCESS_TOKEN, FACEBOOK_UID);
            profileVendor(GOOGLEPLUS_WENDOR, GOOGLEPLUS_API_KEY, GOOGLEPLUS_UID);
            profileVendor(VK_WENDOR, VK_ACCESS_TOKEN, VK_UID);
            profileVendor(null, "bbbbb@bbb.com", "123456");
            profileVendor("bbbbb@bbb.com", "123456");

            editProfile(FACEBOOK_WENDOR, FACEBOOK_ACCESS_TOKEN, FACEBOOK_UID);
            editProfile(GOOGLEPLUS_WENDOR, GOOGLEPLUS_API_KEY, GOOGLEPLUS_UID);
            editProfile(VK_WENDOR, VK_ACCESS_TOKEN, VK_UID);
        } catch (NullPointerException e){
            System.err.println(">>> Invalid OAuth access token");
        }
    }

    /**
     * API Profile Vendor
     */
    public void profileVendor(String WENDOR, String TOKEN, String UID) throws NullPointerException {
        profileVendor.init(WENDOR, TOKEN, UID);
        System.out.println( "        UID: " + profileVendor.getId() );
        System.out.println( "  Full Name: " + profileVendor.getName() );
        System.out.println( "   UserName: " + profileVendor.getUsername());
        System.out.println( "Profile URL: " + profileVendor.getLink() );
        System.out.println( "Picture URL: " + profileVendor.getImage().get("url") );
        System.out.println( "      Email: " + profileVendor.getEmail() );
    }

    public void profileVendor(String TOKEN, String UID) throws NullPointerException {
        profileVendor.init(TOKEN, UID);
        System.out.println( "        UID: " + profileVendor.getId() );
        System.out.println( "  Full Name: " + profileVendor.getName() );
        System.out.println( "   UserName: " + profileVendor.getUsername());
        System.out.println( "Profile URL: " + profileVendor.getLink() );
        System.out.println( "Picture URL: " + profileVendor.getImage().get("url") );
        System.out.println( "      Email: " + profileVendor.getEmail() );
    }

    /**
     * Edit Profile
     */
    public void editProfile(String WENDOR, String TOKEN, String UID) throws NullPointerException {
        profileVendor.init(WENDOR, TOKEN, UID);
        try {
            System.out.println( "               Img: " + getImageProfile(profileVendor.getImage().get("url")) );
            System.out.println( "CachedImageProfile: " + getCachedImageProfile("profile", getImageProfile(profileVendor.getImage().get("url")), "profile_" + 1) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getImageProfile(String url) throws IOException {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        ResponseEntity<byte[]> profileImg = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        return new ByteArrayInputStream(profileImg.getBody());
    }

    private FileUploadWrapper getCachedImageProfile(String serviceName, InputStream img, String filename) throws IOException {
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();
        fileUploadWrapper
                .setServiceName(serviceName.toUpperCase())
                .setInputStream(img)
                .setContentType("")
                .setFilename(filename);
        return fileUploadWrapper;
    }

}