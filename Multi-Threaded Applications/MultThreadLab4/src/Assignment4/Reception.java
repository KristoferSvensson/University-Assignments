package Assignment4;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Kristofer Svensson on 2017-01-10.
 */
public class Reception implements Runnable {

    private boolean isOpen;
    private Random rand;
    private Thread receptionThread;
    private Controller controller;
    //    private EntranceWaitingQueue adventureWait, commonWait;
    private LinkedList<Customer> adventureCustomers, commonCustomers;

    //    public Reception(Controller controller, EntranceWaitingQueue adventureWait, EntranceWaitingQueue commonWait){
//        adventureCustomers = new LinkedList<>();
//        commonCustomers = new LinkedList<>();
//        rand = new Random();
//        isOpen = true;
//        this.controller = controller;
//        this.adventureWait = adventureWait;
//        this.commonWait = commonWait;
//        receptionThread = new Thread(this);
//        receptionThread.start();
//    }
    public Reception(Controller controller) {
        adventureCustomers = new LinkedList<>();
        commonCustomers = new LinkedList<>();
        rand = new Random();
        isOpen = false;
        this.controller = controller;

    }

    public void openReception() {
        isOpen = true;
        receptionThread = new Thread(this);
        receptionThread.start();
    }

    public void closeReception(){
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getQueueSize(String poolName){
        int size = 0;
        if (poolName.equals("adventure")) {
            return adventureCustomers.size();
        } else if (poolName.equals("common")){
            return commonCustomers.size();
        }
        return 0;
    }

    public Customer getCustomer(String poolName){
        Customer movingCustomer = null;
        if (poolName.equals("adventure")){
            movingCustomer = adventureCustomers.removeFirst();
            controller.decreaseLabel("adventure");
        } else if (poolName.equals("common")){
            movingCustomer = commonCustomers.removeFirst();
            controller.decreaseLabel("common");
        }
        return movingCustomer;
    }

    public void run() {
        while (isOpen) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean vip = rand.nextBoolean();
//            int vip = rand.nextInt(2);
            if (vip) {
                adventureCustomers.addLast(new Customer(true));
                controller.increaseLabel("lblWadv");
//                commonWait.addToQueue("common");
            } else {
                commonCustomers.addLast(new Customer(false));
                controller.increaseLabel("lblWcom");
            }
        }
    }

//    @Override
    /*public void run() {
        while (isOpen){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean vip = rand.nextBoolean();
//            int vip = rand.nextInt(2);
            if (vip){
                adventureWait.addToQueue("adventure");
//                commonWait.addToQueue("common");
            } else {
                commonWait.addToQueue("common");
            }
        }
    }*/
}
