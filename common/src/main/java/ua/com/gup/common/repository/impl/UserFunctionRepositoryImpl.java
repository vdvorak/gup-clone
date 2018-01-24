package ua.com.gup.common.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.repository.UserFunctionRepository;

import java.util.Collection;

@Repository
public class UserFunctionRepositoryImpl implements UserFunctionRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Collection<Function> getAll() {
        return mongoTemplate.findAll(Function.class);

    }


    @Override
    public Boolean exists(String name) {
        Query q = new Query(Criteria.where("name").is(name));
        return  mongoTemplate.exists(q, Function.class);
    }

    @Override
    public Function findByName(String name) {
        Query q = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(q, Function.class);
    }

    @Override
    public Function create(Function function) {
        mongoTemplate.insert(function);
        return function;
    }
}
