package java_implementation;

import java.util.Date;

/**
 * Created by upen on 10/13/15.
 */
public class Consumer implements Runnable {

    private BoundedBuffer<Date> buffer;
    public Consumer(BoundedBuffer<Date> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.remove();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
