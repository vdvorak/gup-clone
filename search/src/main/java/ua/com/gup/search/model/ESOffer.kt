package ua.com.gup.search.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ESOffer constructor(val id: String?, val title: String?, val description: String?, val categories: List<Int>?)
