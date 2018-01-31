package ua.com.gup.search.model.search

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ESCategory {
    Integer code
    String title

    ESCategory() {
    }

    ESCategory(code, title) {
        this.code = code
        this.title = title
    }
}
