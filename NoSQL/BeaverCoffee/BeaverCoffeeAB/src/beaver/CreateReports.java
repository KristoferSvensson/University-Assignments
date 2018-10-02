package beaver;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CreateReports extends JFrame implements ActionListener {
	private Main main;

	private JComboBox<String> employeeID;
	private JButton btnCreateReport;

	private JButton btnCreateReportEmployeeTime;
	
	private JButton btnCreateReportProduct;
	
	private JButton btnCreateReportEmployee;

	private JLabel lblFromDate;
	private JTextField tfFromDate;

	private JLabel lblToDate;
	private JTextField tfToDate;

	private JCheckBox cbCoffee;
	private JCheckBox cbEspresso;
	private JCheckBox cbLatte;
	private JCheckBox cbCappuccino;
	private JCheckBox cbChocolate;
	private JCheckBox cbVanilla;
	private JCheckBox cbCaramel;
	private JCheckBox cbIrishCoffee;

	public CreateReports(Main main) {
		super("Create Reports");
		this.main = main;
		addFrame();
		addButtons();
		addText();
		addCheckboxes();
	}

	public void addFrame() {
		setResizable(false);
		setBounds(200, 50, 800, 600);
		setLayout(null);
		setVisible(true);
	}

	public void addButtons() {
		btnCreateReport = new JButton("Create Report sales for timed period");
		btnCreateReport.setBounds(25, 30, 350, 40);
		btnCreateReport.addActionListener(this);
		add(btnCreateReport);

		btnCreateReportProduct = new JButton("Create Report for one or more products over time");
		btnCreateReportProduct.setBounds(400, 30, 350, 40);
		btnCreateReportProduct.addActionListener(this);
		add(btnCreateReportProduct);
		
		btnCreateReportEmployeeTime = new JButton("Create Report employee listing timed period");
		btnCreateReportEmployeeTime.setBounds(25, 350, 350, 40);
		btnCreateReportEmployeeTime.addActionListener(this);
		add(btnCreateReportEmployeeTime);
		
		btnCreateReportEmployee = new JButton("Create Report employee sales for timed period");
		btnCreateReportEmployee.setBounds(400, 350, 350, 40);
		btnCreateReportEmployee.addActionListener(this);
		add(btnCreateReportEmployee);
	}

	public void addCheckboxes() {
		cbCoffee = new JCheckBox("Coffee");
		cbEspresso = new JCheckBox("Espresso");
		cbLatte = new JCheckBox("Latte");
		cbCappuccino = new JCheckBox("Cappuccino");
		cbChocolate = new JCheckBox("Chocolate");
		cbVanilla = new JCheckBox("Vanilla");
		cbCaramel = new JCheckBox("Caramel");
		cbIrishCoffee = new JCheckBox("IrishCoffee");

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		panel.add(cbCoffee);
		panel.add(cbEspresso);
		panel.add(cbLatte);
		panel.add(cbCappuccino);
		panel.add(cbChocolate);
		panel.add(cbVanilla);
		panel.add(cbCaramel);
		panel.add(cbIrishCoffee);
		panel.setBounds(400, 80, 350, 100);
		add(panel);
	}

	public void addText() {
		DateTime dt = new DateTime();
		lblToDate = new JLabel("To Date (dd/MM/yyyy HH:mm:ss)");
		lblToDate.setBounds(380, 500, 200, 20);
		add(lblToDate);
		tfToDate = new JTextField();
		tfToDate.setBounds(590, 500, 150, 20);
		add(tfToDate);
		lblFromDate = new JLabel("From Date (dd/MM/yyyy HH:mm:ss)");
		lblFromDate.setBounds(10, 500, 200, 20);
		add(lblFromDate);
		tfFromDate = new JTextField();
		tfFromDate.setBounds(220, 500, 150, 20);
		add(tfFromDate);

		tfFromDate.setText(dt.getDayOfMonth() + "/" + dt.getMonthOfYear() + "/" + dt.getYear() + " " + dt.getHourOfDay()
				+ ":" + dt.getMinuteOfHour() + ":" + dt.getSecondOfMinute());
		tfToDate.setText(dt.getDayOfMonth() + "/" + dt.getMonthOfYear() + "/" + dt.getYear() + " " + dt.getHourOfDay()
				+ ":" + dt.getMinuteOfHour() + ":" + dt.getSecondOfMinute());
		employeeIDDropdown();

	}

	private void employeeIDDropdown() {

		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Object[]> objectList = main.getEmployees();
		for (Object[] o : objectList) {
			String s = "" + o[0];
			list.add(s);
		}
		String[] stringList = list.toArray(new String[0]);

		employeeID = new JComboBox<String>(stringList);
		employeeID.setBounds(500, 400, 250, 20);
		employeeID.addActionListener(this);
		add(employeeID);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnCreateReport) {
			JFrame report = new JFrame();
			report.setResizable(false);
			report.setBounds(200, 50, 800, 600);
			report.setLayout(null);
			addTable(report);
			report.setVisible(true);
		} else if (e.getSource() == btnCreateReportProduct) {
			JFrame report = new JFrame();
			report.setResizable(false);
			report.setBounds(200, 50, 800, 600);
			report.setLayout(null);
			addTableForProduct(report);
			report.setVisible(true);
		}else if (e.getSource() == btnCreateReportEmployee) {
			JFrame report = new JFrame();
			report.setResizable(false);
			report.setBounds(200, 50, 800, 600);
			report.setLayout(null);
			addTableForEmployee(report);
			report.setVisible(true);
		}else if (e.getSource() == btnCreateReportEmployeeTime) {
			JFrame report = new JFrame();
			report.setResizable(false);
			report.setBounds(200, 50, 800, 600);
			report.setLayout(null);
			addTableEmployee(report);
			report.setVisible(true);
		}

	}

	public void addTable(JFrame report) {
		String[] columnNames = { "Order #", "Date", "Brewed Coffee", "Espresso", "Latte", "Cappuccino", "Chocolate",
				"Vanilla", "Caramel", "Irish Coffee", "ClubId", "Price $" };
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dtFrom = formatter.parseDateTime(tfFromDate.getText());
		DateTime dtTo = formatter.parseDateTime(tfToDate.getText());

		ArrayList<Object[]> list;

		
		list = main.getOrdersFromDates(dtFrom, dtTo);

		Object[][] data = new Object[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			data[i] = list.get(i);
		}
		TableModel tableModel = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(tableModel);

		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.setBounds(0, 0, 800, 600);

		report.add(panel);

	}

	
	public void addTableEmployee(JFrame report) {
		String[] columnNames = { "ID(Person Number)", "Name",  "Position", "Start", "End date", "% full time" };
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dtFrom = formatter.parseDateTime(tfFromDate.getText());
		DateTime dtTo = formatter.parseDateTime(tfToDate.getText());

		ArrayList<Object[]> list;

		
		list = main.getEmployeeFromDate(dtFrom, dtTo);

		Object[][] data = new Object[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			data[i] = list.get(i);
		}
		TableModel tableModel = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(tableModel);

		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.setBounds(0, 0, 800, 600);

		report.add(panel);

	}
	
	
	public void addTableForProduct(JFrame report) {
		ArrayList<String> productList = new ArrayList<String>();
		if (cbCoffee.isSelected()) {
			productList.add("brewedCoffee");

		}
		if (cbEspresso.isSelected()) {
			productList.add("espresso");
		}
		if (cbLatte.isSelected()) {
			productList.add("latte");
		}
		if (cbCappuccino.isSelected()) {
			productList.add("cappuccino");
		}
		if (cbChocolate.isSelected()) {
			productList.add("chocolate");
		}
		if (cbVanilla.isSelected()) {
			productList.add("vanilla");
		}
		if (cbCaramel.isSelected()) {
			productList.add("caramel");
		}
		if (cbIrishCoffee.isSelected()) {
			productList.add("irishCoffee");
		}
		String[] columnNames = { "Order #", "Date", "Brewed Coffee", "Espresso", "Latte", "Cappuccino", "Chocolate",
				"Vanilla", "Caramel", "Irish Coffee", "ClubId", "Price $" };
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dtFrom = formatter.parseDateTime(tfFromDate.getText());
		DateTime dtTo = formatter.parseDateTime(tfToDate.getText());

		ArrayList<Object[]> list = main.getOrdersFromDatesAndProduct(dtFrom, dtTo, productList);

		Object[][] data = new Object[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			data[i] = list.get(i);
		}
		TableModel tableModel = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(tableModel);

		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.setBounds(0, 0, 800, 600);

		report.add(panel);
	}
	
	public void addTableForEmployee(JFrame report) {
		
		
		String[] columnNames = { "Order #", "Date", "Brewed Coffee", "Espresso", "Latte", "Cappuccino", "Chocolate",
				"Vanilla", "Caramel", "Irish Coffee", "ClubId", "Price $" };
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dtFrom = formatter.parseDateTime(tfFromDate.getText());
		DateTime dtTo = formatter.parseDateTime(tfToDate.getText());
		ArrayList<Object[]> list = main.getOrdersMadeByEmployee(dtFrom, dtTo, employeeID.getSelectedItem().toString());

		Object[][] data = new Object[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			data[i] = list.get(i);
		}
		TableModel tableModel = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(tableModel);

		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.setBounds(0, 0, 800, 600);

		report.add(panel);
	}

}
