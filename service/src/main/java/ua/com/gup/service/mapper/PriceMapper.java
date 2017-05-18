package ua.com.gup.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.offer.Price;
import ua.com.gup.service.CurrencyConverterService;
import ua.com.gup.service.dto.offer.OfferPriceDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
public class PriceMapper {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    public OfferPriceDTO moneyToMoneyDTO(Price price) {
        OfferPriceDTO offerPriceDTO = new OfferPriceDTO();
        offerPriceDTO.setAmount(price.getAmount());
        offerPriceDTO.setCurrency(price.getCurrency());
        offerPriceDTO.setPriceWithVAT(price.getPriceWithVAT());
        return offerPriceDTO;
    }

    public Price moneyDTOToMoney(OfferPriceDTO offerPriceDTO) {
        if (offerPriceDTO.getCurrency() == null || offerPriceDTO.getAmount() == null) {
            return null;
        } else {
            Price price = new Price();
            price.setAmount(offerPriceDTO.getAmount());
            price.setCurrency(offerPriceDTO.getCurrency());
            final BigDecimal priceConverted = currencyConverterService.convertToBaseCurrency(offerPriceDTO.getCurrency(), offerPriceDTO.getAmount());
            price.setBaseAmount(priceConverted.doubleValue());
            price.setBaseCurrency(currencyConverterService.getBaseCurrency());
            price.setPriceWithVAT(offerPriceDTO.getPriceWithVAT());
            price.setLastModifiedDate(LocalDateTime.now());
            return price;
        }
    }

}
