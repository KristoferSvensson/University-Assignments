import java.util.Random;

/**
 * Created by Kristofer Svensson on 2016-11-11.
 */

/**
 * Controller-klassen tar hand om den logiska funktionaliteten i programmet. Tanken är att GUIDualThreads ska innehålla
 * så lite logik som möjligt.
 */
public class Controller {

    GUIDualThreads gui;
    private Boolean isLabelRunning = false;
    private Boolean isLineRunning = false;

    public Controller(GUIDualThreads gui) {
        this.gui = gui;
    }

    /**
     * Metoden används för att snabbt få ett slumpvärde som kan användas för grafiska koordinater.
     *
     * @return En int med slumpartad storlek (mellan 0-200)
     */
    public int randomLabelPosition() {
        Random rand = new Random();
        return rand.nextInt(200);
    }

    /**
     * Startar label-tråden.
     */
    public void startLabelThread() {
        Thread moveLblThread = new MoveLabelThread();
        isLabelRunning = true;
        moveLblThread.start();
        System.out.println("Label Thread started.");
    }

    /**
     * Stoppar label-tråden.
     */
    public void stopLabelThread() {
        isLabelRunning = false;
        System.out.println("Label Thread stopped.");
    }

    /**
     * Startar line-tråden.
     */
    public void startLineThread() {
        Thread rotateThread = new LineThread();
        isLineRunning = true;
        rotateThread.start();
        System.out.println("Line Thread started.");
    }

    /**
     * Stoppar line-tråden.
     */
    public void stopLineThread() {
        isLineRunning = false;
        System.out.println("Line Thread stopped.");
    }

    /**
     * En tråd som, så länge den är igång, ser till att text-labeln i GUI:t hela tiden byter position.
     */
    private class MoveLabelThread extends Thread {
        @Override
        public void run() {
            while (isLabelRunning) {
                gui.updateLabel();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * En tråd som, så länge den är igång, ser till att linjen i GUI:t hela tiden byter position och storlek.
     */
    private class LineThread extends Thread {
        Random rand = new Random();

        @Override
        public void run() {
            while (isLineRunning) {
                gui.updateLinePanel(rand.nextInt(175), rand.nextInt(175), rand.nextInt(175), rand.nextInt(175));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
