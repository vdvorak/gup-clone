package ua.com.gup.rent.service.dto.rent.offer.view;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.image.ImageSizeType;
import ua.com.gup.rent.model.rent.RentOfferCategoryShort;
import ua.com.gup.common.service.dto.CommonAuthorDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferSettingsDTO;
import ua.com.gup.rent.service.dto.rent.offer.price.RentOfferPriceDTO;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferViewBaseDTO implements Serializable {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    private String id;

    @ApiModelProperty(position = 5)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime lastModifiedDate;

    @ApiModelProperty(position = 20, example = "58edf17a4c8e83648c2f1aa3")
    private CommonAuthorDTO author;

    @ApiModelProperty(position = 30)
    private LinkedList<RentOfferCategoryShort> categories;

    @ApiModelProperty(position = 40, example = "title")
    private String title;

    @ApiModelProperty(position = 50, example = "description")
    private String description;

    @ApiModelProperty(position = 70)
    private RentOfferPriceDTO price;

    @ApiModelProperty(position = 80)
    private Map<String, Map<ImageSizeType, String>> images;

    @ApiModelProperty(position = 90, example = "prodam-toyota-rav-4-2016hod-ls")
    private String seoUrl;

    @ApiModelProperty(position = 90, example = "settings")
    private RentOfferSettingsDTO settings;
}
