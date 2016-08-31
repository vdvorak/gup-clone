package ua.com.itproekt.gup;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;
import ua.com.itproekt.gup.util.API;
import ua.com.itproekt.gup.util.VK_api;
import ua.com.itproekt.gup.util.VkAPI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 ** {@link https://developers.facebook.com/tools/explorer?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.3}
 * email | UID | ACCESS_TOKEN | API_KEY | socWendor [gup.com.ua : graph.facebook.com : www.googleapis.com : vkontakte.ru]
 */
public class APITest {

    private API profileVendor;

    public APITest(){
        profileVendor = new API();
    }

	public static void main( String[] args ){
        final String FACEBOOK_WENDOR = "graph.facebook.com";
        final String FACEBOOK_ACCESS_TOKEN = "EAACEdEose0cBAL0Ltj0FWuD4KjsrzZB8LN83GkavPXQwwQnQEcWgijGJMHZCiv6nES8i1BUZAZCkqgZAFmiE0CZCQBvKkp0SuzpZC15CRXUQw6RLvbAwrvzzTxSZCwHVER4YeQmfdxxgpleJMTtFeBgbPxmAZAJxtKQ61l8cturml1QZDZD";
        final String FACEBOOK_UID = "1077154112339703";   // "1106460174";
        final String GOOGLEPLUS_WENDOR = "www.googleapis.com";
        final String GOOGLEPLUS_API_KEY = "AIzaSyAMjpEzLQJDYZdrgBGp-zNQ27xjw_6xEDA";
        final String GOOGLEPLUS_UID = "107235630368984173445"; // "117976156812233500456";

        APITest test = new APITest();

        try {
            test.testProfileVendor(FACEBOOK_WENDOR, FACEBOOK_ACCESS_TOKEN, FACEBOOK_UID);
            test.testProfileVendor(GOOGLEPLUS_WENDOR, GOOGLEPLUS_API_KEY, GOOGLEPLUS_UID);
            test.testProfileVendor(null, "bbbbb@bbb.com", "123456");
            test.testProfileVendor("bbbbb@bbb.com", "123456");
            try {
                test.testProfileVK();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            test.testEditProfile(FACEBOOK_WENDOR, FACEBOOK_ACCESS_TOKEN, FACEBOOK_UID);
            test.testEditProfile(GOOGLEPLUS_WENDOR, GOOGLEPLUS_API_KEY, GOOGLEPLUS_UID);
        } catch (NullPointerException e){
            System.err.println(">>> Invalid OAuth access token");
        }
	}

    /**
     * test API-ProfileVendor
     */
    public void testProfileVendor(String WENDOR, String TOKEN, String UID) throws NullPointerException {
        System.out.println( "\n============== " + WENDOR + " ==============" );

        profileVendor.init(WENDOR, TOKEN, UID);
        System.out.println( "        UID: " + profileVendor.getId() );
        System.out.println( "  Full Name: " + profileVendor.getName() );
        System.out.println( "   UserName: " + profileVendor.getUsername());
        System.out.println( "Profile URL: " + profileVendor.getLink() );
        System.out.println( "Picture URL: " + profileVendor.getImage().get("url") );
        System.out.println( "      Email: " + profileVendor.getEmail() );
    }

    public void testProfileVendor(String TOKEN, String UID) throws NullPointerException {
        System.out.println( "\n============== ??? ==============" );

        profileVendor.init(TOKEN, UID);
        System.out.println( "        UID: " + profileVendor.getId() );
        System.out.println( "  Full Name: " + profileVendor.getName() );
        System.out.println( "   UserName: " + profileVendor.getUsername());
        System.out.println( "Profile URL: " + profileVendor.getLink() );
        System.out.println( "Picture URL: " + profileVendor.getImage().get("url") );
        System.out.println( "      Email: " + profileVendor.getEmail() );
    }

    /**
     * test API-ProfileVK
     */
    public void testProfileVK() throws IOException, URISyntaxException, AWTException, InterruptedException, NoSuchAlgorithmException {
        PopupMenu   popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("Выход");

        exitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        popup.add(exitItem); // Добавим пункт в меню
        SystemTray systemTray = SystemTray.getSystemTray();
        Image           image = Toolkit.getDefaultToolkit().getImage("vk_icon.png"); // получим картинку
        TrayIcon     trayIcon = new TrayIcon(image, "VKNotifer", popup);
        trayIcon.setImageAutoSize(true);

        systemTray.add(trayIcon); // добавим иконку в трей
        trayIcon.displayMessage("VKNotifer", "Соединяемся с сервером", TrayIcon.MessageType.INFO);

        VK_api vkAPI = new VK_api();
        vkAPI.setConnection();
        trayIcon.displayMessage("VKNotifer", "Соединение установлено", TrayIcon.MessageType.INFO);
        String oldMessage = vkAPI.getNewMessage();
        String newMessage;

        int i = 0;
        for (;;){
            // Запросы на сервер можно подавать раз в 3 секунды
            Thread.sleep(3000); // ждем три секунды
            if (i == 15000){    // Если прошло 45 000 сек (время взято с запасом, токен дается на день)
                vkAPI.setConnection(); // Обновляем токен
                Thread.sleep(3000);    // Запросы шлем только раз в три секунды
                i = 0;
            }

            newMessage = vkAPI.getNewMessage(); // Здесь отработка
            if (!newMessage.equals(oldMessage)) {
                oldMessage = newMessage;
                trayIcon.displayMessage("VKNotifer", "Получено новое сообщение",TrayIcon.MessageType.INFO);
            }
            i++;
        }
    }

    /**
     * test EditProfile
     */
    public void testEditProfile(String WENDOR, String TOKEN, String UID) throws NullPointerException {
        System.out.println( "\n============== " + WENDOR + " ==============" );

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