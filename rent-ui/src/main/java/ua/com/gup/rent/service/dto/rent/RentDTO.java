package ua.com.gup.rent.service.dto.rent;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RentDTO {

    @ApiModelProperty(position = 30, example = "title may be here")
    private String title;

    @ApiModelProperty(position = 40, example = "description may be here")
    private String description;

    public RentDTO() {
    }

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
