package ua.com.gup.rent.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.common.command.Command;
import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.web.api.AbstractSecurityAccessEndpoint;
import ua.com.gup.rent.command.security.*;

import java.util.List;

@RestController
@RequestMapping("/api/security")
public class SecurityAccessEndpoint extends AbstractSecurityAccessEndpoint {
    @Override
    protected Command<Role> getCreateCommand(RoleDto dto) {
        return new RentCreteRoleCommand(userRoleService, dto);
    }

    @Override
    protected Command<Role> getDeleteCommand(Role role) {
        return new RentDeleteRoleCommand(userRoleService, role);
    }

    @Override
    protected Command<Role> getEditCommand(Role role) {
        return new RentEditRoleCommand(userRoleService, role);
    }

    @Override
    protected Command<Role> getAddFunctionsCommand(Role role, List<String> functions) {
        return new RentAddFunctionsRoleCommand(userRoleService, role, functions);
    }

    @Override
    protected Command<Role> getRemmoveFunctionsCommand(Role role, List<String> functions) {
        return new RentRemoveFunctionsRoleCommand(userRoleService, role, functions);
    }

}
