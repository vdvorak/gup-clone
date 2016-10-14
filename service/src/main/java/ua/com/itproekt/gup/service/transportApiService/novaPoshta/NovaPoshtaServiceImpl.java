package ua.com.itproekt.gup.service.transportApiService.novaPoshta;


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.service.transportApiService.NovaPoshtaService;
import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.Document;
import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.MethodProperties;
import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.NovaPoshtaRequestObject;

import java.net.URI;
import java.util.List;

@Service
public class NovaPoshtaServiceImpl implements NovaPoshtaService {

    private final String API_KEY = "8f289b663428f11de59032d558d84ede";
    private final String API_URL = "https://api.novaposhta.ua/v2.0/json/";


    @Override
    public String tracking(MethodProperties methodProperties) {

        HttpClient httpclient = HttpClients.createDefault();

        try {
            HttpResponse response = httpclient.execute(requestBuilder(methodProperties));
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println("Exception during Nova Poshta API use: tracking - " + e.getMessage());
        }

        return "not available";
    }


    /**
     * Prepare request
     *
     * @param methodProperties
     * @return
     */
    private HttpPost requestBuilder(MethodProperties methodProperties) {

        try {

            URIBuilder builder = new URIBuilder(API_URL);

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");

            NovaPoshtaRequestObject novaPoshtaRequestObject = novaPoshtaRequestObjectPreparator(methodProperties);

            // Request body prepare
            Gson gson = new Gson();
            String requestBody = gson.toJson(novaPoshtaRequestObject); // parse object to JSON

            StringEntity reqEntity = new StringEntity(requestBody);
            request.setEntity(reqEntity);

            return request;

        } catch (Exception e) {
            System.out.println("Exception during Nova Poshta API use: tracking - " + e.getMessage());
        }

        return null;
    }


    /**
     * Prepare request object
     *
     * @param methodProperties
     * @return
     */
    private NovaPoshtaRequestObject novaPoshtaRequestObjectPreparator(MethodProperties methodProperties) {

        NovaPoshtaRequestObject novaPoshtaRequestObject = new NovaPoshtaRequestObject();

        List<Document> documentList = methodProperties.getDocuments();

        novaPoshtaRequestObject
                .setApiKey(API_KEY)
                .setCalledMethod("getStatusDocuments")
                .setModelName("TrackingDocument")
                .getMethodProperties().setDocuments(documentList).setLanguage(methodProperties.getLanguage());

        return novaPoshtaRequestObject;
    }


}
