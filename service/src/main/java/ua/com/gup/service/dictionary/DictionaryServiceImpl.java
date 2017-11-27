package ua.com.gup.service.dictionary;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.dto.dictionary.DictionaryMessageDTO;
import ua.com.gup.mongo.composition.domain.dictionary.Dictionary;
import ua.com.gup.mongo.model.enumeration.Locale;
import ua.com.gup.repository.dictionary.DictionaryRepository;

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
    public void saveMesage(Locale locale, String key, String messages) {
        Dictionary dictionary = dictionaryRepository.findOne(locale);
        dictionary.getMessages().put(key, messages);
        dictionaryRepository.save(dictionary);
    }

    @Override
    public void saveMesages(Locale locale, List<DictionaryMessageDTO> messages) {
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
    public void delete(Locale locale, String key){
        dictionaryRepository.delete(locale, key);
    }
    
    
    @Override
    public boolean isExists(Locale locale, String key){
        return dictionaryRepository.isExists(locale, key);
    }
}
