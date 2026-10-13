package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/** Переказ між рахунками. */
public record Transfer(String fromAccount, String toAccount, BigDecimal amount) implements Transaction {

    public Transfer {
        Validation.requireText(fromAccount, "fromAccount");
        Validation.requireText(toAccount, "toAccount");
        Validation.requirePositive(amount, "amount");
    }
}
