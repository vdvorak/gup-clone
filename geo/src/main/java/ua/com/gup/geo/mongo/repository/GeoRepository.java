package ua.com.gup.geo.mongo.repository;


import ua.com.gup.geo.mongo.model.GeoModel;
import ua.com.gup.geo.web.request.Locale;

import java.util.List;


public interface GeoRepository {

    GeoModel findOne(Long id);

    List<GeoModel> findByIds(Long[] ids);

    List<GeoModel> findByNameStartWithAndLocale(String name, Locale lang);


}
