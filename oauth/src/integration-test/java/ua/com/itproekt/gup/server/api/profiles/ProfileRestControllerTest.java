package ua.com.itproekt.gup.server.api.profiles;

import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.server.api.rest.profiles.dto.ProfileInfo;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProfileRestControllerTest {

    private RestTemplate       restTemplate;
    private final String           urlLogin = "http://localhost:9090/api/oauth/login";
    private final String          urlLogout = "http://localhost:9090/api/oauth/logout";
    private final String urlLoggedInProfile = "http://localhost:9090/api/rest/profilesService/profile/read/loggedInProfile";
    private final String     urlProfileEdit = "http://localhost:9090/api/rest/profilesService/profile/edit";
    private final String        requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";
    private final String    requestEditJson = "{\"idSeoWord\":null,\"email\":\"sss2@gmail.com\",\"mainPhoneNumber\":\"\",\"username\":\"NEO\",\"imgId\":\"577530314c8eb310cacffc49\",\"contact\":{\"member\":false,\"naceId\":null,\"type\":\"INDIVIDUAL\",\"position\":\"\",\"companyName\":\"\",\"aboutUs\":\"\",\"skypeUserName\":\"\",\"linkToWebSite\":\"\",\"contactEmails\":[],\"contactPhones\":[],\"socNetLink\":{}},\"contactList\":[\"572368bffb644cbdbcf3cc1c\",\"575697a53880f94fe2ced184\"],\"userProfile\":{\"usreou\":null,\"bankCode\":null,\"vatNumber\":null,\"beneficiaryBank\":null,\"beneficiaryAccount\":null,\"legalEntityLocation\":null,\"idAddFile\":null},\"point\":0,\"profileRating\":[],\"confirmModerator\":null,\"userRoles\":[\"ROLE_USER\"],\"orderAddressList\":null}";
    private final String   requestEditJson0 = "{\"idSeoWord\":null,\"email\":\"sss2@gmail.com\",\"mainPhoneNumber\":\"0000000\",\"username\":\"NEO\",\"imgId\":\"577530314c8eb310cacffc49\",\"contact\":{\"member\":false,\"naceId\":null,\"type\":\"INDIVIDUAL\",\"position\":\"\",\"companyName\":\"\",\"aboutUs\":\"\",\"skypeUserName\":\"\",\"linkToWebSite\":\"\",\"contactEmails\":[],\"contactPhones\":[],\"socNetLink\":{}},\"contactList\":[\"572368bffb644cbdbcf3cc1c\",\"575697a53880f94fe2ced184\"],\"userProfile\":{\"usreou\":null,\"bankCode\":null,\"vatNumber\":null,\"beneficiaryBank\":null,\"beneficiaryAccount\":null,\"legalEntityLocation\":null,\"idAddFile\":null},\"point\":0,\"profileRating\":[],\"confirmModerator\":null,\"userRoles\":[\"ROLE_USER\"],\"orderAddressList\":null}";
    private final String   requestEditJson1 = "{\"idSeoWord\":null,\"email\":\"sss2@gmail.com\",\"mainPhoneNumber\":\"1111111\",\"username\":\"NEO\",\"imgId\":\"577530314c8eb310cacffc49\",\"contact\":{\"member\":false,\"naceId\":null,\"type\":\"INDIVIDUAL\",\"position\":\"\",\"companyName\":\"\",\"aboutUs\":\"\",\"skypeUserName\":\"\",\"linkToWebSite\":\"\",\"contactEmails\":[],\"contactPhones\":[],\"socNetLink\":{}},\"contactList\":[\"572368bffb644cbdbcf3cc1c\",\"575697a53880f94fe2ced184\"],\"userProfile\":{\"usreou\":null,\"bankCode\":null,\"vatNumber\":null,\"beneficiaryBank\":null,\"beneficiaryAccount\":null,\"legalEntityLocation\":null,\"idAddFile\":null},\"point\":0,\"profileRating\":[],\"confirmModerator\":null,\"userRoles\":[\"ROLE_USER\"],\"orderAddressList\":null}";

    @Before
    public void setUp() {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory( HttpClients.createDefault() );
        restTemplate = new RestTemplate( requestFactory );
    }

    @After
    public void tearDown() {
        restTemplate = null;
    }

    /**
     * test Logged-In Profile-Response
     */
    @Test
    public void testLoggedInProfileResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);

        ProfileInfo              actual = restTemplate.getForObject(urlLoggedInProfile, ProfileInfo.class);
        String               expectedId = "575697a53880f94fe2ced184";
        String         expectedUsername = "NEO";
        Set<UserRole> expectedUserRoles = new HashSet<>();
        expectedUserRoles.add(UserRole.ROLE_USER);
        restTemplate.exchange(urlLogout, HttpMethod.GET, new HttpEntity<>("", headers), String.class);

        assertThat(actual.getProfile().getId(), equalTo(expectedId));
        assertThat(actual.getProfile().getUsername(), equalTo(expectedUsername));
        assertThat(actual.getProfile().getUserRoles(), equalTo(expectedUserRoles));
    }

    /**
     * test Logged-In Profile-Status
     */
    @Test
    public void testLoggedInProfileStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);

        ResponseEntity<ProfileInfo> actual = restTemplate.exchange(urlLoggedInProfile, HttpMethod.GET, new HttpEntity<>("", headers), ProfileInfo.class);
        restTemplate.exchange(urlLogout, HttpMethod.GET, new HttpEntity<>("", headers), String.class);

        assertThat(actual.getStatusCode(), equalTo(HttpStatus.OK));
    }

    /**
     * test Edit-Profile Check
     */
    @Test
    public void testProfileEditCheck() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);

        restTemplate.postForObject(urlProfileEdit, new HttpEntity<>(requestEditJson0, headers), String.class);
        ProfileInfo             actual0 = restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);
        String              expectedId0 = "575697a53880f94fe2ced184";
        String expectedMainPhoneNumber0 = "0000000";
        restTemplate.postForObject(urlProfileEdit, new HttpEntity<>(requestEditJson1, headers), String.class);
        ProfileInfo             actual1 = restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);
        String              expectedId1 = "575697a53880f94fe2ced184";
        String expectedMainPhoneNumber1 = "1111111";
        restTemplate.exchange(urlLogout, HttpMethod.GET, new HttpEntity<>("", headers), String.class);

        assertThat(actual0.getProfile().getId(), equalTo(expectedId0));
        assertThat(actual1.getProfile().getId(), equalTo(expectedId1));
        assertThat(actual0.getProfile().getMainPhoneNumber(), equalTo(expectedMainPhoneNumber0));
        assertThat(actual1.getProfile().getMainPhoneNumber(), equalTo(expectedMainPhoneNumber1));
    }

    /**
     * test Edit-Profile Status
     */
    @Test
    public void testProfileEditStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);

        ResponseEntity<String> actual = restTemplate.exchange(urlProfileEdit, HttpMethod.POST, new HttpEntity<>(requestEditJson, headers), String.class);
        restTemplate.exchange(urlLogout, HttpMethod.GET, new HttpEntity<>("", headers), String.class);

        assertThat(actual.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
