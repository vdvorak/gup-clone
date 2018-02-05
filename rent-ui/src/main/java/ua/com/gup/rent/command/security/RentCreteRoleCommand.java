package ua.com.gup.rent.command.security;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.dto.security.role.RoleDto;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;
import ua.com.gup.rent.command.RentCommand;

import static ua.com.gup.common.model.mongo.operation.OperationType.RENT_ROLE_CREATE;

public class RentCreteRoleCommand extends  AbstractRentRoleCommand{
    private RoleDto dto;

    public RentCreteRoleCommand(UserRoleService userRoleService, RoleDto dto) {
        super(userRoleService, null);
        this.dto = dto;
    }

    @Override
    public Role execute()  {
        role = userRoleService.create(dto);
        return role;
    }

    @Override
    public OperationType getOperationType() {
        return RENT_ROLE_CREATE;
    }


}
