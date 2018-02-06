package ua.com.gup.rent.command.security;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.UserRoleService;
import ua.com.gup.rent.command.RentCommand;

public abstract class AbstractRentRoleCommand extends RentCommand<Role> {

    protected UserRoleService userRoleService;
    protected Role role;

    public AbstractRentRoleCommand(UserRoleService userRoleService, Role role) {
        this.userRoleService = userRoleService;
        this.role = role;
    }

    @Override
    public Journalable getJournalable() {
        return new Journalable() {

            @Override
            public Object getObject() {
                return  role;
            }

            @Override
            public String getObjectId() {
                return role.getName();
            }

            @Override
            public String getObjectType() {
                return ObjectType.ROLE;
            }
        };
    }
}
