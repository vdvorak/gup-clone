package ua.com.gup.server.api.geo;


import ua.com.gup.server.dto.CommonGeoDTO;

import java.util.List;

public interface ISelectStrategy {

    List<CommonGeoDTO> doSelect();
}
