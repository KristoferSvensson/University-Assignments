import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class GUIDualThreads {
    /**
     * These are the components you need to handle.
     * You have to add listeners and/or code
     */
    private JFrame frame;        // The Main window
    private JButton btnDisplay;    // Start thread moving display
    private JButton btnDStop;    // Stop moving display thread
    private JButton btnTriangle;// Start moving graphics thread
    private JButton btnTStop;    // Stop moving graphics thread
    private JPanel pnlMove;        // The panel to move display in
    private JPanel pnlLine;    // The panel to move graphics in
    private JLabel lblMovingText = new JLabel("Test");
    private Controller controller;

    /**
     * Constructor
     */
    public GUIDualThreads() {
    }

    /**
     * Starts the application
     */
    public void Start() {
        controller = new Controller(this);
        frame = new JFrame();
        frame.setBounds(0, 0, 494, 332);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Multiple Thread Demonstrator");
        InitializeGUI();                    // Fill in components
        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen
    }

    /**
     * Sets up the GUI with components
     */
    private void InitializeGUI() {
        // The moving display outer panel
        JPanel pnlDisplay = new JPanel();
        Border b2 = BorderFactory.createTitledBorder("Label Thread");
        pnlDisplay.setBorder(b2);
        pnlDisplay.setBounds(12, 12, 222, 269);
        pnlDisplay.setLayout(null);

        // Add buttons and drawing panel to this panel
        btnDisplay = new JButton("Start Label");
        btnDisplay.setBounds(10, 226, 121, 23);
        pnlDisplay.add(btnDisplay);
        btnDisplay.addActionListener(new btnListener());
        btnDStop = new JButton("Stop");
        btnDStop.setBounds(135, 226, 75, 23);
        btnDStop.addActionListener(new btnListener());
        pnlDisplay.add(btnDStop);
        btnDStop.setEnabled(false);

        pnlMove = new JPanel();
        pnlMove.setBounds(10, 19, 200, 200);
        Border b21 = BorderFactory.createLineBorder(Color.black);
        pnlMove.setBorder(b21);
        pnlMove.add(lblMovingText);
        pnlDisplay.add(pnlMove);
        // Then add this to main window
        frame.add(pnlDisplay);

        // The moving graphics outer panel
        JPanel pnlTriangle = new JPanel();
        Border b3 = BorderFactory.createTitledBorder("Line Thread");
        pnlTriangle.setBorder(b3);
        pnlTriangle.setBounds(240, 12, 222, 269);
        pnlTriangle.setLayout(null);

        // Add buttons and drawing panel to this panel
        btnTriangle = new JButton("Start Line");
        btnTriangle.setBounds(10, 226, 121, 23);
        pnlTriangle.add(btnTriangle);
        btnTriangle.addActionListener(new btnListener());

        btnTStop = new JButton("Stop");
        btnTStop.setBounds(135, 226, 75, 23);
        pnlTriangle.add(btnTStop);
        btnTStop.setEnabled(false);
        btnTStop.addActionListener(new btnListener());

        pnlLine = new JPanel();
        pnlLine.setBounds(10, 19, 200, 200);
        Border b31 = BorderFactory.createLineBorder(Color.black);
        pnlLine.setBorder(b31);
        pnlTriangle.add(pnlLine);
        // Add this to main window
        frame.add(pnlTriangle);
    }

    /**
     * Uppdaterar textlabelns position med koordinater som hämtas från controller-klassen.
     */
    public void updateLabel() {
        lblMovingText.setLocation(controller.randomLabelPosition(), controller.randomLabelPosition());
    }

    /**
     * Ersätter Line-panelen med en ny som fått nya random-koordinater från controller-klassen.
     *
     * @param x1 En random-genererad int som används som en koordinat till linjen.
     * @param y1 En random-genererad int som används som en koordinat till linjen.
     * @param x2 En random-genererad int som används som en koordinat till linjen.
     * @param y2 En random-genererad int som används som en koordinat till linjen.
     */
    public void updateLinePanel(int x1, int y1, int x2, int y2) {
        pnlLine.removeAll();
        JPanel drawPanel = new DrawPanel(x1, y1, x2, y2);
        pnlLine.add(drawPanel);
        pnlLine.revalidate();
    }

    /**
     * Lyssnare för knapparna i applikationen. Blir oklickbara under vissa omständigheter.
     */
    private class btnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDisplay) {
                controller.startLabelThread();
                btnDisplay.setEnabled(false);
                btnDStop.setEnabled(true);
            } else if (e.getSource() == btnDStop) {
                controller.stopLabelThread();
                btnDStop.setEnabled(false);
                btnDisplay.setEnabled(true);
            } else if (e.getSource() == btnTriangle) {
                controller.startLineThread();
                btnTriangle.setEnabled(false);
                btnTStop.setEnabled(true);
            } else if (e.getSource() == btnTStop) {
                controller.stopLineThread();
                btnTriangle.setEnabled(true);
                btnTStop.setEnabled(false);
            }
        }
    }

    /**
     * En skräddarsydd JPanel som instansieras med koordinater för en linje som målas upp.
     */
    class DrawPanel extends JPanel {

        private int x1, y1, x2, y2;

        public DrawPanel(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawLine(x1, y1, x2, y2);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(190, 190);
        }
    }
}


