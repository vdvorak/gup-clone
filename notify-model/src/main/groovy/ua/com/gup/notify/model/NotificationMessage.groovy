package ua.com.gup.notify.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class NotificationMessage implements Serializable {
    String title
    String description
    String author
    String icon

    @Override
    String toString() {
        return "NotificationMessage{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", icon='" + icon + '\'' +
                '}'
    }
}
