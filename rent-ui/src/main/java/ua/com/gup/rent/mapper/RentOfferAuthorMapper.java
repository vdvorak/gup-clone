package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.mapper.CommonAuthorMapper;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.service.profile.RentOfferProfilesService;

@Component
public class RentOfferAuthorMapper extends CommonAuthorMapper {

    @Autowired
    private RentOfferProfilesService rentOfferProfilesService;

    @Override
    protected CommonProfileService getProfileService() {
        return rentOfferProfilesService;
    }


}
