package semaphore_implementation;

import java.util.Date;
import java.util.Vector;

/**
 *This is the simple implementation of the consumer that removes the
 * data form the boundedbuffer
 * @author Upendra Dhakal
 * 10/13/2015
 */
public class Consumer implements Runnable {

    /**
     * The buffer from which the data is to br retrieved
     */
    private BoundedBuffer<Date> boundedBuffer;

    /**
     * The constructor that initializes the consumer object
     * @param boundedBuffer The buffer form which the data is to be retrieved
     */
    public Consumer(BoundedBuffer<Date> boundedBuffer) {
        this.boundedBuffer = boundedBuffer;
    }


    @Override
    public void run() {
        while (true){
            boundedBuffer.remove();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
