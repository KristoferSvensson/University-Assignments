package Assignment3;

import java.util.Random;

/**
 * Created by Kristofer Svensson on 2016-11-30.
 */

/**
 *The class represents a producer, inserting items into a buffer and storing as many items as the buffers capacity allows.
 */
public class Producer extends Thread{

    private Buffer buffer;
    private FoodItem food;
    private Controller controller;
    public Boolean isRunning;
    private Random rand;

    /**
     * Initializes components and starts the thread.
     * @param buffer An instance of the Buffer-class.
     * @param food An instance of the FoodItem-class.
     * @param controller An instance of the Controller-class.
     */
    public Producer (Buffer buffer, FoodItem food, Controller controller){
        this.buffer = buffer;
        this.food = food;
        this.controller = controller;
        rand = new Random();
        isRunning = true;
        start();
    }

    /**
     * Stops the thread by setting the "isRunning"-boolean to false. The thread will finish its current iteration of
     * the "run"-method and terminate itself afterwards.
     */
    public void stopThread(){
        if (isRunning){
            isRunning = false;
        }
    }


    /**
     * When running, the Producer-thread will continue to insert FoodItems into the buffer, as long it still has the
     * capacity to store them. It also communicates with the Controller-class to insure that the GUI updates accordingly.
     */
    @Override
    public void run() {
        while (isRunning) {
                try {
                    Thread.sleep(rand.nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
//                    FoodItem fi = food.getRandomItem();
                    buffer.put(food.getRandomItem());
                    controller.increaseBufferProgress();
//                    buffer.put(fi);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
