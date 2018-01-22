package ua.com.gup.common.repository;

import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.security.UserFunction;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserFunctionRepository {

    Collection<Function> getAll();

    Boolean exists(String name);

    Function findByName(String name);
}
