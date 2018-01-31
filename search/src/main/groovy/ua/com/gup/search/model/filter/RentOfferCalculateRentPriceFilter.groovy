package ua.com.gup.search.model.filter

import org.springframework.format.annotation.DateTimeFormat

import java.time.LocalDate

class RentOfferCalculateRentPriceFilter {

    String[] seoUrls;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate dtRentStart;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate dtRentEnd;

    Integer count;
}
