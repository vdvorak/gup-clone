package ua.com.gup.common.service;

import ua.com.gup.common.dto.security.function.FunctionDto;
import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.model.security.Role;

import java.util.Collection;
import java.util.List;

public interface UserRoleService {

    List<Role> getAll();

    Role findByName(String name);

    Role create(RoleDto dto);

    Role save(Role role);

    void remove(Role role);

    Collection<Function> getAllUserFunctions();

    Function findFunctionByName(String name);

    Collection<Function> getUserFunctionsByRole(String name);

    Function createFunction(FunctionDto dto);

    void addFunctionToRole(String function, String role);

    void addFunctionsToRole(List<String> functions, Role role);

    void removeFunctionToRole(String function, Role role);

    void removeFunctionsToRole(List<String> functions, String role);

    boolean existsRole(String name);

    boolean existsFunction(String name);

    boolean isRoleUsed(String role);
}
