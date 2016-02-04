package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;

/**
 * Created by RAYANT on 24.12.2015.
 */
@Controller
public class AdminController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    TenderService tenderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin")
    public String getLoginForm() {
        return "admin/adminPage";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-users")
    public String getAdminUsers() {
        return "admin/admin-users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-offers")
    public String getAdminOffers() {
        return "admin/admin-offers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-tenders")
    public String getAdminTenders() {
        return "admin/admin-tenders";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-projects")
    public String getAdminProjects() {
        return "admin/admin-projects";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-news")
    public String getAdminNews() {
        return "admin/admin-news";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-blogs")
    public String getAdminBlogs() {
        return "admin/admin-blogs";
    }

    @RequestMapping("/admin-admins")
    public String getAdmins() {
        return "admin/admin-admins";
    }

}
