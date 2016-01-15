package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Comment;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.TypeOfProject;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;

import java.util.HashSet;


@Controller
public class ProjectsAndInvestmentsTestController {
    @Autowired
    ProjectService projectService;

    @RequestMapping("/addProjects/{numberOfProjects}")
    public String addUser(@PathVariable("numberOfProjects") int numberOfProjects, Model model) {
        for (int i = 0; i < numberOfProjects; i++) {

            Project project = new Project()
                    .setAuthorId("5681546ed139e28bcda3f845")
                    .setTypeOfProject(TypeOfProject.KNOW_HOW)
                    .setProjectName("Проект " + i)
                    .setProjectDescription("Описание описание описание описание описание описание описание описание" +
                            " описание описание описание описание описание описание описание описание описание описание" +
                            " описание описание описание описание описание описание описание описание описание описание" +
                            " описание описание описание описание описание описание описание описание описание описание")
                    .setAmountRequested(i * 1000 + 1)
                    .setInvestedAmount(0)
                    .setTotalComments(0)
                    .setViews(0);


                HashSet<Comment> set = new HashSet<>();
                    Comment comment = new Comment()
                    .setComment("comment " + i)
                    .setCreatedDateEqualsToCurrentDate();
                set.add(comment);
            project.setComments(set);
            projectService.create(project);
        }

        model.addAttribute("message", numberOfProjects + " test projects is created.");
        return "index";
    }

    @RequestMapping("/project/payback")
    public String payback() {
        projectService.bringBackMoneyToInvestors();
        return "index";
    }

}
