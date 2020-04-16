package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) (SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            // System.out.println("dequeuin...");
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            // System.out.println("enqueuing...");
            double randomNumber = Math.random() - 0.5;
            buffer.enqueue(randomNumber);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double headValue1 = buffer.dequeue();
        double headValue2 = buffer.peek();
        double newRear = (headValue1 + headValue2) / 2 * DECAY;
        buffer.enqueue(newRear);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}