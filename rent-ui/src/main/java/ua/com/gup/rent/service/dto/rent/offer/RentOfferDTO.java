package ua.com.gup.rent.service.dto.rent.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RentOfferDTO implements Serializable {

    @ApiModelProperty(position = 10, example = "title")
    private String title;

    @ApiModelProperty(position = 20, example = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
