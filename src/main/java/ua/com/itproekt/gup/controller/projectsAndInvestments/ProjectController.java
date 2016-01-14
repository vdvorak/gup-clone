package ua.com.itproekt.gup.controller.projectsAndInvestments;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Sasha on 13.01.2016.
 */
@Controller
public class ProjectController {

    @RequestMapping("/projectList")
    public String getProjects() {
        return "projectsAndInvestments/project/projectList";
    }

    @RequestMapping("/createProject")
    public String createProject() {
        return "projectsAndInvestments/project/createProject";
    }

    @RequestMapping("/project/id/{projectId}")
    public String getProjectById(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "projectsAndInvestments/project/project";
    }
}
