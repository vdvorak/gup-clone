package ua.com.gup.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.domain.Price;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.service.dto.PriceDTO;


@Component
public class PriceMapper {

    public PriceDTO moneyToMoneyDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setAmount(price.getAmount());
        priceDTO.setCurrency(price.getCurrency());
        priceDTO.setPriceWithVAT(price.getPriceWithVAT());
        return priceDTO;
    }

    public Price moneyDTOToMoney(PriceDTO priceDTO, Currency base) {
        Price price = new Price();
        price.setAmount(priceDTO.getAmount());
        price.setCurrency(priceDTO.getCurrency());
        price.setBaseAmount(priceDTO.getAmount().longValue()); // TODO make convert bean
        price.setBaseCurrency(priceDTO.getCurrency());  // TODO make convert bean
        price.setPriceWithVAT(priceDTO.getPriceWithVAT());
        return price;
    }

}
