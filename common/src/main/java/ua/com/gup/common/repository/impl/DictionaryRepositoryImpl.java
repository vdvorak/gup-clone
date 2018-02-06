/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.repository.impl;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.common.model.Locale;
import ua.com.gup.common.repository.DictionaryRepository;
import ua.com.gup.common.model.mongo.dictionary.Dictionary;

@Repository
public class DictionaryRepositoryImpl implements DictionaryRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Dictionary.class)) {
            mongoTemplate.createCollection(Dictionary.class);
        }
        for (Locale l : Locale.values()) {
            Dictionary dicitonary = mongoTemplate.findById(l.name(), Dictionary.class);
            if (dicitonary == null) {
                dicitonary = new Dictionary(l.name(), new HashMap());
                mongoTemplate.save(dicitonary);
            }
        }
    }

    @Override
    public Dictionary findOne(Locale locale) {
        return mongoTemplate.findById(locale.name(), Dictionary.class);
    }

    @Override
    public void save(Dictionary dictionary) {
        mongoTemplate.save(dictionary);
    }

    @Override
    public String get(Locale locale, String key) {
        String field = "messages." + key;
        Criteria criteria = Criteria.where("_id").is(locale.name())
                .andOperator(Criteria.where(field).exists(true));
        Query query = new Query(criteria);
        query.fields().include(field);
        Dictionary dictionary = mongoTemplate.findOne(query, Dictionary.class);
        if (dictionary == null) {
            return null;
        }
        return dictionary.getMessages().get(key);
    }

    @Override
    public void delete(Locale locale, String key) {
        Dictionary dictionary = mongoTemplate.findById(locale.name(), Dictionary.class);
        dictionary.getMessages().remove(key);
        mongoTemplate.save(dictionary);
    }

    @Override
    public boolean isExists(Locale locale, String key) {
        Dictionary dictionary = findOne(locale);
        if (dictionary == null) {
            return false;
        } else {
            return dictionary.getMessages().containsKey(key);
        }
    }

}
