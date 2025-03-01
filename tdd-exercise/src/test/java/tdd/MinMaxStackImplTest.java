package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    public static final int VALUE = 1234;
    public static final int MIN_VALUE = 1;
    MinMaxStack stack;

    @BeforeEach
    void setUp() {
        stack = new MinMaxStackImpl();
    }

    @Test
    void testPushAndCheckEmptiness() {
        stack.push(VALUE);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPushAndGetSize() {
        int pushesNumber = 5;
        for (int i = 0; i < pushesNumber; i++) stack.push(VALUE);
        assertEquals(pushesNumber, stack.size());
    }

    @Test
    void testPopWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    void testPeekWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.peek());
    }

    @Test
    void testPushAndPop() {
        stack.push(VALUE);
        assertEquals(VALUE, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPushAndPeek() {
        stack.push(VALUE);
        assertEquals(VALUE, stack.peek());
        assertFalse(stack.isEmpty());
    }

    @Test
    void testGetMinWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.getMin());
    }

    @Test
    void testGetMin() {
        stack.push(MIN_VALUE);
        assertEquals(MIN_VALUE, stack.getMin());
    }

    @Test
    void testGetMinAfterPushingSomeValues() {
        int minValue = 0;
        int maxValue = 10;
        for (int i = minValue; i < maxValue; i++) stack.push(i);
        assertEquals(minValue, stack.getMin());
    }
}