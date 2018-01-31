package ua.com.gup.search.model.search

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ESCategoriesStatistic {
    Long code
    Long count
    String title

    ESCategoriesStatistic(Long code, Long count) {
        this(code, count, null)
    }

    ESCategoriesStatistic(Long code, Long count, String title) {
        this.code = code
        this.count = count
        this.title = title
    }
}
