package ua.com.gup.service.dto;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Offer entity.
 */
public class OfferDTO  {

    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
