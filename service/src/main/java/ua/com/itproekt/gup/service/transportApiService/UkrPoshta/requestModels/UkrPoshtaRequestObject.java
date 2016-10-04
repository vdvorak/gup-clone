package ua.com.itproekt.gup.service.transportApiService.UkrPoshta.requestModels;


public class UkrPoshtaRequestObject {
    private String guid;
    private String culture;
    private String barcode;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "UkrPoshtaRequestObject{" +
                "guid='" + guid + '\'' +
                ", culture='" + culture + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
