package ua.com.gup.common.repository;

import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.security.UserFunction;

import java.util.List;
import java.util.Set;

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
