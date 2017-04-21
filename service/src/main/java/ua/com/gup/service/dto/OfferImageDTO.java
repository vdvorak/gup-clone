package ua.com.gup.service.dto;


public class OfferImageDTO {

    private String imageId;

    private String filename;

    private String base64Data;

    public OfferImageDTO() {
    }

    public OfferImageDTO(String imageId) {
        this.imageId = imageId;
    }

    public String getImageId() {
        return imageId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }
}
