package ua.com.itproekt.gup.controller.projectsAndInvestments;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InvestmentController {

    @RequestMapping("/investorPost/list")
    public String investorPostList(@RequestParam int pageNumber, Model model) {
        model.addAttribute("pageNumber", pageNumber);
        return "projectsAndInvestments/investment/investorPostList";
    }

    @RequestMapping("/investorPost/create")
    public String createInvestorPost() {
        return "projectsAndInvestments/investment/createInvestorPost";
    }

    @RequestMapping("/investorPost/id/{investorPostId}/edit")
    public String editInvestorPost(@PathVariable String investorPostId, Model model) {
        model.addAttribute("investorPostId", investorPostId);
        return "projectsAndInvestments/investment/editInvestorPost";
    }

    @RequestMapping("/investorPost/id/{investorPostId}")
    public String getInvestorPostById(@PathVariable String investorPostId, Model model) {
        model.addAttribute("investorPostId", investorPostId);
        return "projectsAndInvestments/investment/investorPost";
    }
}
