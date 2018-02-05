package ua.com.gup.rent.command.security;

import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_CREATE;
import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_DELETE;

public class RentDeleteRoleCommand extends AbstractRentRoleCommand {

    public RentDeleteRoleCommand(UserRoleService userRoleService, Role role) {
        super(userRoleService, role);
    }

    @Override
    public Role execute() {
        userRoleService.remove(role);
        return null;
    }

    @Override
    public OperationType getOperationType() {
        return RENT_ROLE_DELETE;
    }


}
