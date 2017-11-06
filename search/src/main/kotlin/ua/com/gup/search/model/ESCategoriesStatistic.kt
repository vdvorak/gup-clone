package ua.com.gup.search.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ESCategoriesStatistic constructor(var code: Long?, var count: Long?, var title: String?) {
    constructor(code: Long?, count: Long?) : this(code, count, null)
}