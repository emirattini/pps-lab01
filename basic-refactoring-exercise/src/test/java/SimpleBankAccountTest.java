import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final String NAME = "Mario";
    public static final String SURNAME = "Rossi";
    public static final int USER_ID = 1;
    public static final int WRONG_USER_ID = 2;
    public static final int INITIAL_BALANCE = 0;
    public static final int DEPOSIT_AMOUNT = 100;
    public static final int WITHDRAW_AMOUNT = 70;
    public static final int WITHDRAW_FEE = 1;
    public static final int NEGATIVE_AMOUNT = -WITHDRAW_AMOUNT;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder(NAME, SURNAME, USER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(WRONG_USER_ID, DEPOSIT_AMOUNT));
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT - WITHDRAW_FEE, bankAccount.getBalance());
    }

    @Test
    void testWithdrawWithWrongID() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(WRONG_USER_ID, WITHDRAW_AMOUNT));
    }

    @Test
    void testWithdrawWithNotEnoughMoney() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(USER_ID, WITHDRAW_AMOUNT));
    }

    @Test
    void testNegativeAmountWithdraw() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(USER_ID, NEGATIVE_AMOUNT));
    }
}
