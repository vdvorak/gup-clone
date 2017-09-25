package ua.com.gup.server.dto.converter;

import org.springframework.util.CollectionUtils;
import ua.com.gup.model.geo.GeoModel;
import ua.com.gup.server.dto.GeoDto;
import ua.com.gup.server.dto.NameDto;
import ua.com.gup.server.dto.TypeDto;
import ua.com.gup.server.dto.CommonGeoDTO;

import java.util.List;

public class GeoDTOConverter {

    private void setByLevel(Integer level, CommonGeoDTO commonGeoDTO, GeoDto geoDto) {
        switch (level) {
            case 0:
                commonGeoDTO.setCountry(geoDto);
                break;
            case 1:
                commonGeoDTO.setRegion(geoDto);
                break;
            case 2:
                commonGeoDTO.setDistrict(geoDto);
                break;
            case 3:
                commonGeoDTO.setCouncil(geoDto);
                break;
            case 4:
                commonGeoDTO.setCity(geoDto);
                break;
        }
    }

    public GeoDto convertToGeoDto(GeoModel geoModel) {
        GeoDto geoDto = new GeoDto();
        geoDto.setId(geoModel.getId());
        geoDto.setLevel(geoModel.getLevel());
        geoDto.setParent(geoModel.getParent());

        NameDto nameDto = new NameDto();
        nameDto.setEn(geoModel.getName().getEn());
        nameDto.setRu(geoModel.getName().getRu());
        nameDto.setUa(geoModel.getName().getUa());

        geoDto.setName(nameDto);

        TypeDto typeDto = new TypeDto();
        typeDto.setEn(geoModel.getType().getEn());
        typeDto.setRu(geoModel.getType().getRu());
        typeDto.setUa(geoModel.getType().getUa());

        geoDto.setType(typeDto);
        return geoDto;
    }


    public CommonGeoDTO convertToCommonDTO(GeoModel geoModel, List<GeoModel> parents) {

        GeoDto mainGeoDto = convertToGeoDto(geoModel);
        CommonGeoDTO commonGeoDTO = new CommonGeoDTO();
        commonGeoDTO.setId(geoModel.getId());
        commonGeoDTO.setParent(geoModel.getParent());
        commonGeoDTO.setLevel(geoModel.getLevel());
        setByLevel(geoModel.getLevel(), commonGeoDTO, mainGeoDto);

        if (!CollectionUtils.isEmpty(parents)) {
            for (GeoModel parent : parents) {
                GeoDto geoDto = convertToGeoDto(parent);
                setByLevel(parent.getLevel(), commonGeoDTO, geoDto);
            }
        }
        return commonGeoDTO;
    }
}
