package ua.com.gup.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.net.URL;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDTO {
    private String s3id;
    private String contentMD5;

    private String contentType;
    private URL imageURL;

    public ImageDTO(String s3id, String contentMD5) {
        this.s3id = s3id;
        this.contentMD5 = contentMD5;
    }

    public ImageDTO(String contentType, URL imageURL) {
        this.contentType = contentType;
        this.imageURL = imageURL;
    }

    public String getContentMD5() {
        return contentMD5;
    }

    public void setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
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

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }
}
