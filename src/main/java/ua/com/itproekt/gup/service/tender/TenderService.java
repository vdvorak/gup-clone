package ua.com.itproekt.gup.service.tender;

import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;


public interface TenderService {
    void createTender(Tender tender);
    Tender findById(String id);
    boolean tenderExists(String id);
    Tender updateTender(Tender tender);
    void deleteTenderById(String id);
    boolean isUserHaveAccess(Profile user, Tender tender);
    EntityPage<Tender> findWihOptions(TenderFilterOptions tenderFilterOptions, Profile user);
    void checkClosedTendersAndSendActivityFeed();
    Tender setVision(Tender tender, Profile UserWhoReed);
}
