package ua.com.itproekt.gup.service.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.tender.TenderRepository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;


@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    private TenderRepository tenderRepository;

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
        if(tender.getNaceId().equals(user.getContact().getNace())){
            return true;
        }
        return false;
    }

    @Override
    public EntityPage<Tender> findWihOptions(TenderFilterOptions tenderFilterOptions, Profile user) {
        return tenderRepository.findWihOptions(tenderFilterOptions, user);
    }

}
