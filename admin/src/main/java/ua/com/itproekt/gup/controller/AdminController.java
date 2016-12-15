package ua.com.itproekt.gup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kobylyatskyy Alexander
 */
@Controller
public class AdminController {

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
    @RequestMapping("/admin-offers-new")
    public String getAdminNewOffers() {
        return "admin/admin-offers-new";
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-offers-edited")
    public String getAdminEditedOffers() {
        return "admin/admin-offers-edited";
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-tariffs")
    public String getAdminTariffs() {
        return "admin/admin-tariffs";
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-edit-tariffs")
    public String editAdminTariffs() {
        return "admin/admin-edit-tariffs";
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-landlord-rents")
    public String getAdminRents() {
        return "admin/admin-landlord-rents";
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-tenant-rents")
    public String getAdminRentsEdit() {
        return "admin/admin-tenant-rents";
    }

//    @RequestMapping("/account")
//    public String getAccount() {
//        return "account";
//    }

    @RequestMapping("/admin-admins")
    public String getAdmins() {
        return "admin/admin-admins";
    }

}
