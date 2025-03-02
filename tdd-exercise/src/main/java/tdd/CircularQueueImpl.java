package tdd;

public class CircularQueueImpl implements CircularQueue {

    private final int[] queue;
    private int index;

    private CircularQueueImpl(int size) {
        queue = new int[size];
    }

    public static CircularQueueImpl of(int size) {
        return new CircularQueueImpl(size);
    }

    @Override
    public void add(int value) {
        if (index == queue.length) {
            index = 0;
        }
        queue[index++] = value;
    }

    @Override
    public int get() {
        int firstElem = queue[0];
        shiftElementsToTheLeft();
        return firstElem;
    }

    private void shiftElementsToTheLeft() {
        for (int i = 0; i < queue.length - 1; i++) {
            queue[i] = queue[i + 1];
        }
        index--;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
