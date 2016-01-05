package ua.com.itproekt.gup.service.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.tender.TenderRepository;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;


@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private ActivityFeedService activityFeedService;

    @Override
    public void createTender(Tender offer) {
        tenderRepository.createTender(offer);
    }

    @Override
    public Tender findById(String id) {
        return tenderRepository.findById(id);
    }

    @Override
    public boolean tenderExists(String id) {
        return tenderRepository.tenderExists(id);
    }

    @Override
    public Tender updateTender(Tender tender) {
        return tenderRepository.findTenderAndUpdate(tender);
    }

    @Override
    public void deleteTenderById(String id) {
        tenderRepository.deleteTenderById(id);
    }

    @Override
    public boolean isUserHaveAccess(Profile user, Tender tender) {
        if(user.getUserRoles().contains(UserRole.ROLE_ADMIN)){
            return true;
        }
        if(tender.getNaceIds().equals(user.getContact().getNace())){
            return true;
        }
        return false;
    }

    @Override
    public EntityPage<Tender> findWihOptions(TenderFilterOptions tenderFilterOptions, Profile user) {
        return tenderRepository.findWihOptions(tenderFilterOptions, user);
    }

    @Override
    public void checkClosedTendersAndSendActivityFeed() {
        List<Tender> endTenders = tenderRepository.getTodayEndTenders();
        endTenders.parallelStream().filter(t -> t.getWinnerId() == null).forEach(tender -> {
           activityFeedService.createEvent(new Event(tender.getAuthorId(), EventType.TENDER_END_DAY_NEED_CHOOSE_WINNER, tender.getId(), tender.getId()));
        });
        System.err.println("Debag ! void checkClosedTendersAndSendActivityFeed()");
    }
}
