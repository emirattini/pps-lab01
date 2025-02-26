package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    private static final int WITHDRAW_FEE = 1;

    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        checkUserValidity(userID);
        this.balance += amount;
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        checkUserValidity(userID);
        checkWithdrawAllowed(amount);
        this.balance -= amount + WITHDRAW_FEE;
    }

    private void checkWithdrawAllowed(final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount not permitted");
        }

        if (this.balance <= amount + WITHDRAW_FEE) {
            throw new IllegalArgumentException("Not enough balance");
        }
    }

    private void checkUserValidity(final int id) {
        if (this.holder.getId() != id) {
            throw new IllegalArgumentException("Wrong user id");
        }
    }
}
