package ua.com.gup.rent.service.dto.rent.bid;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentOfferBidCreateDTO {

    @JsonFormat( pattern = "dd-MM-yyyy")
    private LocalDate dtStart;
    @JsonFormat( pattern = "dd-MM-yyyy")
    private LocalDate dtEnd;
    private String comment;
    private String seoUrl;
}
