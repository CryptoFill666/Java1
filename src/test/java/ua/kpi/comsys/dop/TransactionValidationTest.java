package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class TransactionValidationTest {

    private static final BigDecimal ONE = BigDecimal.ONE;

    private static void assertIae(Runnable action) {
        assertThrows(IllegalArgumentException.class, action::run);
    }

    // ---------- Deposit ----------

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void depositRejectsBadAccount(String account) {
        assertIae(() -> new Deposit(account, ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "0.00", "-1", "-0.01"})
    void depositRejectsNonPositiveAmount(String amount) {
        assertIae(() -> new Deposit("A", new BigDecimal(amount)));
    }

    @Test
    void depositRejectsNullAmount() {
        assertIae(() -> new Deposit("A", null));
    }

    @Test
    void depositValidAndImmutableAccessors() {
        Deposit d = assertDoesNotThrow(() -> new Deposit("A", ONE));
        assertEquals("A", d.accountId());
        assertEquals(ONE, d.amount());
        assertEquals(new Deposit("A", ONE), d);
    }

    // ---------- Withdrawal ----------

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "  "})
    void withdrawalRejectsBadAccount(String account) {
        assertIae(() -> new Withdrawal(account, ONE, "Food"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "  "})
    void withdrawalRejectsBadCategory(String category) {
        assertIae(() -> new Withdrawal("A", ONE, category));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-5"})
    void withdrawalRejectsNonPositiveAmount(String amount) {
        assertIae(() -> new Withdrawal("A", new BigDecimal(amount), "Food"));
    }

    @Test
    void withdrawalRejectsNullAmount() {
        assertIae(() -> new Withdrawal("A", null, "Food"));
    }

    @Test
    void withdrawalValid() {
        Withdrawal w = new Withdrawal("A", ONE, "Food");
        assertEquals("A", w.accountId());
        assertEquals(ONE, w.amount());
        assertEquals("Food", w.category());
    }

    // ---------- Transfer ----------

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "  "})
    void transferRejectsBadFromAccount(String from) {
        assertIae(() -> new Transfer(from, "B", ONE));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "  "})
    void transferRejectsBadToAccount(String to) {
        assertIae(() -> new Transfer("A", to, ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-5"})
    void transferRejectsNonPositiveAmount(String amount) {
        assertIae(() -> new Transfer("A", "B", new BigDecimal(amount)));
    }

    @Test
    void transferRejectsNullAmount() {
        assertIae(() -> new Transfer("A", "B", null));
    }

    @Test
    void transferValid() {
        Transfer t = new Transfer("A", "B", ONE);
        assertEquals("A", t.fromAccount());
        assertEquals("B", t.toAccount());
        assertEquals(ONE, t.amount());
    }
}
