package ua.com.itproekt.gup.api.rest.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;

import java.util.Set;

@RestController
public class ProjectSearchRestController {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/search/autocomplete/project")
    public Set<String> getMachedNames(@RequestParam String term){
        return projectService.getMatchedNames(term);
    }
}
