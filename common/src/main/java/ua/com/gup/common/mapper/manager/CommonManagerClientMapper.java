package ua.com.gup.common.mapper.manager;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.gup.common.dto.profile.manager.ManagerClientProfileDto;
import ua.com.gup.common.dto.profile.manager.client.CommonClientInfoProfileDto;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.manager.ManagerClientInfo;
import ua.com.gup.common.repository.CommonProfileRepository;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class CommonManagerClientMapper<T extends ManagerClientProfileDto, S extends CommonProfile> {

    @Autowired
    private CommonManagerClientInfoMapper managerClientInfoMapper;

    public T convert(S profile) {
        T dto = createDto();
        dto.setChatUID(profile.getChatUID());
        dto.setFirstname(profile.getFirstname());
        dto.setLastname(profile.getLastname());
        dto.setPublicId(profile.getPublicId());
        dto.setMainPhone(profile.getMainPhone());
        dto.setUserRoles(profile.getUserRoles());
        dto.setPublicId(profile.getPublicId());
        dto.setImageUrlSmall(profile.getImageLarge() != null ? profile.getImageLarge().getUrl() : null);
        dto.setImageUrlLarge(profile.getImageLarge() != null ? profile.getImageSmall().getUrl() : null);
        dto.setCreatedDate(profile.getCreatedDate());
        dto.setLastLogin(profile.getLastLoginDate());
        dto.setActive(profile.getActive());
        dto.setBan(profile.getBan());
        dto.setEmail(profile.getEmail());
        ManagerClientInfo managerClientInfo = profile.getManagerClientInfo();
        if (managerClientInfo != null) {
            CommonClientInfoProfileDto clientInfoDto = managerClientInfoMapper.convert(managerClientInfo);
            dto.setManagerInfo(clientInfoDto);
        }

        return dto;
    }

    public abstract T createDto();

}
