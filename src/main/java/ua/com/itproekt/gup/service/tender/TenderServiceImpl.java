package ua.com.itproekt.gup.service.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.tender.TenderRepository;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserType;
import ua.com.itproekt.gup.model.tender.*;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private ActivityFeedService activityFeedService;

    @Autowired
    private ProfilesService profilesService;

    @Override
    public void createTender(Tender tender) {
        tenderRepository.createTender(tender);
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
        if (tender.getMembers() != null && tender.getMembers().size() < 1){
            tender.setMembers(null);
        }
        if (tender.getProposes() != null && tender.getProposes().size() < 1){
            tender.setProposes(null);
        }
        return tenderRepository.findTenderAndUpdate(tender);
    }

    @Override
    public void deleteTenderById(String id) {
        tenderRepository.deleteTenderById(id);
    }

    @Override
    public EntityPage<Tender> findWihOptions(TenderFilterOptions tenderFilterOptions, Profile user) {
        return tenderRepository.findWihOptions(tenderFilterOptions, user);
    }

    @Override
    public void checkClosedTendersAndSendActivityFeed() {
        List<Tender> endTenders = tenderRepository.getTodayEndTenders();
        endTenders.parallelStream().filter(t -> t.getWinnerId() == null).forEach(tender -> {
            activityFeedService.createEvent(new Event(tender.getAuthorId(), EventType.TENDER_END_DAY_NEED_CHOOSE_WINNER, tender.getId(), null, null));
        });
//        System.err.println("Debag ! void checkClosedTendersAndSendActivityFeed()");
    }

    public Tender setIndividualVision(Tender tender) {
        tender.setAuthorId(null);
        tender.setProposes(null);
        if(tender.getUploadFilesIds() != null && tender.getUploadFilesIds().containsValue("pic1")) {
            String docId = null;
            for(Map.Entry<String, String> e : tender.getUploadFilesIds().entrySet()){
                if(e.getValue().equals("pic1")){
                    docId = e.getKey();
                }
            }
            tender.getUploadFilesIds().clear();
            if(docId != null) {
                tender.getUploadFilesIds().put(docId, "pic1");
            }
        } else {
            tender.setUploadFilesIds(null);
        }
        return tender;
    }

    public Tender setLegalEntityVision(Tender tender, String userId) {
        tender = setProposeVision(tender, userId);
        if (!isAuthorOrWinner(tender, userId) && tender.isHideContact()) tender.setAuthorId(null);
        return tender;
    }

    public Tender setProposeVision(Tender tender, String idUserWhoReed) {
        // for not logged user delete all propose
        if (idUserWhoReed == null) {
            tender.setProposes(null);
        }

        //for not tender author
        //check if Propose are hidden
        //and if so, hide all propose where current user is not author
        else if (tender.getAuthorId() != null && !tender.getAuthorId().equals(idUserWhoReed)) {
            if (tender.isHidePropose()) {
                tender.setProposes(tender.getProposes().stream()
                        .filter(p -> p.getAuthorId().equals(idUserWhoReed))
                        .collect(Collectors.toList()));
            // if propose are not hidden by tender author, set visibility chosen by propose author
            } else {
                tender.setProposes(tender.getProposes().stream()
                        .filter(p -> !p.getHidden() || p.getAuthorId().equals(idUserWhoReed))
                        .collect(Collectors.toList()));
            }
        }
        return tender;
    }

    public boolean isMember(Tender tender, String user) {
        if (user == null) {
            return false;
        }
        if (tender.getProposes() == null) {
            tender = findById(tender.getId());
            if (tender.getProposes() == null)
                return false;
        }
        return tender.getProposes().stream().map(Propose::getAuthorId).collect(Collectors.toList()).contains(user) ;
    }

    @Override
    public boolean isAuthorOrWinner(Tender tender, String user) {
        if (user == null) {
            return false;
        }
        if (tender.getAuthorId() == null) {
            tender = findById(tender.getId());
        }
        return user.equals(tender.getAuthorId()) || user.equals(tender.getWinnerId());
    }

    @Override
    public Tender setVision(Tender tender, Profile userWhoReed) {
        if (userWhoReed != null && isAuthorOrWinner(tender, userWhoReed.getId())) {
            return tender;
        }

        if (tender.getType() == TenderType.CLOSE) {
            if (userWhoReed == null || userWhoReed.getContact() == null
                    || (userWhoReed.getContact().getType() != UserType.LEGAL_ENTITY
                    && userWhoReed.getContact().getType() != UserType.ENTREPRENEUR)
                    || (!isAuthorOrWinner(tender, userWhoReed.getId()) && !isMember(tender, userWhoReed.getId()))) {
                return null;
            }
            return setLegalEntityVision(tender, userWhoReed.getId());
        } else if (userWhoReed != null && userWhoReed.getContact() != null &&
                    (userWhoReed.getContact().getType() == UserType.LEGAL_ENTITY
                    || userWhoReed.getContact().getType() == UserType.ENTREPRENEUR)) {
                return setLegalEntityVision(tender, userWhoReed.getId());
        } else {
            return setIndividualVision(tender);
        }
    }

    @Override
    public Tender completeMembers(Tender t) {
        if(t != null && t.getMembers() != null) {
            for (Member m : t.getMembers()) {
                Profile p = profilesService.findWholeProfileById(m.getId());
                if(p != null) {
                    m.setName(p.getUsername());
                    if (p.getContact() != null) {
                        m.setUserPic(p.getImgId());
                    }
                }
            }
        }
        return t;
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        return tenderRepository.getMatchedNames(name);
    }

}
