package ua.com.itproekt.gup.dao.tender.doer;

import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

/**
 * Created by Комп2 on 10.11.2015.
 */
public interface DoerRepository {
    void createDoer(Doer doer);
    Doer findById(String id);
    Doer findDoerAndUpdate(Doer doer);
    void update (Doer doer);
    int deleteDoerById(String id);
    boolean doerExists(String id);
    EntityPage<Doer> findWihOptions(DoerFilterOptions doerFilterOptions);
}
