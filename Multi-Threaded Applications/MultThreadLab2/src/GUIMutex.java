


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The GUI for assignment 2
 */
public class GUIMutex 
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;			// The Main window
	private JLabel lblTrans;		// The transmitted text
	private JLabel lblRec;			// The received text
	private JRadioButton bSync;		// The sync radiobutton
	private JRadioButton bAsync;	// The async radiobutton
	private JTextField txtTrans;	// The input field for string to transfer
	private JButton btnRun;         // The run button
	private JButton btnClear;		// The clear button
	private JPanel pnlRes;			// The colored result area
	private JLabel lblStatus;		// The status of the transmission
	private JTextArea listW;		// The write logger pane
	private JTextArea listR;		// The read logger pane
	private CharacterBuffer buffer;
	private String writerOutput="";
	private String readerOutput="";
	
	/**
	 * Constructor
	 */
	public GUIMutex(){
//		buffer = new CharacterBuffer();
	}
	
	/**
	 * Starts the application
	 */
	public void Start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 601, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Concurrent Read/Write");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen

	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI()
	{
		// First, create the static components
		// First the 4 static texts
		JLabel lab1 = new JLabel("Writer Thread Logger");
		lab1.setBounds(18, 29, 128, 13);
		frame.add(lab1);
		JLabel lab2 = new JLabel("Reader Thread Logger");
		lab2.setBounds(388, 29, 128, 13);
		frame.add(lab2);
		JLabel lab3 = new JLabel("Transmitted:");
		lab3.setBounds(13, 394, 100, 13);
		frame.add(lab3);
		JLabel lab4 = new JLabel("Received:");
		lab4.setBounds(383, 394, 100, 13);
		frame.add(lab4);
		// Then add the two lists (of string) for logging transfer
		listW = new JTextArea();
		listW.setBounds(13, 45, 197, 342);
		listW.setBorder(BorderFactory.createLineBorder(Color.black));
		listW.setEditable(false);
		frame.add(listW);
		listR = new JTextArea();
		listR.setBounds(386, 45, 183, 342);
		listR.setBorder(BorderFactory.createLineBorder(Color.black));
		listR.setEditable(false);
		frame.add(listR);
		// Next the panel that holds the "running" part
		JPanel pnlTest = new JPanel();
		pnlTest.setBorder(BorderFactory.createTitledBorder("Concurrent Tester"));
		pnlTest.setBounds(220, 45, 155, 342);
		pnlTest.setLayout(null);
		frame.add(pnlTest);
		lblTrans = new JLabel("Transmitted string goes here");	// Replace with sent string
		lblTrans.setBounds(13, 415, 200, 13);
		frame.add(lblTrans);
		lblRec = new JLabel("Received string goes here");		// Replace with received string
		lblRec.setBounds(383, 415, 200, 13);
		frame.add(lblRec);
		
		// These are the controls on the user panel, first the radiobuttons
		bSync = new JRadioButton("Syncronous Mode", false);
		bSync.setBounds(8, 37, 131, 17);
		pnlTest.add(bSync);
		bAsync = new JRadioButton("Asyncronous Mode", true);
		bAsync.setBounds(8, 61, 141, 17);
		pnlTest.add(bAsync);
		ButtonGroup grp = new ButtonGroup();
		grp.add(bSync);
		grp.add(bAsync);
		// then the label and textbox to input string to transfer
		JLabel lab5 = new JLabel("String to Transfer:");
		lab5.setBounds(6, 99, 141, 13);
		pnlTest.add(lab5);
		txtTrans = new JTextField();
		txtTrans.setBounds(6, 124, 123, 20);
		listW.setText("");
		listR.setText("");
		pnlTest.add(txtTrans);
		// The run button
		btnRun = new JButton("Run");
		btnRun.setBounds(26, 150, 75, 23);
		btnRun.addActionListener(new ActionListener() {
			/*
			The listener for the Run-button will start a new printing process. It updates the GUI and initializes the
			necessary objects.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				btnClear.setEnabled(false);
				pnlRes.setBackground(Color.GRAY);
				lblStatus.setText("Running...");
				if (bAsync.isSelected()) {
					int bufferSize = txtTrans.getText().length();
					CharacterBuffer buffer = new CharacterBuffer(bufferSize);
					listW.setText("");
					listR.setText("");
					Writer writer = new Writer(txtTrans.getText(), GUIMutex.this, buffer, false);
					Reader reader = new Reader(GUIMutex.this, buffer, bufferSize, false);
				} else if (bSync.isSelected()) {
					int bufferSize = txtTrans.getText().length();
					CharacterBuffer buffer = new CharacterBuffer(bufferSize);
					listW.setText("");
					listR.setText("");
					Writer writer = new Writer(txtTrans.getText(), GUIMutex.this, buffer, true);
					Reader reader = new Reader(GUIMutex.this, buffer, bufferSize, true);
				}
			}
		});
		pnlTest.add(btnRun);
		JLabel lab6 = new JLabel("Running status:");
		lab6.setBounds(23, 199, 110, 13);
		pnlTest.add(lab6);
		// The colored rectangle holding result status
		pnlRes = new JPanel();
		pnlRes.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlRes.setBounds(26, 225, 75, 47);
		pnlRes.setBackground(Color.GRAY);
		pnlTest.add(pnlRes);
		// also to this text
		lblStatus = new JLabel("Status goes here");
		lblStatus.setBounds(23, 275, 100, 13);
		pnlTest.add(lblStatus);
		// The clear input button, starts disabled
		btnClear = new JButton("Clear");
		btnClear.setBounds(26, 303, 75, 23);
		btnClear.setEnabled(true);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listW.setText("");
				listR.setText("");
				lblTrans.setText("");
				lblRec.setText("");
				writerOutput="";
				readerOutput="";
			}
		});
		pnlTest.add(btnClear);
	}

	/*
	Prints a character while the writer thread is running. It displays the character both in the writer logger and
	below that window.
	 */
	public void printWriterSymbol (String symbol){
		listW.append(symbol);
		writerOutput+=symbol;
		lblTrans.setText(writerOutput);
	}

	/*
	Prints a character while the reader thread is running. It displays the character both in the reader logger and
	below that window.
	 */
	public void printReaderSymbol (String symbol){
		listR.append(symbol);
		if (symbol.length()<4) {
			readerOutput += symbol;
		}
		lblRec.setText(readerOutput);
	}

	public static void main (String[] args){
		GUIMutex prog = new GUIMutex();
		prog.Start();
	}

	/*
	Compares the output-strings and updates the status panel and status text accordingly.
	 */
	public void compareOutput() {
		if (!writerOutput.equals(readerOutput)){
			pnlRes.setBackground(Color.RED);
			lblStatus.setText("FAILED!");
		} else if (writerOutput.equals(readerOutput)){
			pnlRes.setBackground(Color.GREEN);
			lblStatus.setText("SUCCESS!");
		}
		btnClear.setEnabled(true);
	}
}
