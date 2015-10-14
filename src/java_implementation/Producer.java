package java_implementation;

import java.util.Date;

/**
 * Created by upen on 10/13/15.
 */
public class Producer implements Runnable {

    private BoundedBuffer<Date> mBuffer;

    public Producer(BoundedBuffer<Date> mBuffer) {
        this.mBuffer = mBuffer;
    }

    @Override
    public void run() {
        while (true){
            mBuffer.insert(new Date());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
