package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    public static final int VALUE = 1234;
    public static final int SIZE = 3;

    CircularQueue queue;

    @BeforeEach
    void setUp() {
        queue = CircularQueueImpl.of(SIZE);
    }

    @Test
    void testAddAndGet() {
        queue.add(VALUE);
        assertFalse(queue.isEmpty());
        assertEquals(VALUE, queue.get());
    }
}
