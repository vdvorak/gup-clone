package ua.com.gup.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.domain.Price;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.service.dto.OfferPriceDTO;


@Component
public class PriceMapper {

    public OfferPriceDTO moneyToMoneyDTO(Price price) {
        OfferPriceDTO offerPriceDTO = new OfferPriceDTO();
        offerPriceDTO.setAmount(price.getAmount());
        offerPriceDTO.setCurrency(price.getCurrency());
        offerPriceDTO.setPriceWithVAT(price.getPriceWithVAT());
        return offerPriceDTO;
    }

    public Price moneyDTOToMoney(OfferPriceDTO offerPriceDTO, Currency base) {
        Price price = new Price();
        price.setAmount(offerPriceDTO.getAmount());
        price.setCurrency(offerPriceDTO.getCurrency());
        price.setBaseAmount(offerPriceDTO.getAmount().longValue()); // TODO make convert bean
        price.setBaseCurrency(offerPriceDTO.getCurrency());  // TODO make convert bean
        price.setPriceWithVAT(offerPriceDTO.getPriceWithVAT());
        return price;
    }

}
