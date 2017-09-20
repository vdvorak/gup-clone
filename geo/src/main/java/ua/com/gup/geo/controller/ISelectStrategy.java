package ua.com.gup.geo.controller;


import ua.com.gup.geo.web.dto.CommonGeoDTO;

import java.util.List;

public interface ISelectStrategy {

    List<CommonGeoDTO> doSelect();
}
