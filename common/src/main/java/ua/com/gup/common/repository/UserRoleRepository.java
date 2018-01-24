package ua.com.gup.common.repository;

import ua.com.gup.common.model.security.Role;

import java.util.List;

public interface UserRoleRepository {

    List<Role> getAll();

    Role findByName(String name);

    Boolean exists(String name);

    Role create(Role role);

    Role save(Role role);

    void remove(Role role);

//    Set<UserFunction> getUserFunctionsByRole(CommonUserRole role);
//
//    void addUserFunctionsToRole(UserFunction function, CommonUserRole role);
//
//    void removeUserFunctionsToRole(UserFunction function, CommonUserRole role);
}
