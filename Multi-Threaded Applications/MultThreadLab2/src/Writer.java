import java.util.Random;

/**
 * Created by Kristofer Svensson on 2016-11-25.
 */

/*
A class containing a writer-thread which will, when started, write elements to a buffer.
 */
public class Writer {

    private String input;
    private int mode;
    private CharacterBuffer buffer;
    private GUIMutex gui;
    private Boolean synced;

    /*
    The constructor receives necessary object references, the string to be processed, and the "mode" (synchronized or unsynchronized).
    It then starts the Writer thread.
     */
    public Writer (String input, GUIMutex gui, CharacterBuffer buffer, Boolean synced){
        this.input=input;
        this.gui = gui;
        this.buffer=buffer;
        this.synced=synced;
        startWriterThread();
    }

    /*
    Starts the Writer thread.
     */
    public void startWriterThread(){
        WriterThread writer = new WriterThread();
        writer.start();
    }

    /*
    The Writer thread will insert characters from the input string into a buffer until there are no unprocessed characters left.
     */
    private class WriterThread extends Thread{

        Random rand = new Random();

        public void run(){
            for (int i = 0; i<input.length(); i++){
                try {
                    Thread.sleep(rand.nextInt(300)+300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(synced){
                    gui.printWriterSymbol(input.charAt(i)+"\n");
                    try {
                        buffer.putSync(input.charAt(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    gui.printWriterSymbol(input.charAt(i)+"\n");
                    buffer.put(input.charAt(i));
                }
            }
        }
    }
}
