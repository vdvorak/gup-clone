package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class RentOfferDetailProfileDTO extends RentOfferProfileDTO {

    @JsonProperty("id")
    protected String publicId;

    @JsonProperty("contactList")
    protected Set<String> contactsList;

    @JsonProperty("imgUrl")
    protected String imageUrl;

    @JsonProperty("createdDate")
    protected Long createdDate;

    @JsonProperty("lastLoginDate")
    protected Long lastLoginDate;

    /*@JsonProperty("profileStatistic")
    protected RentOfferProfileStatistic profileStatistic;*/

    @JsonProperty("online")
    protected Boolean online;


    public RentOfferDetailProfileDTO(RentOfferProfile rentOfferProfile) {
        super(rentOfferProfile.getFirstname(),
                rentOfferProfile.getLastname(),
                rentOfferProfile.getUserType(),
                rentOfferProfile.getEmail(),
                rentOfferProfile.getAddress(),
                rentOfferProfile.getStatus());
    }
}
