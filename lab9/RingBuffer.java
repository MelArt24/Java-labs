package lab9;

public class RingBuffer<T> {
    private final Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public RingBuffer(int capacity) {
        buffer = new Object[capacity];
    }

    public synchronized void put(T item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    @SuppressWarnings("unchecked")
    public synchronized T take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        T item = (T) buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();    // розбуджує всі потоки, що чекають
        return item;
    }
}
