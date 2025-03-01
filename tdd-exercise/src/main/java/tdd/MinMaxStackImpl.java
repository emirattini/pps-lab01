package tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    private final Stack<Integer> stack = new Stack<>();
    private Integer min;
    private Integer max;

    @Override
    public void push(int value) {
        if (min == null || value < min) {
            min = value;
        }
        if (max == null || value > max) {
            max = value;
        }
        stack.push(value);
    }

    @Override
    public int pop() {
        checkStackNonEmptiness();
        return stack.pop();
    }

    private void checkStackNonEmptiness() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    @Override
    public int peek() {
        return stack.peek();
    }

    @Override
    public int getMin() {
        checkStackNonEmptiness();
        return min;
    }

    @Override
    public int getMax() {
        checkStackNonEmptiness();
        return max;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
