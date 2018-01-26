package ua.com.gup.common.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.repository.UserRoleRepository;

import java.util.List;


public abstract class CommonUserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Role> getAll() {
        return  mongoTemplate.findAll(Role.class);
    }

    @Override
    public Role findByName(String name) {
        Query q = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(q, Role.class);
    }

    @Override
    public Boolean exists(String name) {
        Query q = new Query(Criteria.where("name").is(name));
        return mongoTemplate.exists(q, Role.class);
    }

    @Override
    public Role create(Role role) {
        mongoTemplate.insert(role);
        return role;
    }

    @Override
    public Role save(Role role) {
        mongoTemplate.save(role);
        return role;
    }

    @Override
    public void remove(Role role) {
        mongoTemplate.remove(role);
    }

//    @Override
//    public Set<UserFunction> getUserFunctionsByRole(CommonUserRole role) {
//        return UserRoleFunctions.ROLE_FUNCTIONS.get(role);
//    }
//
//    @Override
//    public void addUserFunctionsToRole(UserFunction function, CommonUserRole role) {
//        UserRoleFunctions.ROLE_FUNCTIONS.get(role).add(function);
//    }
//
//    @Override
//    public void removeUserFunctionsToRole(UserFunction function, CommonUserRole role) {
//        UserRoleFunctions.ROLE_FUNCTIONS.get(role).remove(function);
//    }
}
