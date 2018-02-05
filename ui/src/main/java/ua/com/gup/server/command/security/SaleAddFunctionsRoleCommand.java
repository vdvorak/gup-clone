package ua.com.gup.server.command.security;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import java.util.List;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_ADD_FUNCTIONS;
import static ua.com.gup.common.model.mongo.operation.OperationType.SALE_ROLE_ADD_FUNCTIONS;

public class SaleAddFunctionsRoleCommand extends AbstractSaleRoleCommand {

    private List<String> functions;
    public SaleAddFunctionsRoleCommand(UserRoleService userRoleService, Role role, List<String> functions) {
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
        return SALE_ROLE_ADD_FUNCTIONS;
    }


}
