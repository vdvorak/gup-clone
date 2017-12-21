package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.RentOfferSettings;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferSettingsDTO;

@Component
public class RentOfferSettingsMapper {

    public RentOfferSettingsDTO fromModelToDTO(RentOfferSettings rentOfferSettings) {
        RentOfferSettingsDTO rentOfferSettingsDTO = new RentOfferSettingsDTO();
        rentOfferSettingsDTO.setMaxRentDays(rentOfferSettings.getMaxRentDays());
        rentOfferSettingsDTO.setMinRentDays(rentOfferSettings.getMinRentDays());
        rentOfferSettingsDTO.setStartDay(rentOfferSettings.getStartDay());
        rentOfferSettingsDTO.setEndDay(rentOfferSettings.getEndDay());
        return rentOfferSettingsDTO;
    }

    public RentOfferSettings fromDTOToModel(RentOfferSettingsDTO rentOfferSettingsDTO) {
        RentOfferSettings rentOfferSettings = new RentOfferSettings();
        rentOfferSettings.setMaxRentDays(rentOfferSettingsDTO.getMaxRentDays());
        rentOfferSettings.setMinRentDays(rentOfferSettingsDTO.getMinRentDays());
        rentOfferSettings.setStartDay(rentOfferSettingsDTO.getStartDay());
        rentOfferSettings.setEndDay(rentOfferSettingsDTO.getEndDay());
        return rentOfferSettings;
    }
}