package ua.com.gup.rent.mapper.manager;

import org.springframework.stereotype.Component;
import ua.com.gup.common.mapper.manager.CommonManagerClientMapper;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentManagerClientProfileShortDto;

@Component
public class RentManagerClientMapper extends CommonManagerClientMapper<RentManagerClientProfileShortDto, RentOfferProfile> {

    @Override
    public RentManagerClientProfileShortDto createDto() {
        return new RentManagerClientProfileShortDto();
    }
}
