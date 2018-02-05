package ua.com.gup.server.command.security;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_DELETE;
import static ua.com.gup.common.model.mongo.operation.OperationType.SALE_ROLE_DELETE;

public class SaleDeleteRoleCommand extends AbstractSaleRoleCommand {

    public SaleDeleteRoleCommand(UserRoleService userRoleService, Role role) {
        super(userRoleService, role);
    }

    @Override
    public Role execute() {
        userRoleService.remove(role);
        return null;
    }

    @Override
    public OperationType getOperationType() {
        return SALE_ROLE_DELETE;
    }


}
