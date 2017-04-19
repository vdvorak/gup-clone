package ua.com.gup.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.domain.Money;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.service.dto.MoneyDTO;


@Component
public class MoneyMapper {

    public MoneyDTO moneyToMoneyDTO(Money money) {
        MoneyDTO moneyDTO = new MoneyDTO();
        moneyDTO.setAmount(money.getAmount());
        moneyDTO.setCurrency(money.getCurrency());
        return moneyDTO;
    }

    public Money moneyDTOToMoney(MoneyDTO moneyDTO, Currency base) {
        Money money = new Money();
        money.setAmount(moneyDTO.getAmount());
        money.setCurrency(moneyDTO.getCurrency());
        money.setBaseAmount(moneyDTO.getAmount().longValue()); // TODO make convert bean
        money.setBaseCurrency(moneyDTO.getCurrency());  // TODO make convert bean
        return money;
    }

}
