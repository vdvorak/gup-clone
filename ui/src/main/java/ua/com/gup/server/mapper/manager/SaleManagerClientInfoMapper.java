package ua.com.gup.server.mapper.manager;

import org.springframework.stereotype.Component;
import ua.com.gup.common.mapper.manager.CommonManagerClientInfoMapper;
import ua.com.gup.server.dto.profile.manager.SaleClientInfoProfileShortDto;

@Component
public class SaleManagerClientInfoMapper extends CommonManagerClientInfoMapper<SaleClientInfoProfileShortDto> {

    @Override
    public SaleClientInfoProfileShortDto createDto() {
        return new SaleClientInfoProfileShortDto();
    }
}