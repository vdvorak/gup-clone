package ua.com.gup.common.dto.security.role;

import lombok.Data;
import ua.com.gup.common.dto.security.function.FunctionDto;
import ua.com.gup.common.model.security.Role;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleFullDto extends RoleDto {

    List<FunctionDto> functions;

    public RoleFullDto(Role role) {
        super(role);
        functions = new ArrayList<>(role.getFunctions().size());
        role.getFunctions().forEach(r -> functions.add(new FunctionDto(r)));
    }
}
