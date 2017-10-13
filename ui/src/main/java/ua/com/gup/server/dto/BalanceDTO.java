package ua.com.gup.server.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceDTO implements Serializable {

    private BigDecimal amount;

    public BalanceDTO() {
    }

    public BalanceDTO(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
