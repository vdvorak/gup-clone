package ua.com.gup.common.security;

import ua.com.gup.common.model.security.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ua.com.gup.common.security.UserFunction.*;

//use Role.java UserRoleService.java
@Deprecated()
public class UserRoleFunctions {

    public static final Map<String,Set<UserFunction>> ROLE_FUNCTIONS = new HashMap<>();
    static {
        Set<UserFunction> manager = new HashSet<>();
        manager.add(SEARCH_PROFILES_MANAGER);
        manager.add(READ_PROFILE_MANAGER);
        manager.add(LINK_USER_TO_MANAGER);
        manager.add(UNLINK_USER_TO_MANAGER);
        manager.add(GET_MANAGER_USERS);
        manager.add(GET_MANAGER_USER);
        manager.add(UPDATE_PROFILE_CONTACT_INFO_MANAGER);
        manager.add(SEARCH_OFFERS_MANAGER);
        manager.add(READ_OFFER_MANAGER);
        manager.add(CLONE_OFFER);

        manager.add(SEARCH_MANAGER_PROFILES);


        //Manager ACTION functions
        manager.add(SEARCH_MANAGER_ACTIONS);
        manager.add(READ_MANAGER_ACTION);
        manager.add(CREATE_MANAGER_ACTION);
        manager.add(UPDATE_MANAGER_ACTION);
        manager.add(DELETE_MANAGER_ACTION);
        ROLE_FUNCTIONS.put(Role.ROLE_MANAGER, manager);

        Set<UserFunction> moderator = new HashSet<>();
        moderator.add(UPDATE_COMPLAINT_STATUS);
        moderator.add(UPDATE_COMPLAINT_TYPE);
        moderator.add(UPDATE_COMPLAINT_TYPES);
        moderator.add(READ_COMPLAINT);
        moderator.add(READ_COMPLAINTS_BY_OFFER);
        moderator.add(SEARCH_COMPLAINTS_BY_INITIATOR);
        moderator.add(SEARCH_COMPLAINTS_BY_STATUS);
        moderator.add(SEARCH_COMPLAINTS_BY_FILTER);
        moderator.add(READ_MODERATOR_OFFERS_BY_STATUS);
        moderator.add(READ_MODERATOR_OFFERS_BY_FILTER);
        moderator.add(READ_OFFERS_VIEW);
        moderator.add(UPDATE_MODERATOR_OFFER);
        ROLE_FUNCTIONS.put(Role.ROLE_MODERATOR, moderator);

        Set<UserFunction> user = new HashSet<>();
        user.add(CREATE_COMPLAINT);
        user.add(CREATE_OFFER);
        user.add(READ_OFFER);
        user.add(DELETE_OFFER);
        user.add(UPDATE_OFFER_STATUS);
        user.add(UPDATE_MODERATOR_OFFER);
        user.add(CREATE_OFFER_IMAGE);
        user.add(DELETE_OFFER_IMAGE);

        ROLE_FUNCTIONS.put(Role.ROLE_USER, user);


        Set<UserFunction> admin = new HashSet<>();
        admin.addAll(user);
        admin.addAll(moderator);
        admin.addAll(manager);

        admin.add(CREATE_CATEGORY);
        admin.add(READ_CATEGORY);
        admin.add(UPDATE_CATEGORY);
        admin.add(DELETE_CATEGORY);
        admin.add(READ_ALL_CATEGORIES);

        admin.add(CREATE_CATEGORY_ATTRIBUTE);
        admin.add(READ_CATEGORY_ATTRIBUTE);
        admin.add(UPDATE_CATEGORY_ATTRIBUTE);
        admin.add(DELETE_CATEGORY_ATTRIBUTE);
        admin.add(READ_ALL_CATEGORY_ATTRIBUTES);

        admin.add(SEARCH_PROFILES_ADMIN);
        admin.add(READ_PROFILE_ADMIN);
        admin.add(CREATE_PROFILE_ADMIN);
        admin.add(UPDATE_PROFILE_ADMIN);
        admin.add(BAN_PROFILE);
        admin.add(UNBAN_PROFILE);
        admin.add(DELETE_PROFILE_IMAGE);

        admin.add(DICTIONARY_CREATE);
        admin.add(DICTIONARY_EDIT);
        admin.add(DICTIONARY_DELETE);

        admin.add(READ_ALL_ROLES);
        admin.add(READ_ROLE);
        admin.add(CREATE_ROLE);
        admin.add(DELETE_ROLE);
        admin.add(EDIT_ROLE);
        admin.add(ADD_FUNCTIONS_TO_ROLE);
        admin.add(DELETE_ROLE_FUNCTIONS);


        ROLE_FUNCTIONS.put(Role.ROLE_ADMIN, admin);

    }
}
