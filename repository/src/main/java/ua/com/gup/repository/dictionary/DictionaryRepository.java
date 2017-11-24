package ua.com.gup.repository.dictionary;

import ua.com.gup.mongo.composition.domain.dictionary.Dictionary;
import ua.com.gup.mongo.model.enumeration.Locale;

/**
 *
 */
public interface DictionaryRepository {

    public Dictionary findOne(Locale locale);

    public void save(Dictionary dictionary);

    public String get(Locale locale, String key);
    
    public void delete(Locale locale, String key);

    public boolean isExists(Locale locale, String key);
}
