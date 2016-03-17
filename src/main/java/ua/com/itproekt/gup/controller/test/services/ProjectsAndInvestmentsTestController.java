package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPost;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Comment;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ModerationStatus;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ProjectType;
import ua.com.itproekt.gup.service.projectsAndInvestments.investment.InvestorService;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;

import java.util.HashSet;
import java.util.Random;


@Controller
public class ProjectsAndInvestmentsTestController {
    @Autowired
    ProjectService projectService;

    @Autowired
    InvestorService investorService;

    @RequestMapping("/addProjects/{numberOfProjects}")
    public String addProjects(@PathVariable("numberOfProjects") int numberOfProjects, Model model) {
        for (int i = 0; i < numberOfProjects; i++) {

            Project project = new Project()
                    .setAuthorId("5681546ed139e28bcda3f845")
                    .setType(ProjectType.KNOW_HOW)
                    .setTitle("Проект " + i)
                    .setDescription("Описание описание описание описание описание описание описание описание" +
                            " описание описание описание описание описание описание описание описание описание описание" +
                            " описание описание описание описание описание описание описание описание описание описание" +
                            " описание описание описание описание описание описание описание описание описание описание")
                    .setAmountRequested(i * 1000 + 1)
                    .setTotalComments(0)
                    .setModerationStatus(ModerationStatus.COMPLETE)
                    .setViews(i);

                HashSet<Comment> set = new HashSet<>();
                    Comment comment = new Comment()
                    .setComment("comment " + i)
                    .setCreatedDateEqualsToCurrentDate();
                set.add(comment);
            project.setComments(set);
            projectService.create(project);

            project.setModerationStatus(ModerationStatus.NO)
                    .setId(null)
                    .setType(ProjectType.PROTOTYPE);
            projectService.create(project);

        }

        model.addAttribute("message", numberOfProjects + " test projects is created.");
        return "index";
    }

    @RequestMapping("/addInvestorPosts/{numberOfPosts}")
    public String addInvestorPosts(@PathVariable("numberOfPosts") int numberOfPosts, Model model) {
        for (int i = 0; i < numberOfPosts; i++) {

            InvestorPost investorPost = new InvestorPost()
                    .setCreatedDateEqualsToCurrentDate()
                    .setMaxInvestAmount(i * 1000 + 1)
                    .setDescription("Описание описание описание описание описание описание");

            investorService.create(investorPost);
        }

        model.addAttribute("message", numberOfPosts + " test projects is created.");
        return "index";
    }

    @RequestMapping("/project/payback")
    public String payback() {
        projectService.bringBackMoneyToInvestors();
        return "index";
    }

}
