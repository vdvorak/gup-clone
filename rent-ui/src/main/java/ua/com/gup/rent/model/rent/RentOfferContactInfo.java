package ua.com.gup.rent.model.rent;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferContactInfo {

    @Size(min = 2, max = 70)
    private String contactName;

    private Set<String> phoneNumbers;

}
