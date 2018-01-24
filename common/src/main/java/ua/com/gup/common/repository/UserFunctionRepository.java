package ua.com.gup.common.repository;

import ua.com.gup.common.model.security.Function;

import java.util.Collection;

public interface UserFunctionRepository {

    Collection<Function> getAll();

    Boolean exists(String name);

    Function findByName(String name);

    Function create(Function function);
}
