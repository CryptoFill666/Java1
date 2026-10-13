package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/** Зняття коштів з рахунку. */
public record Withdrawal(String accountId, BigDecimal amount, String category) implements Transaction {

    public Withdrawal {
        Validation.requireText(accountId, "accountId");
        Validation.requirePositive(amount, "amount");
        Validation.requireText(category, "category");
    }
}
