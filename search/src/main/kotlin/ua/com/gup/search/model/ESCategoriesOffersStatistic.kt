package ua.com.gup.search.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ESCategoriesOffersStatistic(var category: Long? = null, var count: Long? = null)