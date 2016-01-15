package ua.com.itproekt.gup.service.projectsAndInvestments.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.projectsAndInvestments.investment.InvestmentRepository;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.ApplicationForInvestment;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPost;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPostFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public void create(InvestorPost investorPost) {
        InvestorPost newInvestorPost = new InvestorPost()
                .setuId(investorPost.getuId())
                .setDescription(investorPost.getDescription())
                .setAmountOfMoney(investorPost.getAmountOfMoney())
                .setCategoriesOfIndustry(investorPost.getCategoriesOfIndustry())
                .setCreatedDateEqualsToCurrentDate();
        investmentRepository.create(newInvestorPost);

        investorPost.setId(newInvestorPost.getId());
    }

    @Override
    public InvestorPost findById(String investorPostId) {
        return investmentRepository.findById(investorPostId);
    }

    @Override
    public InvestorPost edit(InvestorPost investorPost) {
        InvestorPost newInvestorPost = new InvestorPost()
                .setId(investorPost.getId())
                .setAmountOfMoney(investorPost.getAmountOfMoney())
                .setCategoriesOfIndustry(investorPost.getCategoriesOfIndustry())
                .setDescription(investorPost.getDescription());

        return investmentRepository.findInvestorAndUpdate(newInvestorPost);
    }

    @Override
    public void delete(String investorPostId) {
        investmentRepository.delete(investorPostId);
    }

    @Override
    public boolean investorPostExist(String investorPostId) {
        return investmentRepository.investorPostExist(investorPostId);
    }

    @Override
    public EntityPage<InvestorPost> findInvestorPostsWihOptions(InvestorPostFilterOptions investorFilterOptions) {
        return investmentRepository.findInvestorPostsWihOptions(investorFilterOptions);
    }

    @Override
    public void addApplication(String investorPostId, ApplicationForInvestment applicationForInvestment) {
        investmentRepository.addApplication(investorPostId, applicationForInvestment);
    }
}
