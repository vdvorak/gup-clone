package ua.com.gup.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Offer entity.
 */
@ApiModel(description = "Offer update DTO")
public class OfferUpdateDTO extends OfferCreateDTO {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
