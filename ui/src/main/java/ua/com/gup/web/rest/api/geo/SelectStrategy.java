package ua.com.gup.web.rest.api.geo;


import ua.com.gup.model.geo.GeoModel;
import ua.com.gup.repository.GeoRepository;
import ua.com.gup.web.rest.dto.CommonGeoDTO;
import ua.com.gup.web.rest.dto.converter.GeoDTOConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SelectStrategy implements ISelectStrategy {

    protected GeoRepository repository;
    protected boolean ancestorsNeeded;

    public SelectStrategy(GeoRepository repository, boolean ancestorsNeeded) {
        this.repository = repository;
        this.ancestorsNeeded = ancestorsNeeded;
    }

    protected abstract List<GeoModel> doMainSelect();

    protected abstract List<CommonGeoDTO> doAfterSelect(List<CommonGeoDTO> responseDtos);

    @Override
    final public List<CommonGeoDTO> doSelect() {
        List<GeoModel> geoModels = doMainSelect();
        List<CommonGeoDTO> responseDtos = new ArrayList<>();
        if (ancestorsNeeded) {
            Map<GeoModel, List<GeoModel>> listMap = new HashMap<>(geoModels.size());
            for (GeoModel mainModel : geoModels) {
                List<GeoModel> ancestorModels = repository.findByIds(mainModel.getAncestors());
                listMap.put(mainModel, ancestorModels);
            }
            for (Map.Entry<GeoModel, List<GeoModel>> geoModelListEntry : listMap.entrySet()) {
                responseDtos.add(new GeoDTOConverter().convertToCommonDTO(geoModelListEntry.getKey(), geoModelListEntry.getValue()));
            }
        } else {
            for (GeoModel model : geoModels) {
                responseDtos.add(new GeoDTOConverter().convertToCommonDTO(model, null));
            }
        }
        return doAfterSelect(responseDtos);
    }
}
