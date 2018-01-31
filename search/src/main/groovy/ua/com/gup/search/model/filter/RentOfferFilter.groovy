package ua.com.gup.search.model.filter

import org.springframework.format.annotation.DateTimeFormat

import java.time.LocalDate

class RentOfferFilter extends RentOfferCalculateRentPriceFilter {
    String query

    AuthorFilter authorFilter

    Integer category

    AddressFilter address

    CoordinatesFilter coordinates

    MoneyFilter price

    List<AttributeFilter> attrs = new ArrayList<>()

    List<AttributeFilter> multiAttrs = new ArrayList<>()

    List<NumericAttributeFilter> numAttrs = new ArrayList<>()

    List<BooleanAttributeFilter> boolAttrs = new ArrayList<>()

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate createdDate
}
