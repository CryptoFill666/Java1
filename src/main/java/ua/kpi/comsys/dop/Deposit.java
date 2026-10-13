package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/** Поповнення рахунку. */
public record Deposit(String accountId, BigDecimal amount) implements Transaction {

    public Deposit {
        Validation.requireText(accountId, "accountId");
        Validation.requirePositive(amount, "amount");
    }
}
