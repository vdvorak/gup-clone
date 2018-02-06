package ua.com.gup.common.repository;

import ua.com.gup.common.model.Locale;
import ua.com.gup.common.model.mongo.dictionary.Dictionary;

/**
 *
 */
public interface DictionaryRepository {

    Dictionary findOne(Locale locale);

    void save(Dictionary dictionary);

    String get(Locale locale, String key);
    
    void delete(Locale locale, String key);

    boolean isExists(Locale locale, String key);
}
