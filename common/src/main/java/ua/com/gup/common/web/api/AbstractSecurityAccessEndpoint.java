package ua.com.gup.common.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.command.Command;
import ua.com.gup.common.command.SessionCommandExecutor;
import ua.com.gup.common.dto.security.function.FunctionDto;
import ua.com.gup.common.dto.security.function.FunctionsDto;
import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.dto.security.role.RoleEditDto;
import ua.com.gup.common.dto.security.role.RoleFullDto;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class AbstractSecurityAccessEndpoint {

    @Autowired
    protected UserRoleService userRoleService;

    protected abstract Command<Role> getCreateCommand(RoleDto dto);

    protected abstract Command<Role> getEditCommand(Role role);

    protected abstract Command<Role> getDeleteCommand(Role role);

    protected abstract Command<Role> getAddFunctionsCommand(Role role, List<String> functions);

    protected abstract Command<Role> getRemmoveFunctionsCommand(Role role, List<String> functions);

    @Autowired
    private SessionCommandExecutor executor;

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
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto dto) throws Exception {
        if (userRoleService.existsRole(dto.getName())) {
            return new ResponseEntity("Role with name [" + dto.getName() + "] allready exists", HttpStatus.CONFLICT);
        }

        executor.doCommand(getCreateCommand(dto));
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
    public ResponseEntity<RoleDto> editRole(@PathVariable String role, @RequestBody RoleEditDto dto) throws Exception {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Role r = userRoleService.findByName(role);
        r.setTitle(dto.getTitle());
        executor.doCommand(getEditCommand(r));
        return new ResponseEntity<>(new RoleDto(r), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @DeleteMapping(value = "/roles/{role}")
    public ResponseEntity deleteRole(@PathVariable String role) throws Exception {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (userRoleService.isRoleUsed(role)) {
            return new ResponseEntity("role is used", HttpStatus.CONFLICT);
        }

        Role r = userRoleService.findByName(role);
        if (!r.getIsEditable()) {
            return new ResponseEntity("role.not.editable", HttpStatus.BAD_REQUEST);
        }

        executor.doCommand(getDeleteCommand(r));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/functions")
    public ResponseEntity getAllFunctions() {
        Collection<Function> functions = userRoleService.getAllUserFunctions();
        List<FunctionDto> response = new ArrayList<>(functions.size());
        functions.forEach(r -> response.add(new FunctionDto(r)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_FUNCTION')")
    @PostMapping(value = "/functions")
    public ResponseEntity createFunction(@Valid @RequestBody FunctionDto dto) {
        if (userRoleService.existsFunction(dto.getName())) {
            return new ResponseEntity("Function with name [" + dto.getName() + "] allready exists", HttpStatus.CONFLICT);
        }
        userRoleService.createFunction(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
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

    @PreAuthorize("hasAuthority('ADD_FUNCTIONS_TO_ROLE')")
    @PutMapping(value = "/roles/{role}/functions")
    public ResponseEntity<List<FunctionDto>> addFunctionsToRole(
            @PathVariable String role,
            @RequestBody FunctionsDto functions) throws Exception {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity("error.role.notFound", HttpStatus.NOT_FOUND);
        }

        Role r = userRoleService.findByName(role);
        executor.doCommand(getAddFunctionsCommand(r, functions.getFunctions()));
        return getFunctionsByRole(role);
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE_FUNCTIONS')")
    @DeleteMapping(value = "/roles/{role}/functions")
    public ResponseEntity<List<FunctionDto>> removeFunctionsFromRole(
            @PathVariable String role,
            @RequestBody FunctionsDto functions) throws Exception {
        if (!userRoleService.existsRole(role)) {
            return new ResponseEntity("error.role.notFound", HttpStatus.NOT_FOUND);
        }
        Role r = userRoleService.findByName(role);
        executor.doCommand(getRemmoveFunctionsCommand(r, functions.getFunctions()));
        return getFunctionsByRole(role);
    }

}
