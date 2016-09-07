package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.service.profile.ProfilesService;

@Controller
public class AdminController {

    @Autowired
    ProfilesService profilesService;


//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin")
    public String getLoginForm() {
        return "admin/admin-main";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-users")
    public String getAdminUsers() {
        return "admin/admin-users";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-offers")
    public String getAdminOffers() {
        return "admin/admin-offers";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-tariffs")
    public String getAdminTariffs() {
        return "admin/admin-tariffs";
    }

//    @RequestMapping("/admin-admins")
    public String getAdmins() {
        return "admin/admin-admins";
    }

}
