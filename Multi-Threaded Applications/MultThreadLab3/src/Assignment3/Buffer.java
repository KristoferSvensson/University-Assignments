package Assignment3;

import sun.awt.Mutex;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by Kristofer Svensson on 2016-11-30.
 */

/**
 * The buffer contains instances of FoodItem and utilizes semaphores and mutex to achieve thread synchronization.
 */
public class Buffer{

    private LinkedList<FoodItem> foodBuffer= new LinkedList<>();
    private Semaphore empty, full;
    private Mutex mutex;

    /**
     * The constructor instantiates the necessary synchronization components, using the bufferSize-argument.
     * @param bufferSize The "max size" of the buffer. Technically, this is the amount of open slots in the Semaphore
     * keeping track of "filled" positions in the buffer.
     */
    public Buffer(int bufferSize){
        empty = new Semaphore(bufferSize, true);
        full = new Semaphore(0, true);
        mutex = new Mutex();
    }

    /**
     * A thread safe method for inserting FoodItems into the buffer.
     * @param item The fooditem to insert.
     * @throws InterruptedException
     */
    public void put(FoodItem item) throws InterruptedException {
        empty.acquire();
        mutex.lock();
        foodBuffer.addLast(item);
        mutex.unlock();
        full.release();
    }

    /**
     * A thread safe method for retrieving FoodItems from the buffer.
     * @return The first FoodItem in the buffer.
     * @throws InterruptedException
     */
    public FoodItem get() throws InterruptedException {
            full.acquire();
            mutex.lock();
            FoodItem itemObject = foodBuffer.removeFirst();
            mutex.unlock();
            empty.release();
            return itemObject;
    }
}
