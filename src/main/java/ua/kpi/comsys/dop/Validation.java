package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/** Спільні перевірки для конструкторів record-ів. */
final class Validation {

    private Validation() {
    }

    static String requireText(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be null or blank");
        }
        return value;
    }

    static BigDecimal requirePositive(BigDecimal value, String name) {
        if (value == null || value.signum() <= 0) {
            throw new IllegalArgumentException(name + " must not be null and must be greater than 0");
        }
        return value;
    }
}
