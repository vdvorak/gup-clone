package ua.com.gup.rent.service.rent.image;

/**
 * @author Back-end
 **/
public class RentOfferPostImageResponse {

    private String s3id;
    private String contentMD5;

    public RentOfferPostImageResponse() {
    }

    public String getS3id() {
        return s3id;
    }

    public void setS3id(String s3id) {
        this.s3id = s3id;
    }

    public String getContentMD5() {
        return contentMD5;
    }

    public void setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
    }
}
