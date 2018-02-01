package ua.com.gup.common.mapper.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDTO;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.service.CommonProfileService;


public abstract class ManagerActionMapper<T extends ManagerAction, K extends ManagerActionDTO> {

    @Autowired
    private CommonProfileService profileService;

    protected abstract K createDto();

    public K toDto(T action) {
        K dto = createDto();
        dto.setId(action.getId());
        dto.setType(action.getType());
        dto.setComment(action.getComment());

        //LocalDateTime time = action.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        dto.setTime( action.getTime());
        //LocalDate date = action.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dto.setDate( action.getDate());
        dto.setPhone(action.getPhone());
        dto.setEmail(action.getEmail());
        dto.setFirstname(action.getFirstname());
        dto.setLastname(action.getLastname());
        dto.setClientId(action.getClientPublicId());

        CommonProfile manager = profileService.findByPublicId(action.getManagerPublicId());
        dto.setManagerFirstname(manager.getFirstname());
        dto.setManagerLastname(manager.getLastname());
        dto.setManagerPublicId(manager.getPublicId());
        return dto;
    }

    public T fromDto(T model, K dto) {
        model.setId(dto.getId());
        model.setComment(dto.getComment());

//        Date date = Date.from(dto.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date time = Date.from(dto.getTime().toInstant(ZoneOffset.UTC));

        model.setTime(dto.getTime());
        model.setDate(dto.getDate());
        model.setEmail(dto.getEmail());
        model.setPhone(dto.getPhone());
        if (!StringUtils.isEmpty(dto.getClientId())) {
            CommonProfile client = profileService.findByPublicId(dto.getClientId());
            if (client != null) {
                model.setFirstname(client.getFirstname());
                model.setLastname(client.getLastname());
                model.setClientPublicId(client.getPublicId());
            }
        } else {
            model.setFirstname(dto.getFirstname());
            model.setLastname(dto.getLastname());
        }

        if (!StringUtils.isEmpty(dto.getManagerPublicId())) {
            model.setManagerPublicId(dto.getManagerPublicId());
        }
        return model;
    }


}
