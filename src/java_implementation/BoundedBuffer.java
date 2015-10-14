package java_implementation;

/**
 * This the consumer producer problem implemented in java using wait() and notifyAll() methods
 * @author Upendra Dhakal, Operating Systems
 */
public class BoundedBuffer<E> {
    /**
     * The size of the buffer
     */
    private static final int BUFFER_SIZE = 10;

    /**
     * Counter that keeps track of the filled units
     */
    private int mCount;

    /**
     * Pointer to indicate the next available position
     */
    private int mIn;

    /**
     * Pointer to indicate the position that constins the next data to be retrieved
     */
    private int mOut;

    /**
     * The buffer that stores the data produced by producer
     */
    private E[] mBuffer;

    public BoundedBuffer(){
        mCount = 0;
        mIn = 0;
        mOut = 0;
        mBuffer = (E[]) new Object[BUFFER_SIZE];
    }

    /**
     * This is the method that adds the item to the buffer and increases the mIn pointer
     * @param item The item to be inserted into the buffer
     */
    public synchronized void insert(E item){
        if (mCount == BUFFER_SIZE){
            System.out.println("Buffer full, waiting consumer to consume");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        mBuffer[mIn] = item;
        mIn = (mIn + 1) % BUFFER_SIZE;
        mCount++;
        System.out.println("Inserted: " + mCount);
    }

    /**
     * This method retrieves the data form the buffer, and decreases the count
     * @return The element that was retrieved
     */
    public synchronized E remove() {
        if (mCount == 0){
            System.out.println("Buffer empty, waiting producer to produce");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        E out = mBuffer[mOut];
        mOut = (mOut + 1) % BUFFER_SIZE;
        mCount--;
        System.out.println("Removed: " + mCount);
        return out;
    }
}
