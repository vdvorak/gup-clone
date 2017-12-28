package ua.com.gup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.mapper.CommonAuthorMapper;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.service.profile.ProfilesService;

@Component
public class OfferAuthorMapper extends CommonAuthorMapper {

    @Autowired
    private ProfilesService profilesService;

    @Override
    protected CommonProfileService getProfileService() {
        return profilesService;
    }
}
