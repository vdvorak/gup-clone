package ua.com.gup.rent.command.security;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import java.util.List;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_REMOVE_FUNCTIONS;

public class RentRemoveFunctionsRoleCommand extends AbstractRentRoleCommand {

    private List<String> functions;
    public RentRemoveFunctionsRoleCommand(UserRoleService userRoleService, Role role, List<String> functions) {
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
        return RENT_ROLE_REMOVE_FUNCTIONS;
    }


}
