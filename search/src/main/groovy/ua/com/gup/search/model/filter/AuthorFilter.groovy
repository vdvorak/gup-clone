package ua.com.gup.search.model.filter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class AuthorFilter {
    String authorId
}
