package beaver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JoinClubGUI extends JFrame implements ActionListener{
	
	private JLabel lblJoinClub;
	
	private JLabel lblName;
	private JTextField tfName;
	
	private JLabel lblAddress;
	private JTextField tfAddress;
	
	private JLabel lblPersonnummer;
	private JTextField tfPersonnummer;
	
	private JButton btnConfirm;
	private Main main;
	
	
	public JoinClubGUI(Main main) {
		super("Join Club");
		this.main = main;
		addFrame();
		setVisible(true);
	}
	
	public void addFrame() {
		setResizable(false);
		setBounds(200, 50, 800, 600);
		addComponents();
	}

	
	public void addComponents() {
		setLayout(null);
		lblJoinClub = new JLabel("Join Club!");
		lblJoinClub.setBounds(80, 20, 100, 20);
		add(lblJoinClub);
		
		lblName = new JLabel("Name");
		lblName.setBounds(120, 50, 50, 20);
		add(lblName);
		tfName = new JTextField();
		tfName.setBounds(300, 50, 100, 20);
		add(tfName);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(120, 150, 50, 20);
		add(lblAddress);
		tfAddress = new JTextField();
		tfAddress.setBounds(300, 150, 100, 20);
		add(tfAddress);
		
		lblPersonnummer = new JLabel("Person Number (Customer ID)");
		lblPersonnummer.setBounds(120, 100, 200, 20);
		add(lblPersonnummer);
		tfPersonnummer = new JTextField();
		tfPersonnummer.setBounds(300, 100, 100, 20);
		add(tfPersonnummer);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(650, 500, 100, 40);
		btnConfirm.addActionListener(this);
		add(btnConfirm);
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnConfirm) {
			String name = tfName.getText();
			String address = tfAddress.getText();
			String personnummer= tfPersonnummer.getText();
			main.joinClub(name, personnummer, address);
			dispose();
		}
		
	}
}
