package beaver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
	private Main main;
	
	private JButton btnPlaceOrder;
	private JButton btnListOfOrders;
	private JButton btnJoinClub;
	private JButton btnEmployees;
	private JButton btnAddEmployees;
	private JButton btnCreateReport;
	private JButton btnStock;
	
	private JComboBox<String> accessControlDropDown;
	
	public GUI(Main main) {
		super("BeaverCoffee");
		this.main = main;
		addFrame();
		addButtons();
		addAccessControlDropDown();
		setVisible(true);
	}
	
	

	public void addFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(200, 50, 800, 600);
		setLayout(null);
	}
	
	public void addButtons() {
		btnPlaceOrder = new JButton("Place Order");
		btnListOfOrders = new JButton("List of Orders");
		btnJoinClub = new JButton("Join Club");
		btnEmployees = new JButton("Employees");
		btnAddEmployees = new JButton("Add Employee");
		btnCreateReport = new JButton("Create Report");
		btnStock = new JButton("Stock");
		btnPlaceOrder.addActionListener(this);
		btnListOfOrders.addActionListener(this);
		btnJoinClub.addActionListener(this);
		btnEmployees.addActionListener(this);
		btnAddEmployees.addActionListener(this);
		btnCreateReport.addActionListener(this);
		btnStock.addActionListener(this);
		
		btnPlaceOrder.setBounds(90, 30, 150, 40);
		add(btnPlaceOrder);
		
		btnListOfOrders.setBounds(310, 30, 150, 40);
		add(btnListOfOrders);
		
		btnJoinClub.setBounds(530, 30, 150, 40);
		add(btnJoinClub);
		
		btnAddEmployees.setBounds(90, 150, 150, 40);
		add(btnAddEmployees);
		
		btnEmployees.setBounds(310, 150, 150, 40);
		add(btnEmployees);
		
		btnCreateReport.setBounds(530, 150, 150, 40);
		add(btnCreateReport);
		
		btnStock.setBounds(90, 270, 150, 40);
		add(btnStock);
		
		btnEmployees.setEnabled(false);
		btnAddEmployees.setEnabled(false);
		btnCreateReport.setEnabled(false);

	}
	
	private void addAccessControlDropDown() {
		String[] accessControl = {"Employee", "Employeer", "Location Manager"};
		accessControlDropDown = new JComboBox<String>(accessControl);
		accessControlDropDown.setBounds(40, 500, 150, 20);
		accessControlDropDown.addActionListener(this);
		add(accessControlDropDown);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == accessControlDropDown) {
			int index = accessControlDropDown.getSelectedIndex();
			
			if(index == 0) {
				btnEmployees.setEnabled(false);
				btnAddEmployees.setEnabled(false);
				btnCreateReport.setEnabled(false);

			} else if(index == 1) {
				btnEmployees.setEnabled(true);
				btnAddEmployees.setEnabled(true);
				btnCreateReport.setEnabled(false);

			} else if(index == 2) {
				btnEmployees.setEnabled(true);
				btnAddEmployees.setEnabled(true);
				btnCreateReport.setEnabled(true);
			}
		} else if(e.getSource() == btnPlaceOrder) {
			PlaceOrderGUI placeOrder = new PlaceOrderGUI(main);
		} else if(e.getSource() == btnListOfOrders) {
			ListOfOrdersGUI orderList = new ListOfOrdersGUI(main);
		} else if(e.getSource() == btnJoinClub) {
			JoinClubGUI joinClub = new JoinClubGUI(main);
		} else if(e.getSource() == btnEmployees) {
			ListOfEmployees listEmployees= new ListOfEmployees(main);
		} else if(e.getSource() == btnAddEmployees) {
			AddEmployee addEmployee = new AddEmployee(main);
		} else if(e.getSource() == btnStock) {
			Stock stock = new Stock(main);
		} else if(e.getSource() == btnCreateReport) {
			CreateReports createReport = new CreateReports(main);
		}
	}
}
