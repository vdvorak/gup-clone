package ua.com.gup.service.dictionary;

import java.util.List;
import java.util.Map;
import ua.com.gup.dto.dictionary.DictionaryMessageDTO;
import ua.com.gup.mongo.model.enumeration.Locale;

/**
 *
 */
public interface DictionaryService {

    public Map<String, String> getByLocale(Locale locale);

    public void saveMesage(Locale locale, String key, String messages);

    public void saveMesages(Locale locale, List<DictionaryMessageDTO> messages);

    public String get(Locale locale, String key);
}
