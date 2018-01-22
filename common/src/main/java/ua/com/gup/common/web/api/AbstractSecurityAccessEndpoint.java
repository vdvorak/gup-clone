package ua.com.gup.common.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.dto.security.function.FunctionDto;
import ua.com.gup.common.dto.security.function.FunctionsDto;
import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.dto.security.role.RoleEditDto;
import ua.com.gup.common.dto.security.role.RoleFullDto;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.security.UserFunction;
import ua.com.gup.common.service.UserRoleService;

import javax.validation.Valid;
import java.util.*;

import static ua.com.gup.common.security.UserRoleFunctions.ROLE_FUNCTIONS;


public abstract class AbstractSecurityAccessEndpoint {

    @Autowired
    private UserRoleService userRoleService;

    @PreAuthorize("hasAuthority('READ_ALL_ROLES')")
    @GetMapping(value = "/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<Role> roles = userRoleService.getAll();
        List<RoleDto> response = new ArrayList<>(roles.size());
        roles.forEach(r -> response.add(new RoleDto(r)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    @PostMapping(value = "/roles")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto dto) {
        if (userRoleService.existsRole(dto.getName())) {
            return new ResponseEntity("Role with name [" + dto.getName() + "] allready exists", HttpStatus.CONFLICT);
        }
        userRoleService.create(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('READ_ROLE')")
    @GetMapping(value = "/roles/{role}")
    public ResponseEntity<RoleFullDto> getRole(@PathVariable String role) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Role r = userRoleService.findByName(role);
        RoleFullDto response = new RoleFullDto(r);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @PutMapping(value = "/roles/{role}")
    public ResponseEntity<RoleDto> editRole(@PathVariable String role, @RequestBody RoleEditDto dto) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Role r = userRoleService.findByName(role);
        r.setTitle(dto.getTitle());
        Role save = userRoleService.save(r);
        return new ResponseEntity<>(new RoleDto(save), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @DeleteMapping(value = "/roles/{role}")
    public ResponseEntity deleteRole(@PathVariable String role) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Role r = userRoleService.findByName(role);
        if (!r.getIsEditable()) {
            return new ResponseEntity("role.not.editable", HttpStatus.BAD_REQUEST);
        }

        userRoleService.remove(r);
        return new ResponseEntity( HttpStatus.OK);
    }

    @GetMapping(value = "/functions")
    public ResponseEntity getAllFunctions() {
        Collection<Function> functions = userRoleService.getAllUserFunctions();
        List<FunctionDto> response = new ArrayList<>(functions.size());
        functions.forEach(r -> response.add(new FunctionDto(r)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/functions/{function}")
    public ResponseEntity<FunctionDto> getFunction(@PathVariable String function) {

        if (!userRoleService.existsFunction(function)) {
            return new ResponseEntity("error.function.notFound", HttpStatus.NOT_FOUND);
        }
        Function f = userRoleService.findFunctionByName(function);
        return new ResponseEntity<>(new FunctionDto(f), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('READ_ALL_ROLES')")
    @GetMapping(value = "/roles/{role}/functions")
    public ResponseEntity<List<FunctionDto>> getFunctionsByRole(@PathVariable String role) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Collection<Function> functions = userRoleService.getUserFunctionsByRole(role);
        List<FunctionDto> response = new ArrayList<>(functions.size());
        functions.forEach(r -> response.add(new FunctionDto(r)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('READ_ALL_ROLES')")
    @Deprecated
    @PostMapping(value = "/roles/{role}/functions/{function}")
    public ResponseEntity<List<FunctionDto>> addFunctionToRole(
            @PathVariable String role,
            @PathVariable String function) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity("error.role.notFound", HttpStatus.NOT_FOUND);
        }

        if (!userRoleService.existsFunction(function)) {
            return new ResponseEntity("error.function.notFound", HttpStatus.NOT_FOUND);
        }
        userRoleService.addFunctionToRole(function, role);
        return getFunctionsByRole(role);
    }

    @PreAuthorize("hasAuthority('ADD_FUNCTIONS_TO_ROLE')")
    @PutMapping(value = "/roles/{role}/functions")
    public ResponseEntity<List<FunctionDto>> addFunctionsToRole(
            @PathVariable String role,
            @RequestBody FunctionsDto functions) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity("error.role.notFound", HttpStatus.NOT_FOUND);
        }

        userRoleService.addFunctionsToRole(functions.getFunctions(), role);
        return getFunctionsByRole(role);
    }

    //@PreAuthorize("hasAuthority('READ_ALL_ROLES')")
    @Deprecated
    @DeleteMapping(value = "/roles/{role}/functions/{function}")
    public ResponseEntity<List<FunctionDto>> removeFunctionFromRole(
            @PathVariable String role,
            @PathVariable String function) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity("error.role.notFound", HttpStatus.NOT_FOUND);
        }

        if (!userRoleService.existsFunction(function)) {
            return new ResponseEntity("error.function.notFound", HttpStatus.NOT_FOUND);
        }

        userRoleService.removeFunctionToRole(function, role);

        return getFunctionsByRole(role);
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE_FUNCTIONS')")
    @DeleteMapping(value = "/roles/{role}/functions}")
    public ResponseEntity<List<FunctionDto>> removeFunctionsFromRole(
            @PathVariable String role,
            @RequestBody FunctionsDto functions) {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity("error.role.notFound", HttpStatus.NOT_FOUND);
        }
        userRoleService.removeFunctionsToRole(functions.getFunctions(), role);
        return getFunctionsByRole(role);
    }

//    @PreAuthorize("hasAuthority('READ_ALL_ROLES')")
//    @GetMapping(value = "/{role}/{function}")
//    public ResponseEntity<Set<UserFunction>> addFunctionToRole(@PathVariable CommonUserRole role) {
//        return new ResponseEntity<>(userRoleService.getUserFunctionsByRole(role), HttpStatus.OK);
//    }




    @GetMapping(value = "/memmory/{role}/functions")
    public ResponseEntity<List> test(@PathVariable String role) {

        CommonUserRole r = CommonUserRole.valueOf(role);
        Set<UserFunction> functions1 = ROLE_FUNCTIONS.get(r);
        List<Map> response = new ArrayList<>();

        for (UserFunction f : functions1) {
            Map<String, Object>res = new HashMap<>();
            res.put("name", f.name());
            res.put("title", f.getTitle());
            res.put("paths", f.getPaths());
            response.add(res);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
