package ua.com.gup.rent.model.rent.profiles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * The type Profile rating filter options, use for pagination.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public final class RentOfferProfileRatingFilterOptions extends RentOfferProfileRating {
    private int skip;
    private int limit;
}
