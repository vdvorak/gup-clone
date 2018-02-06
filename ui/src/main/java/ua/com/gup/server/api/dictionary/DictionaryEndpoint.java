package ua.com.gup.server.api.dictionary;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.common.dto.dictionary.DictionaryMessageDTO;
import ua.com.gup.common.model.Locale;
import ua.com.gup.common.service.DictionaryService;

/**
 *
 */
@RestController
@RequestMapping("/api/translates/dictionaries")
public class DictionaryEndpoint {

    private final Logger log = LoggerFactory.getLogger(DictionaryEndpoint.class);

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/{locale}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getByLocale(@PathVariable Locale locale) {
        Map<String, String> messages = dictionaryService.getByLocale(locale);
        return new ResponseEntity(messages, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DICTIONARY_CREATE')")
    @RequestMapping(value = "/{locale}", method = RequestMethod.POST)
    public ResponseEntity<Void> editMessages(
            @PathVariable Locale locale,
            @RequestBody List<DictionaryMessageDTO> messages) {
        dictionaryService.saveMessages(locale, messages);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('DICTIONARY_EDIT')")
    @RequestMapping(value = "/{locale}/{key}", method = RequestMethod.POST)
    public ResponseEntity<Void> editMessage(
            @PathVariable Locale locale,
            @PathVariable String key,
            @RequestBody(required = false) String message) {
        if(dictionaryService.isExists(locale,key)){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        dictionaryService.saveMessage(locale, key, message);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{locale}/{key}", method = RequestMethod.GET)
    public ResponseEntity<DictionaryMessageDTO> getByLocaleAndKey(
            @PathVariable Locale locale,
            @PathVariable String key) {

        String message = dictionaryService.get(locale, key);
        if (message == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        DictionaryMessageDTO dto = new DictionaryMessageDTO(key, message);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DICTIONARY_DELETE')")
    @RequestMapping(value = "/{locale}/{key}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMessage(
            @PathVariable Locale locale,
            @PathVariable String key,
            @RequestBody(required = false) String message) {
        if (!dictionaryService.isExists(locale, key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        dictionaryService.delete(locale, key);
        return new ResponseEntity(HttpStatus.OK);
    }
}
