package ua.com.itproekt.gup.util;

import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.model.FacebookProfile;
import ua.com.itproekt.gup.model.GooglePlusProfile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *^ @see https://developers.facebook.com/tools/explorer?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.3
 * * * * * * * * * * * * * * * * * * * * * * *
 * email | UID | ACCESS_TOKEN | socWendor [gup.com.ua : graph.facebook.com : www.googleapis.com : vk.ru]
 */

@Service
public class API {

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
                try {
                    initVK();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                break;
            default:
                initGUP(ACCESS_KEY, UID);
                break;
        }
    }

    private void initFacebook(String ACCESS_TOKEN, String UID){
        FacebookAPI api = new FacebookAPI( ACCESS_TOKEN );
        FacebookProfile profile = api.get( UID );

        this.id = profile.getId();
        this.name = profile.getName();
        this.username = profile.getUsername();
        this.link = profile.getLink();
        this.image = profile.getImage();
        this.email = profile.getEmail();
    }

    private void initGooglePlus(String API_KEY, String UID){
        GooglePlusAPI api = new GooglePlusAPI( API_KEY );
        GooglePlusProfile profile = api.get( UID );

        this.id = profile.getId();
        this.name = profile.getDisplayName();
        this.username = profile.getDisplayName();
        this.link = profile.getURL();
        this.image = profile.getImage();
        this.email = profile.getEmail();
    }

    private void initVK() throws IOException, URISyntaxException, AWTException, InterruptedException, NoSuchAlgorithmException {
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
            if (i == 15000){  // Если прошло 45 000 сек (Время взято с запасом, токен дается на день )
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

    private void initGUP(String ACCESS_TOKEN, String UID){
//        System.out.println( "\n============== GUP Results ==============" );
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
