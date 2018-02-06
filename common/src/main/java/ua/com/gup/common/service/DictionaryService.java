package ua.com.gup.common.service;

import ua.com.gup.common.dto.dictionary.DictionaryMessageDTO;
import ua.com.gup.common.model.Locale;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DictionaryService {

    Map<String, String> getByLocale(Locale locale);

    void saveMessage(Locale locale, String key, String messages);

    void saveMessages(Locale locale, List<DictionaryMessageDTO> messages);

    String get(Locale locale, String key);

    String get(String key);

    void delete(Locale locale, String key);

    boolean isExists(Locale locale, String key);
}
