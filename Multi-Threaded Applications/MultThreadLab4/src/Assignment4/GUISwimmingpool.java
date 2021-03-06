
package Assignment4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * The GUI for assignment 2
 */
public class GUISwimmingpool 
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private Controller controller;
	private JFrame frame;			// The Main window
	private JLabel lblWadv;			// Waiting in adventure queue	
	private JLabel lblWcom;			// Waiting in common queue
	private JButton btnOpen;        // The open button
	private JLabel lblOpen;			// Open/Closed status
	
	private JLabel lblAdvLim;		// Max guests in advance pool
	private JLabel lblAdvNr;		// Current number visitors in adventure pool
	private JLabel lblComLim;		// Dito common pool
	private JLabel lblComNr;
	
	private JLabel lblAexit;		// Nr exits from adventure pool
	private JLabel lblCexit;		// Dito common pool
	
	// OPTIONAL
	private JPanel pnlImageAdvIn;	// Holds a red or green circle with an right arrow image for enter to adventure pool
	private JPanel pnlImageComIn;	// Dito for common pool
	private JPanel pnlImageAdvCom;	// Dito with down arrow for enter to common pool from adventure pool
	private JPanel pnlImageComAdv;	// Dito with up arrow for enter to adventure pool from common pool
	
	/**
	 * Constructor
	 */
	public GUISwimmingpool(Controller controller){
		this.controller = controller;
	}
	
	/**
	 * Starts the application
	 */
	public void Start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 574, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Adventure Swimming Pool");
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
		// Create the Reception panel
		JPanel pnlRec = new JPanel();
		pnlRec.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Reception"));
		pnlRec.setBounds(12, 12, 181, 371);
		pnlRec.setLayout(null);
		// Panel contents
		JLabel lblWa = new JLabel("Waiting adventure pool:");
		lblWa.setBounds(7, 84, 140, 13);
		pnlRec.add(lblWa);
		lblWadv = new JLabel("0");
		lblWadv.setBounds(145, 84, 40, 13);
		pnlRec.add(lblWadv);
		JLabel lblWc = new JLabel("Waiting common pool:");
		lblWc.setBounds(7, 302, 140, 13);
		pnlRec.add(lblWc);
		lblWcom = new JLabel("0");
		lblWcom.setBounds(145, 302, 40, 13);
		pnlRec.add(lblWcom);
		btnOpen = new JButton("Open Pool");
		btnOpen.setBounds(29, 181, 120, 23);
		pnlRec.add(btnOpen);
		lblOpen = new JLabel("CLOSED");
		lblOpen.setFont(new Font("Courier", Font.BOLD, 25));
		lblOpen.setBounds(38, 221, 120, 25);
		pnlRec.add(lblOpen);
		// Add Reception to frame
		frame.add(pnlRec);
		
		// The adventure pool panel
		JPanel pnlAdv = new JPanel();
		pnlAdv.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Adventure Pool"));
		pnlAdv.setBounds(259, 12, 246, 150);
		pnlAdv.setLayout(null);
		// Panel content
		JLabel lblAl = new JLabel("Limit:");
		lblAl.setBounds(7, 20, 45, 13);
		pnlAdv.add(lblAl);
		lblAdvLim = new JLabel("0");
		lblAdvLim.setBounds(45, 20, 40, 13);
		pnlAdv.add(lblAdvLim);
		JLabel lblAv = new JLabel("Visitors:");
		lblAv.setBounds(143, 20, 50, 13);
		pnlAdv.add(lblAv);
		lblAdvNr = new JLabel("0");
		lblAdvNr.setBounds(200, 20, 40, 13);
		pnlAdv.add(lblAdvNr);
		JPanel pnlA = new JPanel();
		pnlA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),""));
		pnlA.setBounds(6, 56, 234, 84);
		pnlA.setBackground(new Color(80,255,255));
		pnlA.setLayout(null);
		pnlAdv.add(pnlA);
		// Add adventure panel to frame
		frame.add(pnlAdv);
		
		// The Common pool panel
		JPanel pnlCom = new JPanel();
		pnlCom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Common Pool"));
		pnlCom.setBounds(259, 233, 246, 150);
		pnlCom.setLayout(null);
		// Panel content
		JLabel lblCl = new JLabel("Limit:");
		lblCl.setBounds(7, 20, 45, 13);
		pnlCom.add(lblCl);
		lblComLim = new JLabel("0");
		lblComLim.setBounds(45, 20, 40, 13);
		pnlCom.add(lblComLim);
		JLabel lblCv = new JLabel("Visitors:");
		lblCv.setBounds(143, 20, 50, 13);
		pnlCom.add(lblCv);
		lblComNr = new JLabel("0");
		lblComNr.setBounds(200, 20, 40, 13);
		pnlCom.add(lblComNr);
		JPanel pnlC = new JPanel();
		pnlC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),""));
		pnlC.setBounds(6, 56, 234, 84);
		pnlC.setBackground(new Color(80,255,180));
		pnlC.setLayout(null);
		pnlCom.add(pnlC);
		// Add common pool to frame
		frame.add(pnlCom);
		
		// Exit labels and signals
		JLabel lblEXa = new JLabel("EXIT");
		lblEXa.setForeground(Color.white);
		lblEXa.setBounds(3, 1, 29, 13);
		JPanel pnlEXa = new JPanel();
		pnlEXa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),""));
		pnlEXa.setBounds(512, 96, 33, 15);
		pnlEXa.setBackground(new Color(0,192,0));
		pnlEXa.setLayout(null);
		pnlEXa.add(lblEXa);		
		JLabel lblEXc = new JLabel("EXIT");
		lblEXc.setForeground(Color.white);
		lblEXc.setBounds(3, 1, 29, 13);
		JPanel pnlEXc = new JPanel();
		pnlEXc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),""));
		pnlEXc.setBounds(512, 323, 33, 15);
		pnlEXc.setBackground(new Color(0,192,0));
		pnlEXc.setLayout(null);
		pnlEXc.add(lblEXc);		
		frame.add(pnlEXa);
		frame.add(pnlEXc);
		
		lblAexit = new JLabel("0");
		lblAexit.setBounds(512, 115, 40, 13);
		frame.add(lblAexit);
		lblCexit = new JLabel("0");
		lblCexit.setBounds(512, 342, 40, 13);
		frame.add(lblCexit);
		
		pnlImageAdvIn = new JPanel();
		pnlImageAdvIn.setBounds(210, 98, 32, 32);
		// Remove border !! Add Image
		Border b1 = BorderFactory.createTitledBorder("");
		pnlImageAdvIn.setBorder(b1);
		pnlImageAdvIn.setLayout(null);
		frame.add(pnlImageAdvIn);
		pnlImageComIn = new JPanel();
		pnlImageComIn.setBounds(210, 314, 32, 32);
		// Remove border !! Add Image
		Border b2 = BorderFactory.createTitledBorder("");
		pnlImageComIn.setBorder(b2);
		pnlImageComIn.setLayout(null);
		frame.add(pnlImageComIn);
		pnlImageAdvCom = new JPanel();
		pnlImageAdvCom.setBounds(393, 184, 32, 32);
		// Remove border !! Add Image
		Border b3 = BorderFactory.createTitledBorder("");
		pnlImageAdvCom.setBorder(b3);
		pnlImageAdvCom.setLayout(null);
		frame.add(pnlImageAdvCom);
		pnlImageComAdv = new JPanel();
		pnlImageComAdv.setBounds(438, 184, 32, 32);
		// Remove border !! Add Image
		Border b4 = BorderFactory.createTitledBorder("");
		pnlImageComAdv.setBorder(b4);
		pnlImageComAdv.setLayout(null);
		frame.add(pnlImageComAdv);

		//Listeners
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				btnOpen.setEnabled(false);
				if (!controller.checkReceptionOpen()) {
					lblOpen.setText("OPEN");
					btnOpen.setText("Close pool");
					controller.openPools();
				} else if (controller.checkReceptionOpen()){
					lblOpen.setText("CLOSED");
					btnOpen.setText("Open pool");
					controller.closeReception();
				}
			}
		});
	}

//	public void setLabelReceptionAdventure (String waitingNbr){
//		int incoming = Integer.parseInt(waitingNbr);
//		int updated = Integer.parseInt(controller.checkLabel("lblWadv"));
//		if (incoming<=updated) {
//			lblWadv.setText(waitingNbr);
//		}
//	}

//	public void setLabelReceptionCommon(String waitingNbr) {
//		int incoming = Integer.parseInt(waitingNbr);
//		int updated = Integer.parseInt(controller.checkLabel("lblWcom"));
//		if (incoming<=updated) {
//			lblWcom.setText(waitingNbr);
//		}
//	}

	public void increaseLabel(String lblName) {
		int increment;
		switch (lblName){
			case "lblWadv":
				increment = Integer.parseInt(lblWadv.getText());
				increment++;
				lblWadv.setText(Integer.toString(increment));
				break;
			case "lblWcom":
				increment = Integer.parseInt(lblWcom.getText());
				increment++;
				lblWcom.setText(Integer.toString(increment));
				break;
			case "lblAdvNr":
				increment = Integer.parseInt(lblAdvNr.getText());
				increment++;
				lblAdvNr.setText(Integer.toString(increment));
			default:
				break;
		}
	}

	public void decreaseLabel(String lblName) {
		int decrement;
		switch (lblName){
			case "lblWadv":
				decrement = Integer.parseInt(lblWadv.getText());
				decrement--;
				lblWadv.setText(Integer.toString(decrement));
				break;
			case "lblWcom":
				decrement = Integer.parseInt(lblWcom.getText());
				decrement--;
				lblWcom.setText(Integer.toString(decrement));
				break;
			case "lblAdvNr":
				decrement = Integer.parseInt(lblAdvNr.getText());
				decrement--;
				lblAdvNr.setText(Integer.toString(decrement));
				System.out.println("updated adventure visitor label");
			default:
				break;
		}
	}

	public void updateLimits (String adventureLimit, String commonLimit){
		lblAdvLim.setText(adventureLimit);
		lblComLim.setText(commonLimit);
	}
}
