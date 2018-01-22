package ua.com.gup.common.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.repository.UserFunctionRepository;
import ua.com.gup.common.security.UserFunction;
import ua.com.gup.common.security.UserRoleFunctions;

import java.util.*;

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
        return  mongoTemplate.exists(q, Role.class);
    }

    @Override
    public Function findByName(String name) {
        Query q = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(q, Function.class);
    }
}
