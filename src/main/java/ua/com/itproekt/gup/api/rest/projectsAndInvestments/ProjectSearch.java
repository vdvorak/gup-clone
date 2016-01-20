package ua.com.itproekt.gup.api.rest.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;

import java.util.List;

@RestController
public class ProjectSearch {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/search/project")
    public List<String> getMachedNames(@RequestParam String term){
        return projectService.getMatchedNames(term);
    }
}
