package Assignment3;


/**
 * Created by Kristofer Svensson on 2016-12-11.
 */

/**
 * The controller communicates with the GUI as well as every running thread, to make sure they update accordingly.
 */
public class Controller {

    private Buffer buffer;
    private GUIFoodSupply gui;
    private FoodItem food;
    private Producer threadScan, threadArla, threadAxfood;
    private String producerScan, producerArla, producerAxfood;
    private Consumer threadIca, threadCoop, threadCG;
    private String consumerIca, consumerCoop, consumerCG;

    /**
     * The controller constructor.
     * @param bufferSize The "max size" of the buffer. Technically, this is the amount of open slots in the Semaphore
     * keeping track of "filled" positions in the buffer.
     */
    public Controller(int bufferSize){
        buffer = new Buffer(bufferSize);
        gui = new GUIFoodSupply(this, bufferSize);
        food = new FoodItem();
        producerScan = "producerScan";
        producerArla = "producerArla";
        producerAxfood = "producerAxfood";
        consumerIca = "consumerIca";
        consumerCoop = "consumerCoop";
        consumerCG = "consumerCG";
        gui.Start();
    }

    /**
     * Starts a producer-thread.
     * @param threadName The name of the producer that should be run.
     */
    public void startProducer(String threadName){
        if (threadName.equals(producerScan)){
            threadScan = new Producer (buffer, food, this);
        } else if (threadName.equals(producerArla)){
            threadArla = new Producer (buffer, food, this);
        } else if (threadName.equals(producerAxfood)){
            threadAxfood = new Producer (buffer, food, this);
        }
    }

    /**
     * Stops a producer-thread.
     * @param threadName The name of the producer that should be stopped.
     */
    public void stopProducer(String threadName){
        if (threadName.equals(producerScan)&&threadScan!=null){
            threadScan.stopThread();
        } else if (threadName.equals(producerArla)&&threadArla!=null){
            threadArla.stopThread();
        } else if (threadName.equals(producerAxfood)&&threadAxfood!=null){
            threadAxfood.stopThread();
        }
    }

    /**
     * Starts a consumer-thread.
     * @param threadName The name of the consumer that should be run.
     */
    public void startConsumer (String threadName){
        if (threadName.equals(consumerIca)){
            threadIca = new Consumer (buffer, this, consumerIca);
        } else if (threadName.equals(consumerCoop)){
            threadCoop = new Consumer (buffer, this, consumerCoop);
        } else if (threadName.equals(consumerCG)){
            threadCG = new Consumer (buffer, this, consumerCG);
        }
    }

    /**
     * Stops a consumer-thread.
     * @param threadName The name of the consumer that should be stopped.
     */
    public void stopConsumer (String threadName){
        if (threadName.equals(consumerIca)&&threadIca!=null){
            threadIca.stopThread();
        } else if (threadName.equals(consumerCoop)&&threadCoop!=null){
            threadCoop.stopThread();
        } else if (threadName.equals(consumerCG)&&threadCG!=null){
            threadCG.stopThread();
        }
    }

    /**
     * Increases the value of the GUI process-bar.
     */
    public void increaseBufferProgress(){
        gui.increaseBufferProgress();
    }

    /**
     * Decreases the value of the GUI process-bar.
     */
    public void decreaseBufferProgress(){
        gui.decreaseBufferProgress();
    }

    /**
     * Communicates with the GUI to update the values of a consumers loaded items.
     * @param consumerName The name of the consumer.
     * @param nbrOfItems The number of items the consumer holds.
     * @param weight The weight of the items the consumer holds.
     * @param volume The volume of the items the consumer holds.
     */
    public void updateLimits (String consumerName, double nbrOfItems, double weight, double volume) {
        String numberStr = Double.toString(nbrOfItems);
        String weightStr = Double.toString(weight);
        String volumeStr = Double.toString(volume);
        gui.updateLimits(consumerName, numberStr, weightStr, volumeStr);
    }

    public void setContinueLoad (String threadName, boolean condition){
        if (threadName.equals(consumerIca)){
            threadIca.setContinueLoad(condition);
        } else if (threadName.equals(consumerCoop)){
            threadCoop.setContinueLoad(condition);
        } else if (threadName.equals(consumerCG)){
            threadCG.setContinueLoad(condition);
        }
    }

    /**
     * Communicates with the GUI to update the textbox containing the list of loaded items for a specific consumer.
     * @param threadName The name of the consumer.
     * @param itemName The name of the item to be added to the list.
     */
    public void updateItemList (String threadName, String itemName){
        gui.updateItemList(threadName, itemName);
    }

    /**
     * Communicates with the GUI to clear the textbox of loaded items for a specific consumer.
     * @param threadName The name of the consumer.
     */
    public void clearItemList (String threadName){
        gui.clearItemList(threadName);
    }
    public static void main (String[] args){
        Controller prog = new Controller(20);
    }
}
