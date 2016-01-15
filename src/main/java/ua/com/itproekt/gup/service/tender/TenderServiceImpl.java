package ua.com.itproekt.gup.service.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.tender.TenderRepository;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserType;
import ua.com.itproekt.gup.model.tender.Propose;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.model.tender.TenderType;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private ActivityFeedService activityFeedService;

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
            activityFeedService.createEvent(new Event(tender.getAuthorId(), EventType.TENDER_END_DAY_NEED_CHOOSE_WINNER, tender.getId(), tender.getId()));
        });
        System.err.println("Debag ! void checkClosedTendersAndSendActivityFeed()");
    }

    public Tender setIndividualVision(Tender tender) {
        tender.setAuthorId(null);
        tender.setProposes(null);
        return tender;
    }

    public Tender setLegalEntityVision(Tender tender, String userId) {
        if (!isAuthorOrWinner(tender, userId) && tender.isHideContact()) tender.setAuthorId(null);
        tender = setProposeVision(tender, userId);
        return tender;
    }

    public Tender setProposeVision(Tender tender, String idUserWhoReed) {
        if (idUserWhoReed == null) {
            tender.setProposes(null);
        } else if (!tender.getAuthorId().equals(idUserWhoReed)) {
            if (tender.isHidePropose()) {
                tender.setProposes(null);
            } else {
                tender.getProposes().stream().filter(p -> p.getHidden() && p.getAuthorId().equals(idUserWhoReed)).forEach(p -> {
                    tender.getProposes().remove(p);
                });
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
        if (tender.getType() == TenderType.CLOSE) {
            if (userWhoReed == null || userWhoReed.getContact() == null
                    || (userWhoReed.getContact().getType() != UserType.LEGAL_ENTITY
                    && userWhoReed.getContact().getType() != UserType.ENTREPRENEUR)
                    || (!isAuthorOrWinner(tender, userWhoReed.getId()) && !isMember(tender, userWhoReed.getId()))) {
                return null;
            }
            return setLegalEntityVision(tender, userWhoReed.getId());
        } else {
            if (isAuthorOrWinner(tender, userWhoReed.getId())) {
                return tender;
            } else if (userWhoReed.getContact().getType() == UserType.LEGAL_ENTITY
                    || userWhoReed.getContact().getType() == UserType.ENTREPRENEUR) {
                return setLegalEntityVision(tender, userWhoReed.getId());
            } else {
                return setIndividualVision(tender);
            }
        }
    }
}
