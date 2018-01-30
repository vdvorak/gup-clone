package ua.com.gup.search.model.filter.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferCalculateRentPriceFilter {


    protected String[] seoUrls;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    protected LocalDate dtRentStart;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    protected LocalDate dtRentEnd;

    protected Integer count;

}
