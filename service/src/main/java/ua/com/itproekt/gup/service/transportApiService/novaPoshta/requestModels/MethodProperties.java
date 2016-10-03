package ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels;


import java.util.List;

public class MethodProperties {
    private String Language;
    private List<Document> Documents;

//    public MethodProperties() {
//        this.Documents = new ArrayList<>(Documents);
//    }

    public String getLanguage() {
        return Language;
    }

    public MethodProperties setLanguage(String language) {
        Language = language;
        return this;
    }

    public List<Document> getDocuments() {
        return Documents;
    }

    public MethodProperties setDocuments(List<Document> documents) {
        Documents = documents;
        return this;
    }

    @Override
    public String toString() {
        return "MethodProperties{" +
                "Language='" + Language + '\'' +
                ", Documents=" + Documents +
                '}';
    }
}
