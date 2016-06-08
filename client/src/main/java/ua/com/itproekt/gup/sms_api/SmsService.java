package ua.com.itproekt.gup.sms_api;

import java.util.ArrayList;

/**
 * Created by Optical Illusion on 02.12.2015.
 */
public class SmsService {
    private static final String URL = "http://api.atompark.com/members/sms/xml.php";
    private static final String login = "alexdubitandum@gmail.com";
    private static final String password = "varZanSms";
    private static final String sender = "NovaEra";

    private static RequestBuilder Request = new RequestBuilder(URL);
    private static API ApiSms = new API(Request, login, password);

    public static void sendSms(final String number, int code) {

        ApiSms.sendSms(sender, "Ваш проверочный код: " + code, new ArrayList<Phones>() {
            {
                add(new Phones("id", "", "+38" + number));
            }
        });
    }


}
