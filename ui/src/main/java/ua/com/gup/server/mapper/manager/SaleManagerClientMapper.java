package ua.com.gup.server.mapper.manager;

import org.springframework.stereotype.Component;
import ua.com.gup.common.mapper.manager.CommonManagerClientMapper;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.server.dto.profile.manager.SaleManagerClientProfileShortDto;

@Component
public class SaleManagerClientMapper extends CommonManagerClientMapper<SaleManagerClientProfileShortDto, Profile> {

    @Override
    public SaleManagerClientProfileShortDto createDto() {
        return new SaleManagerClientProfileShortDto();
    }
}
