package ua.com.gup.rent.model.rent.profiles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * The type Profile rating is a part of Profile entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferProfileRating {
    private String profileRatingId;
    private Long createdDate;
    private Integer earnPoints;
    private String shortDescription;
    private String longDescription;
    private String idAttachedFile;
}