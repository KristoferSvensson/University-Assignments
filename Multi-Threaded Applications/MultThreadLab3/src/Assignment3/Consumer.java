package Assignment3;

import java.util.Random;

/**
 * Created by Kristofer Svensson on 2016-11-30.
 */

/**
 * The class represents a consumer, retrieving items from a buffer and storing as many items as its capacity allows.
 */
public class Consumer extends Thread{

    private Buffer buffer;
    public Boolean isRunning;
    private Random rand;
    private String id;
    private Controller controller;
    private double loadedItemsCount, loadedItemsWeight, loadedItemsVolume, limitCount, limitWeight, limitVolume;
    private boolean continueLoad;

    /**
     * The Consumer constructor will define the capacity limits of the Consumer as well as receive the necessary
     * references needed.
     * @param buffer An instance of the Buffer-class.
     * @param controller An instance of the Controller-class.
     * @param id The id of this specific Consumer.
     */
    public Consumer (Buffer buffer, Controller controller, String id){
        this.buffer = buffer;
        this.controller = controller;
        loadedItemsCount = 0;
        loadedItemsWeight = 0;
        loadedItemsVolume = 0;
        limitCount = 15;
        limitWeight = 30;
        limitVolume = 30;
        rand = new Random();
        this.id = id;
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
     * Allows for manipulation of the "isRunning"-boolean variable.
     * @param condition Whether the thread should use "continue load" or not. If enabled, the consumer will pause for
     * a number of seconds when full and empty its contents. After it has done this, it will resume operations.
     */
    public void setContinueLoad(boolean condition){
        continueLoad = condition;
        isRunning = true;
        start();
    }

    /**
     * When running, the Consumer-thread will continue to fetch FoodItems from the buffer, as long it still has the
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
                    if ((loadedItemsCount<limitCount)&&(loadedItemsWeight<limitWeight)&&(loadedItemsVolume<limitVolume)){
                        FoodItem loadedItem = buffer.get();
                        loadedItemsCount++;
                        loadedItemsWeight+=loadedItem.getWeight();
                        loadedItemsVolume+=loadedItem.getVolume();
                        controller.updateLimits(id, loadedItemsCount, loadedItemsWeight, loadedItemsVolume);
                        controller.decreaseBufferProgress();
                        controller.updateItemList(id, loadedItem.getName());

                    } else if (continueLoad){
                        Thread.sleep(3000);
                        loadedItemsCount=0;
                        loadedItemsWeight=0;
                        loadedItemsVolume=0;
                        controller.clearItemList(id);
                    }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        controller.clearItemList(id);
    }
}
