package ua.com.itproekt.gup.service.transportApiService.UkrPoshta;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.service.transportApiService.UkrPoshtaService;

import java.net.URI;


@Service
public class UkrPoshtaServiceImpl implements UkrPoshtaService {

    // url for making reques
    private final String API_URL = "http://services.ukrposhta.com/barcodestatistic/barcodestatistic.asmx/GetBarcodeInfo";

    // AP key for making request
    private final String GUID = "fcc8d9e1-b6f9-438f-9ac8-b67ab44391dd";

    // language of response
    private final String CULTURE = "uk";


    /**
     * @param barcode
     * @return
     */
    @Override
    public String tracking(String barcode) {
        System.err.println("Barcode input: " + barcode);
        return makeRequest(barcode);
    }


    /**
     * Prepare and make request to UkrPoshta
     *
     * @param barcode
     * @return
     */
    private String makeRequest(String barcode) {
        org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();

        try {

            URIBuilder builder = new URIBuilder(API_URL)
                    .addParameter("guid", GUID)
                    .addParameter("culture", CULTURE)
                    .addParameter("barcode", barcode);

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);

            HttpResponse response = httpclient.execute(request);

            HttpEntity entity = response.getEntity();

            System.err.println("Response: " + EntityUtils.toString(entity));

            return EntityUtils.toString(entity);

        } catch (Exception e) {
            System.out.println("Exception during Ukr Posht API use: tracking - " + e.getMessage());
        }

        return null;
    }


}
