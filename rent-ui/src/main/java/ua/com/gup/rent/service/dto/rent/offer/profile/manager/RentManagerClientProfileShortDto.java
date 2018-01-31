package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.common.dto.profile.manager.ManagerClientProfileDto;
import ua.com.gup.common.dto.profile.manager.client.CommonClientInfoProfileDto;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.rent.model.mongo.user.RentManagerClientInfo;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;

public class RentManagerClientProfileShortDto extends ManagerClientProfileDto<RentManagerClientInfo, RentOfferProfile> {

//
//    public RentManagerClientProfileShortDto(CommonProfile profile, RentOfferProfile manager) {
//        super(profile, manager);
//    }
//
//    @Override
//    public CommonClientInfoProfileDto copyManagerClientInfo(RentManagerClientInfo managerClientInfo, RentOfferProfile manager) {
//        return new RentClientInfoProfileShortDto(managerClientInfo, manager);
//    }
}
