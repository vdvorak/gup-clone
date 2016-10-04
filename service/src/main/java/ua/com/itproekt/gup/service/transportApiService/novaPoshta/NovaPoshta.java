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
import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.NovaPoshtaRequestObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class NovaPoshta implements NovaPoshtaService {

    private final String API_KEY = "8f289b663428f11de59032d558d84ede";
    private final String API_URL = "https://api.novaposhta.ua/v2.0/json/";



    @Override
    public String tracking(String trackingNumber, String phoneNumber) {
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder(API_URL);

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");

            NovaPoshtaRequestObject novaPoshtaRequestObject = novaPoshtaRequestObjectPreparator(trackingNumber, phoneNumber);

            // Request body

            Gson gson = new Gson();

            String requestBody = gson.toJson(novaPoshtaRequestObject); // parse object to JSON

            StringEntity reqEntity = new StringEntity(requestBody);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "not available";
    }

    @Override
    public String tracking(String trackingNumber) {
        return null;
    }




    private NovaPoshtaRequestObject novaPoshtaRequestObjectPreparator(String trackingNumber, String phoneNumber){

        NovaPoshtaRequestObject novaPoshtaRequestObject = new NovaPoshtaRequestObject();

        List<Document> documentList = new ArrayList<>();
        Document document = new Document();
        document.setDocumentNumber(trackingNumber);
        document.setPhone(phoneNumber);

        documentList.add(document);

        novaPoshtaRequestObject
                .setApiKey(API_KEY)
                .setCalledMethod("getStatusDocuments")
                .setModelName("TrackingDocument")
                .getMethodProperties().setDocuments(documentList).setLanguage("UA");

        return novaPoshtaRequestObject;
    }


}
