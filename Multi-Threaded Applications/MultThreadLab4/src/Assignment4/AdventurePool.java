package Assignment4;

import java.util.LinkedList;

/**
 * Created by Kristofer Svensson on 2017-01-10.
 */
public class AdventurePool implements Runnable{

    private Controller controller;
    private int limit;
    private LinkedList<Customer> visitors;
    private Thread adventureThread;

    public AdventurePool(Controller controller, int limit){
        this.controller = controller;
        this.limit = limit;
        this.visitors = new LinkedList<>();
    }

    public void openPool(){
        adventureThread = new Thread(this);
        adventureThread.start();
    }

    public int getVisitorCount(){
        return visitors.size();
    }

    public void push (Customer customer){
        visitors.addLast(customer);
        controller.decreaseLabel("lblWadv");
        controller.increaseLabel("lblAdvNr");
    }

    public Customer pop (){
        Customer customer;
        customer = visitors.getFirst();
        controller.decreaseLabel("lblAdvNr");
        return customer;
//        return null;
    }

    @Override
    public void run() {
        while (true){
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (controller.checkReceptionQueue("adventure")>0&&visitors.size()<limit){
                visitors.addLast(controller.getCustomer("adventure"));
                controller.decreaseLabel("lblWadv");
                controller.increaseLabel("lblAdvNr");
            }*/
        }
    }
}
