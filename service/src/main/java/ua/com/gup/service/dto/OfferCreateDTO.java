package ua.com.gup.service.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedHashSet;

/**
 * A DTO for creation the Offer entity.
 */
@ApiModel(description = "Offer create DTO")
public class OfferCreateDTO extends OfferBaseDTO {

    @ApiModelProperty(position = 60)
    private OfferAddressDTO address;

    @ApiModelProperty(position = 80)
    private LinkedHashSet<OfferImageDTO> images;

    @ApiModelProperty(position = 100, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    public OfferAddressDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressDTO address) {
        this.address = address;
    }

    public LinkedHashSet<OfferImageDTO> getImages() {
        return images;
    }

    public void setImages(LinkedHashSet<OfferImageDTO> images) {
        this.images = images;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }
}
