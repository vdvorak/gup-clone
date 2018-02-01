package ua.com.gup.common.mapper.manager;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.gup.common.dto.profile.manager.client.ClientContactInfoDto;
import ua.com.gup.common.dto.profile.manager.client.CommonClientInfoProfileDto;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;
import ua.com.gup.common.repository.CommonProfileRepository;

public abstract class CommonManagerClientInfoMapper<T extends CommonClientInfoProfileDto> {

    @Autowired
    private CommonProfileRepository profileRepository;

    public T convert(ManagerClientInfo info) {
        T dto = createDto();
        if (info != null) {
            dto.setContactInfo(new ClientContactInfoDto(info.getContactInfo()));
            dto.setAdditionalInfo(info.getAdditionalInfo());
            dto.setInterestingStatus(info.getInterestingStatus());
        }
        if (info.getManager() != null) {
            CommonProfile manager = profileRepository.findById(info.getManager());
            dto.setManagerPublicId(manager.getPublicId());
            dto.setManagerFirstname(manager.getFirstname());
            dto.setManagerLastname(manager.getLastname());
        }
        return dto;
    }

    public abstract T createDto();
    //ManagerClientInfo
}
