package ua.com.gup.server.command.security;

import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;

import static ua.com.gup.common.model.mongo.operation.OperationType.SALE_ROLE_CREATE;

public class SaleCreteRoleCommand extends AbstractSaleRoleCommand {
    private RoleDto dto;

    public SaleCreteRoleCommand(UserRoleService userRoleService, RoleDto role) {
        super(userRoleService, null);
    }

    @Override
    public Role execute()  {
        role = userRoleService.create(dto);
        return role;
    }

    @Override
    public OperationType getOperationType() {
        return SALE_ROLE_CREATE;
    }


}
