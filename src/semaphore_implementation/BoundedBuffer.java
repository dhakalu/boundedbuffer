package semaphore_implementation;

import java.util.concurrent.Semaphore;

/**
 * Suppose we have as circular buffer with two pointers 'in' and 'out' to indicate the next available position
 * and the position that contains the next data to be retrieved. There are two groups of threads,
 * producers and consumers. Each producer deposits a data items ito the 'in' position and advances the pointer
 * pointer 'in', and each consumer retrives the data item in position 'out' and advances the pointer 'out'
 *
 * Write a program that can correctly coordinate the producers and consumers and their depositing and
 * retrieving activities
 *
 * @author Upendra Dhakal
 * 10/13/2015
 */
public class BoundedBuffer<E>{

    /**
     * The size of the buffer
     */
    private static final int BUFFER_SIZE = 10;
    /**
     * Pointer to indicate the next available position
     */
    private int mIn;

    /**
     * Pointer to indicate the position that constins the next data to be retrieved
     */
    private int mOut;

    /**
     * Semaphore that makes sure that two consumers or two producers are mutually exclusive
     */
    private Semaphore mMutex;
    /**
     * Semaphore that indicated the number of empty units in the buffer
     */
    private Semaphore mEmpty;

    /**
     * Semaphore that indicates the number of the full units in the buffer
     */
    private Semaphore mFull;

    /**
     * The buffer that stores the data produced by producer
     */
    private E[] mBuffer;

    /**
     * Constructor that initializes the BoundedBuffer
     */
    public BoundedBuffer(){
        mIn = 0;
        mOut = 0;
        mMutex = new Semaphore(1); // Bineary Semaphore
        mEmpty = new Semaphore(BUFFER_SIZE); // Initially all the units are free/empty
        mFull = new Semaphore(0); //Initially none of the units are full
        mBuffer = (E[]) new Object[BUFFER_SIZE];

    }

    /**
     * This is the method that deposits the data produced by the
     * producer in to the mIn position and advances the mIn
     * @param item The Data to be inserted into the buffer
     */
    public void insert(E item){
        try {
            mEmpty.acquire();      // Check if there are empty units, if none available wait until consumer consumes
            mMutex.acquire();
            mBuffer[mIn] = item;
            mIn = (mIn + 1) % BUFFER_SIZE;
            mMutex.release();
            mFull.release();    // increase the number of filled units

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves the item form the buffer, decreases mOut pointer and
     * returns the retrieved data
     * @return The data being retrieved from the buffer
     */
    public E remove(){
        E out = null;
        try {
            mFull.acquire();    // check if the buffer is empty, if empty wait
            mMutex.acquire();
            out = mBuffer[mOut];
            mOut = (mOut - 1) % BUFFER_SIZE;
            mMutex.release();
            mEmpty.release();  // increase the number of empty units or let producer insert
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return out;
    }


}
