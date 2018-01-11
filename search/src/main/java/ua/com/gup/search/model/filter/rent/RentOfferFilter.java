package ua.com.gup.search.model.filter.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferFilter {

    private String query;

    private AuthorFilter author;

    private Integer category;

    private AddressFilter address;

    private CoordinatesFilter coordinates;

    private MoneyFilter price;

    private List<AttributeFilter> attrs = new ArrayList<>();

    private List<AttributeFilter> multiAttrs = new ArrayList<>();

    private List<NumericAttributeFilter> numAttrs = new ArrayList<>();

    private List<BooleanAttributeFilter> boolAttrs = new ArrayList<>();

    private String[] seoUrls;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdDate;

}
