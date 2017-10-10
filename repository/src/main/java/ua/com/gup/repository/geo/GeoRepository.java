package ua.com.gup.repository.geo;



import ua.com.gup.domain.geo.GeoModel;
import ua.com.gup.model.enumeration.Locale;

import java.util.List;


public interface GeoRepository {

    GeoModel findOne(Long id);

    List<GeoModel> findByIds(Long[] ids);

    List<GeoModel> findByNameStartWithAndLocale(String name, Locale lang);


}
