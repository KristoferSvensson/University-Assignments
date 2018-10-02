package beaver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddEmployee extends JFrame implements ActionListener {
	private JButton btnConfirm;
	private Main main;
	
	private JLabel lblName;
	private JTextField tfName;

	private JLabel lblPersonnummer;
	private JTextField tfPersonnummer;

	private JLabel lblPosition;
	private JTextField tfPosition;

	private JLabel lblStart;
	private JTextField tfStart;

	private JLabel lblEndDate;
	private JTextField tfEndDate;
	
	private JLabel lblFullTime;
	private JTextField tfFullTime;

	
	
	
	
	public AddEmployee(Main main) {
		super("Add Employee");
		this.main = main;
		addFrame();
		addButton();
		addTextfields();
		setVisible(true);
	}
	
	
	public void addFrame() {
		setResizable(false);
		setBounds(200, 50, 800, 600);
		setLayout(null);
	}
	
	public void addButton() {
		btnConfirm = new JButton("Confirm");
		
		btnConfirm.addActionListener(this);
		btnConfirm.setBounds(600, 500, 150, 40);
		
		add(btnConfirm);
	}
	
	public void addTextfields() {

		lblName = new JLabel("Name");
		lblName.setBounds(50, 60, 100, 20);
		add(lblName);
		tfName = new JTextField();
		tfName.setBounds(200, 60, 100, 20);
		add(tfName);

		lblPersonnummer = new JLabel("Person number");
		lblPersonnummer.setBounds(50, 120, 100, 20);
		add(lblPersonnummer);
		tfPersonnummer = new JTextField();
		tfPersonnummer.setBounds(200, 120, 100, 20);
		add(tfPersonnummer);

		lblPosition = new JLabel("Position");
		lblPosition.setBounds(50, 180, 100, 20);
		add(lblPosition);
		tfPosition = new JTextField();
		tfPosition.setBounds(200, 180, 100, 20);
		add(tfPosition);

		lblStart = new JLabel("Start(dd/MM/yyyy)");
		lblStart.setBounds(50, 240, 150, 20);
		add(lblStart);
		tfStart = new JTextField();
		tfStart.setBounds(200, 240, 100, 20);
		add(tfStart);

		lblEndDate = new JLabel("End date(dd/MM/yyyy)");
		lblEndDate.setBounds(50, 300, 150, 20);
		add(lblEndDate);
		tfEndDate = new JTextField();
		tfEndDate.setBounds(200, 300, 100, 20);
		add(tfEndDate);

		lblFullTime = new JLabel("% of full time");
		lblFullTime.setBounds(50, 360, 100, 20);
		add(lblFullTime);
		tfFullTime = new JTextField();
		tfFullTime.setBounds(200, 360, 100, 20);
		add(tfFullTime);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnConfirm) {
			String name = tfName.getText();
			String personnummer= tfPersonnummer.getText();
			String position= tfPosition.getText();
			String start  = tfStart.getText();
			String enddates = tfEndDate.getText();
			String fulltime = tfFullTime.getText();
			
			if(!(name.equals("")) && !(personnummer.equals("") && !(position.equals("")) && !(start.equals("")) && !(enddates.equals("")) && !(fulltime.equals("")))) {
				main.addEmployee(name, personnummer, position, start, enddates, fulltime);
			}
			
			dispose();
		}
	}
	
	

}
