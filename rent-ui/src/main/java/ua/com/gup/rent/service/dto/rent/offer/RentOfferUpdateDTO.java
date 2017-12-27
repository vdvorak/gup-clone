package ua.com.gup.rent.service.dto.rent.offer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Offer entity.
 */
@ApiModel(description = "Rent Offer update DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferUpdateDTO extends RentOfferCreateDTO {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    @NotNull
    private String id;

}
