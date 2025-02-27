package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int PIN = 1234;
    public static final int WRONG_PIN = 1111;
    private SmartDoorLock lock;

    @BeforeEach
    void setUp() {
        lock = new SmartDoorLockImpl();
    }

    @Test
    void testLock() {
        setPinAndLock();
        assertTrue(lock.isLocked());
    }

    @Test
    void testLockWithoutPinSet() {
        assertThrows(IllegalStateException.class, () -> lock.lock());
    }

    @Test
    void testLockWhenAlreadyLocked() {
        setPinAndLock();
        assertThrows(IllegalStateException.class, () -> lock.lock());
    }

    private void setPinAndLock() {
        lock.setPin(PIN);
        lock.lock();
    }

    @Test
    void testSetPinLockAndUnlock() {
        setPinAndLock();
        lock.unlock(PIN);
        assertFalse(lock.isLocked());
    }

    @Test
    void testUnlockInUnlockedState() {
        assertThrows(IllegalStateException.class, () -> lock.unlock(PIN));
    }

    @Test
    void testBlockedState() {
        setPinAndLock();
        IntStream.range(0, lock.getMaxAttempts())
                        .forEach(i -> lock.unlock(WRONG_PIN));
        assertTrue(lock.isBlocked());
    }

    @Test
    void testSomeFailedAttempts() {
        setPinAndLock();
        int failedAttempts = lock.getMaxAttempts() - 1;
        IntStream.range(0, failedAttempts)
                .forEach(i -> lock.unlock(WRONG_PIN));
        assertFalse(lock.isBlocked());
        assertEquals(failedAttempts, lock.getFailedAttempts());
    }

    @Test
    void testBlockedStateAndReset() {
        setPinAndLock();
        IntStream.range(0, lock.getMaxAttempts())
                .forEach(i -> lock.unlock(WRONG_PIN));
        lock.reset();
        assertFalse(lock.isLocked());
        assertFalse(lock.isBlocked());
    }

    @Test
    void testUnlockInBlockedState() {
        setPinAndLock();
        IntStream.range(0, lock.getMaxAttempts())
                .forEach(i -> lock.unlock(WRONG_PIN));
        assertThrows(IllegalStateException.class, () -> lock.unlock(PIN));
    }

    @Test
    void testLockInBlockedState() {
        setPinAndLock();
        IntStream.range(0, lock.getMaxAttempts())
                .forEach(i -> lock.unlock(WRONG_PIN));
        assertTrue(lock.isLocked());
        assertThrows(IllegalStateException.class, () -> lock.lock());
    }

    @Test
    void testSetPinInBlockedState() {
        setPinAndLock();
        IntStream.range(0, lock.getMaxAttempts())
                .forEach(i -> lock.unlock(WRONG_PIN));
        assertTrue(lock.isLocked());
        assertThrows(IllegalStateException.class, () -> lock.setPin(PIN));
    }
}
