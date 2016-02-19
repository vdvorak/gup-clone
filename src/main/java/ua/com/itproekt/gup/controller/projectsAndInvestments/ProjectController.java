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
//        @RequestParam(required = false, defaultValue = "0") int pageNum
//        ProjectFilterOptions projectFO = new ProjectFilterOptions();
//        if (EnumUtils.isValidEnum(ProjectType.class, type.toUpperCase()) ) {
//            projectFO.setType(ProjectType.valueOf(type.toUpperCase()));
//        }
//        projectFO.setSkip(pageNum);
//
//        model.addAttribute("projectFO", new Gson().toJson(projectFO) );
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



}
