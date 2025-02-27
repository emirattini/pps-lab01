package tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    final Stack<Integer> stack = new Stack<>();

    @Override
    public void push(int value) {
        stack.push(value);
    }

    @Override
    public int pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return 0;
    }

    @Override
    public int peek() {
        return stack.peek();
    }

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
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
