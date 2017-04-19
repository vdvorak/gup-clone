package ua.com.gup.service.dto;


import javax.validation.constraints.NotNull;

public class OfferShortDTO extends OfferBaseDTO {

    @NotNull
    private String id;

    private String seoUrl;

    private String authorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
