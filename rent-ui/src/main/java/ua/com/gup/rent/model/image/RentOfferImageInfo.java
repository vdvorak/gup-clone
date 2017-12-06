package ua.com.gup.rent.model.image;

public class RentOfferImageInfo {

    private String fileName;
    private String s3id;
    private String contentType;
    private Long size;

    public RentOfferImageInfo() {
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getS3id() {
        return s3id;
    }

    public void setS3id(String s3id) {
        this.s3id = s3id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
