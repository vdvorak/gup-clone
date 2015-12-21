package ua.com.itproekt.gup.service.projectsAndInvestments.investment;

import ua.com.itproekt.gup.model.projectsAndInvestments.investment.ApplicationForInvestment;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPost;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPostFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

public interface InvestorService {
    void create(InvestorPost investorPost);

    InvestorPost findById(String investorPostId);

    InvestorPost edit(InvestorPost investorPost);

    void delete(String investorPostId);

    boolean investorPostExist(String investorPostId);

    EntityPage<InvestorPost> findInvestorPostsWihOptions(InvestorPostFilterOptions investorPostFO);

    void addApplication(String investorPostId, ApplicationForInvestment applicationForInvestment);
}
