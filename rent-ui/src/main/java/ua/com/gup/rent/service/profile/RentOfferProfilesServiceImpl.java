package ua.com.gup.rent.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;


@Service
public class RentOfferProfilesServiceImpl implements RentOfferProfilesService {
    private final Logger log = LoggerFactory.getLogger(RentOfferProfilesServiceImpl.class);

    @Autowired
    private RentOfferProfileRepository rentOfferProfileRepository;


    @Override
    public RentOfferProfile findById(String id) {
        return rentOfferProfileRepository.findById(id);
    }

    @Override
    public RentOfferProfile findByPublicId(String id) {
        return rentOfferProfileRepository.findByPublicId(id);
    }

}
