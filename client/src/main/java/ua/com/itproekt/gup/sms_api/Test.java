package ua.com.itproekt.gup.sms_api;

/**
 * Created by Optical Illusion on 30.10.2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String URL = "http://api.atompark.com/members/sms/xml.php";
        String login="alexdubitandum@gmail.com";
        String password="varZanSms";

        RequestBuilder Request= new RequestBuilder(URL);
        API ApiSms=new API(Request, login, password);


        //GET STATUS *************************************************************
        System.out.print(ApiSms.getStatus("1299"));
      /*
       * response: <?xml version="1.0" encoding="UTF-8"?>
            <deliveryreport><message id="1299" sentdate="0000-00-00 00:00:00" donedate="0000-00-00 00:00:00" status="0" /></deliveryreport>
      */

        //GET PRICE *************************************************************
        Map<String, String>  phones = new HashMap();
        phones.put("id1", "+380666027034");
        System.out.print(ApiSms.getPrice("Test sms",phones));
      /*
       * response: <?xml version="1.0" encoding="UTF-8"?><RESPONSE><status>0</status><credits>0.682</credits></RESPONSE>
      */


        //GET BALANCE *************************************************************
        System.out.print(ApiSms.getBalance());
      /*
       * response: <?xml version="1.0" encoding="UTF-8"?><RESPONSE><status>0</status><credits>780.64</credits></RESPONSE>
      */

        //SEND PHONE *************************************************************
        ArrayList<Phones> phoneSend=new ArrayList<Phones>();
        phoneSend.add(new Phones("id1","","+380666027034"));

        System.out.print(ApiSms.sendSms("Shopup", "Welcome to the future 2", phoneSend));
      /*
       * response: <?xml version="1.0" encoding="UTF-8"?><RESPONSE><status>2</status><credits>0.682</credits></RESPONSE>
      */
    }
}
