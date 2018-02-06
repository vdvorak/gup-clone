package ua.com.gup.repository.geo;


import ua.com.gup.mongo.composition.domain.geo.GeoModel;
import ua.com.gup.common.model.Locale;

import java.util.List;


public interface GeoRepository {

    GeoModel findOne(Long id);

    List<GeoModel> findByIds(Long[] ids);

    List<GeoModel> findByNameStartWithAndLocale(String name, Locale lang);


}
