package ua.com.itproekt.gup.service.transportApi.novaPoshta.requestModels;


public class Document {

    private String DocumentNumber;
    private String Phone;

    public String getDocumentNumber() {
        return DocumentNumber;
    }

    public Document setDocumentNumber(String documentNumber) {
        DocumentNumber = documentNumber;
        return this;
    }

    public String getPhone() {
        return Phone;
    }

    public Document setPhone(String phone) {
        Phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "Document{" +
                "DocumentNumber='" + DocumentNumber + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
