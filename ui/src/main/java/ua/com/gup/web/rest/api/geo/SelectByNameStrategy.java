package ua.com.gup.web.rest.api.geo;


import ua.com.gup.model.geo.GeoModel;
import ua.com.gup.repository.GeoRepository;
import ua.com.gup.util.Locale;
import ua.com.gup.web.rest.dto.CommonGeoDTO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SelectByNameStrategy extends SelectStrategy {
    private String name;
    private Locale locale;

    public SelectByNameStrategy(GeoRepository repository, boolean ancestorsNeeded, String name, Locale locale) {
        super(repository, ancestorsNeeded);
        this.name = name;
        this.locale = locale;
    }

    @Override
    public List<GeoModel> doMainSelect() {
        return repository.findByNameStartWithAndLocale(name, locale);
    }

    /* Swap 3 and 4 levels */
    @Override
    protected List<CommonGeoDTO> doAfterSelect(List<CommonGeoDTO> responseDtos) {
        List<CommonGeoDTO> orderedDtos = responseDtos.stream().sorted(new Comparator<CommonGeoDTO>() {
            @Override
            public int compare(CommonGeoDTO o1, CommonGeoDTO o2) {
                if ((o1.getLevel() == 4 && o2.getLevel() == 3)
                        || (o1.getLevel() == 3 && o2.getLevel() == 4))
                    return o2.getLevel() - o1.getLevel();
                else
                    return o1.getLevel() - o2.getLevel();
            }
        }).collect(Collectors.toList());
        return orderedDtos;
    }
}
