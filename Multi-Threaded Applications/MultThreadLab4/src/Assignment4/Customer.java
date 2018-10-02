package Assignment4;

/**
 * Created by Kristofer Svenson on 2017-01-10.
 */
public class Customer {

    private boolean isVIP;

    public Customer (boolean isVIP){
        this.isVIP = isVIP;
    }

    public boolean getVIPStatus(){
        return isVIP;
    }
}
