//package ua.com.itproekt.gup.service.transportApiService.novaPoshta;
//
//
//import com.google.gson.Gson;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.Document;
//import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.NovaPoshtaRequestObject;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Test {
//
//
//    public static void main(String[] args) {
//        HttpClient httpclient = HttpClients.createDefault();
//
//        try {
//            URIBuilder builder = new URIBuilder("https://api.novaposhta.ua/v2.0/json/");
//
//
//            URI uri = builder.build();
//            HttpPost request = new HttpPost(uri);
//            request.setHeader("Content-Type", "application/json");
//
//
//            NovaPoshtaRequestObject novaPoshtaRequestObject = novaPoshtaRequestObjectPreparator("59000207410866", "380506785935");
//
//            // Request body
//
//            Gson gson = new Gson();
////            System.err.println("This is JSON: " + gson.toJson(novaPoshtaRequestObject));
//
//            String requestBody = gson.toJson(novaPoshtaRequestObject); // parse object to JSON
//
//            StringEntity reqEntity = new StringEntity(requestBody);
//            request.setEntity(reqEntity);
//
//            HttpResponse response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) {
//                System.out.println(EntityUtils.toString(entity));
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//
//    private static NovaPoshtaRequestObject novaPoshtaRequestObjectPreparator(String trackingNumber, String phoneNumber){
//
//        NovaPoshtaRequestObject novaPoshtaRequestObject = new NovaPoshtaRequestObject();
//
//        List<Document> documentList = new ArrayList<>();
//        Document document = new Document();
//        document.setDocumentNumber(trackingNumber);
//        document.setPhone(phoneNumber);
//
//        documentList.add(document);
//
//        novaPoshtaRequestObject
//                .setApiKey("8f289b663428f11de59032d558d84ede")
//                .setCalledMethod("getStatusDocuments")
//                .setModelName("TrackingDocument")
//                .getMethodProperties().setDocuments(documentList).setLanguage("UA");
//
//        return novaPoshtaRequestObject;
//    }
//
//
//}
