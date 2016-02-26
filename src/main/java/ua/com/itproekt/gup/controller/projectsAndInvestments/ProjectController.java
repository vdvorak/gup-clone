package ua.com.itproekt.gup.controller.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ModerationStatus;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/project")
    public String getProjectById(@RequestParam String id, HttpServletRequest request) {
        Project project = projectService.findById(id);
        if (project == null) {
            throw new ResourceNotFoundException();
        }

        if (ModerationStatus.COMPLETE.equals(project.getModerationStatus()) ||
                project.getAuthorId().equals(SecurityOperations.getLoggedUserId()) ||
                request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {

            return "projectsAndInvestments/project/project";
        } else {
            throw new AccessDeniedException("The project is not moderated by admin");
        }
    }

    @RequestMapping("/project/list")
    public String getProjects(@RequestParam(required = false) String type,
                              @RequestParam(required = false) String name) {
        return "projectsAndInvestments/project/projectList";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/project/create")
    public String createProject() {
        return "projectsAndInvestments/project/createProject";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/project/edit")
    public String editProject(@RequestParam String id, HttpServletRequest request) {
        if (!projectService.projectExists(id)) {
            throw new ResourceNotFoundException();
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (projectService.findById(id).getAuthorId().equals(userId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            return "projectsAndInvestments/project/editProject";
        } else {
            throw new AccessDeniedException("Editing of project is allowed only for owner or admin");
        }
    }
}
