package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.dao.projectsAndInvestments.project.ProjectRepository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Comment;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.TypeOfProject;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;


@Controller
public class ProjectsAndInvestmentsTestController {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @RequestMapping("/addProjects/{numberOfProjects}")
    public String addUser(@PathVariable("numberOfProjects") int numberOfProjects, Model model) {
        for (int i = 0; i < numberOfProjects; i++) {

            Project project = new Project();
                Profile profile = new Profile();
                profile.setId("560e99243c422930019f3381");
//            project.setAuthor(profile);   ///////
            project.setInvestedAmount(1000);
            project.setProjectName("project name");
                HashSet<Comment> set = new HashSet<>();
                Comment comment = new Comment();
                comment.setComment("comment");
                comment.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
                set.add(comment);
            project.setComments(set);
            project.setTypeOfProject(TypeOfProject.KNOW_HOW);
            projectRepository.create(project);
        }

        model.addAttribute("message", numberOfProjects + " test projects is created.");
        return "index";
    }

    @RequestMapping("/project/payback")
    public String payback() {
        projectService.bringBackMoneyToInvestorsTest();
        return "index";
    }


}
