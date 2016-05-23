package ua.com.itproekt.gup.dao.tender;

import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;
import java.util.List;
import java.util.Set;


public interface TenderRepository  {
        void createTender(Tender tender);
        Tender findById(String id);
        Tender findTenderAndUpdate(Tender tender);
        void update (Tender tender);
        int deleteTenderById(String id);
        boolean tenderExists(String id);
        EntityPage<Tender> findWihOptions(TenderFilterOptions tenderFilterOptions, Profile currUser);
        List<Tender> getTodayEndTenders();
        Set<String> getMatchedNames(String name);
        Set<String> getMatchedTenderNumber(String tenderNumb);
}
