package ua.com.gup.rent.command.security;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_DELETE;
import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_UPDATE;

public class RentEditRoleCommand extends AbstractRentRoleCommand {

    public RentEditRoleCommand(UserRoleService userRoleService, Role role) {
        super(userRoleService, role);
    }

    @Override
    public Role execute() {
        return userRoleService.save(role);
    }

    @Override
    public OperationType getOperationType() {
        return RENT_ROLE_UPDATE;
    }


}
