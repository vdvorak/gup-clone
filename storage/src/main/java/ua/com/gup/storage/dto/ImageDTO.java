package ua.com.gup.storage.dto;

import java.net.URL;

public class ImageDTO {
    private String contentType;
    private URL imageURL;

    public ImageDTO(String contentType, URL imageURL) {
        this.contentType = contentType;
        this.imageURL = imageURL;
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
