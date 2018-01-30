package ua.com.gup.search.model.filter.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferFilter extends RentOfferCalculateRentPriceFilter {

    private String query;

    private AuthorFilter authorFilter;

    private Integer category;

    private AddressFilter address;

    private CoordinatesFilter coordinates;

    private MoneyFilter price;

    private List<AttributeFilter> attrs = new ArrayList<>();

    private List<AttributeFilter> multiAttrs = new ArrayList<>();

    private List<NumericAttributeFilter> numAttrs = new ArrayList<>();

    private List<BooleanAttributeFilter> boolAttrs = new ArrayList<>();

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;


    public RentOfferFilter(RentOfferCalculateRentPriceFilter filter) {
        this.count = filter.getCount();
        this.dtRentStart = filter.getDtRentStart();
        this.dtRentEnd = filter.getDtRentEnd();
        this.seoUrls = filter.getSeoUrls();
    }


}
