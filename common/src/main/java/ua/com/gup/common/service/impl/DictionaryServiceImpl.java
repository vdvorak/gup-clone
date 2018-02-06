package ua.com.gup.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.common.dto.dictionary.DictionaryMessageDTO;
import ua.com.gup.common.model.Locale;
import ua.com.gup.common.model.mongo.dictionary.Dictionary;
import ua.com.gup.common.repository.DictionaryRepository;
import ua.com.gup.common.service.DictionaryService;

import java.util.List;
import java.util.Map;

/*

 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Override
    public Map<String, String> getByLocale(Locale locale) {
        return dictionaryRepository.findOne(locale).getMessages();
    }

    @Override
    public void saveMessage(Locale locale, String key, String messages) {
        Dictionary dictionary = dictionaryRepository.findOne(locale);
        dictionary.getMessages().put(key, messages);
        dictionaryRepository.save(dictionary);
    }

    @Override
    public void saveMessages(Locale locale, List<DictionaryMessageDTO> messages) {
        Dictionary dictionary = dictionaryRepository.findOne(locale);
        for (DictionaryMessageDTO message : messages) {
            dictionary.getMessages().put(message.getKey(), message.getValue());
        }
        dictionaryRepository.save(dictionary);
    }

    @Override
    public String get(Locale locale, String key) {
        return dictionaryRepository.get(locale, key);
    }

    @Override
    public String get(String key) {
        return get(Locale.ua, key);
    }

    @Override
    public void delete(Locale locale, String key) {
        dictionaryRepository.delete(locale, key);
    }

    @Override
    public boolean isExists(Locale locale, String key) {
        return dictionaryRepository.isExists(locale, key);
    }
}
