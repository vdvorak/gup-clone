package ua.com.gup.rent.mapper.manager;

import org.springframework.stereotype.Component;
import ua.com.gup.common.mapper.manager.CommonManagerClientInfoMapper;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentClientInfoProfileShortDto;

@Component
public class RentManagerClientInfoMapper extends CommonManagerClientInfoMapper<RentClientInfoProfileShortDto> {

    @Override
    public RentClientInfoProfileShortDto convert(ManagerClientInfo info) {
        RentClientInfoProfileShortDto dto = super.convert(info);
        if (info != null) {
            dto.setOfferQuantity(info.getOfferQuantity());
            dto.setSpendedMoney(info.getSpendedMoney());
            dto.setRentOfferQuantity(info.getRentOfferQuantity());
            dto.setBonusMoney( info.getBonusMoney());
            dto.setBookingRequests(info.getBookingRequests());
        }

        return dto;
    }

    @Override
    public RentClientInfoProfileShortDto createDto() {
        return new RentClientInfoProfileShortDto();
    }
}
