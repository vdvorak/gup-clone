package ua.com.gup.server.command.security;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_UPDATE;
import static ua.com.gup.common.model.mongo.operation.OperationType.SALE_ROLE_UPDATE;

public class SaleEditRoleCommand extends AbstractSaleRoleCommand {

    public SaleEditRoleCommand(UserRoleService userRoleService, Role role) {
        super(userRoleService, role);
    }

    @Override
    public Role execute() {
        return userRoleService.save(role);
    }

    @Override
    public OperationType getOperationType() {
        return SALE_ROLE_UPDATE;
    }


}
