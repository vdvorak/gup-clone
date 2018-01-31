package ua.com.gup.search.model.search

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ESOffer {

    String id
    String title
    String description
    List<Integer> categories

    ESOffer(String id, String title, String description, List<Integer> categories) {
        this.id = id
        this.title = title
        this.description = description
        this.categories = categories
    }
}
