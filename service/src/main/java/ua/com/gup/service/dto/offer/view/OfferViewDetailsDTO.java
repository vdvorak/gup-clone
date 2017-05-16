package ua.com.gup.service.dto.offer.view;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.service.dto.offer.OfferAddressDTO;
import ua.com.gup.service.dto.offer.OfferContactInfoDTO;

public class OfferViewDetailsDTO extends OfferViewBaseDTO {

    @ApiModelProperty(position = 6)
    private OfferStatus status;

    @ApiModelProperty(position = 60)
    private OfferAddressDTO address;

    @ApiModelProperty(position = 100, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    @ApiModelProperty(position = 105)
    private OfferContactInfoDTO contactInfo;

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public OfferAddressDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressDTO address) {
        this.address = address;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public OfferContactInfoDTO getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(OfferContactInfoDTO contactInfo) {
        this.contactInfo = contactInfo;
    }
}
