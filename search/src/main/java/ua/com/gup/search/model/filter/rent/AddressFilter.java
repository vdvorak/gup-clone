package ua.com.gup.search.model.filter.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressFilter {
    private String countries;
    private String regions;
    private String districts;
    private String cities;

}
