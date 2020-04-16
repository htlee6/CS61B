package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T returnVal = rb[first];
        // rb[first] = null;
        first = plusOne(first);
        fillCount -= 1;
        return returnVal;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

    /* HELPER METHOD */

    private int plusOne(int index) {
        if (index + 1 == capacity) {
            return 0;
        }
        return index + 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int current;
        private int remain;

        BufferIterator() {
            remain = fillCount();
            current = first;
        }

        @Override
        public boolean hasNext() {
            return remain > 0;
        }

        @Override
        public T next() {
            T returnValue = rb[current];
            remain -= 1;
            current = plusOne(current);
            return returnValue;
        }
    }
}
