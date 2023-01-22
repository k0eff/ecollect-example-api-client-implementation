package org.ecollect.api.classes;

import java.math.BigDecimal;
import java.util.Currency;

public class Amount {

    private Currency currency;
    private BigDecimal value;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
