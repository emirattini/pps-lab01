package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    public static final int VALUE = 1234;
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
}