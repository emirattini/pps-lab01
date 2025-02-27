package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    public static final int MAX_ATTEMPTS = 3;
    private Integer pin = null;
    private int failedAttempts = 0;

    private boolean isLocked = false;
    private boolean isBlocked = false;

    @Override
    public void setPin(int pin) {
        checkBlockedState();
        checkPinFormat(pin);
        this.pin = pin;
    }

    private static void checkPinFormat(int pin) {
        if (pin < 1000 || pin > 9999) {
            throw new IllegalArgumentException("Pin must be in 4-digit format");
        }
    }

    @Override
    public void unlock(int pin) {
        checkBlockedState();
        checkPinSet();
        if (!isLocked) {
            throw new IllegalStateException("Already unlocked");
        }

        checkPinFormat(pin);
        if (this.pin == pin) {
            this.isLocked = false;
        } else {
            if (++failedAttempts >= MAX_ATTEMPTS) {
                isBlocked = true;
            }
        }
    }

    @Override
    public void lock() {
        checkBlockedState();
        checkPinSet();
        if (isLocked) {
            throw new IllegalStateException("Already locked");
        }
        isLocked = true;
    }

    private void checkBlockedState() {
        if (isBlocked) {
            throw new IllegalStateException("Lock is in blocked state");
        }
    }

    private void checkPinSet() {
        if (pin == null) {
            throw new IllegalStateException("Pin not set");
        }
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public boolean isBlocked() {
        return isBlocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public void reset() {
        pin = null;
        isLocked = false;
        failedAttempts = 0;
        isBlocked = false;
    }
}
