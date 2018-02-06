
package ua.com.gup.common.model.mongo.dictionary;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document(collection = Dictionary.COLLECTION_NAME, language = "russian")
public class Dictionary {

    public static final String COLLECTION_NAME = "dictionary";
    @Id
    private String locale;

    private Map<String, String> messages;

    public Dictionary() {
    }

    public Dictionary(String locale, Map<String, String> messages) {
        this.locale = locale;
        this.messages = messages;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Map<String, String> getMessages() {
        if (messages == null) {
            messages = new HashMap<>();
        }
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
}
