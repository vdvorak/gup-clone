package ua.com.gup.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.gup.common.dto.security.function.FunctionDto;
import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.common.repository.UserFunctionRepository;
import ua.com.gup.common.repository.UserRoleRepository;
import ua.com.gup.common.security.UserFunction;
import ua.com.gup.common.service.UserRoleService;

import java.util.*;


public abstract class CommonUserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository roleRepository;
    @Autowired
    private UserFunctionRepository functionRepository;
    @Autowired
    private CommonProfileRepository profileRepository;

    private static final Set<UserFunction> functions = new HashSet<>(Arrays.asList(UserFunction.values()));

    @Override
    public List<Role> getAll() {
        return roleRepository.getAll();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }


    @Override
    public Role create(RoleDto dto) {
        Role role = new Role();
        role.setTitle(dto.getTitle());
        role.setName(dto.getName());
        role.setIsEditable(true);
        return roleRepository.create(role);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void remove(Role role) {
        roleRepository.remove(role);
    }

    @Override
    public Collection<Function> getAllUserFunctions() {
        return functionRepository.getAll();
    }

    @Override
    public Function findFunctionByName(String name) {
        return functionRepository.findByName(name);
    }

    @Override
    public Collection<Function> getUserFunctionsByRole(String name) {
        Role r = roleRepository.findByName(name);
        if (r == null) {
            return Collections.EMPTY_SET;
        }
        return r.getFunctions();
    }

    @Override
    public Function createFunction(FunctionDto dto) {
        Function function = new Function();
        function.setTitle(dto.getTitle());
        function.setName(dto.getName());
        function.setPaths(dto.getPaths());
        return functionRepository.create(function);
    }

    @Override
    public void addFunctionToRole(String function, String role) {
        Role r = roleRepository.findByName(role);
        Function f = functionRepository.findByName(function);
        r.getFunctions().add(f);
        roleRepository.save(r);
    }

    @Override
    public void addFunctionsToRole(List<String> functions, Role role) {
        for (String function : functions) {
            Function f = functionRepository.findByName(function);
            if (f != null) {
                role.getFunctions().add(f);
            }
        }
        roleRepository.save(role);
    }

    @Override
    public void removeFunctionToRole(String function, Role role) {
        Function f = functionRepository.findByName(function);
        role.getFunctions().remove(f);
        roleRepository.save(role);
    }

    @Override
    public void removeFunctionsToRole(List<String> functions, String role) {
        Role r = roleRepository.findByName(role);
        for (String function : functions) {
            Function f = functionRepository.findByName(function);
            r.getFunctions().remove(f);
        }
        roleRepository.save(r);
    }

    @Override
    public boolean existsRole(String name) {
        return roleRepository.exists(name);
    }

    @Override
    public boolean existsFunction(String name) {
        return functionRepository.exists(name);
    }



    @Override
    public boolean isRoleUsed(String role) {
        return profileRepository.existsByRole(role);
    }
}
