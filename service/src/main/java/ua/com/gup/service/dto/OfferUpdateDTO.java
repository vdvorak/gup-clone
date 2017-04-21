package ua.com.gup.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Offer entity.
 */
@ApiModel(description = "Offer update DTO")
public class OfferUpdateDTO extends OfferBaseDTO {

    @NotNull
    @ApiModelProperty(required = true)
    private String id;

    @ApiModelProperty(value = "Offer title, size[2;70]", required = true)
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
