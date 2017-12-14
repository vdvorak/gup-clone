package ua.com.gup.rent.service.dto.rent.offer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ua.com.gup.rent.model.rent.RentOfferPolygons;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "DTO for Rent Offer Lnads Offer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class RentOfferLandsDTO implements Serializable {

    @ApiModelProperty(position = 0)
    private String cadnums;

    @ApiModelProperty(position = 10)
    private List<RentOfferPolygons> polygons;

}
