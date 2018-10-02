import java.util.LinkedList;

/**
 * Created by Kristofer Svensson on 2016-11-25.
 */

/*
The class represents a buffer meant to hold characters (char). Put- and get-methods are available in synchronized and unsynchronized forms.
 */
public class CharacterBuffer {

    private int size;
    public LinkedList buffer = new LinkedList();

    public CharacterBuffer (int size){
        this.size=size;
    }
    /*
    A standard put-method for the buffers list.
     */
    public void put(char symbol){
        buffer.addLast(symbol);
    }

    /*
    A standard get-method for the buffers list. Will notify the user when the buffer is empty.
     */
    public Object get(){
        if (!buffer.isEmpty()){
            return buffer.removeLast();
        }
        return "Waiting for writer...";
    }

    /*
    A synchronized put-method which will only do work if the buffer is empty. Otherwise it waits for the reader thread.
     */
    public synchronized void putSync(char symbol) throws InterruptedException {
        if(!buffer.isEmpty()) {
            wait();
        }
        buffer.addLast(symbol);
        notifyAll();
    }

    /*
    A synchronized get-method which will only do work if the buffer is NOT empty. Otherwise it waits for the writer thread.
     */
    public synchronized Object getSync() throws InterruptedException {
        if(buffer.isEmpty()){
            wait();
        }
        Object temp = buffer.removeLast();
        notifyAll();
        return temp;
    }
}
