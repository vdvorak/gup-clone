package ua.com.gup.web.rest.api.geo;

import ua.com.gup.model.geo.GeoModel;
import ua.com.gup.repository.GeoRepository;
import ua.com.gup.web.rest.dto.CommonGeoDTO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectByIdsStrategy extends SelectStrategy {

    private Map<Long, Integer> mapOrder;
    private Long[] ids;

    public SelectByIdsStrategy(GeoRepository repository, boolean ancestorsNeeded, Long[] ids) {
        super(repository, ancestorsNeeded);
        this.ids = ids;
        this.mapOrder = new HashMap<>(ids.length);
        for (int i = 0; i < ids.length; i++) {
            mapOrder.put(ids[i], i);
        }
    }

    @Override
    public List<GeoModel> doMainSelect() {
        return repository.findByIds(ids);


    }

    /* sort objects  */
    @Override
    protected List<CommonGeoDTO> doAfterSelect(List<CommonGeoDTO> responseDtos) {
        List<CommonGeoDTO> orderedDtos = responseDtos.stream().sorted(new Comparator<CommonGeoDTO>() {
            @Override
            public int compare(CommonGeoDTO o1, CommonGeoDTO o2) {
                return mapOrder.get(o1.getId()).intValue() - mapOrder.get(o2.getId()).intValue();
            }
        }).collect(Collectors.toList());
        return orderedDtos;
    }
}
