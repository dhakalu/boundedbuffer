package semaphore_implementation;

import java.util.Date;

/**
 * @author Upendra Dhakal, Operating Systems
 * This is the calss that implements the simple producer
 * that inserts the data to the given boundedbuffer
 */
public class Producer implements Runnable{

    /**
     * Bounded buffer to which the produced data is added/inserted
     */
    private BoundedBuffer<Date> boundedBuffer;

    /**
     * Constructor that takes the boundedBuffer as the paramente
     * @param boundedBuffer BoundedBuffer to which the data is to be stored
     */
    public Producer(BoundedBuffer<Date> boundedBuffer) {
        this.boundedBuffer = boundedBuffer;
    }

    /**
     * This is the method that runs when the Producer thread is
     * executed.
     */
    @Override
    public void run() {
        while (true){
            boundedBuffer.insert(new Date());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
