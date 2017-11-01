package ua.com.gup.search.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ESOffer constructor(val id: String?, val title: String?, val description: String?, val categories: List<Int>?)
