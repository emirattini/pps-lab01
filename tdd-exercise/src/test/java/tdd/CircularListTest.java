package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    public static final int SIZE = 3;
    public static final int FIRST = 1234;
    public static final int SECOND = 2345;
    public static final int THIRD = 3456;

    CircularQueue queue;

    @BeforeEach
    void setUp() {
        queue = CircularQueueImpl.of(SIZE);
    }

    @Test
    void testAddAndGet() {
        queue.add(FIRST);
        assertFalse(queue.isEmpty());
        assertEquals(FIRST, queue.get());
    }

    @Test
    void testRightOrderOfGets() {
        queue.add(FIRST);
        queue.add(SECOND);
        queue.add(THIRD);
        assertEquals(FIRST, queue.get());
        assertEquals(SECOND, queue.get());
        assertEquals(THIRD, queue.get());
    }

    @Test
    void testCircularity() {
        for (int i = 0; i < SIZE; i++) queue.add(i);
        queue.add(FIRST);
        assertEquals(FIRST, queue.get());
    }

    @Test
    void testRightOrderOfGetsWithCircularity() {
        int newLastElement = 4567;
        queue.add(FIRST);
        queue.add(SECOND);
        queue.add(THIRD);
        queue.get();
        queue.add(newLastElement);
        assertEquals(SECOND, queue.get());
        assertEquals(THIRD, queue.get());
        assertEquals(newLastElement, queue.get());
    }
}
