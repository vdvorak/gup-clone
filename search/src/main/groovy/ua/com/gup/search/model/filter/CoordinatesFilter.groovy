package ua.com.gup.search.model.filter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CoordinatesFilter {
    BigDecimal[] minYX
    BigDecimal[] maxYX

}
