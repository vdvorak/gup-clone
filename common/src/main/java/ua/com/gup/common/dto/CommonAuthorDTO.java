package ua.com.gup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonAuthorDTO {


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

}
