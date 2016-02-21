package ua.com.itproekt.gup.controller.projectsAndInvestments;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProjectController {

    @RequestMapping("/project")
    public String getProjectById(@RequestParam String id) {
        return "projectsAndInvestments/project/project";
    }

    @RequestMapping("/project/list")
    public String getProjects(@RequestParam(required = false) String type,
                              @RequestParam(required = false) String name) {
        return "projectsAndInvestments/project/projectList";
    }

    @RequestMapping("/project/create")
    public String createProject() {
        return "projectsAndInvestments/project/createProject";
    }

    @RequestMapping("/project/edit")
    public String editProject(@RequestParam String id) {
        return "projectsAndInvestments/project/editProject";
    }
}
