package ua.com.gup.search.model.filter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MoneyFilter {
    Double from
    Double to
}
