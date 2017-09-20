package ua.com.itproekt.gup.service.transportApiService.delivery;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.service.transportApiService.DeliveryService;

import java.net.URI;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    // url for making request
    private final String API_URL = "http://www.delivery-auto.com/api/Public/GetReceiptDetails";

    // language of response
    private final String CULTURE = "uk-UA";

    /**
     * @param trackNumber
     * @return
     */
    @Override
    public String tracking(String trackNumber) {
        return makeRequest(trackNumber);
    }


    /**
     * Prepare and make request to Delivery API
     *
     * @param trackNumber
     * @return
     */
    private String makeRequest(String trackNumber) {
        org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();

        try {

            URIBuilder builder = new URIBuilder(API_URL)
                    .addParameter("culture", CULTURE)
                    .addParameter("number", trackNumber);

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);

            HttpResponse response = httpclient.execute(request);

            HttpEntity entity = response.getEntity();

            String result = EntityUtils.toString(entity);

            System.err.println("Response: " + result);

            return result;

        } catch (Exception e) {
            System.out.println("Exception during Delivery API use: tracking - " + e.getMessage());
        }

        return null;
    }
}
