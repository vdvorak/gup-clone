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
    public String getProjects(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                              Model model) {
        model.addAttribute("pageNumber", pageNumber);
        return "projectsAndInvestments/project/projectList";
    }

//    @RequestMapping("/project/search")
//    public String getProjects(@RequestBody ProjectFilterOptions projectFO,
//                              @RequestParam int pageNumber, Model model) {
//        projectFO.setSkip(pageNumber);
//
//        model.addAttribute("pageNumber", pageNumber);
//        model.addAttribute("projectFO", projectFO);
//        return "projectsAndInvestments/project/projectSearchList";
//    }

    @RequestMapping("/createProject")
    public String createProject() {
        return "projectsAndInvestments/project/createProject";
    }

    @RequestMapping("/editProject/id/{projectId}")
    public String editProject(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "projectsAndInvestments/project/editProject";
    }

    @RequestMapping("/project/id/{projectId}")
    public String getProjectById(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "projectsAndInvestments/project/project";
    }

}
