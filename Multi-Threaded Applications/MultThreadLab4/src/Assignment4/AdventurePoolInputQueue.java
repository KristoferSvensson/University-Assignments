package Assignment4;

import java.util.LinkedList;

/**
 * Created by Kristofer Svensson on 2017-01-10.
 */
public class AdventurePoolInputQueue implements Runnable{

    private Controller controller;
    private AdventurePool adventure;
    private int adventureLimit;

    public AdventurePoolInputQueue(Controller controller, AdventurePool adventure, int adventureLimit){
        this.controller = controller;
        this.adventure = adventure;
        this.adventureLimit = adventureLimit;
    }


    @Override
    public void run() {
        while (true){
            //Hämta väntade customer från reception
            //Sätt in i adventure pool
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (controller.checkReceptionQueue("adventure")>0&&visitors.size()<limit){
            if (controller.checkReceptionQueue("adventure")>0&&adventure.getVisitorCount()<adventureLimit){
                adventure.push(controller.getCustomer("adventure"));
//                controller.decreaseLabel("lblWadv");
//                controller.increaseLabel("lblAdvNr");
            }
        }
    }
}
