package ua.com.gup.rent.command.security;

import ua.com.gup.common.dto.security.function.FunctionsDto;
import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import java.util.List;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_ADD_FUNCTIONS;
import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_UPDATE;

public class RentAddFunctionsRoleCommand extends AbstractRentRoleCommand {

    private List<String> functions;
    public RentAddFunctionsRoleCommand(UserRoleService userRoleService, Role role, List<String> functions) {
        super(userRoleService, role);
        this.functions = functions;
    }

    @Override
    public Role execute() {
        userRoleService.addFunctionsToRole(functions, role);
        return null;
    }

    @Override
    public OperationType getOperationType() {
        return RENT_ROLE_ADD_FUNCTIONS;
    }


}
