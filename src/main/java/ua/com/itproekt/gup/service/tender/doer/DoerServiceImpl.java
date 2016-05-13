package ua.com.itproekt.gup.service.tender.doer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.tender.doer.DoerRepository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerFilterOptions;
import ua.com.itproekt.gup.model.tender.doer.Recall;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.Set;


@Service
public class DoerServiceImpl implements DoerService {
    @Autowired
    private DoerRepository doerRepository;

    @Override
    public void createDoer(Doer doer) {
        doerRepository.createDoer(doer);
    }

    @Override
    public Doer findById(String id) {
        return doerRepository.findById(id);
    }

    @Override
    public boolean doerExists(String id) {
        return doerRepository.doerExists(id);
    }

    @Override
    public Doer updateDoer(Doer tender) {
        return doerRepository.findDoerAndUpdate(tender);
    }

    @Override
    public void deleteDoerById(String id) {
        doerRepository.deleteDoerById(id);
    }

    @Override
    public boolean isUserHaveAccess(Profile user, Doer doer) {
        if(user.getUserRoles().contains(UserRole.ROLE_ADMIN)){
            return true;
        }
        return false;
    }

    @Override
    public EntityPage<Doer> findWihOptions(DoerFilterOptions doerFilterOptions) {
        return doerRepository.findWihOptions(doerFilterOptions);
    }

    @Override
    public long countRecallSum(Doer doer) {
        long likes = doer.getRecalls().stream().filter(recall -> recall.getMark() == Recall.Mark.LIKE).count();
        long dislikes = doer.getRecalls().stream().filter(recall -> recall.getMark() == Recall.Mark.DISLIKE).count();

        return likes - dislikes;
    }

    @Override
    public Set<String> getMatchedNames(String term) {
        return doerRepository.getMatchedNames(term);
    }


}
