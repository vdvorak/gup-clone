package ua.com.gup.web.rest.api.geo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.model.geo.GeoModel;
import ua.com.gup.repository.dao.geo.GeoRepository;
import ua.com.gup.util.Locale;
import ua.com.gup.web.rest.dto.CommonGeoDTO;
import ua.com.gup.web.rest.dto.converter.GeoDTOConverter;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/geo")
public class GeoController {

    @Autowired
    private GeoRepository repository;

    @RequestMapping(path = "/{atuid}", method = RequestMethod.GET)
    public ResponseEntity<CommonGeoDTO> findById(@PathVariable("atuid") Long atuid,
                                                 @RequestParam(name = "ancestors", defaultValue = "true") boolean ancestorsNeeded) {
        if (atuid == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        GeoModel mainModel = repository.findOne(atuid);
        List<GeoModel> ancestorModels = null;
        if (ancestorsNeeded) {
            ancestorModels = repository.findByIds(mainModel.getAncestors());
        }
        List<CommonGeoDTO> commonGeoDTOs = Arrays.asList(new GeoDTOConverter().convertToCommonDTO(mainModel, ancestorModels));
        return new ResponseEntity(commonGeoDTOs, HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CommonGeoDTO>> findByName(@RequestParam(name = "id", required = false) Long[] ids,
                                                         @RequestParam(name = "name", required = false) String atuname,
                                                         @RequestParam(name = "lang", defaultValue = "ua") Locale locale,
                                                         @RequestParam(name = "ancestors", defaultValue = "true") boolean ancestorsNeeded) {

        if (StringUtils.isEmpty(atuname) && ids == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.isEmpty(atuname) && ids != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ISelectStrategy iSelectStrategy = null;
        if (!StringUtils.isEmpty(atuname))
            iSelectStrategy = new SelectByNameStrategy(repository, ancestorsNeeded, atuname, locale);
        if (ids != null && ids.length > 0) {
            iSelectStrategy = new SelectByIdsStrategy(repository, ancestorsNeeded, ids);

        }
        List<CommonGeoDTO> response = iSelectStrategy.doSelect();
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
