package ua.com.itproekt.gup.controller.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.projectsAndInvestments.investment.InvestorService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;


@Controller
public class InvestmentController {

    @Autowired
    InvestorService investorService;

    @RequestMapping("/investorPost")
    public String getInvestorPostById(@RequestParam String id, HttpServletRequest request) {
        //..
        //..
        return "projectsAndInvestments/investment/investorPost";
    }

//    @RequestMapping("/investorPost/list")
//    public String investorPostList(@RequestParam int pageNumber, Model model) {
//        model.addAttribute("pageNumber", pageNumber);
//        return "projectsAndInvestments/investment/investorPostList-OLD";
//    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/investorPost/create")
    public String createInvestorPost(Model model) {
        String flag = "project";
        model.addAttribute("flag", flag);
        return "projectsAndInvestments/investment/createInvestorPost";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/investorPost/edit")
    public String editInvestorPost(@RequestParam String id, HttpServletRequest request, Model model) {

        if (!investorService.investorPostExist(id)) {
            throw new ResourceNotFoundException();
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (investorService.findById(id).getuId().equals(userId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            String flag = "project";
            model.addAttribute("flag", flag);
            return "projectsAndInvestments/investment/editInvestorPost";
        } else {
            throw new AccessDeniedException("Editing of investor post is allowed only for owner or admin");
        }
    }


}
