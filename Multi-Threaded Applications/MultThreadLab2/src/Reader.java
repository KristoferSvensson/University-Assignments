import java.util.Random;

/**
 * Created by Kristofer Svensson on 2016-11-25.
 */

/*
A class containing a reader-thread which will, when started, retrieve elements from a buffer.
 */
public class Reader {

    private CharacterBuffer buffer;
    private GUIMutex gui;
    private int stringSize;
    private Boolean synced;

    /*
    The constructor receives necessary object references, the size of the buffer, and the "mode" (synchronized or unsynchronized).
     It then starts the Reader thread.
     */
    public Reader (GUIMutex gui, CharacterBuffer buffer, int stringSize, Boolean synced) {
        this.gui = gui;
        this.buffer=buffer;
        this.stringSize=stringSize;
        this.synced = synced;
        startReaderThread();
    }

/*
Starts the Reader thread.
 */
    public void startReaderThread(){
        ReaderThread reader = new ReaderThread();
        reader.start();
    }

    /*
    The Reader thread will retrieve characters from a buffer and send them to the GUI-class until there are no unprocessed characters left.
     */
    private class ReaderThread extends Thread{

        Random rand = new Random();

        public void run() {
            for (int i=0; i<stringSize;i++){
                try {
                    Thread.sleep(rand.nextInt(300) + 300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    if (synced) {
                        try {
                            gui.printReaderSymbol(buffer.getSync() + "\n");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        gui.printReaderSymbol(buffer.get()+"\n");
                }
        }
        gui.compareOutput();
        }
    }
}
