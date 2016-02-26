package ua.com.itproekt.gup.controller.projectsAndInvestments;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class InvestmentController {

    @RequestMapping("/investorPost")
    public String getInvestorPostById(@RequestParam String id, HttpServletRequest request) {
        //..
        //..
        return "projectsAndInvestments/investment/investorPost-OLD";
    }

//    @RequestMapping("/investorPost/list")
//    public String investorPostList(@RequestParam int pageNumber, Model model) {
//        model.addAttribute("pageNumber", pageNumber);
//        return "projectsAndInvestments/investment/investorPostList-OLD";
//    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/investorPost/create")
    public String createInvestorPost() {
        return "projectsAndInvestments/investment/createInvestorPost";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/investorPost/edit")
    public String editInvestorPost(@RequestParam String id, HttpServletRequest request) {
        //..
        //..
        return "projectsAndInvestments/investment/editInvestorPost";
    }


}
