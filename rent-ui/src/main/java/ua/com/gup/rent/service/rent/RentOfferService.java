package ua.com.gup.rent.service.rent;


import ua.com.gup.rent.service.abstracted.generic.RentGenericService;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;

import java.util.List;

public interface RentOfferService extends RentGenericService<RentOfferDTO, String> {

    void create(RentOfferCreateDTO rentOfferCreateDTO);

    void update(RentOfferUpdateDTO rentOfferUpdateDTO);

    void deleteById(String renOfferId);

    List<RentOfferViewShortDTO> findAll();
}
