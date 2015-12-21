package ua.com.itproekt.gup.service.tender.doer;

import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

/**
 * Created by Комп2 on 10.11.2015.
 */
public interface DoerService {
    void createDoer(Doer doer);
    Doer findById(String id);
    boolean doerExists(String id);
    Doer updateDoer(Doer doer);
    void deleteDoerById(String id);
    boolean isUserHaveAccess(Profile user, Doer doer);
    EntityPage<Doer> findWihOptions(DoerFilterOptions tenderFilterOptions);

    long countRecallSum(Doer doer);
}
