package tdd;

public class CircularQueueImpl implements CircularQueue {

    private final int[] queue;
    private int index;

    private CircularQueueImpl(int size) {
        queue = new int[]{size};
    }

    public static CircularQueueImpl of(int size) {
        return new CircularQueueImpl(size);
    }

    @Override
    public void add(int value) {
        if (index == queue.length - 1) {
            index = 0;
        }
        queue[index++] = value;
    }

    @Override
    public int get() {
        return queue[0];
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
