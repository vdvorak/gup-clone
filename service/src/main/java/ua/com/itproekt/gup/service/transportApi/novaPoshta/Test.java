package ua.com.itproekt.gup.service.transportApi.novaPoshta;


import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class Test {



    public static void main(String[] args)
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.novaposhta.ua/v2.0/json/");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");


            // Request body

            String testRequest = "{\n" +
                    "\"apiKey\": \"8f289b663428f11de59032d558d84ede\",\n" +
                    "\"modelName\": \"TrackingDocument\",\n" +
                    "\"calledMethod\": \"getStatusDocuments\",\n" +
                    "\"methodProperties\": {\n" +
                    "\"Documents\": [\n" +
                    "{\n" +
                    "\"DocumentNumber\":\"59000207410866\",\n" +
                    "\"Phone\": \"380506785935\"\n" +
                    "}\n" +
                    "],\n" +
                    "\"Language\": \"UA\"\n" +
                    "}\n" +
                    "}";


            StringEntity reqEntity = new StringEntity(testRequest);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }






}
