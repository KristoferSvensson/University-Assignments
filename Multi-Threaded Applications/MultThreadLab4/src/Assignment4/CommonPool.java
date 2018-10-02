package Assignment4;

/**
 * Created by Kristofer Svensson on 2017-01-10.
 */
public class CommonPool {

    private Controller controller;
    private int limit;

    public CommonPool(Controller controller, int limit){
        this.controller = controller;
        this.limit = limit;
    }
}
