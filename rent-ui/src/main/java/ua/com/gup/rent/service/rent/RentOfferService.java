package ua.com.gup.rent.service.rent;


import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.rent.RentOfferModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;

import java.util.List;
import java.util.Optional;

public interface RentOfferService extends RentOfferGenericService<RentOfferDTO, String> {

    void create(RentOfferCreateDTO rentOfferCreateDTO);

    void update(RentOfferUpdateDTO rentOfferUpdateDTO);

    void deleteById(String renOfferId);

    List<RentOfferViewShortDTO> findAll();

    Optional<RentOfferViewDetailsDTO> findOne(String id);

    RentOfferViewDetailsDTO save(RentOfferModerationReportDTO offerModerationReportDTO);

    RentOfferViewDetailsDTO save(RentOfferCreateDTO rentOfferCreateDTO);

}
