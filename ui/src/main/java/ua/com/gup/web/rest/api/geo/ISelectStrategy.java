package ua.com.gup.web.rest.api.geo;


import ua.com.gup.web.rest.dto.CommonGeoDTO;

import java.util.List;

public interface ISelectStrategy {

    List<CommonGeoDTO> doSelect();
}
