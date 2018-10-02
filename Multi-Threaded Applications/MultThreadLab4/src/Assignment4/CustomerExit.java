package Assignment4;

/**
 * Created by Kristofer Svensson on 2017-01-10.
 */
public class CustomerExit implements Runnable{

    private Thread exitThread;
    private Controller controller;

    public CustomerExit (Controller controller){
        this.controller = controller;
    }
    @Override
    public void run() {

    }
}
