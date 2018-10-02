package Assignment4;

import java.awt.*;

/**
 * Created by Kristofer Svensson on 2017-01-10.
 */
public class EntranceWaitingQueue {

    private int nbrOfWaiting;
    private Object lockObject, lockObject2;
    private Controller controller;


    public EntranceWaitingQueue (Controller controller){
        this.controller = controller;
        this.lockObject = new Object();
        this.lockObject2 = new Object();
    }
//    public void addToQueue(String queueName) {
//        synchronized (lockObject) {
//            nbrOfWaiting++;
//        /*
//        Anropa controller för att uppdatera siffran i GUI.
//         */
//        if (queueName.equals("adventure"))
//            controller.updateLabel(0, nbrOfWaiting);
//        }
//        if (queueName.equals("common")){
//            controller.updateLabel(1, nbrOfWaiting);
//        }
//    }

//    public int getNbrOfWaiting(){
//        return nbrOfWaiting;
//    }

//    public boolean getWaiting() {
//        synchronized (lockObject2) {
//            if (nbrOfWaiting > 0) {
//                nbrOfWaiting--;
//            /*
//            Anropa controller för att uppdatera siffran i GUI.
//             */
//                controller.updateLabel(1, nbrOfWaiting);
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
}
