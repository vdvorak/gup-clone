package ua.com.itproekt.gup.service.tender;

import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.Set;


public interface TenderService {
    void createTender(Tender tender);
    Tender findById(String id);
    boolean tenderExists(String id);
    Tender updateTender(Tender tender);
    void deleteTenderById(String id);
    EntityPage<Tender> findWihOptions(TenderFilterOptions tenderFilterOptions, Profile user);
    void checkClosedTendersAndSendActivityFeed();
    Tender setVision(Tender tender, Profile UserWhoReed);
    boolean isAuthorOrWinner(Tender tender, String user);
    Tender completeMembers(Tender t);
    Set<String> getMatchedNames(String name);
    Set<String> getMatchedTenderNumber(String tenderNumb);
}
