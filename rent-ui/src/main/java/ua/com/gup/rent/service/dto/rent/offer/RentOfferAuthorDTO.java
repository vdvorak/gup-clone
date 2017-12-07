package ua.com.gup.rent.service.dto.rent.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class RentOfferAuthorDTO {


    @JsonProperty("id")
    @ApiModelProperty(position = 10, example = "id666")
    private String id;

    @JsonProperty("firstname")
    @ApiModelProperty(position = 20, example = "Ivan")
    private String firstName;

    @JsonProperty("lastname")
    @ApiModelProperty(position = 30, example = "Ivanenko")
    private String lastName;

    @JsonProperty("imgId")
    @ApiModelProperty(position = 40, example = "58ff0d6c821847a4bc8c5bfc")
    private String imageId;

    @JsonProperty("imgUrl")
    @ApiModelProperty(position = 50)
    private String imageUrl;

    public RentOfferAuthorDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
