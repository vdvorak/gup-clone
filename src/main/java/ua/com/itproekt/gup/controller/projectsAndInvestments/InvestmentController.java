package ua.com.itproekt.gup.controller.projectsAndInvestments;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Sasha on 14.01.2016.
 */
@Controller
public class InvestmentController {

    @RequestMapping("/investorPostList")
    public String investorPostList() {
        return "projectsAndInvestments/project/projectList";
    }

    @RequestMapping("/createInvestorPost")
    public String createInvestorPost() {
        return "projectsAndInvestments/project/createProject";
    }

    @RequestMapping("/editInvestorPost/id/{investorPostId}")
    public String editInvestorPost(@PathVariable String investorPostId, Model model) {
        model.addAttribute("investorPostId", investorPostId);
        return "projectsAndInvestments/project/editProject";
    }

    @RequestMapping("/investorPost/id/{investorPostId}")
    public String getInvestorPostById(@PathVariable String investorPostId, Model model) {
        model.addAttribute("investorPostId", investorPostId);
        return "projectsAndInvestments/project/project";
    }
}
