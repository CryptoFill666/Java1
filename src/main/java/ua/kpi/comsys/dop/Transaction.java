package ua.kpi.comsys.dop;

/**
 * Фінансова транзакція. Закрита ієрархія: {@link Deposit}, {@link Withdrawal}, {@link Transfer}.
 */
public sealed interface Transaction permits Deposit, Withdrawal, Transfer {
}
