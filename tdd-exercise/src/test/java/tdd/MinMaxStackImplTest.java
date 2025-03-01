package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    public static final int VALUE = 1234;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 10;

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
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++)
            stack.push(i);
        assertEquals(MIN_VALUE, stack.getMin());
    }

    @Test
    void testGetMaxWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.getMax());
    }

    @Test
    void testGetMax() {
        stack.push(VALUE);
        assertEquals(VALUE, stack.getMax());
    }

    @Test
    void testGetMaxAfterPushingSomeValues() {
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++)
            stack.push(i);
        assertEquals(MAX_VALUE, stack.getMax());
    }
}