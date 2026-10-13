package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TransactionProcessorTest {

    private final TransactionProcessor processor = new TransactionProcessor();

    @Test
    void processDeposit() {
        String expected = """
                Transaction Type: DEPOSIT
                Account: ACC-1
                Amount: USD 100.50""";
        assertEquals(expected, processor.process(new Deposit("ACC-1", new BigDecimal("100.50"))));
    }

    @Test
    void processWithdrawal() {
        String expected = """
                Transaction Type: WITHDRAWAL
                Account: ACC-2
                Category: Food
                Amount: USD 25""";
        assertEquals(expected, processor.process(new Withdrawal("ACC-2", new BigDecimal("25"), "Food")));
    }

    @Test
    void processTransfer() {
        String expected = """
                Transaction Type: TRANSFER
                From: ACC-1
                To: ACC-3
                Amount: USD 7.99""";
        assertEquals(expected, processor.process(new Transfer("ACC-1", "ACC-3", new BigDecimal("7.99"))));
    }

    @Test
    void processNullThrowsWithMessage() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> processor.process(null));
        assertEquals("Transaction cannot be null", ex.getMessage());
    }
}
