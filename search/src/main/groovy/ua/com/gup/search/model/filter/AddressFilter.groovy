package ua.com.gup.search.model.filter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class AddressFilter {
    String countries
    String regions
    String districts
    String cities

}
