package java_implementation;


import java.util.Date;
import java.util.Vector;

/**
 * Created by upen on 10/13/15.
 */
public class App {
    private static final int SIZE = 10;
    public static void main(String[] args) {
        BoundedBuffer<Date> dateBoundedBuffer = new BoundedBuffer<>();
        Thread producerThread = new Thread(new Producer(dateBoundedBuffer));
        Thread consumerThread = new Thread(new Consumer(dateBoundedBuffer));
        producerThread.start();
        consumerThread.start();
    }
}
