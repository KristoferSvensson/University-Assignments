package Assignment4;


/**
 * Created by Koffe on 2017-01-10.
 */
public class Controller {

    private GUISwimmingpool gui;
    private Reception reception;
    private AdventurePool adventure;
    private AdventurePoolInputQueue adventureQueue;
    private CommonPool common;

    //    private EntranceWaitingQueue adventureWait, commonWait;
//    public Controller (){
//        gui = new GUISwimmingpool(this);
////        waitQueue = new EntranceWaitingQueue(this);
//        adventureWait = new EntranceWaitingQueue(this);
//        commonWait = new EntranceWaitingQueue(this);
////        reception = new Reception(this, adventureWait, commonWait);
//        gui.Start();
//    }
    public Controller(int adventureLimit, int commonLimit) {
        gui = new GUISwimmingpool(this);
        reception = new Reception(this);
        adventure = new AdventurePool(this, adventureLimit);
        common = new CommonPool(this, commonLimit);
        adventureQueue = new AdventurePoolInputQueue(this, adventure, adventureLimit);
        gui.Start();
        gui.updateLimits(Integer.toString(adventureLimit), Integer.toString(commonLimit));
    }

    public void openPools() {
        reception.openReception();
        adventure.openPool();
    }

    public void closeReception(){
        reception.closeReception();
    }

    public void increaseLabel(String lblName) {
        gui.increaseLabel(lblName);
    }

    public void decreaseLabel(String lblName) {
        gui.decreaseLabel(lblName);
    }

    public Customer getCustomer(String poolName){
        return reception.getCustomer(poolName);
    }

    public int checkReceptionQueue(String queueName){
        return reception.getQueueSize(queueName);
    }

    public boolean checkReceptionOpen(){
        return reception.isOpen();
    }

//    public String checkLabel(String labelName){
//        String label = "";
//        switch (labelName){
//            case "lblWadv":
//                label = Integer.toString(adventureWait.getNbrOfWaiting());
//                break;
//            case "lblWcom":
//                label = Integer.toString(commonWait.getNbrOfWaiting());
//                break;
//            default:
//                break;
//        }
//        return label;
//    }

//    public void updateLabel (int label, int number){
//        String numberToGUI = Integer.toString(number);
//        switch (label){
//            case 0:
//                gui.setLabelReceptionAdventure(numberToGUI);
//                break;
//            case 1:
//                gui.setLabelReceptionCommon(numberToGUI);
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            default:
//                break;
//        }
//    }

    public static void main(String[] args) {
        Controller prog = new Controller(10, 20);
    }
}
